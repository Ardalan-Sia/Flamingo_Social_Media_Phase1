package Pages;

import a.CLI;
import a.ConsoleColor.ConsoleColor;
import java.util.LinkedList;

public class Notifications extends Page {
    private static final LinkedList<String> commands = new LinkedList<>();
    public static void notifications(){
        setCommands();
        First:
        while (true){
            printCommands(commands);
            String command = SC.nextLine().toUpperCase();
            switch (command){
                case "SYSTEMNOTIFICATIONS" :
                    printSystemNotifications();
                    break;
                case "REQUESTS" :
                    RequestsPage.requestsPage();
                    break;
                case "CLEARESYSTEMNOTIFICATIONS" :
                    logic.getCurrentUser().getSystemNotification().clear();
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " cleared all notifications ");
                    System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ConsoleColor.RESET);
                            break;
                case "SENTREQUESTS" :
                    sentRequests();
                    break ;
                case "BACK" :
                    break First;
                case "EXIT" :
                    exit();
                    break;
                default:
                    System.out.println(ConsoleColor.RED_BRIGHT+"Invalid command"+ConsoleColor.RESET);
                    break ;
            }
        }
    }
    private static void printSystemNotifications(){
        for (String s: logic.getCurrentUser().getSystemNotification()) {
            System.out.println(s);
            System.out.println(ConsoleColor.YELLOW_BRIGHT+"----------------------------------------------------------"+ConsoleColor.RESET);
        }
    }
    private static void setCommands(){
        commands.clear();
        commands.add("SystemNotifications");
        commands.add("Requests");
        commands.add("ClearSystemNotifications");
        commands.add("SentRequests");
        commands.add("Back");
        commands.add("Exit");
    }
    private static void sentRequests(){
        if (!logic.getCurrentUser().getSentRequests().isEmpty())
        for (Integer id :logic.getCurrentUser().getSentRequests().keySet()) {
            if (logic.getCurrentUser().getSentRequests().get(id).equalsIgnoreCase("accepted"))
            System.out.println("@"+logic.idToUsername(id)+" "+ ConsoleColor.GREEN_BRIGHT+"Accepted"+ConsoleColor.RESET);
            else if(logic.getCurrentUser().getSentRequests().get(id).equalsIgnoreCase("declined"))
                System.out.println("@"+logic.idToUsername(id)+" "+ ConsoleColor.RED_BRIGHT+"Declined"+ConsoleColor.RESET);
            else if(logic.getCurrentUser().getSentRequests().get(id).equalsIgnoreCase("sent"))
                System.out.println("@"+logic.idToUsername(id)+" "+ ConsoleColor.BLACK_BRIGHT+"Sent"+ConsoleColor.RESET);

        }
        else System.out.println("Its empty");
    }

}
