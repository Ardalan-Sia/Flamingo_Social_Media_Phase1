package Pages;

import Models.ChatRoom;
import Models.Message;
import a.CLI;
import a.ConsoleColor.ConsoleColor;


import java.util.LinkedList;

public class OpenChatRoom extends Page{
    private final static LinkedList<String> commands = new LinkedList<>();
    public static void openChatRoom(ChatRoom chatRoom){
        chatRoom.getNotSeen().clear();
        setCommands();
        String command = "";
        First:
        while (true){
            printChats(chatRoom);
            printCommands(commands);
            command = SC.nextLine().toUpperCase();
            switch (command){
                case "NEWMESSAGE":
                    sendMessage(chatRoom);
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " sent a message to "+"id:"+chatRoom.getUser2id());
                    break ;
                case "SAVEMESSAGE":
                    saveMessage(chatRoom);
                    CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " saved a message from "+"id:"+chatRoom.getUser2id());
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
    private static void setCommands(){
        commands.clear();
        commands.add("NewMessage");
        commands.add("SaveMessage");
        commands.add("Back");
        commands.add("Exit");
    }
    private static void printChats(ChatRoom chatRoom){
        if (chatRoom.getMessages().isEmpty()) {
            System.out.println(ConsoleColor.PURPLE_BRIGHT+"No messages here yet..."+ConsoleColor.RESET);
        }
        int count = 0;
        for (Message message : chatRoom.getMessages()){
            System.out.println((count+1)+" "+message.toString());
            count++;
        }
    }
    public static void sendMessage(ChatRoom chatRoom){
        System.out.println("Your message :");
        String body = SC.nextLine();
        Message message = new Message(body,logic.getCurrentUser());
        chatRoom.getMessages().add(message);
        logic.findChatRoom(chatRoom.getUser2id() , logic.getCurrentUser().getId()).getNotSeen().add(message);
        logic.findChatRoom(chatRoom.getUser2id(),logic.getCurrentUser().getId()).getMessages().add(message);
    }
    public static void sendMessage(ChatRoom chatRoom , String body){
        Message message = new Message(body,logic.getCurrentUser());
        chatRoom.getMessages().add(message);
        logic.findChatRoom(chatRoom.getUser2id() , logic.getCurrentUser().getId()).getNotSeen().add(message);
        logic.findChatRoom(chatRoom.getUser2id(),logic.getCurrentUser().getId()).getMessages().add(message);
    }

    private static void saveMessage(ChatRoom chatRoom){
        LinkedList<String> list = new LinkedList<>();
        if (!chatRoom.getMessages().isEmpty())
            for (Message message:
                    chatRoom.getMessages()) {
                list.add(message.toString());
            }
        while (true) {
            System.out.println("Which one ?");
            String command = SC.nextLine();
            try {
                int index = Integer.parseInt(command);
                if (list.get(index - 1) != null) {
                    logic.getCurrentUser().getSavedMessage().add(list.get(index - 1));
                    System.out.println("done!");
                    break;
                }
                    else System.out.println("Not found");
            } catch (NumberFormatException e) {
                System.out.println("Wrong command");
            }
        }


    }
}
