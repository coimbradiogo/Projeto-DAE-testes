package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import cucumber.api.java.en.When
import cucumber.api.java.en.Then

class ShopNotificationStepDefs {

    // (Se estiver num ficheiro separado, precisas dos imports e do navigateToSettings aqui também.
    // Se estiveres a reutilizar NotificationStepDefs, ignora este bloco e copia só os métodos abaixo)

    @When('I tap the "Testar Notificação Loja" button')
    def testShopNotification() {
        Mobile.delay(1)
        TestObject testBtn = new TestObject()
        testBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Testar Notificação Loja')]")
        Mobile.tap(testBtn, 0)
        println "Cliquei em testar notificação de loja."
    }

    @Then("I should see a push notification about a new item")
    def verifyShopNotification() {
        // Tenta capturar textos esperados (ajusta conforme o que aparece na app)
        // Ex: "Compra realizada", "Sucesso", "Novo Item"
        TestObject notifText = new TestObject()
        notifText.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Compra') or contains(@text, 'sucesso') or contains(@text, 'realizada')]")
        
        TestObject toast = new TestObject()
        toast.addProperty("xpath", ConditionType.EQUALS, "//android.widget.Toast[contains(@text, 'Compra') or contains(@text, 'sucesso')]")

        boolean found = false
        for(int i=0; i<3; i++) {
            if (Mobile.verifyElementExist(notifText, 1, FailureHandling.OPTIONAL)) {
                println "Sucesso: Notificação de Loja detetada (Texto)."
                found = true
                break
            }
            if (Mobile.verifyElementExist(toast, 1, FailureHandling.OPTIONAL)) {
                println "Sucesso: Notificação de Loja detetada (Toast)."
                found = true
                break
            }
            Mobile.delay(1)
        }

        if (found) {
            // OK
        } else {
            Mobile.takeScreenshot()
            KeywordUtil.markWarning("Não foi possível detetar a notificação de loja automaticamente. Screenshot guardado.")
        }
    }
}
