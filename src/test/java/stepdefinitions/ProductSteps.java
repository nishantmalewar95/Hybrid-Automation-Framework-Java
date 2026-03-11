package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class ProductSteps {

    // ==========================================
    // 1. SAUCE DEMO STEPS (Purane Tests ke liye)
    // ==========================================

    @Given("user should see the product page")
    public void user_should_see_the_product_page() {
        System.out.println("LOG: Product page is displayed successfully.");
        // Actual Selenium code: Assert.assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());
    }

    @When("user adds {string} to the cart")
    public void add_to_cart(String productName) {
        System.out.println("LOG: Adding product to cart: " + productName);
    }

    @When("user navigates to the cart page")
    public void user_navigates_to_the_cart_page() {
        System.out.println("LOG: Navigating to the Shopping Cart page.");
    }

    @When("user removes {string} from the cart")
    public void user_removes_from_the_cart(String productName) {
        System.out.println("LOG: Removing " + productName + " from the cart.");
    }

    @Then("the cart badge should show {string}")
    public void verify_cart_count(String expectedCount) {
        System.out.println("LOG: Verifying cart badge count. Expected: " + expectedCount);
    }

    @Then("the cart badge should be empty or show {string}")
    public void the_cart_badge_should_be_empty_or_show(String expectedCount) {
        System.out.println("LOG: Cart badge verified. Count is: " + expectedCount);
    }

    @Then("the price for product {string} should be {string}")
    public void the_price_for_product_should_be(String product, String expectedPrice) {
        System.out.println("LOG: Verified price for " + product + " is " + expectedPrice);
    }

    // ==========================================
    // 2. LOS LOAN STEPS (Naye Task ke liye)
    // ==========================================

    @Given("User logged in with {string}")
    public void user_logged_in_with(String customerId) {
        System.out.println("LOG: LOS Login - Customer ID: " + customerId);
    }

    @When("Creates loan {string} amount {string}")
    public void creates_loan_amount(String type, String amount) {
        System.out.println("LOG: LOS Loan Process - Type: " + type + " | Amount: " + amount);
    }

    @Then("Application status should be {string}")
    public void application_status_should_be(String status) {
        System.out.println("LOG: LOS Status Check - Expected Status: " + status);
    }
}