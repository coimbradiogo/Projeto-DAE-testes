@US24
Feature: Purchase Avatars
  As a player
  I want to buy new avatars
  So that I can customize my appearance

  Scenario: User buys an avatar successfully
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Loja" button
    Then I should see the shop screen
    When I attempt to buy an available avatar
    Then I should see the avatar unlocked
    And I should see my coin balance decreased
    And I close the AUT
