package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import cucumber.api.java.en.And

class SimpleDataStepDefs {

    static int balanceBefore = 0

    @And("I check the initial coin balance")
    def checkInitialBalance() {
        Mobile.delay(2)
        TestObject balanceLabel = new TestObject()
        // Procura o texto do saldo (ex: "Saldo: 100")
        balanceLabel.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Saldo:')]")
        
        if (Mobile.verifyElementExist(balanceLabel, 5, FailureHandling.OPTIONAL)) {
            String text = Mobile.getText(balanceLabel, 0)
            // Extrai apenas os dígitos
            String numText = text.replaceAll("[^0-9]", "")
            if (numText.length() > 0) {
                balanceBefore = Integer.parseInt(numText)
                println "Saldo Inicial: " + balanceBefore
            }
        } else {
            KeywordUtil.markWarning("Não consegui ler o saldo inicial.")
        }
    }

    @Then("The coin balance should be lower than before")
    def verifyBalanceDropped() {
        Mobile.delay(3) // Espera atualização da UI após compra
        
        TestObject balanceLabel = new TestObject()
        balanceLabel.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Saldo:')]")
        
        if (Mobile.verifyElementExist(balanceLabel, 5, FailureHandling.OPTIONAL)) {
            String text = Mobile.getText(balanceLabel, 0)
            String numText = text.replaceAll("[^0-9]", "")
            
            if (numText.length() > 0) {
                int balanceAfter = Integer.parseInt(numText)
                println "Saldo Final: " + balanceAfter
                
                if (balanceAfter < balanceBefore) {
                    println "SUCESSO: O saldo diminuiu (" + balanceBefore + " -> " + balanceAfter + ")."
                } else {
                    KeywordUtil.markFailed("FALHA: O saldo não mudou (" + balanceBefore + " -> " + balanceAfter + "). A compra falhou ou saldo não atualizou.")
                }
            }
        } else {
            KeywordUtil.markFailed("Não consegui ler o saldo final.")
        }
    }
}
