package co.com.hfbanilat.tallerselenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestIframe {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testIFrameAccordion() {
        driver.get("https://automationtesting.co.uk/iframes.html");

        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@src='index.html']")));
        driver.switchTo().frame(iframe);

        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#sidebar > a")));

        Actions actionsIframe = new Actions(driver);
        actionsIframe.moveToElement(menuButton).perform();

        menuButton.click();

        WebElement accordionButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#menu > ul > li:nth-child(2) > a")));
        accordionButton.click();

        driver.switchTo().defaultContent();

        WebElement menuButtonOutside = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#sidebar > a")));

        Actions actionsOutside = new Actions(driver);
        actionsOutside.moveToElement(menuButtonOutside).perform();

        menuButtonOutside.click();

        boolean isMenuClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#menu_sidebar")));
        assertTrue(isMenuClosed, "El menú lateral no se cerró correctamente.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}