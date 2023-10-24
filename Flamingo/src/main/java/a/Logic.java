package a;

import Models.ChatRoom;
import Models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

public class Logic {
    @JsonIgnore
    private User currentUser;
    private final LinkedList<User> users;
    public Logic() {

        users = new LinkedList<>();
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void save() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter("resources\\Data\\data.json"), this);
            CLI.logger.info("data saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean signIn(String userName, String password) {
        System.out.println(users);
        for (User user : users)
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println(currentUser.toString());

                return true;
            }

        return false;
    }

    public boolean signUp(String firstName, String lastName, String userName, String password, String email, Date birthDate, String bio, String phoneNumber) {
        int id = 1;
        for (User user: users)
            if (user.getUserName().equals(userName))
                return false;
            if (!users.isEmpty())
                id = users.getLast().getId()+1;
        currentUser = new User(firstName, lastName, userName, password, email, birthDate, bio, phoneNumber,id);
        users.add(currentUser);
        return true;
    }

    public User searchUser(String username){
        for (User user:users) {
            if (user.getUserName().equals(username))
                return user;
        }
        return null;
    }
    public User searchUser(int id){
        String username = idToUsername(id);
        for (User user:users) {
            if (user.getUserName().equals(username))
                return user;
        }
        return null;
    }
    public ChatRoom findChatRoom(int user1id, int user2id){
        if (!searchUser(user1id).getChatRooms().isEmpty())
        for (ChatRoom chatRoom : searchUser(user1id).getChatRooms()) {
            if (chatRoom.getUser2id() == user2id)
                return chatRoom;
        }
        return null;
    }


    public String idToUsername(int id){
        for (User user:users) {
            if (user.getId()==id)
                return user.getUserName();
        }
        return null;
    }

    public void updateUserLasSeen(){
        if (currentUser.isLastSeenEveryone())
            for (User user: users) {
                currentUser.getContacts().clear();
                currentUser.getContacts().add(user.getId());
            }
        else if (currentUser.isLastSeenNoOne())
            currentUser.getContacts().clear();
        else if (currentUser.isLastSeenFollowing())
            currentUser.getContacts().clear();
            currentUser.getContacts().addAll(currentUser.getFollowings());
    }
}
