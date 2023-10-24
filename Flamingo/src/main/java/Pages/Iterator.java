package Pages;

import Models.ChatRoom;
import Models.Comment;
import Models.Group;
import Models.Tweet;
import a.CLI;
import a.ConsoleColor.ConsoleColor;

import java.util.LinkedList;

public class Iterator extends Page {

    public static void iterateOnUsers(LinkedList<Integer> list){
        String command = "";
        First:
        while(true){
            if (!printUsers(list))
                break;
            System.out.println("Back");
            System.out.println("Exit");
            System.out.println("Which one ?");
            command = SC.nextLine();
            try {
                int index = Integer.parseInt(command);
                if (list.get(index-1) != null)
                    TrackedUser.trackedUser(logic.searchUser(logic.idToUsername(list.get(index-1))));
                else System.out.println(ConsoleColor.RED_BRIGHT+"Not found"+ConsoleColor.RESET);
            }catch (NumberFormatException e){
                switch (command.toUpperCase()){
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
    private static boolean printUsers(LinkedList<Integer> usersList){
       if (!usersList.isEmpty())
        for (int i = 0; i < usersList.size(); i++) {
            System.out.println(ConsoleColor.MAKE_ANY_BOLD_BRIGHT_COLOR(i%8)+ (i+1) +"-"+ConsoleColor.RESET+logic.idToUsername(usersList.get(i)));
        }
       else {
           System.out.println("It's empty");
           return false;
       }
       return true;
    }


    public static void iterateOnTweets(LinkedList<Tweet> list){
        String command = "";
        First:
        while(true) {
            if (!printTweets(list))
                break;
            System.out.println("Back");
            System.out.println("Exit");
            System.out.println("Which one ?");
            command = SC.nextLine();
            try {
                int index = Integer.parseInt(command);
                if (list.get(index - 1) != null){
                    if (!logic.getCurrentUser().getTweets().contains(list.get(index-1)))
                    OpenTweet.openTweet(list.get(index - 1));
                    else OpenMyTweet.openMyTweet(list.get(index-1));
                }

                else System.out.println(ConsoleColor.RED_BRIGHT + "Not found" + ConsoleColor.RESET);
            } catch (NumberFormatException e) {
                switch (command.toUpperCase()) {
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
    private static boolean printTweets(LinkedList<Tweet> tweetsList){
        if (!tweetsList.isEmpty())
            for (int i = 0; i < tweetsList.size(); i++) {
                System.out.println(ConsoleColor.MAKE_ANY_BOLD_BRIGHT_COLOR(i%8)+ (i+1) +"-"+ConsoleColor.RESET+tweetsList.get(i).toString());
            }
        else {
            System.out.println("It's empty");
            return false;
        }
        return true;
    }


    public static void iterateOnComments(LinkedList<Comment> list){
        String command = "";
        First:
        while(true) {
            if (!printComments(list))
                break;
            System.out.println("Back");
            System.out.println("Exit");
            System.out.println("Which one ?");
            command = SC.nextLine();
            try {
                int index = Integer.parseInt(command);
                if (list.get(index - 1) != null){
                    OpenComment.openComment(list.get(index - 1));
                }

                else System.out.println(ConsoleColor.RED_BRIGHT + "Not found" + ConsoleColor.RESET);
            } catch (NumberFormatException e) {
                switch (command.toUpperCase()) {
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
    private static boolean printComments(LinkedList<Comment> commentsList){
        if (!commentsList.isEmpty())
            for (int i = 0; i < commentsList.size(); i++) {
                System.out.println(ConsoleColor.MAKE_ANY_BOLD_BRIGHT_COLOR(i%8)+ (i+1) +"-"+ConsoleColor.RESET+commentsList.get(i).toString());
            }
        else {
            System.out.println(" it's empty");
            return false;
        }
        return true;
    }


    public static void iterateOnChatRooms(LinkedList<ChatRoom> list){
        String command = "";
        First:
        while(true) {
            if (!printChatRooms(list))
                break;
            System.out.println("Back");
            System.out.println("Exit");
            System.out.println("Which one ?");
            command = SC.nextLine();
            try {
                int index = Integer.parseInt(command);
                if (list.get(index - 1) != null){
                        OpenChatRoom.openChatRoom(list.get(index-1));
                }

                else System.out.println(ConsoleColor.RED_BRIGHT + "Not found" + ConsoleColor.RESET);
            } catch (NumberFormatException e) {
                switch (command.toUpperCase()) {
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
    private static boolean printChatRooms(LinkedList<ChatRoom> chatRooms){
        if (!chatRooms.isEmpty())
            for (int i = 0; i < chatRooms.size(); i++) {
                String temp = CLI.getLogic().searchUser(chatRooms.get(i).getUser2id()).getContacts().contains(chatRooms.get(i).getUser2id()) ?
                        ConsoleColor.GREEN+ "LastSeen : "+CLI.getLogic().searchUser(chatRooms.get(i).getUser2id()).getLastSeen()+ConsoleColor.RESET
                        :ConsoleColor.RED+"Last seen recently"+ConsoleColor.RESET;
                System.out.println(ConsoleColor.MAKE_ANY_BOLD_BRIGHT_COLOR(i%8)+ (i+1) +"-"+ConsoleColor.RESET+chatRooms.get(i).toString()+"\t\t"
                       +temp );
            }
        else {
            System.out.println("It's empty");
            return false;
        }
        return true;
    }

    public static void iterateOnSavedMessages(LinkedList<String> list){
        String command = "";
        First:
        while(true) {
            if (!printSavedMessages(list))
                break;
            System.out.println("Back");
            System.out.println("Exit");
            System.out.println("Which one ?");
            command = SC.nextLine();
            try {
                int index = Integer.parseInt(command);
                if (list.get(index - 1) != null){
                    OpenSavedMessage.savedMessage(list.get(index-1));
                }

                else System.out.println(ConsoleColor.RED_BRIGHT + "Not found" + ConsoleColor.RESET);
            } catch (NumberFormatException e) {
                switch (command.toUpperCase()) {
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
    private static boolean printSavedMessages(LinkedList<String> list){
        if (!list.isEmpty())
            for (int i = 0; i < list.size(); i++) {
                System.out.println(ConsoleColor.MAKE_ANY_BOLD_BRIGHT_COLOR(i%8)+ (i+1) +"-"+ConsoleColor.RESET+list.get(i));
            }
        else {
            System.out.println(" it's empty");
            return false;
        }
        return true;
    }

    public static void iterateOnGroups(LinkedList<Group> list) {
        String command = "";
        First:
        while(true) {
            if (!printGroups(list)) {
                System.out.println("NewGroup");
                System.out.println("Back");
                System.out.println("Exit");
                command = SC.nextLine();
                switch (command.toUpperCase()) {
                    case "NEWGROUP":
                        System.out.println("Group name :");
                        Group.createGroup(SC.nextLine());
                        continue  ;
                    case "BACK":
                        break First;
                    case "EXIT":
                        exit();
                        break;
                    default:
                        System.out.println(ConsoleColor.RED_BRIGHT + "Wrong command" + ConsoleColor.RESET);
                        break;
                }

            }
//            printGroups(list);
            System.out.println("NewGroup");
            System.out.println("Back");
            System.out.println("Exit");
            System.out.println("Which one ?");
            command = SC.nextLine();
            try {
                int index = Integer.parseInt(command);
                if (list.get(index - 1) != null){
                OpenGroupsPage.openGroupsPage(list.get(index - 1));

                }

                else System.out.println(ConsoleColor.RED_BRIGHT + "Not found" + ConsoleColor.RESET);
            } catch (NumberFormatException e) {
                switch (command.toUpperCase()) {
                    case "NEWGROUP":
                        System.out.println("Group name :");
                        Group.createGroup(SC.nextLine());
                        break;
                    case "BACK":
                        break First;
                    case "EXIT":
                        exit();
                        break;
                    default:
                        System.out.println(ConsoleColor.RED_BRIGHT + "Wrong command" + ConsoleColor.RESET);
                        break;
                }

            }
        }
    }
    private static boolean printGroups(LinkedList<Group> list){
        if (!list.isEmpty())
            for (int i = 0; i < list.size(); i++) {

                System.out.println(ConsoleColor.MAKE_ANY_BOLD_BRIGHT_COLOR(i%8)+ (i+1) +"-"+ConsoleColor.RESET+list.get(i).getName());
            }
        else {
            System.out.println("It's empty");
            return false;
        }
        return true;
    }

}
