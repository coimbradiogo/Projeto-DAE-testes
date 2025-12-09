@US17
Feature: Insufficient Coins Warning
  As a player
  I want to be alerted if I don't have enough coins
  So that I can understand why I cannot play

  Scenario: User sees warning when trying to play without enough coins
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with empty balance account
    Then I see the dashboard screenn
    When I tap the "JOGAR PARTIDA" button
    Then I should see an insufficient balance warning
    And I close the AUT
