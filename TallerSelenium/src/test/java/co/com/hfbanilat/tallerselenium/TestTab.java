package co.com.hfbanilat.tallerselenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class TestTab {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void configuracionInicial() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testAbrirNuevaPestana() {
        driver.get("https://automationtesting.co.uk/browserTabs.html");

        WebElement openTabButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input")));
        openTabButton.click();

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        while (allWindows.size() == 1) {
            allWindows = driver.getWindowHandles();
        }
        String newWindow = null;
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                newWindow = window;
                break;
            }
        }

        assert newWindow != null;
        driver.switchTo().window(newWindow);

        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@aria-label='Buscar']")));
        searchField.click();
        searchField.sendKeys("Banilat");
    }

    @AfterEach
    public void configuracionFinal() {
        driver.quit();
    }
}