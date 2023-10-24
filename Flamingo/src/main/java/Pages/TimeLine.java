package Pages;

import Models.Tweet;
import a.ConsoleColor.ConsoleColor;

import java.util.LinkedList;

public class TimeLine extends Page {
    private final static LinkedList<String> commands = new LinkedList<>();
    private static int tweetsIndex;
    private static Tweet currentTweet;
    public static void timeLine(LinkedList<Tweet> list){
        tweetsIndex = 0;
        currentTweet = null;
        try{
            currentTweet = list.getFirst();
            System.out.println(currentTweet.toString());
        }catch (Exception e){
            System.out.println("It is empty");
            return;
        }
        setCommands();
        String command = "";
        First:
        while (true){
            printCommands(commands);
            command = SC.nextLine().toUpperCase();
            switch (command){
                case "NEXT":
                    next(list);
                    break;
                case "PREVIOUS":
                    previous(list);
                    break ;
                case "OPEN":
                    OpenTweet.openTweet(currentTweet);
                    break;
                case "GETWRITER":
                    TrackedUser.trackedUser(logic.searchUser(currentTweet.getUserId()));
                    break ;
                case "BACK":
                    break First;
                case "EXIT":
                    exit();
                    break ;
                default:
                    System.out.println(ConsoleColor.RED_BRIGHT + "Invalid command" + ConsoleColor.RESET);
                    break;
            }
        }
    }
    private static void setCommands(){
        commands.clear();
        commands.add("Next");
        commands.add("Previous");
        commands.add("Open");
        commands.add("GetWriter");
        commands.add("Back");
        commands.add("Exit");
    }
    private static void next(LinkedList<Tweet> list){
        try{
            tweetsIndex++;
            currentTweet = list.get(tweetsIndex);
            System.out.println(currentTweet.toString());
        }catch (Exception e){
            System.out.println(ConsoleColor.RED_BRIGHT+"There is no next"+ConsoleColor.RESET);
            previous(list);
        }
    }
    private static void previous(LinkedList<Tweet> list){
        try{
            tweetsIndex--;
            currentTweet = list.get(tweetsIndex);
            System.out.println(currentTweet.toString());
        }catch (Exception e){
            System.out.println(ConsoleColor.RED_BRIGHT+"there is no previous"+ConsoleColor.RESET);
            next(list);
        }
    }
}
