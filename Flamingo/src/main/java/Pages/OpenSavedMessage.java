package Pages;

import java.util.LinkedList;

public class OpenSavedMessage extends Page {
    private final static LinkedList<String> commands = new LinkedList<>();

    public static void savedMessage(String str){
        setCommands();
        String command = "";
        First:
        while (true){

            printCommands(commands);
            command = SC.nextLine().toUpperCase();
            switch (command){
                case "DELETE":
                    delete(str);
                    break First;
                case "BACK":
                    break First;
                case "EXIT":
                    exit();
                    break;

            }
        }
    }

    private static void setCommands(){
        commands.clear();
        commands.add("Delete");
        commands.add("Back");
        commands.add("Exit");


    }
    private static void delete(String str){
        logic.getCurrentUser().getSavedMessage().remove(str);
        System.out.println("done!");
    }
}
