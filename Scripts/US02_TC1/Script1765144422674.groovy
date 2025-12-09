import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

Mobile.startApplication('C:\\Users\\coimb\\StudioProjects\\Taes-Grupo3-Codigo-PL3\\app\\build\\outputs\\apk\\debug\\app-debug.apk',
        true)

Mobile.tap(findTestObject('Object Repository/android.widget.Button - Fazer Login'), 0)

Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Email'), 'a1@mail.pt', 0)
Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Password'), '123', 0)

Mobile.tap(findTestObject('Object Repository/android.widget.Button - Entrar'), 0)

// Verifica se o dashboard é mostrado
Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.TextView - Jogo da Bisca'), 0)

// Terminar sessão e fechar app
Mobile.tap(findTestObject('Object Repository/android.widget.Button - Terminar Sesso (1)'), 0)

Mobile.closeApplication()
