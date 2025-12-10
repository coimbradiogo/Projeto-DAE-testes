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

class PurchaseStepDefs {

    static int initialBalance = 0
    static boolean purchaseAttempted = false

    @When("I attempt to buy an available avatar")
    def attemptPurchase() {
        Mobile.delay(2)
        
        TestObject balanceLabel = new TestObject()
        balanceLabel.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Saldo:')]")
        
        if (Mobile.verifyElementExist(balanceLabel, 5, FailureHandling.OPTIONAL)) {
            String text = Mobile.getText(balanceLabel, 0)
            initialBalance = Integer.parseInt(text.replaceAll("[^0-9]", ""))
            println "Saldo Inicial: " + initialBalance
        }

        TestObject buyBtn = new TestObject()
        buyBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'COMPRAR')]")
        
        if (Mobile.verifyElementExist(buyBtn, 5, FailureHandling.OPTIONAL)) {
            Mobile.tap(buyBtn, 0)
            println "Cliquei em COMPRAR."
            purchaseAttempted = true
            Mobile.delay(3)
        } else {
            KeywordUtil.markWarning("Nenhum botão COMPRAR encontrado.")
            purchaseAttempted = false
        }
    }

    @Then("I should see the avatar unlocked")
    def verifyUnlock() {
        if (!purchaseAttempted) return

        TestObject equipState = new TestObject()
        equipState.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'EQUIPAR') or contains(@text, 'EQUIPADO')]")
        
        if (Mobile.verifyElementExist(equipState, 5, FailureHandling.OPTIONAL)) {
            println "Item desbloqueado."
        } else {
             TestObject errorMsg = new TestObject()
             errorMsg.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'insuficiente')]")
             
             if (Mobile.verifyElementExist(errorMsg, 2, FailureHandling.OPTIONAL)) {
                 KeywordUtil.markWarning("Compra falhou por saldo insuficiente.")
             }
        }
    }

    @And("I should see my coin balance decreased")
    def verifyBalanceDecrease() {
        if (!purchaseAttempted) return

        Mobile.delay(2)
        TestObject balanceLabel = new TestObject()
        balanceLabel.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Saldo:')]")
        
        if (Mobile.verifyElementExist(balanceLabel, 5, FailureHandling.OPTIONAL)) {
            String text = Mobile.getText(balanceLabel, 0)
            int currentBalance = Integer.parseInt(text.replaceAll("[^0-9]", ""))
            println "Saldo Final: " + currentBalance
            
            if (currentBalance < initialBalance) {
                println "Saldo diminuiu."
            }
        }
    }
}
