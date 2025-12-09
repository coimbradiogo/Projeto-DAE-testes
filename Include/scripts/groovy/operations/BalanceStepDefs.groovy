package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import cucumber.api.java.en.And
import org.openqa.selenium.WebElement

class BalanceStepDefs {

	@And("I should see my coin balance displayed")
	def verifyBalanceDisplayed() {
		Mobile.delay(3)
		println "A procurar saldo de moedas..."

		// OPÇÃO 1: Procura explicitamente pela palavra "Moedas"
		TestObject coinLabel = new TestObject()
		coinLabel.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Moedas')]")

		if (Mobile.verifyElementExist(coinLabel, 5, FailureHandling.OPTIONAL)) {
			String text = Mobile.getText(coinLabel, 0)
			println "Saldo encontrado (Label Moedas): " + text
		} else {
			// OPÇÃO 2 (Plano B): Procura por números isolados (ex: "100")
			// Isto serve para quando o saldo é apenas um número sem a palavra "Moedas"
			println "Texto 'Moedas' não encontrado. A procurar números..."

			TestObject anyText = new TestObject()
			anyText.addProperty("class", ConditionType.EQUALS, "android.widget.TextView")

			List<WebElement> allTexts = Mobile.findElements(anyText, 5)
			boolean foundNumber = false

			for (WebElement el : allTexts) {
				String val = el.getText()
				// Verifica se o texto é composto apenas por dígitos
				if (val.matches("^[0-9]+")) {
					println "Encontrei um número que deve ser o saldo: " + val
					foundNumber = true
					break
				}
			}

			if (!foundNumber) {
				// Se não encontrar nem "Moedas" nem números, falha o teste
				throw new com.kms.katalon.core.exception.StepFailedException("ERRO: Saldo não encontrado no ecrã.")
			}
		}
	}
}
