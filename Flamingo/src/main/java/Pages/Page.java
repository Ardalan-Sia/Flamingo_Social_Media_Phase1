package Pages;

import Models.ChatRoom;
import a.CLI;
import a.ConsoleColor.ConsoleColor;
import a.Logic;
import a.Time;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.LinkedList;
import java.util.Scanner;
@JsonDeserialize
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChatRoom.class, name = "chatroom"),
})
public abstract class Page {
    protected static final Scanner SC = new Scanner(System.in);
    protected static Logic logic = CLI.getLogic();
    public static void exit() {
        logic.updateUserLasSeen();
        logic.getCurrentUser().setLastSeen(Time.currentTime());
        logic.save();
        System.out.println("Thanks for using Flamingo :)");
        System.out.println("Good Luck!");
        CLI.logger.info("program stopped");
        System.exit(0);

    }
    protected static void printCommands(LinkedList<String> commands) {
        System.out.println(ConsoleColor.WHITE_BRIGHT+"\nvalid commands :"+ConsoleColor.RESET);
        commands.forEach(command-> System.out.println("\t"+ ConsoleColor.CYAN_BRIGHT+ command+ConsoleColor.RESET));
    }

//    protected  static void setCommands(){};

}
