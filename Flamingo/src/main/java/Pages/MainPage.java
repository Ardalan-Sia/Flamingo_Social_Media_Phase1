package Pages;

import Models.Tweet;
import Models.User;
import a.ConsoleColor.ConsoleColor;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class MainPage extends Page{
    private static final LinkedList<String> commands = new LinkedList<>() ;
    public static void mainPage(){
        setCommands();
        Logout:
        while(true){
            printCommands(commands);
            String command = SC.nextLine().toUpperCase();
            switch (command){
                case "PERSONALPAGE":
                    PersonalPage.personalPage();
                    break;
                case "TIMELINE":
                    timeLine();
                    break;
                case "EXPLORER":
                    Explorer.explorer();
                    break;
                case "MESSENGER":
                    Messenger.messenger();
                    break;
                case "SETTINGS":
                    if (!Settings.settings())
                    break Logout;
                    else break ;
                case "EXIT":
                    exit();
                    break;
                default:
                    System.out.println(ConsoleColor.RED_BRIGHT+"Invalid command"+ConsoleColor.RESET);
                    break ;
            }
        }
    }
    protected  static void setCommands() {
        commands.clear();
        commands.add("PersonalPage");
        commands.add("Timeline");
        commands.add("Explorer");
        commands.add("Messenger");
        commands.add("Settings");
        commands.add("Exit");
    }
    protected static void timeLine(){
        LinkedList<Tweet> timeLineList = new LinkedList<>();
        for (Integer id:
             logic.getCurrentUser().getFollowings()) {
            if (!logic.getCurrentUser().getMute().contains(id)) {
                timeLineList.addAll(logic.searchUser(id).getLikedTweets());
                timeLineList.addAll(logic.searchUser(id).getTweets());
            }
        }
        Collections.sort(timeLineList, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        TimeLine.timeLine(timeLineList);
    }

}
