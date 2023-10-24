package Pages;
import Models.User;
import a.CLI;
import a.ConsoleColor.ConsoleColor;
import a.Time;

import java.util.LinkedList;

public class Settings extends Page{
    private static final LinkedList<String> commands = new LinkedList<>();

    public static boolean settings(){
        setCommands();
        First:
        while(true){
            printCommands(commands);
            String command = SC.nextLine().toUpperCase();
            switch (command){
                case "PRIVACY":
                    Privacy.privacy();
                    break;
                case "DELETEACCOUNT":
                    if (!deleteAccount())
                        break ;
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " deleted his account ");
                case "LOGOUT":
                    logic.save();
                    logic.updateUserLasSeen();
                    logic.getCurrentUser().setLastSeen(Time.currentTime());
                    CLI.logger.info("@"+logic.getCurrentUser()+ " logged out " );
                    return false;
                case "BACK":
                    break First;
                case "EXIT":
                    exit();
                    break;
                default:
                    System.out.println(ConsoleColor.RED_BRIGHT+"Invalid command"+ConsoleColor.RESET);
                    break ;
            }
        }
        return true;
    }
    private static void setCommands(){
        commands.clear();
        commands.add("Privacy");
        commands.add("DeleteAccount");
        commands.add("Logout");
        commands.add("Back");
        commands.add("Exit");
    }
    private static boolean deleteAccount(){
        User currentUser = logic.getCurrentUser();
        String password;
        while (true) {
            System.out.println("Enter your password:\t"+ConsoleColor.RED_BRIGHT+"/Cancel"+ConsoleColor.RESET);
            password = SC.nextLine();
            if (password.equals(currentUser.getPassword()))
                break;
            else if (password.equalsIgnoreCase("/cancel"))
                return false;
            else if (!password.equals(currentUser.getPassword()))
                System.out.println(ConsoleColor.RED_BRIGHT+"Wrong password"+ConsoleColor.RESET);
        }

            for (User user : logic.getUsers()) {
            if (user.getBlackList().contains(currentUser.getId()))
                user.getBlackList().remove(Integer.valueOf(currentUser.getId()));
            if (user.getFollowers().contains(currentUser.getId()))
                user.getFollowers().remove(Integer.valueOf(currentUser.getId()));
            if (user.getFollowings().contains(currentUser.getId()))
                user.getFollowings().remove(Integer.valueOf(currentUser.getId()));
        }
        logic.getUsers().remove(currentUser);
        System.out.println();
        System.out.println(ConsoleColor.GREEN_BRIGHT+"Thanks for using Flamingo farewell body :( "+ConsoleColor.RESET);
        System.out.println();
            return true;
    }

}
