@US27
Feature: Persist Custom Choices
  As a player
  I want my customizations to be saved
  So that I can keep my preferences between sessions
#TER EM ATENCAO QUE TEMOS DE TERMINAR SESSAO ASSIM QUE REABRE
  Scenario: Equipped item persists after closing and reopening app
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    
    # Ir à Loja e equipar
    When I tap the "Loja" button
    Then I should see the shop screen
    When I select a deck design to buy or equip
    Then I should see the deck marked as equipped
    
    # Fechar e Reabrir a App
    When I close and reopen the application
    
    # Login novamente
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    
    # Voltar à Loja e validar persistência
    When I tap the "Loja" button
    Then I should see the shop screen
    And I should see the deck marked as equipped
    And I close the AUT
