package Pages;

import a.CLI;
import a.ConsoleColor.ConsoleColor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Edit extends Page {
    private static final LinkedList<String> commands = new LinkedList<>();


    public static void edit() {
        setCommands();
        First:
        while (true) {
            printCommands(commands);
            String command = SC.nextLine().toUpperCase();
            switch (command) {
                case "CHANGEFIRSTNAME":
                    changeFirstName();
                    break;
                case "CHANGELASTNAME":
                    changeLastName();
                    break;
                case "CHANGEUSERNAME":
                    changeUserName();
                    break;
                case "CHANGEEMAIL":
                    changeEmail();
                    break;
                case "CHANGEBIRTHDATE":
                    System.out.println("Birth :");
                    changeBirthDate();
                    break;
                case "CHANGEBIO":
                    changeBio();
                    break;
                case "CHANGEPHONENUMBER":
                    changePhoneNumber();
                    break;
                case "BACK":
                    break First;
                case "EXIT":
                    exit();
                    break;
            }
        }
    }

    public static void changeFirstName(){
        System.out.println("New first name :\tor"+ConsoleColor.RED_BOLD_BRIGHT+"/Cancel"+ConsoleColor.RESET);
        String temp = "";
        temp = LoginPage.scanRequiredField();
        if (temp.equalsIgnoreCase("/cancel"))
            return ;
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " changed his firstname to "+temp);
        logic.getCurrentUser().setFirstName(temp);
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ConsoleColor.RESET);
    }
    public static void changeLastName(){
        System.out.println("New last name :\tor"+ConsoleColor.RED_BOLD_BRIGHT+"/Cancel"+ConsoleColor.RESET);
        String temp = "";
        temp = LoginPage.scanRequiredField();
        if (temp.equalsIgnoreCase("/cancel"))
            return;
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " changed his lastname to "+temp);
        logic.getCurrentUser().setLastName(temp);
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ConsoleColor.RESET);
    }
    public static void changeUserName(){
        System.out.println("New username :\tor"+ConsoleColor.RED_BOLD_BRIGHT+"/Cancel"+ConsoleColor.RESET);
        String temp = "";
        do {
            temp = LoginPage.scanRequiredField();
            if (temp.equalsIgnoreCase("/cancel"))
                return;
        } while (!LoginPage.checkUsername(temp));
        logic.getCurrentUser().setUserName(temp);
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " changed his username to "+temp);
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ConsoleColor.RESET);
    }
    public static void changeEmail(){
        System.out.println("New email :\tor"+ConsoleColor.RED_BOLD_BRIGHT+"/Cancel"+ConsoleColor.RESET);
        String temp = "";
        boolean flag = false;
        while (true) {
            temp = LoginPage.scanRequiredField();
            if (temp.equalsIgnoreCase("/cancel")) {
                return;
            }
            if (LoginPage.checkEmail(temp)) {
                if (LoginPage.isValidEmail(temp))
                    break;
                System.out.println(ConsoleColor.RED_BRIGHT + "Please type a valid email" + ConsoleColor.RESET);
            }
        }
        logic.getCurrentUser().setEmail(temp);
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " changed his Email to "+temp);
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ConsoleColor.RESET);
    }
    public static void changeBirthDate(){
        System.out.println("New Birthdate :\tor"+ConsoleColor.RED_BOLD_BRIGHT+"/Cancel"+ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLACK_BRIGHT + "You should follow this pattern 'yyyy-MM-dd'" + ConsoleColor.RESET);

        String temp = "";
        System.out.println(ConsoleColor.BLACK_BRIGHT + "You should follow this pattern 'yyyy-MM-dd'" + ConsoleColor.RESET);
        Date birthDate = null;
        boolean flag2 = false;
        while (true) {
            temp = SC.nextLine();
            if (temp.equalsIgnoreCase("/cancel"))
                return;
            try {
                birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(temp);
            } catch (ParseException e) {
                if (temp.isEmpty())
                    break;
                System.out.println(ConsoleColor.RED_BRIGHT + "Invalid date pattern" + ConsoleColor.RESET);
            }
        }
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " changed his birthdate to "+temp);
        logic.getCurrentUser().setBirthDate(birthDate);
    }
    public static void changePhoneNumber(){
        System.out.println("New phone number :\tor"+ConsoleColor.RED_BOLD_BRIGHT+"/Cancel"+ConsoleColor.RESET);
        String temp = "";
        boolean flag1 = false;
        while (true) {
            temp = SC.nextLine();
            if (temp.equalsIgnoreCase("/cancel")){
                return;
            }
            if (LoginPage.checkPhoneNumber(temp)) {

                if (temp.matches("\\+?\\d*"))
                    break;
                System.out.println(ConsoleColor.RED_BRIGHT + "Invalid phone pattern" + ConsoleColor.RESET);
            }
        }
        logic.getCurrentUser().setPhoneNumber(temp);
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " changed his phone number to "+temp);
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ConsoleColor.RESET);
    }
    public static void changeBio(){
        System.out.println("New bio :\tor"+ConsoleColor.RED_BOLD_BRIGHT+"/Cancel"+ConsoleColor.RESET);
        String temp = "";
        temp = SC.nextLine();
        if (temp.equalsIgnoreCase("/cancel"))
            return;
        logic.getCurrentUser().setBio(temp);
        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " changed his bio to "+temp);
        System.out.println(ConsoleColor.GREEN_BRIGHT+"done!"+ConsoleColor.RESET);
    }

    private static void setCommands() {
        commands.clear();
        commands.add("ChangeFirstname");
        commands.add("ChangLastname");
        commands.add("ChangeUsername");
        commands.add("ChangeEmail");
        commands.add("ChangeBirthdate");
        commands.add("ChangeBio");
        commands.add("ChangePhoneNumber");
        commands.add("Back");
        commands.add("Exit");
    }
}
