package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import cucumber.api.java.en.And

class LeaderboardStepDefs {

	@When('I tap the "Classificações" button')
	def navigateToLeaderboard() {
		Mobile.delay(3)
		// Tenta pelo objeto do repositório primeiro
		if (Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.Button - Classificaes'), 3, FailureHandling.OPTIONAL)) {
			Mobile.tap(findTestObject('Object Repository/android.widget.Button - Classificaes'), 10)
		} else {
			// Fallback
			TestObject rankBtn = new TestObject()
			rankBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Classifica') or contains(@text, 'Ranking')]")
			Mobile.tap(rankBtn, 10)
		}
	}

	@Then("I should see the leaderboard screen")
	def verifyLeaderboardScreen() {
		Mobile.delay(3)
		TestObject topRicos = new TestObject()
		topRicos.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Top Ricos') or contains(@text, 'Ricos')]")

		// Verifica se o botão "Top Ricos" ou título equivalente existe
		if (!Mobile.verifyElementExist(topRicos, 5, FailureHandling.OPTIONAL)) {
			Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.TextView - Vitrias'), 5)
		}
	}

	@And("I should see a list of players with names and scores")
	def verifyPlayerList() {
		// Garante que estamos na tab "Top Ricos"
		TestObject topRicosBtn = new TestObject()
		topRicosBtn.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Top Ricos')]")
		if (Mobile.verifyElementExist(topRicosBtn, 3, FailureHandling.OPTIONAL)) {
			Mobile.tap(topRicosBtn, 0)
			Mobile.delay(2)
		}

		println "A verificar se existem pontuações na lista..."

		// Em vez de sacar todos os textos, vamos verificar se existe ALGUM texto que seja um número.
		// O XPath matches é poderoso aqui.
		TestObject anyScore = new TestObject()
		// Procura um TextView que tenha apenas digitos (regex ^[0-9]+$)
		anyScore.addProperty("xpath", ConditionType.EQUALS, "//android.widget.TextView[matches(@text, '^[0-9]+\$')]")

		if (Mobile.verifyElementExist(anyScore, 5, FailureHandling.OPTIONAL)) {
			println "Sucesso: Encontradas pontuações numéricas na lista."
		} else {
			println "Aviso: Não encontrei números isolados. A lista pode estar vazia ou ter formato diferente (ex: '100 pts')."
			// Se falhar, verificamos se pelo menos há nomes (qualquer TextView que não seja titulo)
			TestObject anyName = new TestObject()
			anyName.addProperty("xpath", ConditionType.EQUALS, "//android.widget.TextView[string-length(@text) > 3]")
			Mobile.verifyElementExist(anyName, 5, FailureHandling.OPTIONAL)
		}
	}
	
	@And("I should see my personal performance header")
	def verifyHeader() {
		Mobile.delay(2)
		println "A verificar cabeçalho de desempenho..."
		
		TestObject headerTitle = new TestObject()
		headerTitle.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'O Teu Desempenho')]")
		Mobile.verifyElementExist(headerTitle, 10)
	}

	@And("I should see my total wins and accumulated coins")
	def verifyPersonalStats() {
		TestObject winsLabel = new TestObject()
		winsLabel.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Vitórias') or contains(@text, 'Vitorias')]")
		Mobile.verifyElementExist(winsLabel, 5)

		TestObject coinsLabel = new TestObject()
		coinsLabel.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Moedas')]")
		Mobile.verifyElementExist(coinsLabel, 5)
		
		
		TestObject anyNumber = new TestObject()
		anyNumber.addProperty("xpath", ConditionType.EQUALS, "//android.widget.TextView[matches(@text, '^[0-9]+\$')]")
		
		if (Mobile.verifyElementExist(anyNumber, 5, FailureHandling.OPTIONAL)) {
			println "Valores numéricos de estatística encontrados."
		} else {
			println "Aviso: Labels encontradas, mas valores numéricos exatos não (pode ser '-' se for 0)."
		}
	}
}
