package Pages;

import Models.Group;
import a.CLI;
import a.ConsoleColor.ConsoleColor;
import java.util.LinkedList;

public class OpenGroupsPage extends Page {
    private final static LinkedList<String> commands = new LinkedList<>();


    public static void openGroupsPage(Group group) {
        setCommands();
        String command;
        First:
        while (true) {
            printMember(group);
            printCommands(commands);
            command = SC.nextLine().toUpperCase();
            switch (command) {
                case "SENDGROUPMESSAGE":
                    sendGroupMessage(group);
                    break;
                case "ADDMEMBER":
                    addMember(group);
                    break;
                case "CHANGENAME":
                    changeName(group);
                    break;
                case "REMOVEMEMBER":
                    removeMember(group);
                    break;
                case "DELETEGROUP":
                    deleteGroup(group);
                    break First;
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


    private static void printMember(Group group) {
        System.out.println(ConsoleColor.YELLOW_BRIGHT + "Members : " + ConsoleColor.RESET);
        group.getMembers().forEach(member -> System.out.println(logic.idToUsername(member)));
    }

    private static void setCommands() {
        commands.clear();
        commands.add("SendGroupMessage");
        commands.add("AddMember");
        commands.add("RemoveMember");
        commands.add("ChangeName");
        commands.add("DeleteGroup");
        commands.add("Back");
        commands.add("Exit");
    }

    private static void sendGroupMessage(Group group) {
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " sent a message to group "+group.getName());
        group.getMembers().forEach(member -> OpenChatRoom.sendMessage(logic.findChatRoom(logic.getCurrentUser().getId(), member)));
    }

    private static void addMember(Group group) {
        System.out.println("Username : ");
        String username = SC.nextLine();
        if (!logic.getCurrentUser().getAllowedToChat().isEmpty())
        if (logic.getCurrentUser().getAllowedToChat().contains(logic.searchUser(username).getId())) {
            if (!group.getMembers().contains(logic.searchUser(username).getId())) {
                group.getMembers().add(logic.searchUser(username).getId());
                CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " add "+"@"+username+" to group "+group.getName());
                System.out.println("done!");
            } else System.out.println(ConsoleColor.RED_BRIGHT + "Already exists!" + ConsoleColor.RESET);
        } else System.out.println(ConsoleColor.RED_BRIGHT + "Not fount!" + ConsoleColor.RESET);
        else System.out.println(ConsoleColor.RED_BRIGHT + "Not fount!" + ConsoleColor.RESET);
    }

    private static void removeMember(Group group) {
        System.out.println("Username : ");
        String username = SC.nextLine();
        if (group.getMembers().contains(logic.searchUser(username).getId())) {
            group.getMembers().remove(logic.searchUser(username).getId());
            CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " removed "+"@"+username+" from group "+group.getName());
            System.out.println("done!");
        } else System.out.println(ConsoleColor.RED_BRIGHT + "Not found!" + ConsoleColor.RESET);
    }

    private static void deleteGroup(Group group) {
        logic.getCurrentUser().getGroups().remove(group);
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " deleted group "+group.getName());
        System.out.println(ConsoleColor.RED_BRIGHT + "Removed" + ConsoleColor.RESET);
    }

    private static void changeName(Group group) {
        String name = SC.nextLine();
        if (!name.equals("")) {
            boolean flag = false;
            for (Group g :
                    logic.getCurrentUser().getGroups()) {
                if (g.getName().equals(name)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println(ConsoleColor.RED_BRIGHT + "Already used" + ConsoleColor.RESET);
                return;
            }
        } else {
            System.out.println(ConsoleColor.RED_BRIGHT + "It's not a valid name" + ConsoleColor.RESET);
            return;
        }
        group.setName(name);
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " changed group name to "+group.getName());
        System.out.println("done!");
    }

}
