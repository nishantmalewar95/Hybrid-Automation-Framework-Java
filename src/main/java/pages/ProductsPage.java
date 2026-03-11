/*
 * ProductsPage (Inventory Page) represents the page where all products are listed 
 * after a successful login.
 */

package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * POM class for the Products inventory page after successful login.
 * Manages product listings, adding items to cart, and navigation to cart.
 */

public class ProductsPage extends BasePage{

    // constructor :Pass the driver to the constructor of the base page
    public ProductsPage(WebDriver driver) {
        super(driver);    
    }
    
    // Cart Icon / Cart Badge locator
    private final By cartIcon = By.cssSelector("a.shopping_cart_link");
    
    // Cart Badge locator (jo count dikhata hai)
    private final By shoppingCartBadge = By.cssSelector("a.shopping_cart_link span.shopping_cart_badge");
    
    
    // Methods: Elements par Actions
    
    /**
     * Clicks the 'Add to cart' button for the specific product by name.
     */
    public void addProductByName(String name) {
        
    	// Dynamic XPath creation
    	String buttonXPath = String.format(
    	        "//div[contains(@class, 'inventory_item_name') and contains(text(), '%s')]/ancestor::div[@class='inventory_item_description']//button[contains(@data-test, 'add-to-cart')]", 
    	        name);
    	
    	//Use the clickElement of BasePage which will wait first and then click
    	clickElement(By.xpath(buttonXPath));
    	System.out.println(name+" added to cart successfully.");
        }
    
    /**
     * Navigates to the Cart page using BasePage utility.
     */
    public void goToCart() {
        clickElement(cartIcon);
        System.out.println("Navigated to the Cart page.");
    }
    
    /**
     * Returns the badge count. Instead of Thread.sleep, we will update the logic.  
     */
    
    
    public String getCartBadgeCount() {
        // Step: Pehle check karo badge screen par hai ya nahi (Wait included in isElementDisplayed)
        if (isElementDisplayed(shoppingCartBadge)) {
            return getElementText(shoppingCartBadge);
        } else {
            // Agar badge nahi dikh raha, iska matlab cart empty hai
            return "0";
        }
        
      }
    
    public List<String> getProductsStartingWithSauce(){
    	// 1. Take the list of Saare Product Elements
    	List<WebElement> productElements =driver.findElements(By.className("inventory_item_name"));
    	
    	//2. use java 8 stream
    	return productElements.stream().map(WebElement::getText) //change webelement into string
    			.filter(name-> name.startsWith("Sauce"))         //only take this which is start from "sauce" word
    			.collect(Collectors.toList());                   //save result in list
    	
    			
    }
      
}