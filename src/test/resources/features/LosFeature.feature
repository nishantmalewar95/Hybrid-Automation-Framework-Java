
Feature: LOS loan Origination

 Scenario Outline : Create loan application
    Given User logged in with "<customerID>"
    When Creates loan "<type>" amount "<amount>"
    Then Application status should be "<Status>"

    Examples: 
      | customerId | type     | amount | status   |
      | cust001    | Home     | 5L     | APPROVED |
      | cust002    | Personal | 2L     | PENDING  |