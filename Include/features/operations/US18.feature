@US18
Feature: Transaction History
  As a registered user
  I want to view my coin transaction history
  So that I can control my earnings and expenses

  Scenario: User views transaction history details
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Histórico de Transações" button
    Then I should see the history screen title
    And I should see transaction records with date and value
    And I close the AUT
