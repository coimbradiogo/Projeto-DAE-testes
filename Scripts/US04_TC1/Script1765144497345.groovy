import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

Mobile.startApplication('C:\\Users\\coimb\\StudioProjects\\Taes-Grupo3-Codigo-PL3\\app\\build\\outputs\\apk\\debug\\app-debug.apk',
        true)

Mobile.tap(findTestObject('Object Repository/android.widget.Button - Jogar como Convidado (1)'), 0)

// Verifica a mensagem de limitações do modo prática
Mobile.verifyElementText(
        findTestObject('Object Repository/android.widget.TextView -  Modo Prtica Sem moedas ou histrico'),
        'Partidas de prática não geram moedas nem histórico.'
)

// Extra opcional: entrar numa partida e verificar aviso de resultados
Mobile.tap(findTestObject('Object Repository/android.widget.Button - JOGAR PARTIDA'), 0)
Mobile.verifyElementExist(
        findTestObject('Object Repository/android.widget.TextView - Modo Convidado Resultados no sero guardados'), 0)

Mobile.pressBack()
Mobile.tap(findTestObject('Object Repository/android.widget.Button - Terminar Sesso'), 0)

Mobile.closeApplication()
