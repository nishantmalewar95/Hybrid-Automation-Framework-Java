package tests;

import pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test; 
import org.testng.Assert;

public class LoginTests extends Basetest {
    
    /**
     * DataProvider to supply multiple sets of credentials for testing.
     */
    @DataProvider(name="sauceLoginData")
    public Object[][] getLoginData(){
        return new Object[][] {
            { "standard_user", "secret_sauce", "valid", "inventory.html" },  
            { "locked_out_user", "secret_sauce", "locked", "locked out" },
            { "problem_user", "secret_sauce", "valid", "inventory.html" },
            { "invalid_user", "wrong_pass", "error", "do not match" }
        };
    }
    
    /**
     * Test Case to verify multiple login scenarios using DataProvider.
     * Uses getDriver() for Thread-Safe parallel execution.
     */
    @Test(dataProvider = "sauceLoginData")
    public void testMultipleLogins(String user, String pass, String type, String expectedPart) {
        logger.info("Running login test for user: " + user + " on Thread: " + Thread.currentThread().getId());
        
        // Pass the thread-specific driver to LoginPage
        LoginPage loginPage = new LoginPage(getDriver());
        
        // Execute login action
        loginPage.login(user, pass);
        
        // Validation logic using getDriver()
        if(type.equals("valid")) {
            Assert.assertTrue(getDriver().getCurrentUrl().contains(expectedPart), "Login failed for valid user: " + user);
            loginPage.logout(); 
        } 
        else {
            // Updated method name from getErrorMessaage to getErrorMessage
            String actualError = loginPage.getErrorMessage(); 
            Assert.assertTrue(actualError.contains("Epic sadface"), "Error icon missing for user: " + user);
            Assert.assertTrue(actualError.contains(expectedPart), "Expected error text not found!");
        }
    }

    /**
     * Testing the helper method 'performLogin' inherited from Basetest.
     */
    @Test
    public void testLoginWithHelper() {
        performLogin("standard_user");
        
        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory.html"), "Helper login failed!");
        new LoginPage(getDriver()).logout();
    }

    /**
     * Individual test case for specific negative scenario validation.
     */
    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("wrong_user", "wrong_password");
        
        String actualError = loginPage.getErrorMessage();
        
        Assert.assertTrue(actualError.contains("Epic sadface"), "Epic sadface icon missing!");
        Assert.assertTrue(actualError.contains("do not match"), "Invalid login error mismatch!"); 
    }
}