package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Common Methods
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        WebElement element = waitForClickable(locator);

        try {
            // Scroll into view before clicking
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            element.click();
        } catch (Exception e) {
            // Fallback: use JS click if normal click fails
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // 🔽 Select logic
    protected void selectByVisibleText(By locator, String text) {
        WebElement dropdown = waitForVisibility(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    protected void selectByValue(By locator, String value) {
        WebElement dropdown = waitForVisibility(locator);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    protected void selectByIndex(By locator, int index) {
        WebElement dropdown = waitForVisibility(locator);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }
}
