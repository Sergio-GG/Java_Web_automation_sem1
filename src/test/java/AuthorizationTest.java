
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AuthorizationTest extends AuthorizationAbstractTest{

    @Test
    void auth() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver webDriver = getWebDriver();
        WebElement login = webDriver.findElement(By.xpath("//input[@type='text']"));
        WebElement pass = webDriver.findElement(By.xpath("//input[@type='password']"));
        WebElement buttonLogin = webDriver.findElement(By.xpath("//button[@type='submit']"));

        login.sendKeys(getAppLogin());
        pass.sendKeys(getAppPassword());
        buttonLogin.click();

        Thread.sleep(5000L);

        List<WebElement> forCheckList = webDriver.findElements(By.partialLinkText("Hello,"));
        Assertions.assertEquals(1, forCheckList.size());

    }

    // Homework sem_1
    @Test
    void authWithWait() throws InterruptedException {
        String titleName = "Game";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver webDriver = getWebDriver();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement login = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")));
        WebElement pass = webDriver.findElement(By.xpath("//input[@type='password']"));
        WebElement buttonLogin = webDriver.findElement(By.xpath("//button[@type='submit']"));

        login.sendKeys(getAppLogin());
        pass.sendKeys(getAppPassword());
        buttonLogin.click();

        WebElement cross = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='create-btn']")));
        cross.click();

        WebElement postTitle = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")));
        WebElement saveButton = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='mdc-button__label']")));
        postTitle.sendKeys(titleName);
        saveButton.click();
        Thread.sleep(5000L);

        File screenshot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);

        try{
            FileUtils.copyFile(screenshot, new File("./src/main/resources/screenshot.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

        WebElement forCheckElement = webDriver.findElement(By.xpath("//h1[@class='svelte-tv8alb']"));
        Assertions.assertEquals("Game", forCheckElement.getText());
    }
}
