package Pages;

import Models.*;
import a.CLI;
import a.ConsoleColor.ConsoleColor;
import a.Time;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


public class OpenTweet extends Page{
    private final static LinkedList<String> commands = new LinkedList<>();
    public static void openTweet(Tweet tweet){
        setCommands();
        First:
        while (true) {
            printCommands(commands);
            String command = SC.nextLine().toUpperCase();
            switch (command) {
                case "LIKE":
                    addLike(tweet);
                    break;
                case "UNLIKE":
                    unLike(tweet);
                    break;
                case "COMMENTS":
                    Iterator.iterateOnComments(tweet.getComments());
                    break;
                case "ADDCOMMENT":
                    addComment(tweet);
                    break;
                case "FORWARD":
                    forward(tweet);
                    break;
                case "RETWEET":
                    reTweet(tweet);
                    break;
                case "SAVE":
                    saveTweet(tweet);
                    break;
                case "REPORT":
                    reportTweet(tweet);
                    break;
                case "BACK":
                    break First;

                case "EXIT":
                    exit();
                    break;
                default:
                    System.out.println(ConsoleColor.RED_BRIGHT + "Invalid command" + ConsoleColor.RESET);
                    break;
            }
        }
    }

    private static void setCommands(){
        commands.clear();
        commands.add("Like");
        commands.add("UnLike");
        commands.add("Comments");
        commands.add("AddComment");
        commands.add("Forward");
        commands.add("ReTweet");
        commands.add("Save");
        commands.add("Report");
        commands.add("Back");
        commands.add("Exit");

    }
    protected static void addLike(Tweet tweet){
        boolean check = true;
        for (Like like : tweet.getLikes()) {
            if (like.getUserId() == logic.getCurrentUser().getId())
                check = false;
        }
        if (check) {
            tweet.getLikes().add(new Like(logic.getCurrentUser()));
            logic.getCurrentUser().getLikedTweets().add(tweet);
            CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " liked "+tweet.toString());
            System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ ConsoleColor.RESET);
        }
        else System.out.println(ConsoleColor.YELLOW+"you have already liked this"+ ConsoleColor.RESET);
    }
    protected static void unLike(Tweet tweet){
        boolean check = true;
        Like like1 = null;
        for (Like like : tweet.getLikes()) {
            if (like.getUserId() == logic.getCurrentUser().getId())
                like1 = like;
                check = false;
        }
        if (!check) {
            tweet.getLikes().remove(like1);
            logic.getCurrentUser().getLikedTweets().remove(tweet);
            CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " unliked "+tweet.toString());
            System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ ConsoleColor.RESET);
        }
        else System.out.println(ConsoleColor.YELLOW+"You haven't liked this"+ConsoleColor.RESET);
    }
    protected static void addComment(Tweet tweet){
        System.out.println("Your comment :\t"+ConsoleColor.RED_BRIGHT+"/Cancel"+ConsoleColor.RESET);
        String body = "";
        body = SC.nextLine();
        if (body.equalsIgnoreCase("/cancel"))
            return;
        Comment comment = new Comment(body , logic.getCurrentUser());
        tweet.getComments().add(comment);
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " commented on "+tweet.toString());
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ ConsoleColor.RESET);

    }
    protected static void reTweet(Tweet tweet){
        logic.getCurrentUser().getReTweets().add(tweet);
        logic.getCurrentUser().getTweets().add(tweet);
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " retweet "+tweet.toString());
    }
    protected static void saveTweet(Tweet tweet){
        if (!logic.getCurrentUser().getSavedTweets().contains(tweet)) {
            logic.getCurrentUser().getSavedTweets().add(tweet);
            logic.getCurrentUser().getSavedMessage().add(tweet.toString());
        }
    }
    protected static void forward(Tweet tweet){
        System.out.println("username : ");
        String username = SC.nextLine();
        if (!logic.getCurrentUser().getAllowedToChat().contains(logic.searchUser(username).getId())) {
            System.out.println("Not allowed/doesn't exist");
        return;
        }
        OpenChatRoom.sendMessage(logic.findChatRoom(logic.getCurrentUser().getId(),logic.searchUser(username).getId()),tweet.toString());
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " retweet  tweet:"+tweet.toString());
        System.out.println("done!");
    }
    private static void reportTweet(Tweet tweet){
        String reason = "Not mentioned";

        System.out.println("Why are you reporting this tweet ?");
        reason = SC.nextLine();
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " reported  tweet:"+tweet.toString());
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ConsoleColor.RESET);
        try {
            FileWriter writer = new FileWriter("resources/Reported/tweets.txt",true);
            writer.write(Time.currentTime()+" - @"+logic.getCurrentUser().getUserName()+" reported "+" this tweet ("+tweet.toString()+ ") - Reason : "+reason+"\n");
            writer.write("-------------------------------------------------------\n");
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
