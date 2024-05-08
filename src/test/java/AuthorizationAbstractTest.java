import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class AuthorizationAbstractTest {
    static WebDriver webDriver;
    String appLogin = "Drago1";
    String appPassword = "8be708fab8";

    @BeforeAll
    static void authorization(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://test-stand.gb.ru/login");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterAll
    static void closeApp(){
        webDriver.quit();
    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }

    public String getAppLogin(){
        return appLogin;
    }
    public String getAppPassword(){
        return appPassword;
    }
}
