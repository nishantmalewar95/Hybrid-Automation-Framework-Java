package tests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WaitPractice {

    public static void main(String[] args) {
        // Driver Initialize
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Explicit Wait (10 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            driver.get("https://www.saucedemo.com/");
            
            // --- Scenario 1: Successful Login ---
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            
            wait.until(ExpectedConditions.urlContains("inventory.html"));
            System.out.println("Scenario 1 Passed: Login successful, URL matched!");
        
            // --- Scenario 2: Add to Cart ---
            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
            addToCartBtn.click();
            System.out.println("Scenario 2 Passed: Add to Cart button clicked.");
          
            // --- Scenario 3: Error Message Validation ---
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys("wrong_user");
            driver.findElement(By.id("password")).sendKeys("wrong_pass"); // Password daalna zaroori hai "match" message ke liye
            driver.findElement(By.id("login-button")).click();
          
            // Wait for error message
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
          
            String actualError = errorMsg.getText();
            System.out.println("Actual Error Found: " + actualError); 
          
            // Ab message "do not match" contains karega kyunki humne wrong password daala hai
            Assert.assertTrue(actualError.contains("do not match"), "Error message mismatch! Actual was: " + actualError);     
          
            System.out.println("Scenario 3 Passed: Error message verified.");

        } catch (Exception e) {
            System.out.println("Test Failed due to: " + e.getMessage());
        } finally {
            // Browser band karna zaroori hai
            driver.quit();
        }
    }
}