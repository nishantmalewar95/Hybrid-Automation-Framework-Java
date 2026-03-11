Feature: Shopping Cart Functionality

As a user of SauceDemo
I want to manage my shopping cart 
So that I can verify y selected items before purchase

 Background: 
  Given user is logged in as "standard_user"
  And user should see the product page
  
@Smoke @Cart

 Scenario Outline: Add various products to cart and verify badge count
  When user adds "<product_name>" to the cart
  Then the cart badge should show "<expected_count>"
  
Examples:
 | product_name            | expected_count |
 | Sauce Labs Backpack     | 1              |
 | Sauce Labs Bike Light   | 2              |
 | Sauce Labs Bolt T-Shirt | 3              |
 
@Regression @Cart
Scenario: Remove product from cart and verify badge updates
 When user adds "Sauce Labs Backpack" to the cart 
 And user navigates to the cart page
 And user removes "Sauce Labs Backpack" from the cart
 Then the cart badge should be empty or show "0"