package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.className("login-btn");
    private By errorMsg = By.id("error-msg");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public LoginPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    public LoginPage clickLogin() {
        click(loginButton);
        return this;
    }

    public LoginPage loginAs(String email, String password) {
        return enterEmail(email).enterPassword(password).clickLogin();
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }
}
