package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginTests extends Basetest {
    
    @DataProvider(name="sauceLoginData")
    public Object[][] getLoginData(){
        return new Object[][] {
            { "standard_user", "secret_sauce", "valid", "inventory.html" },  
            { "locked_out_user", "secret_sauce", "locked", "locked out" },
            { "problem_user", "secret_sauce", "valid", "inventory.html" },
            { "invalid_user", "wrong_pass", "error", "do not match" }
        };
    }
    
    @Test(dataProvider = "sauceLoginData")
    public void testMultipleLogins(String user, String pass, String type, String expectedPart) {
        logger.info("Running login test for user: " + user);
        
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(user, pass);
        
        if(type.equals("valid")) {
            Assert.assertTrue(getDriver().getCurrentUrl().contains(expectedPart), "Login failed for valid user: " + user);
            loginPage.logout(); 
        } 
        else {
            String actualError = loginPage.getErrorMessage(); 
            Assert.assertTrue(actualError.contains("Epic sadface"), "Error icon missing for user: " + user);
            Assert.assertTrue(actualError.contains(expectedPart), "Expected error text not found!");
        }
    }

    /**
     * Is method ko call karte waqt super keyword ka use karein 
     * ya pakka karein ki Basetest mein ye method 'public' ya 'protected' hai.
     */
    @Test
    public void testLoginWithHelper() {
        // Basetest ke method ko call kar rahe hain
        super.performLogin("standard_user");
        
        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory.html"), "Helper login failed!");
        new LoginPage(getDriver()).logout();
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("wrong_user", "wrong_password");
        
        String actualError = loginPage.getErrorMessage();
        
        Assert.assertTrue(actualError.contains("Epic sadface"), "Epic sadface icon missing!");
        Assert.assertTrue(actualError.contains("do not match"), "Invalid login error mismatch!"); 
    }
}