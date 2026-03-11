package com.saucedemo.reportings;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public static void createInstance() {

        String reportPath = System.getProperty("user.dir") + "/Reports/ExtentReport.html";

        File folder = new File(System.getProperty("user.dir") + "/Reports");
        if (!folder.exists()) {
            folder.mkdir();
        }

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("SauceDemo Automation Report");
        spark.config().setDocumentTitle("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }
}
