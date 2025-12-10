@US31
Feature: Persistent Data Storage
  As a developer
  I want to ensure data is saved in the backend
  So that I can ensure persistence and consistency

  Scenario: Coin balance updates immediately after purchase
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    When I tap the "Loja" button
    Then I should see the shop screen
    
    # Passo 1: Ler saldo antes
    And I check the initial coin balance
    
    # Passo 2: Comprar item (Reutiliza passo da US27/US25)
    When I select a deck design to buy or equip
    
    # Passo 3: Verificar que o saldo diminuiu
    Then The coin balance should be lower than before
    And I close the AUT
