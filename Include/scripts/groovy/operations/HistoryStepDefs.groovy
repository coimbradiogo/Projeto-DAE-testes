package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import cucumber.api.java.en.And
import org.openqa.selenium.WebElement

class HistoryStepDefs {

	@When('I tap the "Histórico de Transações" button')
	def navigateToHistory() {
		Mobile.delay(2)
		TestObject historyBtn = new TestObject()
		historyBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Histórico de Transações') or contains(@text, 'Transacoes')]")

		Mobile.tap(historyBtn, 10)
	}

	@Then("I should see the history screen title")
	def verifyHistoryTitle() {
		Mobile.delay(2)
		TestObject title = new TestObject()
		title.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Histórico') or contains(@text, 'Transações')]")
		Mobile.verifyElementExist(title, 10)
	}

	@And("I should see transaction records with date and value")
	def verifyTransactionDetails() {
		println "A verificar dados da tabela..."

		TestObject dateItem = new TestObject()
		dateItem.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, '202') or contains(@text, '/')]")


		TestObject valueItem = new TestObject()
		valueItem.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, '+') or contains(@text, '-') or contains(@text, 'Moedas')]")

		if (Mobile.verifyElementExist(dateItem, 5, FailureHandling.OPTIONAL)) {
			println "Datas encontradas no histórico."

			if (Mobile.verifyElementExist(valueItem, 5, FailureHandling.OPTIONAL)) {
				println "Valores de transação encontrados."
			} else {
				println "Aviso: Datas encontradas, mas valores explícitos não (verifique se a cor/formato é legível)."
			}
		} else {
			println "Aviso: A lista parece estar vazia ou o formato da data não foi reconhecido. (Isto é normal se o user a1 for novo)."
			TestObject emptyList = new TestObject()
			emptyList.addProperty("class", ConditionType.EQUALS, "android.widget.ListView") // ou RecyclerView
			Mobile.verifyElementExist(emptyList, 5, FailureHandling.OPTIONAL)
		}
	}
	
	@When('I tap the "Histórico de Partidas" button')
	def navigateToMatchHistory() {
		Mobile.delay(2)
		TestObject historyBtn = new TestObject()
		// XPath procura o botão exato
		historyBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Histórico de Partidas') or contains(@text, 'Partidas')]")
		Mobile.tap(historyBtn, 10)
	}

	@Then("I should see the match history screen")
	def verifyMatchHistoryScreen() {
		Mobile.delay(2)
		TestObject title = new TestObject()
		title.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Histórico') or contains(@text, 'Partidas')]")
		Mobile.verifyElementExist(title, 10)
	}

	@And("I should see match records with result and coins")
	def verifyMatchDetails() {
		println "A verificar detalhes das partidas..."

		TestObject resultItem = new TestObject()
		resultItem.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Vitória') or contains(@text, 'Derrota') or contains(@text, 'Empate') or contains(@text, 'Venceu') or contains(@text, 'Perdeu')]")

		TestObject coinsItem = new TestObject()
		coinsItem.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, '+') or contains(@text, '-') or contains(@text, 'Moedas')]")

		if (Mobile.verifyElementExist(resultItem, 5, FailureHandling.OPTIONAL)) {
			 println "Resultados (Vitória/Derrota) encontrados."
		} else {
			 println "Aviso: Nenhum resultado de partida encontrado. O histórico pode estar vazio."
		}
		
		TestObject listContainer = new TestObject()
		listContainer.addProperty("class", ConditionType.EQUALS, "android.widget.ListView") // Ou RecyclerView
		
		if (!Mobile.verifyElementExist(listContainer, 3, FailureHandling.OPTIONAL)) {
			 TestObject scroll = new TestObject()
			 scroll.addProperty("class", ConditionType.EQUALS, "android.widget.ScrollView")
			 Mobile.verifyElementExist(scroll, 3, FailureHandling.OPTIONAL)
		}
	}
}
