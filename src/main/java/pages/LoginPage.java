/*
 * LoginPage represents the login page of the Sauce Demo application.
 * Contains methods for user login and logout functionality.
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    
    // Constructor receives driver and passes it to BasePage
    public LoginPage(WebDriver driver) {
        super(driver); 
    }
    
    // Locators - Using private final as a best practice
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By errorMessageBy = By.cssSelector("div.error-message-container h3");
    
    /**
     * Enters credentials and clicks the login button using BasePage utilities.
     */
    public void login(String username, String password) {
        typetext(usernameField, username);
        typetext(passwordField, password);
        clickElement(loginButton);
    }
    
    /**
     * Fetches the error message text after validation.
     */
    public String getErrorMessage() {
        return getElementText(errorMessageBy);
    }
    
    /**
     * Helper method to login based on user profile type.
     */
    public void loginAs(String userType) {
        switch (userType.toLowerCase()) {
            case "standard_user":
                login("standard_user", "secret_sauce");
                break;
            case "locked_out_user":
                login("locked_out_user", "secret_sauce");
                break;
            case "problem_user":
                login("problem_user", "secret_sauce");
                break;
            default:
                System.err.println("Invalid user type provided: " + userType);
        }
    }
    
    /**
     * Logs out the user from the application using the side menu.
     */
    public void logout() {
        try {
            clickElement(menuButton);
            clickElement(logoutLink);
            // Verify logout by waiting for the login button to be visible again
            waitForVisible(loginButton);
        } catch (Exception e) {
            System.err.println("Logout failed: " + e.getMessage());
        }
    }
}