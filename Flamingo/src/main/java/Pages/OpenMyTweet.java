package Pages;

import Models.Tweet;
import a.ConsoleColor.ConsoleColor;

import java.util.LinkedList;

public class OpenMyTweet extends OpenTweet{
    private final static LinkedList<String> commands = new LinkedList<>();
    public static void openMyTweet(Tweet tweet){
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
                case "SAVE":
                        saveTweet(tweet);
                    break;
                case "DELETE":
                    deleteTweet(tweet);
                    break First;
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
        commands.add("Save");
        commands.add("Delete");
        commands.add("Back");
        commands.add("Exit");

    }
    private static void deleteTweet(Tweet tweet){
        logic.getCurrentUser().getTweets().remove(tweet);
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ConsoleColor.RESET);
    }

}
