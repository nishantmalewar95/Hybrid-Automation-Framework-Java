package tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.LoginPage; // LoginPage import karna zaroori hai

public class Basetest {

    protected static final Logger logger = LogManager.getLogger(Basetest.class);
    protected static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return tdriver.get();
    }

    @BeforeMethod
    public void setUp() {
        logger.info("Setting up WebDriver for Thread ID: " + Thread.currentThread().getId());
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        // --- GITHUB ACTIONS KE LIYE ZAROORI SETTINGS ---
        options.addArguments("--headless=new"); 
        options.addArguments("--no-sandbox"); 
        options.addArguments("--disable-dev-shm-usage"); 
        options.addArguments("--window-size=1920,1080"); 
        // ----------------------------------------------
        
        tdriver.set(new ChromeDriver(options));
        
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        
        logger.info("Browser launched (Headless) and navigated to URL");
    }

    // --- YE METHOD MISSING THA, ISSE ADD KIYA HAI ---
    public void performLogin(String userType) {
        logger.info("Performing auto-login for user type: " + userType);
        LoginPage loginPage = new LoginPage(getDriver());
        
        // standard_user ke liye password fix hai, baaki ke liye aap logic badal sakte hain
        loginPage.login(userType, "secret_sauce"); 
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            logger.error("Test Failed: " + result.getName() + ". Taking screenshot...");
            captureScreenshots(result.getName() + "_failed");
        }

        if (getDriver() != null) {
            getDriver().quit();
            tdriver.remove();
            logger.info("WebDriver closed for Thread: " + Thread.currentThread().getId());
        }
    }

    public void captureScreenshots(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) getDriver();
            File src = ts.getScreenshotAs(OutputType.FILE);
            String screenshotDir = System.getProperty("user.dir") + "/Screenshots/";
            File folder = new File(screenshotDir);
            if (!folder.exists()) { folder.mkdirs(); }

            String path = screenshotDir + testName + "_" + System.currentTimeMillis() + ".png";
            File destination = new File(path);
            FileUtils.copyFile(src, destination);
            logger.info("Screenshot saved at: " + path);
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
        }
    }
}