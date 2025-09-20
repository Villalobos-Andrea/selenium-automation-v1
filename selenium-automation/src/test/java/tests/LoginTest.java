package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            // Load credentials from config.properties
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            props.load(fis);

            String email = props.getProperty("email");
            String password = props.getProperty("password");

            // Go to login page
            driver.get("https://secaspi.org/login.php");

            // Use Page Object
            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLogin();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.titleContains("iADOPT | Dashboard"));
            
            // Simple validation
            String title = driver.getTitle();
            if (title.contains("iADOPT | Dashboard")) {
                System.out.println("✅ Login successful!");
            } else {
                System.out.println("❌ Login failed. Page title: " + title);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //driver.quit();
        }
    }
}
