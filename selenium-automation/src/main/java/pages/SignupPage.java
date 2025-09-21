package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage {

    // Locators
    private By accessLogin = By.xpath("/html/body/header/nav/ul/li[6]/a");
    private By accessSignup = By.xpath("//*[@id=\"loginForm\"]/div[2]/div[6]/p/a");
    private By lastNameField = By.xpath("//*[@id=\"last-name\"]");
    private By firstNameField = By.xpath("//*[@id=\"first-name\"]");
    private By birthDateField = By.xpath("//*[@id=\"birthdate\"]");
    private By genderField = By.xpath("//*[@id=\"gender\"]");
    private By fbProfileField = By.xpath("//*[@id=\"facebook-profile\"]");
    private By contactNumberField = By.xpath("//*[@id=\"contact-number\"]");
    private By emailField = By.xpath("//*[@id=\"email\"]");
    private By passwordField = By.xpath("//*[@id=\"password\"]");
    private By confirmPasswordField = By.xpath("//*[@id=\"confirm-password\"]");
    private By signupButton = By.xpath("//*[@id=\"sign-up-btn\"]");
    private By passwordErrormessage = By.xpath("  //*[@id=\"signup-form\"]/div[6]/div");
    

    // Constructor
    public SignupPage(WebDriver driver) {
        super(driver);
    }

    // Navigate to signup form
    public SignupPage navigateSignup() {
        click(accessLogin);
        click(accessSignup);
        return this;
    }

    // Page Actions
    public SignupPage enterFirstName(String firstName) {
        type(firstNameField, firstName);
        return this;
    }

    public SignupPage enterLastName(String lastName) {
        type(lastNameField, lastName);
        return this;
    }

    public SignupPage enterBirthDate(String birthDate) {
        type(birthDateField, birthDate);
        return this;
    }

    public SignupPage selectGender(String gender) {
        selectByVisibleText(genderField, gender);
        return this;
    }

    public SignupPage enterFbProfile(String fbProfile) {
        type(fbProfileField, fbProfile);
        return this;
    }

    public SignupPage enterContactNumber(String contactNumber) {
        type(contactNumberField, contactNumber);
        return this;
    }

    public SignupPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    public SignupPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    public SignupPage enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordField, confirmPassword);
        return this;
    }
    
    public String getErrorMessage() {
        return getText(passwordErrormessage);
    }

    public SignupPage clickSignup() {
        click(signupButton);
        return this;
    }

    // Complete signup flow
    public SignupPage signupAs(
            String firstName,
            String lastName,
            String birthDate,
            String gender,
            String fbProfile,
            String contactNumber,
            String email,
            String password,
            String confirmPassword
    ) {
        return navigateSignup()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterBirthDate(birthDate)
                .selectGender(gender)
                .enterFbProfile(fbProfile)
                .enterContactNumber(contactNumber)
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword(confirmPassword)
                .clickSignup();
    }
}
