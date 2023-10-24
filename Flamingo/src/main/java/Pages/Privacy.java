package Pages;

import a.CLI;
import a.ConsoleColor.ConsoleColor;

import java.util.LinkedList;

public class Privacy extends Page{
    private static final LinkedList<String> commands = new LinkedList<>();
    public static void privacy(){
        setCommands();
        First:
        while(true){
            printCommands(commands);
            String command = SC.nextLine().toUpperCase();
            switch (command){
                case "PUBLIC":
                    logic.getCurrentUser().setPrivate(false);
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " set his account public " );
                    System.out.println(ConsoleColor.GREEN_BRIGHT+"done!");
                    break;
                case "PRIVATE":
                    logic.getCurrentUser().setPrivate(true);
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " set his account private " );
                    System.out.println(ConsoleColor.GREEN_BRIGHT+"done!");
                    break;
                case "EVERYONE":
                    logic.getCurrentUser().lastSeenEveryOne();
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " set his account LastSeen : every one  " );
                    System.out.println(ConsoleColor.GREEN_BRIGHT+"done!");
                    break ;
                case "NO ONE":
                    logic.getCurrentUser().lastSeenNoOne();
                    System.out.println(ConsoleColor.GREEN_BRIGHT+"done!");
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " set his account LastSeen : no one  " );
                    break;
                case "FOLLOWINGS":
                    logic.getCurrentUser().lastSeenFollowings();
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " set his account LastSeen : just followings  " );
                    System.out.println(ConsoleColor.GREEN_BRIGHT+"done!");
                    break;
                case "ACTIVE":
                    logic.getCurrentUser().setActive(true);
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " set his account active " );
                    System.out.println(ConsoleColor.GREEN_BRIGHT+"done!");
                    break;
                case "INACTIVE":
                    logic.getCurrentUser().setActive(false);
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " set his account inactive " );
                    System.out.println(ConsoleColor.GREEN_BRIGHT+"done!");
                    break;
                case "CHANGEPASSWORD":
                    changePassword();
                    break;
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
    }
    public static void setCommands() {
        commands.clear();
        commands.add("Private/Public");
        commands.add("LastSeen : everyone , no one ,followings");
        commands.add("Active/InActive");
        commands.add("ChangePassword");
        commands.add("Back");
        commands.add("Exit");
          }

    public static void changePassword(){
        System.out.println("New password :\tor"+ ConsoleColor.RED_BOLD_BRIGHT+"/Cancel"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLACK_BRIGHT + "Your password must be at least 5 characters and include uppercases, lowercases and digits" + ConsoleColor.RESET);

        String temp = "";
        while (true) {
            temp = LoginPage.scanRequiredField();
            if (temp.equalsIgnoreCase("/cancel"))
                return;
            if (LoginPage.isValidPassword(temp))
                break;
            System.out.println(ConsoleColor.RED_BRIGHT + "Please choose a stronger password" + ConsoleColor.RESET);
        }
        logic.getCurrentUser().setPassword(temp);
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " changed his password " );
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ConsoleColor.RESET);
    }

}
