package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * BasePage is in src/main/java. 
 * It uses the driver passed by Test classes through its constructor.
 */
public class BasePage {
    
    protected WebDriver driver; // Local driver variable
    protected WebDriverWait wait;

    // Rule: Constructor receives the ThreadLocal driver from the Test
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Step: Directly use the 'driver' variable or 'wait' object in all methods

    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    
    public void typetext(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear(); 
        element.sendKeys(text);
    }
    
    public String getElementText(By locator) {
        return waitForVisible(locator).getText();
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return waitForVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}