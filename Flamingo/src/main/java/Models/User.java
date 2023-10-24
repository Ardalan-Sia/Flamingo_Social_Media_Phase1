package Models;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private Date birthDate;
    private String bio;
    private String phoneNumber;
    private int id;
    String lastSeen;
    private boolean isPrivate;
    private boolean isActive = true;
    private boolean lastSeenNoOne;
    private boolean lastSeenFollowing;
    private boolean isLastSeenEveryone = true;
    private LinkedList<Tweet> Tweets = new LinkedList<>();
    private LinkedList<Group> groups = new LinkedList<>();
    private LinkedList<Tweet> savedTweets = new LinkedList<>();
    private LinkedList<String> savedMessage = new LinkedList<>();
    private LinkedList<Tweet> reTweets = new LinkedList<>();
    private LinkedList<Integer> mute = new LinkedList<>();
    private LinkedList<Integer> followings = new LinkedList<>();
    private LinkedList<Integer> followers = new LinkedList<>();
    private LinkedList<Integer> blackList = new LinkedList<>();
    private LinkedList<Tweet> likedTweets = new LinkedList<>();
    private LinkedList<String> systemNotification = new LinkedList<>();
    private LinkedList<Integer> receivedRequests = new LinkedList<>();
    private LinkedHashMap<Integer,String> sentRequests = new LinkedHashMap<Integer,String>();
    private LinkedList<Integer> contacts = new LinkedList<>();
    private LinkedList<ChatRoom> chatRooms = new LinkedList<>();
    private LinkedList<Integer> allowedToChat = new LinkedList<>();


    public User() {}

    public User(String firstName, String lastName, String userName, String password, String email, Date birthDate, String bio, String phoneNumber, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public LinkedList<String> getSavedMessage() {
        return savedMessage;
    }

    public void setSavedMessage(LinkedList<String> savedMessage) {
        this.savedMessage = savedMessage;
    }

    public LinkedList<Group> getGroups() {
        return groups;
    }

    public void setGroups(LinkedList<Group> groups) {
        this.groups = groups;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getBio() {
        return bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }

    public LinkedList<Tweet> getTweets() {
        return Tweets;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public LinkedList<String> getSystemNotification() {
        return systemNotification;
    }

    public LinkedList<Integer> getContacts() {
        return contacts;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public boolean isLastSeenNoOne() {
        return lastSeenNoOne;
    }

    public LinkedList<Tweet> getSavedTweets() {
        return savedTweets;
    }

    public LinkedList<Integer> getMute() {
        return mute;
    }

    public void setMute(LinkedList<Integer> mute) {
        this.mute = mute;
    }

    public void setSavedTweets(LinkedList<Tweet> savedTweets) {
        this.savedTweets = savedTweets;
    }

    public LinkedList<Tweet> getReTweets() {
        return reTweets;
    }

    public LinkedList<Integer> getAllowedToChat() {
        return allowedToChat;
    }

    public void setAllowedToChat(LinkedList<Integer> allowedToChat) {
        this.allowedToChat = allowedToChat;
    }

    public void setReTweets(LinkedList<Tweet> reTweets) {
        this.reTweets = reTweets;
    }

    public void setLastSeenNoOne(boolean lastSeenNoOne) {
        this.lastSeenNoOne = lastSeenNoOne;
    }

    public boolean isLastSeenFollowing() {
        return lastSeenFollowing;
    }

    public LinkedList<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(LinkedList<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public void setLastSeenFollowing(boolean lastSeenFollowing) {
        this.lastSeenFollowing = lastSeenFollowing;
    }

    public boolean isLastSeenEveryone() {
        return isLastSeenEveryone;
    }

    public void setLastSeenEveryone(boolean lastSeenEveryone) {
        isLastSeenEveryone = lastSeenEveryone;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public void setContacts(LinkedList<Integer> contacts) {
        this.contacts = contacts;
    }

    public void setSystemNotification(LinkedList<String> systemNotification) {
        this.systemNotification = systemNotification;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setTweets(LinkedList<Tweet> tweets) {
        this.Tweets = tweets;
    }

    public LinkedList<Integer> getFollowings() {
        return followings;
    }

    public void setFollowings(LinkedList<Integer> followings) {
        this.followings = followings;
    }

    public LinkedList<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(LinkedList<Integer> followers) {
        this.followers = followers;
    }

    public LinkedList<Integer> getBlackList() {
        return blackList;
    }

    public void setBlackList(LinkedList<Integer> blackList) {
        this.blackList = blackList;
    }

    public LinkedList<Tweet> getLikedTweets() {
        return likedTweets;
    }

    public LinkedList<Integer> getReceivedRequests() {
        return receivedRequests;
    }

    public void setReceivedRequests(LinkedList<Integer> receivedRequests) {
        this.receivedRequests = receivedRequests;
    }

    public LinkedHashMap<Integer, String> getSentRequests() {
        return sentRequests;
    }

    public void setSentRequests(LinkedHashMap<Integer, String> sentRequests) {
        this.sentRequests = sentRequests;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setLikedTweets(LinkedList<Tweet> likedTweets) {
        this.likedTweets = likedTweets;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void lastSeenNoOne(){
        this.lastSeenNoOne = true;
        this.lastSeenFollowing = false;
        this.isLastSeenEveryone = false;

    }
    public void lastSeenFollowings(){
        this.lastSeenNoOne = false;
        this.lastSeenFollowing = true;
        this.isLastSeenEveryone = false;
    }
    public void lastSeenEveryOne(){
        this.lastSeenNoOne = false;
        this.lastSeenFollowing = false;
        this.isLastSeenEveryone = true;
    }


    public ChatRoom findChatRoom(int user1id,int user2id){
            for (ChatRoom chatRoom : chatRooms) {
                if (chatRoom.getUser1id() == (user1id) && chatRoom.getUser2id() == user2id)
                    return chatRoom;
            }
            return null;
        }

    }


