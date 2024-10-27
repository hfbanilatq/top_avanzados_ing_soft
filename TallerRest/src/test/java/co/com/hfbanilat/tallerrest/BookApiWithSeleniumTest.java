package co.com.hfbanilat.tallerrest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookApiWithSeleniumTest {

    private static final String BASE_URL = "https://demoqa.com";
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeEach
    public void setupWebDriver() {
        driver = new ChromeDriver();
        driver.get(BASE_URL + "/login");
        this.wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCreateAuthenticateAndDeleteUser() {
        String username = "banilat";
        String password = "Password123!";

        createUser(username, password);

        authenticateUser(username, password);

        deleteUser();
        handleAlertAndRedirectToLogin();
        authenticateUser(username, password);

        validateErrorMessage();
    }

    private void createUser(String username, String password) {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"userName\": \"" + username + "\", \"password\": \"" + password + "\"}")
                .post(BASE_URL + "/Account/v1/User");
        Assertions.assertEquals(201, response.getStatusCode(), "Usuario no creado correctamente");
    }

    private void authenticateUser(String username, String password) {
        WebElement usernameField = driver.findElement(By.cssSelector("#userName"));
        WebElement passwordField = driver.findElement(By.cssSelector("#password"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        loginButton.click();
    }

    private void deleteUser() {

        WebElement deleteAccountButton = this.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.text-center.button button#submit")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteAccountButton);
        deleteAccountButton.click();

        WebElement acceptDeleteButton = driver.findElement(By.cssSelector("#closeSmallModal-ok"));

        acceptDeleteButton.click();
        this.wait.until(ExpectedConditions.alertIsPresent());
    }

    private void handleAlertAndRedirectToLogin() {
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("User Deleted.", alert.getText(), "Mensaje de alerta incorrecto");
        alert.accept();
    }

    private void validateErrorMessage() {
        WebElement errorMessage = this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#name")));
        Assertions.assertTrue(errorMessage.isDisplayed(), "El mensaje de error no se mostró correctamente");
    }
}
