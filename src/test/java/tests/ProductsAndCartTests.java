package tests;

import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class ProductsAndCartTests extends Basetest {

    private final String standardUser = "standard_user";
    private final String secretSauce = "secret_sauce";

    /**
     * Test 1: Logs in, adds two products, and verifies both are present in the cart.
     */
    @Test
    public void testAddTwoProductsAndVerifyCart() {
        String product1 = "Sauce Labs Backpack";
        String product2 = "Sauce Labs Bike Light";
        
        // 1. Login - Passing getDriver()
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(standardUser, secretSauce);  
        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory.html"), "ERROR: Login failed!");

        // 2. Add Products
        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.addProductByName(product1); 
        productsPage.addProductByName(product2); 
        
        // 3. Verify Cart Badge Count
        Assert.assertEquals(productsPage.getCartBadgeCount(), "2", "ERROR: Cart count badge mismatch."); 
        
        // 4. Navigation
        productsPage.goToCart(); 
        CartPage cartPage = new CartPage(getDriver());
        
        int finalCount = cartPage.getNumberOfItemInCart();
        Assert.assertEquals(finalCount, 2, "ERROR: Total items in cart list is not 2"); 
        
        Assert.assertTrue(cartPage.isProductInCart(product1), product1 + " is missing from the cart.");
        Assert.assertTrue(cartPage.isProductInCart(product2), product2 + " is missing from the cart.");
        
        logger.info("Test PASSED: Two products added and verified.");
    }
    
    /**
     * Test 2: Adds a product, removes it, and verifies the cart is empty.
     */
    @Test
    public void testAddAndRemoveSingleProduct() {
        String product = "Sauce Labs Fleece Jacket";
        
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(standardUser, secretSauce);  
        
        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.addProductByName(product);
        
        productsPage.goToCart();
        CartPage cartPage = new CartPage(getDriver());
        cartPage.removeProductByName(product); 
        
        int count = cartPage.getNumberOfItemInCart();
        Assert.assertEquals(count, 0, "Item count should be 0 after removal.");
        
        logger.info("Test 2 PASSED: Product added and removed.");
    }
    
    /**
     * Test 3: Adds a product, performs logout, and verifies redirection.
     */
    @Test
    public void testAddProductAndLogout() throws InterruptedException {
        String product = "Sauce Labs Bolt T-Shirt";
        LoginPage loginPage = new LoginPage(getDriver());
        ProductsPage productsPage = new ProductsPage(getDriver());

        loginPage.login(standardUser, secretSauce);  
        productsPage.addProductByName(product); 
        
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1", "ERROR: Product add nahi hua!");
        
        loginPage.logout(); 
        
        // Final Expectation: Verify URL using getDriver()
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.equals("https://www.saucedemo.com/") || currentUrl.contains("index.html"), 
                "Logout failed. Actual URL: " + currentUrl); 
        
        logger.info("Test 3 PASSED: User logged out successfully.");
    }
    
    /**
     * Test 4: Verify products filtered by Java 8 Streams.
     */
    @Test
    public void verifyProductsUsingStream() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(standardUser, secretSauce);
        
        ProductsPage productsPage = new ProductsPage(getDriver());
        
        java.util.List<String> sauceProducts = productsPage.getProductsStartingWithSauce();
       
        logger.info("Products count: " + sauceProducts.size());
        sauceProducts.forEach(name -> logger.info("Stream Result: " + name));
        
        Assert.assertTrue(sauceProducts.size() > 0, "No products found starting with 'Sauce'!");
    }
}