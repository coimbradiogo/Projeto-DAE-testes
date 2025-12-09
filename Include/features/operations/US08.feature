@US08
Feature: Gameplay Visual Feedback
  As a player
  I want to see visual indications of plays and turns
  So that I can easily track the game progress

  Scenario: Game shows whose turn it is
    Given I have the device ready
    When I start the application
    And I tap the "Jogar como Convidado" button
    And I tap the "JOGAR PARTIDA" button
    Then I should see the game board
    And I should see an indication of whose turn it is
    And I close the AUT
