package Models;
import Models.Message;
import Pages.Page;
import a.CLI;
import a.ConsoleColor.ConsoleColor;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.LinkedList;
@JsonTypeName("chatroom")

public class ChatRoom {
    private int user1id;
    private int user2id;
    private LinkedList<Message> messages = new LinkedList<>();
    private LinkedList<Message> notSeen = new LinkedList<>();


    public ChatRoom() {
    }

    public ChatRoom(int userid1, int userid2) {

        this.user1id = userid1;
        this.user2id = userid2;
    }

    public int getUser1id() {
        return user1id;
    }

    public void setUser1id(int user1id) {
        this.user1id = user1id;
    }

    public int getUser2id() {
        return user2id;
    }


    public void setUser2id(int user2id) {
        this.user2id = user2id;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
    }

    public LinkedList<Message> getNotSeen() {
        return notSeen;
    }

    public void setNotSeen(LinkedList<Message> notSeen) {
        this.notSeen = notSeen;
    }

    public int notSeenCount(){
        return notSeen.size();
    }

    @Override
    public String toString() {


        return  CLI.getLogic().idToUsername(user2id )+
                 "       " + ConsoleColor.BLUE+ notSeen.size() + ConsoleColor.RESET ;


    }
}
