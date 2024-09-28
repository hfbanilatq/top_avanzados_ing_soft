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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSauceDemo {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void configuracionInicial() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testCompraExitosa() {
        // ARRANGE
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // ACT
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='add-to-cart-sauce-labs-fleece-jacket']"))).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.cssSelector("[data-test='checkout']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Hector");
        driver.findElement(By.id("last-name")).sendKeys("Banilat");
        driver.findElement(By.id("postal-code")).sendKeys("050037");
        driver.findElement(By.cssSelector("[data-test='continue']")).click();

        // Validar precios
        validarSumaDePrecios();

        // Finalizar compra
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='finish']"))).click();
        assertTrue(driver.findElement(By.cssSelector("[data-test='complete-header']")).getText().contains("Thank you for your order!"));

        // Regresar a productos
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='back-to-products']"))).click();
    }

    @AfterEach
    public void configuracionFinal() {
        // Cerrar sesión
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bm-burger-button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
        driver.quit();
    }

    private void validarSumaDePrecios() {
        List<WebElement> precios = driver.findElements(By.className("inventory_item_price"));
        double precioBackpack = Double.parseDouble(precios.get(0).getText().replace("$", ""));
        double precioJacket = Double.parseDouble(precios.get(1).getText().replace("$", ""));
        String itemTotalText = driver.findElement(By.cssSelector(".summary_subtotal_label")).getText();
        double itemTotal = Double.parseDouble(itemTotalText.replace("Item total: $", ""));

        assertEquals(precioBackpack + precioJacket, itemTotal, "La suma de los precios de los productos no coincide con el total.");
    }
}