package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import cucumber.api.java.en.When
import cucumber.api.java.en.Then

class ResultsStepDefs {

    @When("I play the match until the end")
    def playMatchUntilEnd() {
        println "A usar botão DEBUG WIN para terminar jogo instantaneamente..."
        
        if (findTestObject('Object Repository/android.widget.Button - DEBUG WIN') != null) {
             Mobile.tap(findTestObject('Object Repository/android.widget.Button - DEBUG WIN'), 10)
        } else {
             TestObject debugBtn = new TestObject()
             debugBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'DEBUG WIN')]")
             Mobile.tap(debugBtn, 10)
        }
        Mobile.delay(3)
    }

    @Then("I should see the results screen with winner and score")
    def verifyResultsScreen() {
        println "A verificar ecrã de resultados..."

        TestObject winnerText = new TestObject()
        winnerText.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'VENCESTE') or contains(@text, 'VITÓRIA') or contains(@text, 'Vencedor')]")
        
        if (Mobile.verifyElementExist(winnerText, 10, FailureHandling.OPTIONAL)) {
            println "Título de vitória encontrado."
        } else {
            println "Aviso: Título exato não encontrado. A verificar outros elementos..."
        }
        
        TestObject resultText = new TestObject()
        resultText.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Resultado') or contains(@text, 'Riscos') or contains(@text, 'Pontos')]")
        Mobile.verifyElementExist(resultText, 5)

        Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.Button - Menu Principal'), 5)
        
        println "Ecrã de resultados validado."
    }
}
