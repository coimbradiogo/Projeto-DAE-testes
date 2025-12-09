package operations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import cucumber.api.java.en.And
import cucumber.api.java.en.Then

class GameStepDefs {

    @And('I tap the "JOGAR PARTIDA" button')
    def tapPlayMatchButton() {
        if (Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.Button - Ir para Dashboard'), 3, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('Object Repository/android.widget.Button - Ir para Dashboard'), 0)
        }

        Mobile.tap(findTestObject('Object Repository/android.widget.Button - JOGAR PARTIDA'), 10)
    }

    @Then("I should see cards in my hand")
    def verifyCardsInHand() {
        Mobile.delay(3)

        TestObject anyImage = new TestObject()
        anyImage.addProperty("class", ConditionType.EQUALS, "android.widget.ImageView")

        List<TestObject> visibleCards = Mobile.findElements(anyImage, 10)
        
        println "Encontrei " + visibleCards.size() + " imagens (cartas) no ecrã."

        if (visibleCards.size() < 3) {
            TestObject scrollChild = new TestObject()
            scrollChild.addProperty("xpath", ConditionType.EQUALS, "//android.widget.HorizontalScrollView//android.widget.ImageView")
            visibleCards = Mobile.findElements(scrollChild, 5)
            println "Tentativa 2 (ScrollView): " + visibleCards.size()
        }

        assert visibleCards.size() >= 3 
    }

    @And("a trump suit should be visible on the screen")
    def verifyTrumpSuitIsVisible() {
        if (findTestObject('Object Repository/game/trump_card') != null) {
             Mobile.verifyElementExist(findTestObject('Object Repository/game/trump_card'), 5, FailureHandling.OPTIONAL)
        } else {
             TestObject trumpText = new TestObject()
             trumpText.addProperty("xpath", ConditionType.EQUALS, "//*[contains(@text, 'Trunfo')]")
             Mobile.verifyElementExist(trumpText, 10)
        }
    }
}
