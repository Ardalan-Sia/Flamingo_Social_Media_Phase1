package Pages;

import Models.User;
import a.CLI;
import a.ConsoleColor.ConsoleColor;
import a.Logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginPage extends Page {
    public static void loginPage(Logic logic) {
        System.out.println(ConsoleColor.BLUE_BACKGROUND_BRIGHT + "Hello! Welcome To 'Flamingo'" + ConsoleColor.RESET);
        System.out.println();


        while (true) {
            System.out.println("If you already have an account please type " + ConsoleColor.BLUE_BOLD_BRIGHT + "Sign in" + ConsoleColor.RESET);
            System.out.println("If you want to create an account please type " + ConsoleColor.BLUE_BOLD_BRIGHT + "Sign up" + ConsoleColor.RESET);
            System.out.println("If you want to do nothing type " + ConsoleColor.RED_UNDERLINED + "Exit" + ConsoleColor.RESET);
            System.out.println();

            String command = SC.nextLine();
            switch (command.toUpperCase()) {
                case "SIGN IN":
                    if (signIn(logic)){
                        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " signed in");
                        return;
                    }
                    break;
                case "SIGN UP":
                    if (signUp(logic)) {
                        logic.save();
                        CLI.logger.info("@"+logic.getCurrentUser().getUserName()+ " signed up");
                        return;
                    }
                    break;

                case "EXIT":
                    exit();
                default:
                    System.out.println(ConsoleColor.RED_BRIGHT + "Invalid command" + ConsoleColor.RESET);
            }
        }
    }

    public static boolean signIn(Logic logic) {
        while (true) {
            System.out.println("Username :");
            String username = SC.nextLine();
            System.out.println("Password :");
            String password = SC.nextLine();

            if (logic.signIn(username, password)) {
                System.out.println("You signed in successfully!");
                return true;
            } else {
                System.out.println("Invalid username/password!");
                System.out.println("Do you want to try again? Y/N");
                while (true) {
                    String command = SC.nextLine().toUpperCase();
                    if (command.equals("N"))
                        return false;
                    if (command.equals("Y"))
                        break;
                    System.out.println("Invalid command");
                }
            }
        }
    }

    public static boolean signUp(Logic logic) {
        System.out.println(ConsoleColor.RED_BRIGHT + "* " + ConsoleColor.RESET + "First name :\tor " + ConsoleColor.RED_BOLD_BRIGHT + "/Cancel" + ConsoleColor.RESET);
        String firstName = scanRequiredField();
            if (firstName.equalsIgnoreCase("/cancel"))
                return false;
        System.out.println(ConsoleColor.RED_BRIGHT + "* " + ConsoleColor.RESET + "Last name :\tor" + ConsoleColor.RED_BOLD_BRIGHT + "/Cancel" + ConsoleColor.RESET);
        String lastName = scanRequiredField();
        if (lastName.equalsIgnoreCase("/cancel"))
            return false;
        System.out.println(ConsoleColor.RED_BRIGHT + "* " + ConsoleColor.RESET + "Username :\tor" + ConsoleColor.RED_BOLD_BRIGHT + "/Cancel" + ConsoleColor.RESET);
        String userName ="";

        do {
            userName = scanRequiredField();
            if (userName.equalsIgnoreCase("/cancel"))
                return false;
        } while (!checkUsername(userName));

        String password = "";
        System.out.println(ConsoleColor.RED_BRIGHT + "* " + ConsoleColor.RESET + "Password :\tor" + ConsoleColor.RED_BOLD_BRIGHT + "/Cancel" + ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLACK_BRIGHT + "Your password must be at least 5 characters and include uppercases, lowercases and digits" + ConsoleColor.RESET);
        while (true) {
            password = scanRequiredField();
            if (password.equalsIgnoreCase("/cancel"))
                return false;
            if (isValidPassword(password))
                break;
            System.out.println(ConsoleColor.RED_BRIGHT + "Please choose a stronger password" + ConsoleColor.RESET);
        }

        String email = "";
        System.out.println(ConsoleColor.RED_BRIGHT + "* " + ConsoleColor.RESET + " Email address :\tor" + ConsoleColor.RED_BOLD_BRIGHT + "/Cancel" + ConsoleColor.RESET);
        while (true) {
            email = scanRequiredField();
            if (email.equalsIgnoreCase("/cancel"))
                return false;
            if (checkEmail(email)) {
                if (isValidEmail(email))
                    break;
                System.out.println(ConsoleColor.RED_BRIGHT + "Please type a valid email" + ConsoleColor.RESET);
            }
        }

        Date birthDate = null;
        System.out.println("Birth :\tor" + ConsoleColor.RED_BOLD_BRIGHT + "/Cancel" + ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLACK_BRIGHT + "You should follow this pattern 'yyyy-MM-dd'" + ConsoleColor.RESET);
        while (true) {
            String date = SC.nextLine();
            if (date.equalsIgnoreCase("/cancel"))
                return false;
            try {
                birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (ParseException e) {
                if (date.isEmpty())
                    break;
                System.out.println(ConsoleColor.RED_BRIGHT + "Invalid date pattern" + ConsoleColor.RESET);
            }
        }

        System.out.println("Bio :\tor" + ConsoleColor.RED_BOLD_BRIGHT + "/Cancel" + ConsoleColor.RESET);
        if (firstName.equalsIgnoreCase("/cancel"))
            return false;
        String bio = SC.nextLine();

        String phoneNumber = "";
        System.out.println("Phone number :\tor" + ConsoleColor.RED_BOLD_BRIGHT + "/Cancel" + ConsoleColor.RESET);
        while (true) {
            phoneNumber = SC.nextLine();
            if (firstName.equalsIgnoreCase("/cancel"))
                return false;
            if (checkPhoneNumber(phoneNumber)) {

                if (phoneNumber.matches("\\+?\\d*"))
                    break;
                System.out.println(ConsoleColor.RED_BRIGHT + "Invalid phone pattern" + ConsoleColor.RESET);
            }
        }

        return logic.signUp(firstName, lastName, userName, password, email, birthDate, bio, phoneNumber);
    }

    public static String scanRequiredField() {
        while (true) {
            String s = SC.nextLine();
            if (!s.isEmpty())
                return s;
            System.out.println(ConsoleColor.RED_BRIGHT + "This is required" + ConsoleColor.RESET);
        }
    }

    public static boolean checkUsername(String check) {
        for (User user : logic.getUsers()) {
            if (user.getUserName().equals(check)) {
                System.out.println(ConsoleColor.RED_BRIGHT + "This username has already been taken" + ConsoleColor.RESET);
                return false;
            }
        }
        return true;
    }

    public static boolean checkEmail(String check) {
        for (User user : logic.getUsers()) {
            if (user.getEmail().equals(check)) {
                System.out.println(ConsoleColor.RED_BRIGHT + "This email has already been taken" + ConsoleColor.RESET);
                return false;
            }
        }
        return true;
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        for (User user : logic.getUsers()) {
            if (user.getPhoneNumber().equals(phoneNumber)&& !phoneNumber.equals("")) {
                System.out.println(ConsoleColor.RED_BRIGHT + "This phone number has already been taken" + ConsoleColor.RESET);
                return false;
            }

        }
        return true;
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 5)
            return false;

        boolean digit = false;
        boolean lowerCase = false;
        boolean upperCase = false;
        for (char c : password.toCharArray()) {
            digit |= Character.isDigit(c);
            lowerCase |= Character.isLowerCase(c);
            upperCase |= Character.isUpperCase(c);
        }

        return digit && lowerCase && upperCase;
    }

    public static boolean isValidEmail(String email) {
        return email.matches("[A-Za-z\\d._]+@[A-Za-z\\d]+.[a-z]+");
    }

}
