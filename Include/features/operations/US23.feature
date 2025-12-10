@US23
Feature: Shop Access
  As a registered user
  I want to access the customization shop
  So that I can buy cosmetic items

  Scenario: User views available items in the shop
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Loja" button
    Then I should see the shop screen
    And I should see a list of items with prices
    And I close the AUT
