package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.annotation.Keyword
import cucumber.api.java.en.Given
import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import cucumber.api.java.en.And

class HomeStepDefs {

	@Given("I have the device ready")
	def deviceReady() {
		println "\nDevice/emulator ligado.\n"
	}

	@When("I start the application")
	def startApplication() {
		Mobile.startApplication('C:\\Users\\coimb\\StudioProjects\\Taes-Grupo3-Codigo-PL3\\app\\build\\outputs\\apk\\debug\\app-debug.apk', false)
	}


	@Then("I see the entry buttons for login and guest")
	def seeEntryButtons() {
		Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.Button - Fazer Login'), 0)
		Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.Button - Jogar como Convidado'), 0)
	}

	@And('I tap the "Fazer Login" button')
	def tapLoginButton() {
		Mobile.tap(findTestObject('Object Repository/android.widget.Button - Fazer Login'), 0)
	}

	@And('I tap the "Jogar como Convidado" button')
	def tapGuestButton() {
		Mobile.tap(findTestObject('Object Repository/android.widget.Button - Jogar como Convidado'), 0)
	}

	@Then("I see the login screen")
	def seeLoginScreen() {
		Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.EditText - Email'), 0)
	}

	@Then("I see the practice mode screen")
	def seePracticeScreen() {
		Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.TextView - Bem-vindo, Convidado'), 0)
	}

	@Then('I see that I am in practice mode as "Convidado"')
	def seeGuestPracticeMode() {
		Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.TextView - Bem-vindo, Convidado'), 0)
	}

	@Then("I see the guest limitations message")
	def seeGuestLimitations() {
		Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.TextView -  Modo Prtica Sem moedas ou histrico'), 5)
	}

	@And("I close the AUT")
	def closeAUT() {
		Mobile.closeApplication()
	}
}