@US29
Feature: Leader Change Notification
  As a registered user
  I want to receive notifications about leaderboard changes
  So that I can track important updates

  Scenario: User receives push notification about leaderboard changes
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Definições" button
    Then I should see the settings screen
    When I tap the "Testar Notificação Ranking" button
    Then I should see a push notification about a new leader
    And I close the AUT
