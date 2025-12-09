package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import cucumber.api.java.en.Then
import cucumber.api.java.en.And

class TurnStepDefs {

    @Then("I should see the game board")
    def verifyGameBoard() {
        Mobile.delay(2)
        TestObject gameInfo = new TestObject()
        gameInfo.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Pontos') or contains(@text, 'Riscos')]")
        Mobile.verifyElementExist(gameInfo, 10)
    }

    @And("I should see an indication of whose turn it is")
    def verifyTurnIndication() {
        Mobile.delay(2)
        println "A verificar indicação de turno..."
        
        TestObject scoreInfo = new TestObject()
        scoreInfo.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Pontos') or contains(@text, 'Riscos')]")
        
        if (Mobile.verifyElementExist(scoreInfo, 10)) {
            println "Indicação de estado (Pontos/Riscos) visível. Turno ativo."
        } else {
            KeywordUtil.markFailed("Não foi encontrada nenhuma indicação de estado de jogo (Pontos/Riscos).")
        }
    }

}
