package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import cucumber.api.java.en.And
import cucumber.api.java.en.Then
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import cucumber.api.java.en.And
import cucumber.api.java.en.Then

class LoginStepDefs {

	@And("I login with valid credentials")
	def loginValid() {
		Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Email'),
				'a1@mail.pt', 0)
		Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Password'),
				'123', 0)
		Mobile.tap(findTestObject('Object Repository/android.widget.Button - Entrar'), 0)
	}

	@And("I login with invalid credentials")
	def loginInvalid() {
		Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Email'),
				'user@exemplo.com', 0)
		Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Password'),
				'errada', 0)
		Mobile.tap(findTestObject('Object Repository/android.widget.Button - Entrar'), 0)
	}

	@Then("I see the dashboard screen")
	def seeDashboard() {
		Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.TextView - Ol, First Administrator'), 0)
	}

	@Then("I see a login error message")
    def seeLoginError() {
        String expectedText = "Credenciais incorretas"
        
        TestObject dynamicToast = new TestObject()
        dynamicToast.addProperty("xpath", ConditionType.EQUALS, "//*[@text='" + expectedText + "']")
        
        Mobile.verifyElementExist(dynamicToast, 5)
    }
}
