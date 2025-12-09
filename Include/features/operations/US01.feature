@US01
Feature: Entry Options Display
  As a user
  I want to see the options "Fazer Login" and "Jogar como Convidado"
  So that I can escolher rapidamente o modo de entrada

  Scenario: Home screen shows login and guest buttons
    Given I have the device ready
    When I start the application
    Then I see the entry buttons for login and guest
    And I close the AUT

  Scenario: Login button navigates to login screen
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    Then I see the login screen
    And I close the AUT

  Scenario: Guest button navigates to practice mode
    Given I have the device ready
    When I start the application
    And I tap the "Jogar como Convidado" button
    Then I see the practice mode screen
    And I close the AUT

