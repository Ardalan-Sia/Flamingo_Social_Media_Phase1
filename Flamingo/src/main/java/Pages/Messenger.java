package Pages;

import Models.ChatRoom;
import a.ConsoleColor.ConsoleColor;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Messenger extends Page {
    private final static LinkedList<String> commands = new LinkedList<>();

    public static void messenger() {
        setCommands();
        String command;
        setAllowedToChat();
        First:
        while (true) {
            printCommands(commands);
            command = SC.nextLine().toUpperCase();
            switch (command) {
                case "SAVEDMESSAGES":
                    Iterator.iterateOnSavedMessages(logic.getCurrentUser().getSavedMessage());
                    break;
                case "CHATS":
                    chats();
                    break;
                case "GROUPS":
                    Iterator.iterateOnGroups(logic.getCurrentUser().getGroups());
                    break;
                case "BACK":
                    break First;
                case "EXIT":
                    exit();
                    break;
                default:
                    System.out.println(ConsoleColor.RED_BRIGHT + "Invalid command" + ConsoleColor.RESET);
                    break;
            }

        }
    }

    private static void setCommands() {
        commands.clear();
        commands.add("SavedMessages");
        commands.add("Chats");
        commands.add("Groups");
        commands.add("Back");
        commands.add("Exit");


    }

    public static void setAllowedToChat() {
        logic.getCurrentUser().getAllowedToChat().clear();
        logic.getCurrentUser().getAllowedToChat().addAll(logic.getCurrentUser().getFollowings());
        logic.getCurrentUser().getAllowedToChat().addAll(logic.getCurrentUser().getFollowers());
        LinkedHashSet<Integer> temp = new LinkedHashSet<>();
        temp.addAll(logic.getCurrentUser().getAllowedToChat());
        logic.getCurrentUser().getAllowedToChat().clear();
        logic.getCurrentUser().getAllowedToChat().addAll(temp);

    }
    public static void check(){
        setAllowedToChat();
        if (!logic.getCurrentUser().getAllowedToChat().isEmpty())
            for (Integer id : logic.getCurrentUser().getAllowedToChat()) {
                boolean flag = false;
                for (ChatRoom chatroom :
                        logic.getCurrentUser().getChatRooms()) {
                    if (chatroom.getUser2id() == id)
                        flag = true;
                }
                if (!flag) {
                    logic.getCurrentUser().getChatRooms().add(new ChatRoom(logic.getCurrentUser().getId(), id));
                    logic.searchUser(id).getChatRooms().add(new ChatRoom(id, logic.getCurrentUser().getId()));
                }
            }
    }
    private static void chats() {
        check();
        Collections.sort(logic.getCurrentUser().getChatRooms(), Comparator.<ChatRoom>
                comparingInt(chatroom1 -> chatroom1.getNotSeen().size())
                .thenComparingInt(chatroom2 -> chatroom2.getNotSeen().size()));
        Iterator.iterateOnChatRooms(logic.getCurrentUser().getChatRooms());
    }



}
