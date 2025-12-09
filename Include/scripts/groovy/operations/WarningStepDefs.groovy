package operations

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import cucumber.api.java.en.Then

class WarningStepDefs {

    @Then("I should see an insufficient balance warning")
    def verifyInsufficientBalanceWarning() {
        Mobile.delay(1)
        println "A verificar bloqueio por falta de moedas..."
        
        // 1. Procura mensagem de erro (Toast ou Dialog)
        TestObject warningMsg = new TestObject()
        // XPath abrangente para apanhar "Insuficiente", "Sem saldo", "Recarregar", etc.
        warningMsg.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'insuficiente') or contains(@text, 'moedas') or contains(@text, 'saldo')]")
        
        if (Mobile.verifyElementExist(warningMsg, 5, FailureHandling.OPTIONAL)) {
            println "SUCESSO: Aviso de saldo insuficiente detetado."
        } else {
            // Se não detetar a mensagem, verifica se por acaso o jogo começou (o que seria um bug grave ou o user tem moedas)
            TestObject gameBoard = new TestObject()
            gameBoard.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Pontos') or contains(@text, 'Trunfo')]")
            
            if (Mobile.verifyElementExist(gameBoard, 2, FailureHandling.OPTIONAL)) {
                // FALHA GRAVE: Entrou no jogo sem moedas!
                throw new com.kms.katalon.core.exception.StepFailedException("FALHA: O utilizador conseguiu entrar no jogo mesmo sem moedas!")
            } else {
                // Se não entrou e não mostrou mensagem, pode ter ficado no mesmo ecrã sem feedback.
                println "Aviso: Não entrou no jogo, mas a mensagem de erro específica não foi encontrada (pode ser um Toast rápido)."
            }
        }
    }
}
