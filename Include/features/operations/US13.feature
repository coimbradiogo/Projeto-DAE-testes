@US13
Feature: Display Current Balance
  As a registered user
  I want to see my current coin balance
  So that I can know how much I can spend

  Scenario: Balance is displayed on Dashboard
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    And I should see my coin balance displayed
    And I close the AUT
