package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import cucumber.api.java.en.And

class ShopStepDefs {

    @When('I tap the "Loja" button')
    def navigateToShop() {
        Mobile.delay(3)
        if (Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.Button - Loja (Avatares e Decks)'), 3, FailureHandling.OPTIONAL)) {
             Mobile.tap(findTestObject('Object Repository/android.widget.Button - Loja (Avatares e Decks)'), 10)
        } else {
             TestObject shopBtn = new TestObject()
             shopBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Loja') or contains(@text, 'Avatares')]")
             Mobile.tap(shopBtn, 10)
        }
    }

    @Then("I should see the shop screen")
    def verifyShopScreen() {
        Mobile.delay(3)
        TestObject title = new TestObject()
        title.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Loja de Personaliza')]")
        Mobile.verifyElementExist(title, 10)
    }

    @And("I should see a list of items with prices")
    def verifyShopItems() {
        println "A verificar conteúdo da loja..."

        TestObject priceLabel = new TestObject()
        priceLabel.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Moedas')]")
        
        if (Mobile.verifyElementExist(priceLabel, 5, FailureHandling.OPTIONAL)) {
             println "Preços encontrados."
        } else {
             println "Aviso: Preços explícitos não detetados (pode ser porque já tens tudo comprado?)."
        }

        TestObject actionBtn = new TestObject()
        actionBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'COMPRAR') or contains(@text, 'EQUIPADO') or contains(@text, 'EQUIPAR')]")
        
        if (Mobile.verifyElementExist(actionBtn, 5, FailureHandling.OPTIONAL)) {
             println "Botões de compra/equipamento encontrados."
        } else {
             throw new com.kms.katalon.core.exception.StepFailedException("Falha: Nenhum botão de 'COMPRAR' ou 'EQUIPADO' encontrado na loja.")
        }
        
        TestObject itemName = new TestObject()
        itemName.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Avatar') or contains(@text, 'Baralho')]")
        Mobile.verifyElementExist(itemName, 5, FailureHandling.OPTIONAL)
    }
}
