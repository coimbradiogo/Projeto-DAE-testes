import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

Mobile.startApplication('C:\\Users\\coimb\\StudioProjects\\Taes-Grupo3-Codigo-PL3\\app\\build\\outputs\\apk\\debug\\app-debug.apk',
        true)

// Critério (a): existem os dois botões
Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.Button - Fazer Login'), 0)
Mobile.verifyElementExist(findTestObject('Object Repository/android.widget.Button - Jogar como Convidado (1)'), 0)

Mobile.closeApplication()
