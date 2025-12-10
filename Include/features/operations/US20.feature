@US20
Feature: History Filters
  As a registered player
  I want to filter history by win or loss
  So that I can find specific matches

  Scenario: Filter match history by Wins and Losses
    Given I have the device ready
    When I start the application
    And I tap the "Fazer Login" button
    And I login with valid credentials
    Then I see the dashboard screen
    
    # Reutiliza o passo que já tens
    When I tap the "Histórico de Partidas" button
    Then I should see the match history screen
    
    # --- NOVOS PASSOS PARA OS FILTROS ---
    
    # Filtro Vitórias
    When I tap the "Vitórias" filter button
    Then I should only see won matches
    
    # Filtro Derrotas
    When I tap the "Derrotas" filter button
    Then I should not see any won matches
    
    # Voltar a Tudo
    When I tap the "Tudo" filter button
    Then I should see all matches
    And I close the AUT
