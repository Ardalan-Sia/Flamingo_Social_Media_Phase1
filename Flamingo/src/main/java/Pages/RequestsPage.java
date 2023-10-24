package Pages;

import java.util.LinkedList;

import a.CLI;
import a.ConsoleColor.ConsoleColor;
import a.Time;

public class RequestsPage extends Page{
    private static final LinkedList<String> commands = new LinkedList<>();

    public static void requestsPage(){

        First:
        while (true) {
            if (!printReqs())
                break;
            System.out.println("Back");
            System.out.println("Exit");
            String username2 = "";
            System.out.println("which one ?");
            String temp = SC.nextLine();
            try {
                int index  = Integer.parseInt(temp);
                if (logic.getCurrentUser().getReceivedRequests().get(index-1)!=null) {
                    username2 = logic.idToUsername(logic.getCurrentUser().getReceivedRequests().get(index - 1));
                    req(username2);
                }
                else System.out.println(ConsoleColor.RED_BRIGHT + "Not found" + ConsoleColor.RESET);
            }catch (NumberFormatException e){
                switch (temp.toUpperCase()){
                    case "BACK":
                        break First;
                    case "EXIT":
                        exit();
                        break;
                }
                System.out.println(ConsoleColor.RED_BRIGHT + "Wrong command" + ConsoleColor.RESET);
            }
        }
    }

    private static void req(String username2){
        setCommands();
        First:
        while (true){
            printCommands(commands);
            String command = SC.nextLine().toUpperCase();
            switch (command){
                case "ACCEPT":
                    accept(username2);
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " accepted " + username2+"'s request");
                    System.out.println("done!");
                    break First;
                case "DECLINE WITH NOTIFY":
                    declineWithNotify(username2);
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " declined with notify " + username2+"'s request");
                    System.out.println("done!");
                    break First;
                case "DECLINE WITHOUT NOTIFY":
                    declineWithoutNotify(username2);
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " declined without notify " + username2+"'s request");
                    System.out.println("done!");
                    break First;
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

    private static void accept(String username2){
        logic.getCurrentUser().getFollowers().add(logic.searchUser(username2).getId());
        logic.searchUser(username2).getFollowings().add(logic.getCurrentUser().getId());
        logic.searchUser(username2).getSystemNotification().add(Time.currentTime()+" @"+logic.getCurrentUser().getUserName()+" has accepted your request");
        logic.searchUser(username2).getSentRequests().replace(logic.getCurrentUser().getId(),"accepted");
        logic.getCurrentUser().getReceivedRequests().remove(Integer.valueOf(logic.searchUser(username2).getId()));
    }

    private static void declineWithoutNotify(String username2){
        logic.searchUser(username2).getSentRequests().replace(logic.getCurrentUser().getId(),"sent");
        logic.getCurrentUser().getReceivedRequests().remove(Integer.valueOf(logic.searchUser(username2).getId()));
    }

    private static void declineWithNotify(String username2){
        logic.searchUser(username2).getSentRequests().replace(logic.getCurrentUser().getId(),"declined");
        logic.searchUser(username2).getSystemNotification().add(Time.currentTime()+" @"+logic.getCurrentUser().getUserName()+ " has declined your request");
        logic.getCurrentUser().getReceivedRequests().remove(Integer.valueOf(logic.searchUser(username2).getId()));
    }

    private static boolean printReqs(){
        if (!logic.getCurrentUser().getReceivedRequests().isEmpty())
        for (int i = 0; i < logic.getCurrentUser().getReceivedRequests().size(); i++) {
            System.out.println(ConsoleColor.MAKE_ANY_BOLD_BRIGHT_COLOR(i%8)+ (i+1) +"-"+ConsoleColor.RESET+" @"+logic.idToUsername(logic.getCurrentUser().getReceivedRequests().get(i))+" wants to follow you");
        }
        else{
            System.out.println(" it's empty");
        return false;
        }
    return true;
    }

    public static void setCommands() {
        commands.clear();
        commands.add("Accept");
        commands.add("Decline with notify");
        commands.add("Decline without notify");
        commands.add("Back");
        commands.add("Exit");    }
}
