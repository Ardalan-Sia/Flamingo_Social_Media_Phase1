package Pages;
import Models.Tweet;
import Models.User;
import a.CLI;
import a.ConsoleColor.ConsoleColor;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Explorer extends Page{
    private static final LinkedList<String> commands = new LinkedList<>();
    public static void explorer(){
        setCommands();
       First:
        while (true){
            printCommands(commands);
            String command = SC.nextLine().toUpperCase();
            switch (command){
                case "SEARCH":
                    search();
                    break ;
                case "EXPLORE":
                    explore();
                    break ;
                case "BACK":
                    break First;
                case "EXIT":
                    exit();
                    break ;
                default:
                    System.out.println(ConsoleColor.RED_BRIGHT+"Invalid command"+ConsoleColor.RESET);
                    break ;
            }
        }
    }


    public static void setCommands() {
        commands.clear();
        commands.add("Search");
        commands.add("Explore");
        commands.add("Back");
        commands.add("Exit");

    }
    private static void search(){
        while (true) {

            System.out.println("Write username :\t"+"/Back");
            String username = SC.nextLine();
            CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " searched " + username);
            User temp = logic.searchUser(username);
            if (username.equalsIgnoreCase("/back"))
                return ;
            if (temp != null)
            if (temp.getBlackList().contains(logic.getCurrentUser().getId()))
                temp = null;
            if (temp != null && !logic.getCurrentUser().equals(temp))
                TrackedUser.trackedUser(temp);
            else
                System.out.println(ConsoleColor.RED_BRIGHT+"User not found"+ConsoleColor.RESET);
        }
    }
    private static void explore(){
        LinkedList<Tweet> exploreList = new LinkedList<>();
        if (logic.getUsers()!=null)
        for (User user :
                logic.getUsers()) {
            if (!user.isPrivate() &&
                    !logic.getCurrentUser().getBlackList().contains(user.getId())&&
                    !user.getBlackList().contains(logic.getCurrentUser().getId()) &&
                    !logic.getCurrentUser().getMute().contains(user.getId())
            ) {
                exploreList.addAll(user.getTweets());
            }

        }
        Collections.shuffle(exploreList,new Random());
        TimeLine.timeLine(exploreList);
    }


}
