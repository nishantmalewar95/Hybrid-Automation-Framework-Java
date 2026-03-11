Feature: Login Functionality for SauceDemo

 Background:
  Given user is on the SauceDemo login page
  
 @Smoke @Login
 Scenario Outline: Verify login behavior for different user types
   When user logs in with username "<username>" and password "<password>"
   Then the system should react according to the "<scenario_type>" with message "<message>"
  
  Examples:
  | username        | password     | scenario_type | message                                               |
  | standard_user   | secret_sauce | success       | inventory.html                                        |
  | locked_out_user | secret_sauce | locked        | Epic sadface: Sorry, this user has been locked out.   |
  | standard_user   | wrong_pass   | error         | Username and password do not match                    |
  
  