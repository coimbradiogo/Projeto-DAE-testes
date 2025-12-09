@US02
Feature: Login for Registered Users
  As a registered user
  I want to authenticate with email and password
  So that I can access my dashboard

  Scenario: Login with valid credentials
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    And I close the AUT

  Scenario: Login with invalid credentials
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with invalid credentials
    Then I see a login error message
    And I close the AUT
