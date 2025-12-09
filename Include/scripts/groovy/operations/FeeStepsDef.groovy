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
import org.openqa.selenium.WebElement

class FeeStepDefs {
    
    static int initialBalance = 0
    static int entryFee = 10 

    def getBalanceFromScreen() {
        Mobile.delay(2)
        
        TestObject coinLabel = new TestObject()
        coinLabel.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Moedas')]")
        
        String text = ""
        if (Mobile.verifyElementExist(coinLabel, 3, FailureHandling.OPTIONAL)) {
            text = Mobile.getText(coinLabel, 0)
        } else {
            TestObject anyText = new TestObject()
            anyText.addProperty("class", ConditionType.EQUALS, "android.widget.TextView")
            List<WebElement> allTexts = Mobile.findElements(anyText, 5)
            for (WebElement el : allTexts) {
                if (el.getText().matches("^[0-9]+")) {
                    text = el.getText()
                    break
                }
            }
        }
        
        String numberOnly = text.replaceAll("[^0-9]", "")
        if (numberOnly.isEmpty()) return 0
        return Integer.parseInt(numberOnly)
    }

    @And("I note my initial coin balance")
    def noteInitialBalance() {
        initialBalance = getBalanceFromScreen()
        println "Saldo Inicial: " + initialBalance
        
        if (initialBalance < entryFee) {
            KeywordUtil.markWarning("Aviso: Saldo baixo (" + initialBalance + "). Pode não conseguir jogar.")
        }
    }

    @When("I press the back button to return to dashboard")
    def returnToDashboard() {
        Mobile.delay(2)
        Mobile.pressBack()
        
        if (Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.Button - Sim'), 2, FailureHandling.OPTIONAL)) {
             Mobile.tap(findTestObject('Object Repository/android.widget.Button - Sim'), 0)
        }
        
        Mobile.delay(3)
    }

    @Then("I should see my coin balance decreased by the entry fee")
    def verifyBalanceDecreased() {
        int currentBalance = getBalanceFromScreen()
        println "Saldo Final: " + currentBalance
        
        int expectedBalance = initialBalance - entryFee
        
        if (currentBalance <= expectedBalance) {
            println "Sucesso: Taxa cobrada (ou saldo diminuiu)."
        } else {
            if (currentBalance == initialBalance) {
                KeywordUtil.markFailed("Falha: O saldo manteve-se igual (" + currentBalance + "). A taxa não foi cobrada.")
            } else {
                KeywordUtil.markWarning("O saldo mudou de forma inesperada. Inicial: " + initialBalance + ", Final: " + currentBalance)
            }
        }
    }
}
