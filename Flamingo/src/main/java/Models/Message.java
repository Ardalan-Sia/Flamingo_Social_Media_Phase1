package Models;

import a.CLI;
import a.ConsoleColor.ConsoleColor;
import a.Time;

public class Message {
    private String body;
    private int userId;
    private String time;

    public Message() {
    }

    public Message(String body, User user){
        this.userId = user.getId();
        this.body = body;
        this.time = Time.currentTime();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return ConsoleColor.YELLOW+CLI.getLogic().idToUsername(userId)+ConsoleColor.RESET +" : " + body
                +ConsoleColor.BLACK_BRIGHT+"    ("+time+")"+ConsoleColor.RESET;
    }
}
