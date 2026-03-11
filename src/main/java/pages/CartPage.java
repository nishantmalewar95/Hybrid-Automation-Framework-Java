/*
 * CartPage represents the shopping cart view where added items are displayed.
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

	// Constructor: It is necessary to reach Driver or BasePage
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    // Locators
    private final By cartItemContainer = By.cssSelector("div.cart_item");
    private final By checkoutButtonBy = By.id("checkout");
    
    
    /**
     * Returns the total number of items currently displayed in the cart.
     */
    public int getNumberOfItemInCart() {
    	if(isElementDisplayed(cartItemContainer)) {
    		return driver.findElements(cartItemContainer).size();
    		}
    	return 0;
    	}
    
    /**
     * Checks if a specific product (by name) is present in the cart.
     */
 // Method to verify if a product is in the cart
    public boolean isProductInCart(String productName) {
        String xpath = "//div[@class='inventory_item_name' and text()='" + productName + "']";
        return isElementDisplayed(By.xpath(xpath));
    }
  

    
    /**
     * Removes a product from the cart by its name.
     * Reuses clickElement utility from BasePage.
     */
    public void removeProductByName(String productName) {
        String removeButtonXpath = String.format(
            "//div[text()='%s']/ancestor::div[@class='cart_item']//button[text()='Remove']", 
            productName);
        
        // BasePage ka method use kar rahe hain jo internally wait + click karta hai
        clickElement(By.xpath(removeButtonXpath));
        System.out.println(productName + " removed from cart successfully.");
    }

    /**
     * Clicks the Checkout button to proceed.
     * Reuses clickElement utility from BasePage.
     */
    public void clickCheckout() {
        clickElement(checkoutButtonBy);
        System.out.println("Clicked Checkout");
    }
}