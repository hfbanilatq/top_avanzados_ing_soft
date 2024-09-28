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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RetoUno {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void configuracionInicial() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testRegistroExitoso() {
        // ARRANGE
        driver.get("https://teststore.automationtesting.co.uk/index.php");

        // ACT
        WebElement linkSignIn = driver.findElement(By.linkText("Sign in"));
        linkSignIn.click();

        WebElement linkCreateAccount = driver.findElement(By.linkText("No account? Create one here"));
        linkCreateAccount.click();

        WebElement radioTitleMr = driver.findElement(By.cssSelector("label[for='field-id_gender-1']"));
        radioTitleMr.click();

        WebElement inputFirstName = driver.findElement(By.xpath("//*[@id='field-firstname']"));
        inputFirstName.sendKeys("Hector Fabio");

        WebElement inputLastName = driver.findElement(By.xpath("//input[contains(@id, 'lastname')]"));
        inputLastName.sendKeys("Banilat Quintero");

        WebElement inputEmail = driver.findElement(By.xpath("//input[@id='field-email']"));
        inputEmail.sendKeys("pruebahfbanilat"+ generarCadenaAleatoria(3)+"@gmail.com");

        WebElement inputPassword = driver.findElement(By.xpath("//*[@id='field-password']"));
        inputPassword.sendKeys("password_prueba12");

        WebElement inputBirthdate = driver.findElement(By.xpath("//input[@placeholder='MM/DD/YYYY']"));
        inputBirthdate.sendKeys("12/22/1997");

        WebElement checkboxReceiveOffers = driver.findElement(By.xpath("//label[.//input[@name='optin']]"));
        checkboxReceiveOffers.click();

        WebElement checkboxAgreeTerms = driver.findElement(By.xpath("//input[@name='psgdpr']"));
        checkboxAgreeTerms.click();

        WebElement checkboxSignUp = driver.findElement(By.xpath("//input[@name='newsletter']"));
        checkboxSignUp.click();

        WebElement buttonSave = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonSave.click();

        // ASSERT
        WebElement userInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='account']/span[@class='hidden-sm-down']")));
        assertTrue(userInfo.getText().contains("Hector Fabio Banilat Quintero"));
    }

    @AfterEach
    public void configuracionFinal() {
        WebElement linkSignOut = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='user-info']/a[@class='logout hidden-sm-down']")));
        linkSignOut.click();
        driver.quit();
    }

    private String generarCadenaAleatoria(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(longitud);
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return sb.toString();
    }
}
