package Models;

import a.CLI;
import a.ConsoleColor.ConsoleColor;

import java.util.LinkedList;

public class Group {
    private int user1id;
    private LinkedList<Integer> members = new LinkedList<>();
    private String name;

    private Group(String name) {
        this.name = name;
    }
    public Group(){}

    public int getUser1id() {
        return user1id;
    }

    public void setUser1id(int user1id) {
        this.user1id = user1id;
    }

    public LinkedList<Integer> getMembers() {
        return members;
    }

    public void setMembers(LinkedList<Integer> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void createGroup(String name){
        if (!name.equals("")) {
            boolean flag = false;
            for (Group g :
                   CLI.getLogic().getCurrentUser().getGroups()) {
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
        Group group = new Group(name);
        CLI.getLogic().getCurrentUser().getGroups().add(group);
        CLI.logger.info("@"+CLI.getLogic().getCurrentUser().getUserName()+ " creat a group ---> name : "+group.getName()) ;
        System.out.println("done!");

    }

}
