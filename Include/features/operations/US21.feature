@US21
Feature: Global Leaderboard
  As a player
  I want to view the global leaderboard
  So that I can compare my performance with others

  Scenario: User views global rankings
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Classificações" button
    Then I should see the leaderboard screen
    And I should see a list of players with names and scores
    And I close the AUT
