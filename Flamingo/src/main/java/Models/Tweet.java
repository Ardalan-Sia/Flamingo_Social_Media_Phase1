package Models;

import a.CLI;
import a.Time;

import java.util.LinkedList;


public class Tweet {

    protected String body;
    protected int userId;
    protected String time;
    protected LinkedList<Like> likes = new LinkedList<>();
    protected LinkedList<Comment> comments = new LinkedList<>();

    public Tweet() {
    }

    public Tweet(String body, User user){
        this.userId = user.getId();
        this.body = body;
        this.time = Time.currentTime();
    }




    public String getBody() {
        return body;
    }

    public int getUserId() {
        return userId;
    }

    public String getTime() {
        return time;
    }

    public LinkedList<Like> getLikes() {
        return likes;
    }

    public LinkedList<Comment> getComments() {

        return comments;
    }

    public void setComments(LinkedList<Comment> comments) {
        this.comments = comments;
    }

    public void setLikes(LinkedList<Like> likes) {
        this.likes = likes;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return   time +
                " @" + CLI.getLogic().idToUsername(userId) +
                " said :"+
                 body  +
                ", likes : " + likes.size() +
                ", comment : " + comments.size();

    }
}
