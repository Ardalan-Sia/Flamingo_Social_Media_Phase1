package a;

import java.io.File;

public class Main {
    private static final CLI cli = new CLI();

    public static void main(String[] args) {
        CLI.logger.info("program started");
        while (true) {
            cli.loginPage();
            cli.mainPage();
        }
    }
}
