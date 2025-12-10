@US26
Feature: Apply Avatar
  As a player
  I want to select a purchased avatar
  So that I can use it on my profile

  Scenario: User equips a purchased avatar
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Loja" button
    Then I should see the shop screen
    When I select an available avatar to equip
    Then I should see the avatar marked as equipped
    And I close the AUT
