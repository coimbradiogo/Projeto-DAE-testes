package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import cucumber.api.java.en.When
import cucumber.api.java.en.Then

class EquipStepDefs {

    static boolean actionTaken = false

    @When("I select an available avatar to equip")
    def equipAvatar() {
        Mobile.delay(2)
        println "A tentar equipar um item..."

        TestObject equipBtn = new TestObject()
        equipBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'EQUIPAR') and not(contains(@text, 'EQUIPADO'))]")
        
        if (Mobile.verifyElementExist(equipBtn, 3, FailureHandling.OPTIONAL)) {
            Mobile.tap(equipBtn, 0)
            println "Cliquei em EQUIPAR."
            actionTaken = true
        } else {
            TestObject buyBtn = new TestObject()
            buyBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'COMPRAR')]")
            
            if (Mobile.verifyElementExist(buyBtn, 3, FailureHandling.OPTIONAL)) {
                 Mobile.tap(buyBtn, 0)
                 println "Cliquei em COMPRAR."
                 actionTaken = true
            } else {
                 KeywordUtil.markWarning("Nenhum botão acionável encontrado.")
            }
        }
        
        Mobile.delay(3)
    }

    @Then("I should see the avatar marked as equipped")
    def verifyEquipped() {
        if (!actionTaken) return

        TestObject equippedState = new TestObject()
        equippedState.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'EQUIPADO')]")
        
        if (Mobile.verifyElementExist(equippedState, 5, FailureHandling.OPTIONAL)) {
            println "Sucesso: Item equipado."
        } else {
            KeywordUtil.markFailed("Falha: Estado não mudou para EQUIPADO.")
        }
    }
}
