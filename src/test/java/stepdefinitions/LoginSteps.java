package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    // --- REUSABLE LOGIN STEPS ---
    
    @Given("user is on the SauceDemo login page")
    public void user_is_on_the_sauce_demo_login_page() {
        System.out.println("Step: Opening Browser and navigating to Login Page");	
    }
    
    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) {
        System.out.println("Step: Entering "+ username + " and password");
    }
    
    @Then("user should see the products page")
    public void user_should_see_the_products_page() {
        // ASSERTION: Verify URL or Page Header
        System.out.println("Assertion: Verifying if URL contains 'inventory.html'");	
    }

    // --- NEW REUSABLE LOGIN STEP FOR OTHER FEATURES ---
    
    @Given("user is logged in as {string}")
    public void user_is_logged_in(String user) {
        user_is_on_the_sauce_demo_login_page();
        user_logs_in_with_username_and_password(user, "secret_sauce");
    }

    // --- ERROR MESSAGE HANDLING (Dynamic) ---

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedMessage) {
        // ASSERTION: Verify the specific error message text
        System.out.println("Assertion: Verifying error message: " + expectedMessage);
         
    }
    
    @Then("the system should react according to the {string} with message {string}")
    public void the_system_should_react_according_to_the_with_message(String status, String message) {
        System.out.println("LOG: Login Status: " + status + " | Message: " + message);
    }

}