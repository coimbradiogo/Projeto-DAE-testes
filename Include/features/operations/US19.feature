@US19
Feature: Match History Overview
  As a registered user
  I want to consult all matches played
  So that I can review my performance

  Scenario: User views match history details
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Histórico de Partidas" button
    Then I should see the match history screen
    And I should see match records with result and coins
    And I close the AUT