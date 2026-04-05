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
        // Agar aap GitHub par chala rahe hain, toh headless true hona chahiye
        options.addArguments("--headless=new"); // Cloud pe bina screen ke chalne ke liye
        options.addArguments("--no-sandbox"); // Security layer bypass karne ke liye (Linux mandatory)
        options.addArguments("--disable-dev-shm-usage"); // Memory issues se bachne ke liye
        options.addArguments("--window-size=1920,1080"); // Standard screen size fix karna
        // ----------------------------------------------
        
        tdriver.set(new ChromeDriver(options));
        
        WebDriver driver = getDriver();
        // Headless mode mein maximize() kabhi-kabhi kaam nahi karta, isliye window-size upar de diya hai
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        
        logger.info("Browser launched (Headless) and navigated to URL");
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