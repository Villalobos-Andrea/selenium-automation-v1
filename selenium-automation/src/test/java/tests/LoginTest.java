package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        String email = ConfigReader.getProperty("admin_email");
        String password = ConfigReader.getProperty("password");

        new LoginPage(driver)
                .loginAs(email, password);

        // Add wait for redirection
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard.php"));

       
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard.php"),
                "Expected to be redirected to dashboard, but was: " + currentUrl);
    }

    @Test
    public void invalidLoginTest() {
        new LoginPage(driver)
                .loginAs("wronguser@example.com", "wrongpass");

        // Add wait for error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("error-msg"))
        );

        String errorMessage = errorElement.getText();
        Assert.assertEquals(errorMessage, "No account found with that email.",
                "Error message did not match!");
    }
    
    @Test
    public void invalidLoginTest_IncorrectPassword() {
        String email = ConfigReader.getProperty("admin_email");
        new LoginPage(driver)
                .loginAs(email, "wrongpass");

        // Add wait for error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("error-msg"))
        );

        String errorMessage = errorElement.getText();
        Assert.assertEquals(errorMessage, "Invalid password. Please try again.",
                "Error message did not match!");
    }
}
