Feature: Product Page Price Validation

  Background:
  Given user is logged in as "standard_user"
  And user should see the product page
  
  @Regression @PriceCheck
  Scenario Outline: Verify product price is displayed correctly for various items
   Then the price for product "<product_name>" should be "<expected_price>"
   
   Examples:
      | product_name             | expected_price |
      | Sauce Labs Backpack      | $29.99         |
      | Sauce Labs Bolt T-Shirt  | $15.99         |
      | Sauce Labs Onesie        | $7.99          |