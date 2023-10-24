package Pages;

import Models.Tweet;
import Models.User;
import a.CLI;
import a.ConsoleColor.ConsoleColor;
import java.util.LinkedList;

public class PersonalPage extends Page{
    private static final LinkedList<String> commands = new LinkedList<>();

    public static void personalPage(){
        setCommands();
        First:
        while (true) {
            printCommands(commands);
            String command = SC.nextLine().toUpperCase();
            switch (command) {
                case "NEWTWEET":
                    newTweet();
                    break;
                case "MYTWEETS":
                    Iterator.iterateOnTweets(logic.getCurrentUser().getTweets());
                    break;
                case "EDIT":
                    Edit.edit();
                    break;
                case "FOLLOWINGS":
                    Iterator.iterateOnUsers(logic.getCurrentUser().getFollowings());
                    break;
                case "FOLLOWERS":
                    Iterator.iterateOnUsers(logic.getCurrentUser().getFollowers());
                    break;
                case "BLACKLIST":
                    Iterator.iterateOnUsers(logic.getCurrentUser().getBlackList());
                    break;
                case "INFO":
                    info(logic.getCurrentUser());
                    break;
                case "NOTIFICATIONS":
                    Notifications.notifications();
                    break;
                case "BACK":
                    break First;
                case "EXIT":
                    exit();
                    break;
                default:
                    System.out.println(ConsoleColor.RED_BRIGHT+"Invalid command"+ConsoleColor.RESET);
            }
        }
    }

    private static void setCommands() {
        commands.clear();
        commands.add("NewTweet");
        commands.add("MyTweets");
        commands.add("Edit");
        commands.add("Followings");
        commands.add("Followers");
        commands.add("BlackList");
        commands.add("Info");
        commands.add("Notifications");
        commands.add("Back");
        commands.add("Exit");
    }

    private static void info(User user){
        System.out.println(ConsoleColor.BLUE+"Firstname :\t"+ConsoleColor.RESET+user.getFirstName());
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE+"Lastname :\t"+ConsoleColor.RESET+user.getLastName());
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE+"Username :\t"+ConsoleColor.RESET+user.getUserName());
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE+"Email :\t"+ConsoleColor.RESET+user.getEmail());
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE+"Birthdate :\t"+ConsoleColor.RESET+user.getBirthDate());
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE+"Phone number :\t"+ConsoleColor.RESET+user.getPhoneNumber());
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE+"Bio :\t"+ConsoleColor.RESET+user.getBio());
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE+"Followers :"+ConsoleColor.RESET);
        if (!user.getFollowers().isEmpty())
        user.getFollowers().forEach(id-> System.out.println(logic.idToUsername(id)));
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE+"Followings :"+ConsoleColor.RESET);
        if (!user.getFollowings().isEmpty())
            user.getFollowings().forEach(id-> System.out.println(logic.idToUsername(id)));
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE+"BlackList :"+ConsoleColor.RESET);
        if (!user.getBlackList().isEmpty())
            user.getBlackList().forEach(id-> System.out.println(logic.idToUsername(id)));
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);

    }

    private static void newTweet(){
        System.out.println("What's on your mind ?\t"+ConsoleColor.RED_BRIGHT+"/Cancel"+ConsoleColor.RESET);
        String body = "";
        body = SC.nextLine();
        if (body.equalsIgnoreCase("/cancel"))
            return;
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " added new tweet");

        Tweet tweet = new Tweet(body, logic.getCurrentUser());
        System.out.println(tweet.toString());
        logic.getCurrentUser().getTweets().add(tweet);
    }


}
