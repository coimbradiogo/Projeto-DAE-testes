@US29
Feature: New Item Notification
  As a registered user
  I want to receive notifications when buying items in the shop
  So that I can discover new items quickly

  Scenario: User receives push notification about shop purchase
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Definições" button
    Then I should see the settings screen
    When I tap the "Testar Notificação Loja" button
    Then I should see a push notification about a new item
    And I close the AUT
