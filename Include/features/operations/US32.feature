@US32
Feature: Notification Preferences
  As a registered user
  I want to enable or disable notifications in settings
  So that I can control what I receive

  Scenario: User toggles notification settings
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Definições" button
    Then I should see the settings screen
    
    # Testar Toggle do Ranking
    When I toggle the "Ranking" switch
    Then The "Ranking" switch should be disabled
    When I toggle the "Ranking" switch
    Then The "Ranking" switch should be enabled
    
    # Testar Toggle da Loja
    When I toggle the "Loja" switch
    Then The "Loja" switch should be disabled
    And I close the AUT
