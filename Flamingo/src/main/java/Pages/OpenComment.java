package Pages;

import Models.Comment;
import Models.Like;
import Models.Tweet;
import a.ConsoleColor.ConsoleColor;

import java.util.LinkedList;

public class OpenComment extends Page{
    private final static LinkedList<String> commands = new LinkedList<>();

    public static void openComment(Comment comment){
        setCommands();
        First:
        while (true) {
            String command = SC.nextLine().toUpperCase();
            printCommands(commands);
            switch (command) {
                case "LIKE":
                    addLike(comment);
                    break;
                case "UNLIKE":
                    unLike(comment);
                    break;
                case "REPLIES":
                    Iterator.iterateOnComments(comment.getComments());
                    break;
                case "REPLY":
                    reply(comment);
                    break ;
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
        commands.add("Replies");
        commands.add("Reply");
        commands.add("Back");
        commands.add("Exit");

    }
    private static void addLike(Tweet tweet){
        Like like = new Like(logic.getCurrentUser());
        if (!tweet.getLikes().contains(like)) {
            tweet.getLikes().add(like);
            System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ ConsoleColor.RESET);
        }
        else System.out.println(ConsoleColor.YELLOW+"you have already liked this"+ ConsoleColor.RESET);
    }
    private static void unLike(Tweet tweet){
        Like like = new Like(logic.getCurrentUser());
        if (tweet.getLikes().contains(like)) {
            tweet.getLikes().remove(like);
            System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ ConsoleColor.RESET);
        }
        else System.out.println(ConsoleColor.YELLOW+"You haven't liked this"+ConsoleColor.RESET);
    }
    private static void reply(Comment comment){
        System.out.println("Your comment :\t"+ConsoleColor.RED_BRIGHT+"/Cancel"+ConsoleColor.RESET);
        String body = "";
        body = SC.nextLine();
        if (body.equalsIgnoreCase("/cancel"))
            return;
        Comment comment1 = new Comment(body , logic.getCurrentUser());
        comment.getComments().add(comment1);
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ ConsoleColor.RESET);

    }

}