import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

Mobile.startApplication('C:\\Users\\coimb\\StudioProjects\\Taes-Grupo3-Codigo-PL3\\app\\build\\outputs\\apk\\debug\\app-debug.apk',
        true)

Mobile.tap(findTestObject('Object Repository/android.widget.Button - Fazer Login'), 0)

Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Email'), '1a1@mail.pt', 0)
Mobile.setText(findTestObject('Object Repository/android.widget.EditText - Password'), '1234', 0)

Mobile.tap(findTestObject('Object Repository/android.widget.Button - Entrar'), 0)

// Verifica se aparece mensagem de erro de login
Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.TextView - Erro login'), 0)

// Voltar atrás (se fizer sentido na tua app) e fechar
Mobile.tap(findTestObject('Object Repository/android.widget.Button - Voltar'), 0)

Mobile.closeApplication()
