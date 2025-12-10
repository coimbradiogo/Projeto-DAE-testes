@US22
Feature: Personal Records
  As a player
  I want to view my personal records
  So that I can track my evolution

  Scenario: User views personal statistics on Leaderboard screen
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Classificações" button
    Then I should see the leaderboard screen
    And I should see my personal performance header
    And I should see my total wins and accumulated coins
    And I close the AUT
