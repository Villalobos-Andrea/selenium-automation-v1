package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignupPage;
import utils.ConfigReader;

import java.time.Duration;

public class SignupTest extends BaseTest {

    @Test
    public void validSignupTest() {
    	
        String password = ConfigReader.getProperty("password");
        
        String firstName = "John";
        String lastName = "Doe";
        String birthDate = "1990-05-15";
        String gender = "Male";
        String fbProfile = "https://facebook.com/johndoe";
        String contactNumber = "09171234567";
        String email = "secaspiiadopt" + System.currentTimeMillis() + "@gmail.com";
        
        new SignupPage(driver)
                .signupAs(firstName, lastName, birthDate, gender, fbProfile, contactNumber, email, password, password);

        // Add wait for redirection after signup
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       // wait.until(ExpectedConditions.urlContains("dashboard.php"));

        /*String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard.php"),
                "Expected to be redirected to dashboard after signup, but was: " + currentUrl);*/
    }

    public void invalidSignupTest_EmailAlreadyExists() {
        String firstName = "Jane";
        String lastName = "Smith";
        String birthDate = "1995-09-10";
        String gender = "Female";
        String fbProfile = "https://facebook.com/janesmith";
        String contactNumber = "09981234567";
        String email = ConfigReader.getProperty("admin_email"); // already registered
        String password = "Password123!";

        new SignupPage(driver)
                .signupAs(firstName, lastName, birthDate, gender, fbProfile, contactNumber, email, password, password);

        // Wait for error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("error-msg"))
        );

        String errorMessage = errorElement.getText();
        Assert.assertEquals(errorMessage, "Email already exists.",
                "Error message did not match!");
    }

    @Test
    public void invalidSignupTest_PasswordMismatch() {
        String firstName = "Mark";
        String lastName = "Lee";
        String birthDate = "2000-01-01";
        String gender = "Male";
        String fbProfile = "https://facebook.com/marklee";
        String contactNumber = "09181231234";
        String email = "mark.lee" + System.currentTimeMillis() + "@example.com";
        String password = "Password123!";
        String confirmPassword = "Password321!"; // mismatch

        new SignupPage(driver)
                .signupAs(firstName, lastName, birthDate, gender, fbProfile, contactNumber, email, password, confirmPassword);

        // Wait for error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signup-form\"]/div[6]/div"))
        );

        String errorMessage = errorElement.getText();
        Assert.assertEquals(errorMessage, "Passwords do not match.",
                "Error message did not match!");
    }
}
