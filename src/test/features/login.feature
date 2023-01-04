Feature: Feature to test login functionality
  @login
  Scenario: Check login functionality
    Given User is on login page
    When User enters username and password
    And Click on login button
    Then user is navigated to the home page