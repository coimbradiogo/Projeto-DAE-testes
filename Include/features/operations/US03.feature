@US03
Feature: Guest Quick Access
  As a guest user
  I want to access practice mode without registration
  So that I can try the game immediately

  Scenario: Enter practice mode as guest
    Given I have the device ready
    When I start the application
    And I tap the "Jogar como Convidado" button
    Then I see that I am in practice mode as "Convidado"
    And I close the AUT
