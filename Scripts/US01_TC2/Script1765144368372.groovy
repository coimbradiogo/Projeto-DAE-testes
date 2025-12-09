import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

Mobile.startApplication('C:\\Users\\coimb\\StudioProjects\\Taes-Grupo3-Codigo-PL3\\app\\build\\outputs\\apk\\debug\\app-debug.apk',
        true)

Mobile.tap(findTestObject('Object Repository/android.widget.Button - Fazer Login'), 0)

// Verifica se está no ecrã de login (campo Email visível)
Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.EditText - Email'), 0)

Mobile.closeApplication()
