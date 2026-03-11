package com.los.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LosLoginPage;         
import pages.LoanDashboardPage;   
import tests.Basetest;           
import api.utils.ApiUtils;       
import models.LoanData;          

public class LoanOriginationTest extends Basetest { 

    @Test
    public void testLosCompleteFlow() {
        // --- API PART ---
        logger.info("Starting API Flow for Loan Origination");
        
        // Step 1: Customer Login API
        String customerData = "{\"username\":\"customer1\", \"password\":\"pass123\"}";
        
        Response loginResp = ApiUtils.getRequestSpec()
                .baseUri("https://your-api-url.com")
                .body(customerData)
                .post("/los/customer/login");
        
        String token = loginResp.jsonPath().getString("token");
        ApiUtils.verifyStatus(loginResp, 200);
        logger.info("API Login successful, Token generated.");
        
        // Step 2: Create Loan Application
        LoanData loan = new LoanData("Home Loan", 5000000, 240);
        Response loanResp = ApiUtils.postWithToken("/los/applications", loan, token);
        ApiUtils.verifyStatus(loanResp, 201);
        logger.info("Loan Application created via API.");
        
        // --- UI PART (Selenium) ---
        // Change: Use getDriver() instead of 'driver'
        logger.info("Starting UI validation for Loan ID: HL-001");
        getDriver().get("https://your-los-system-url.com");
        
        // Change: Pass getDriver() to Page Objects
        LosLoginPage loginPage = new LosLoginPage(getDriver());
        loginPage.login("customer1", "pass123");
        
        LoanDashboardPage dashboard = new LoanDashboardPage(getDriver());
        
        boolean isFound = dashboard.isLoanVisible("HL-001");
        Assert.assertTrue(isFound, "Loan ID HL-001 dashboard par nahi mila!");
        
        logger.info("Test Passed: Loan application is visible on UI.");
    }
}