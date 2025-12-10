@US27
Feature: Purchase and Apply Deck Designs
  As a player
  I want to buy and apply new deck designs
  So that I can customize the game interface

  Scenario: User purchases and equips a deck design
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Loja" button
    Then I should see the shop screen
    When I select a deck design to buy or equip
    Then I should see the deck marked as equipped
    And I close the AUT
