package Pages;

import Models.User;
import a.CLI;
import a.ConsoleColor.ConsoleColor;
import a.Time;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class TrackedUser extends Page{
    private static final LinkedList<String> commands = new LinkedList<>();
    public static void trackedUser(User user2){
        setCommands();
        printUser(user2);
        First:
        while (true){

            printCommands(commands);
            String command = SC.nextLine().toUpperCase();
            switch (command){
                case "FOLLOW":
                    if (!follow(user2))
                        Messenger.check();
                        System.out.println(ConsoleColor.RED_BOLD+"You are already following this person!"+ConsoleColor.RESET);
                    break;
                case "UNFOLLOW":
                    if (!unFollow(user2))
                        Messenger.check();
                        System.out.println(ConsoleColor.RED_BOLD+"You are not following this user!"+ConsoleColor.RESET);
                    break;
                case "BLOCK":
                    block(user2);
                    break First;
                case "UNBLOCK":
                    unBlock(user2);
                    break;
                case "REPORT":
                    reportUser(user2);
                    break First;
                case "MUTE":
                    mute(user2);
                    break;
                case "UNMUTE":
                    unMute(user2);
                    break;
                case "SHOWTWEETS":
                    Iterator.iterateOnTweets(user2.getTweets());
                    break;
                case "MESSAGE":
                    if (logic.findChatRoom(logic.getCurrentUser().getId(),user2.getId()) != null)
                    OpenChatRoom.openChatRoom(logic.findChatRoom(logic.getCurrentUser().getId(),user2.getId()));
                    else System.out.println("Can't do that");
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
    private static boolean follow(User user2){
        if (logic.getCurrentUser().getFollowings().contains(user2.getId()))
            return false;
        if (user2.isPrivate()) {
            sendRequest(user2);
            CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " sent follow request to "+user2.getUserName());
            System.out.println(ConsoleColor.GREEN + "Your request has been sent" + ConsoleColor.RESET);
            user2.getSystemNotification().add(Time.currentTime()+" "+ "@"+logic.getCurrentUser().getUserName()+ " has requested to follow you");
        }else{
            user2.getFollowers().add(logic.getCurrentUser().getId());
            logic.getCurrentUser().getFollowings().add(user2.getId());
            user2.getSystemNotification().add(Time.currentTime()+" "+ "@"+logic.getCurrentUser().getUserName()+ " has followed you");
            CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " followed "+user2.getUserName());
            System.out.println(ConsoleColor.GREEN+"Now you are following "+user2.getUserName()+ConsoleColor.RESET);
            System.out.println();
        }
        return true;
    }
    private static boolean unFollow(User user2){
        if (!logic.getCurrentUser().getFollowings().contains(user2.getId()))
            return false;
        logic.getCurrentUser().getFollowings().remove(Integer.valueOf(user2.getId()));
        user2.getFollowers().remove(Integer.valueOf(logic.getCurrentUser().getId()));
        System.out.println(ConsoleColor.GREEN+"You unfollowed "+ user2.getUserName()+ConsoleColor.RESET);
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ "  unfollowed "+user2.getUserName());
        user2.getSystemNotification().add(Time.currentTime()+" "+ "@"+logic.getCurrentUser().getUserName()+ " has unfollowed you");
        return true;
    }
    private static void block(User user2){
        logic.getCurrentUser().getBlackList().add(user2.getId());
        if (logic.getCurrentUser().getFollowers().contains(user2.getId())) {
            logic.getCurrentUser().getFollowers().remove(Integer.valueOf(user2.getId()));
            user2.getFollowings().remove(Integer.valueOf(logic.getCurrentUser().getId()));
        }
        if (logic.getCurrentUser().getFollowings().contains(user2.getId())) {
            logic.getCurrentUser().getFollowings().remove(Integer.valueOf(user2.getId()));
            user2.getFollowers().remove(Integer.valueOf(logic.getCurrentUser().getId()));
        }
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " blocked "+user2.getUserName());
        System.out.println(ConsoleColor.RED_BRIGHT+"You blocked "+ user2.getUserName()+ConsoleColor.RESET);

    }
    private static void unBlock(User user2){
        if (logic.getCurrentUser().getBlackList().contains(user2.getId()))
            logic.getCurrentUser().getBlackList().remove(Integer.valueOf(user2.getId()));
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ "  unblocked "+user2.getUserName());
        System.out.println(ConsoleColor.GREEN_BRIGHT+"You unblocked "+ user2.getUserName()+ConsoleColor.RESET);
    }
    private static void sendRequest(User user2){
        user2.getReceivedRequests().add(logic.getCurrentUser().getId());
        logic.getCurrentUser().getSentRequests().put(user2.getId(),"sent");
    }
    private static void printUser(User user2){
        System.out.println(ConsoleColor.BLUE+"Firstname :\t"+ConsoleColor.RESET+user2.getFirstName());
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE+"Lastname :\t"+ConsoleColor.RESET+user2.getLastName());
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE+"Username :\t"+ConsoleColor.RESET+user2.getUserName());
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        String statue =  user2.isPrivate()  ? ConsoleColor.PURPLE_BOLD+"Private" : ConsoleColor.GREEN_BOLD+"Public";
        statue =  statue+ConsoleColor.RESET;
        System.out.println("This account is "+statue);
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);
        if (user2.getContacts().isEmpty() || !user2.getContacts().contains(logic.getCurrentUser().getId()))
            System.out.println("Last seen recently");
        else if (user2.getContacts().contains(logic.getCurrentUser().getId()))
            System.out.println("Last seen at "+user2.getLastSeen());
        System.out.println(ConsoleColor.YELLOW_BRIGHT+"**********************************************"+ConsoleColor.RESET);

    }
    private static void reportUser(User user2){
        String reason = "Not mentioned";
        block(user2);
        System.out.println("Why are you reporting this account ?");
        reason = SC.nextLine();
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " reported @"+user2.getUserName());
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ConsoleColor.RESET);
        try {
            FileWriter writer = new FileWriter("resources/Reported/users.txt",true);
            writer.write(Time.currentTime()+" - @"+logic.getCurrentUser().getUserName()+" reported"+" @"+user2.getUserName()+" - Reason : "+reason+"\n");
            writer.write("-------------------------------------------------------\n");
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void mute(User user2){
        if(!logic.getCurrentUser().getMute().contains(user2.getId())) {
            logic.getCurrentUser().getMute().add(user2.getId());
            CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " muted @"+user2.getUserName());
            System.out.println(ConsoleColor.GREEN_BRIGHT + "done!" + ConsoleColor.RESET);
        }else System.out.println(ConsoleColor.YELLOW+"You have already muted this person"+ConsoleColor.RESET);
    }
    private static void unMute(User user2){
        if(!logic.getCurrentUser().getMute().contains(user2.getId())) {
            logic.getCurrentUser().getMute().remove(user2.getId());
            CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " unMuted @"+user2.getUserName());
            System.out.println(ConsoleColor.GREEN_BRIGHT + "done!" + ConsoleColor.RESET);
        }else System.out.println(ConsoleColor.YELLOW+"You haven't muted this person"+ConsoleColor.RESET);
    }
    private static void setCommands() {
        commands.clear();
        commands.add("Follow");
        commands.add("UnFollow");
        commands.add("Block");
        commands.add("UnBlock");
        commands.add("Report");
        commands.add("Mute");
        commands.add("UnMute");
        commands.add("ShowTweets");
        commands.add("Back");
        commands.add("Exit");    }


}
