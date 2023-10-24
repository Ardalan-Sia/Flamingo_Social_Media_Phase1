package a;

import Pages.LoginPage;
import Pages.MainPage;
import Pages.Messenger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
public class CLI {
    private final File dataDirectory = new File("resources/Data");
    private static Logic logic;

    public static final Logger logger = LogManager.getLogger(Logic.class);

    public CLI() {
        dataDirectory.mkdirs();
        logic = new Logic();
        File file = new File("resources\\Data\\data.json");
        try {
            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                logic = objectMapper.readValue(file, new TypeReference<Logic>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginPage() {
        LoginPage.loginPage(logic);
        logic.updateUserLasSeen();
        Messenger.setAllowedToChat();
        Messenger.check();
    }
    public void mainPage(){
        MainPage.mainPage();
    }

    public static Logic getLogic() {
        return logic;
    }
}