@US05
Feature: Session Persistence
  As a registered user
  I want to keep my session active between application restarts
  So that I can access quickly without logging in again

  Scenario: Session persists after restarting application
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    
    When I close the AUT
    And I start the application
    Then I see the dashboard screen
