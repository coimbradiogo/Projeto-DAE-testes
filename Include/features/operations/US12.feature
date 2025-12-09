@US12
Feature: Results Screen
  As a player
  I want to see a results screen at the end of the match
  So that I can see the winner and total score

  Scenario: Game shows results after match ends
    Given I have the device ready
    When I start the application
    And I tap the "Jogar como Convidado" button
    And I tap the "JOGAR PARTIDA" button
    And I play the match until the end
    Then I should see the results screen with winner and score
    And I close the AUT
