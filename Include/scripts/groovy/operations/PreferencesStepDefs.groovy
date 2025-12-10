package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import org.openqa.selenium.WebElement

class PreferencesStepDefs {

    @When('I toggle the {string} switch')
    def toggleSwitch(String type) {
        Mobile.delay(1)
        println "A alterar switch: " + type
        
        // Estratégia: Encontrar o texto (ex: "Notificações de Ranking") e clicar no switch que está "ao lado" ou "dentro do mesmo grupo".
        // O XPath genérico para switch é //android.widget.Switch
        
        // Se a app usar texto descritivo, podemos tentar clicar no switch associado.
        // Vamos tentar encontrar o switch específico.
        
        String xpathQuery = ""
        if (type == "Ranking") {
            // Procura um Switch que esteja "perto" do texto Ranking, ou simplesmente o 1º Switch do ecrã
            // Assumindo que Ranking é o primeiro (índice 1 no XPath base-1)
            xpathQuery = "(//android.widget.Switch)[1]"
        } else {
            // Assumindo que Loja é o segundo (índice 2)
            xpathQuery = "(//android.widget.Switch)[2]"
        }
        
        TestObject sw = new TestObject()
        sw.addProperty("xpath", ConditionType.EQUALS, xpathQuery)
        
        if (Mobile.verifyElementExist(sw, 3, FailureHandling.OPTIONAL)) {
            Mobile.tap(sw, 0)
            println "Cliquei no switch."
        } else {
            KeywordUtil.markFailed("Switch para " + type + " não encontrado.")
        }
    }

    @Then('The {string} switch should be disabled')
    def verifyDisabled(String type) {
        verifySwitchState(type, "false") // "false" significa unchecked/desativado no Android
    }

    @Then('The {string} switch should be enabled')
    def verifyEnabled(String type) {
        verifySwitchState(type, "true") // "true" significa checked/ativado
    }

    def verifySwitchState(String type, String expectedState) {
        Mobile.delay(1)
        
        String xpathQuery = (type == "Ranking") ? "(//android.widget.Switch)[1]" : "(//android.widget.Switch)[2]"
        
        TestObject sw = new TestObject()
        sw.addProperty("xpath", ConditionType.EQUALS, xpathQuery)
        
        // Obtém o valor do atributo 'checked'
        String currentState = Mobile.getAttribute(sw, 'checked', 5)
        
        println "Estado atual do switch " + type + ": " + currentState
        
        if (currentState == expectedState) {
            println "Sucesso: Switch está no estado correto (" + expectedState + ")."
        } else {
            KeywordUtil.markFailed("Falha: O switch " + type + " devia estar " + expectedState + " mas está " + currentState)
        }
    }
}
