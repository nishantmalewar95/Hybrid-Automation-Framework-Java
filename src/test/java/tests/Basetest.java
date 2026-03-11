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

public class Basetest {

    // Initialize Logger
    protected static final Logger logger = LogManager.getLogger(Basetest.class);

    // ThreadLocal driver for parallel execution
    protected static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    // Helper method to access current thread's driver
    public static WebDriver getDriver() {
        return tdriver.get();
    }

    @BeforeMethod
    public void setUp() {
        logger.info("Setting up WebDriver for Thread ID: " + Thread.currentThread().getId());
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        
        // Setting the driver instance
        tdriver.set(new ChromeDriver(options));
        
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        
        logger.info("Browser launched and navigated to URL on Thread: " + Thread.currentThread().getId());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Step 1: Handle failure and capture screenshot
        if (ITestResult.FAILURE == result.getStatus()) {
            logger.error("Test Failed: " + result.getName() + ". Taking screenshot...");
            captureScreenshots(result.getName() + "_failed");
        }

        // Step 2: Cleanup and remove thread reference
        if (getDriver() != null) {
            getDriver().quit();
            tdriver.remove(); // Essential to prevent memory leaks in parallel runs
            logger.info("WebDriver closed and ThreadLocal removed for Thread: " + Thread.currentThread().getId());
        }
    }

    /**
     * Captures screenshot and saves it to the /Screenshots folder.
     */
    public void captureScreenshots(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) getDriver();
            File src = ts.getScreenshotAs(OutputType.FILE);
            
            // Ensuring the directory exists
            String screenshotDir = System.getProperty("user.dir") + "/Screenshots/";
            File folder = new File(screenshotDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String path = screenshotDir + testName + "_" + System.currentTimeMillis() + ".png";
            File destination = new File(path);
            
            FileUtils.copyFile(src, destination);
            logger.info("Screenshot saved successfully at: " + path);
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
        }
    }

    public void performLogin(String userType) {
        logger.info("Performing login as: " + userType);
        pages.LoginPage loginPage = new pages.LoginPage(getDriver());
        loginPage.loginAs(userType);
    }
}