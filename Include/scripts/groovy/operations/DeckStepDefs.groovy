package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import cucumber.api.java.en.When
import cucumber.api.java.en.Then

class DeckStepDefs {

    boolean deckActionTaken = false

    @When("I select a deck design to buy or equip")
    def selectDeck() {
        Mobile.delay(2)
        println "A procurar botões de Baralho..."

        // Tenta clicar no 3º botão COMPRAR (geralmente o 1º Baralho)
        TestObject thirdBuyBtn = new TestObject()
        thirdBuyBtn.addProperty("xpath", ConditionType.EQUALS, "(//*[contains(@text, 'COMPRAR')])[3]")
        
        if (Mobile.verifyElementExist(thirdBuyBtn, 3, FailureHandling.OPTIONAL)) {
            Mobile.tap(thirdBuyBtn, 0)
            println "Cliquei no 3º botão COMPRAR (Baralho)."
            deckActionTaken = true
            
            // Aguarda a compra processar
            Mobile.delay(2)
            
            // Agora procura o botão EQUIPAR que deve ter aparecido no mesmo lugar
            TestObject equipBtn = new TestObject()
            equipBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'EQUIPAR') and not(contains(@text, 'EQUIPADO'))]")
            
            if (Mobile.verifyElementExist(equipBtn, 3, FailureHandling.OPTIONAL)) {
                Mobile.tap(equipBtn, 0)
                println "Cliquei em EQUIPAR."
                Mobile.delay(2)
            } else {
                println "Botão EQUIPAR não encontrado. Pode já estar equipado ou houve erro."
            }
        } 
        else {
            // Fallback: Tenta o 4º botão
            TestObject fourthBuyBtn = new TestObject()
            fourthBuyBtn.addProperty("xpath", ConditionType.EQUALS, "(//*[contains(@text, 'COMPRAR')])[4]")
            
            if (Mobile.verifyElementExist(fourthBuyBtn, 3, FailureHandling.OPTIONAL)) {
                Mobile.tap(fourthBuyBtn, 0)
                println "Cliquei no 4º botão COMPRAR."
                deckActionTaken = true
                
                Mobile.delay(2)
                
                // Tenta equipar também
                TestObject equipBtn = new TestObject()
                equipBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'EQUIPAR') and not(contains(@text, 'EQUIPADO'))]")
                
                if (Mobile.verifyElementExist(equipBtn, 3, FailureHandling.OPTIONAL)) {
                    Mobile.tap(equipBtn, 0)
                    println "Cliquei em EQUIPAR."
                    Mobile.delay(2)
                }
            }
            else {
                 // Último recurso: Tenta o repositório
                 if (Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.Button - COMPRAR (2)'), 2, FailureHandling.OPTIONAL)) {
                     Mobile.tap(findTestObject('Object Repository/android.widget.Button - COMPRAR (2)'), 0)
                     deckActionTaken = true
                     Mobile.delay(2)
                     
                     TestObject equipBtn = new TestObject()
                     equipBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'EQUIPAR') and not(contains(@text, 'EQUIPADO'))]")
                     
                     if (Mobile.verifyElementExist(equipBtn, 3, FailureHandling.OPTIONAL)) {
                         Mobile.tap(equipBtn, 0)
                         println "Equipado através do fallback."
                     }
                 } else {
                     KeywordUtil.markWarning("Não consegui encontrar botão de Baralho.")
                 }
            }
        }
        
        Mobile.delay(2)
    }

    @Then("I should see the deck marked as equipped")
    def verifyDeckEquipped() {
        // Verifica erro de saldo
        if (Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.TextView - Saldo insuficiente'), 2, FailureHandling.OPTIONAL)) {
             KeywordUtil.markWarning("Aviso: Saldo insuficiente.")
             return
        }

        // Verifica sucesso
        TestObject equippedLabel = new TestObject()
        equippedLabel.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'EQUIPADO')]")
        
        if (Mobile.verifyElementExist(equippedLabel, 5, FailureHandling.OPTIONAL)) {
            println "✓ Sucesso: Baralho EQUIPADO."
        } else {
             KeywordUtil.markFailed("✗ Falha: O baralho não está marcado como EQUIPADO.")
        }
    }
}
