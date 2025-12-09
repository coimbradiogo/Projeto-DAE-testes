@US14
Feature: Entry Fee for Official Matches
  As a registered user
  I want to pay an entry fee before playing
  So that I can participate in official matches

  Scenario: Coins are deducted automatically when starting a match
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    And I note my initial coin balance
    When I tap the "JOGAR PARTIDA" button
    Then I should see the game board
    When I press the back button to return to dashboard
    Then I should see my coin balance decreased by the entry fee
    And I close the AUT
