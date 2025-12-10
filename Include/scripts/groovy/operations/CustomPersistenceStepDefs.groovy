package operations

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.util.KeywordUtil
import cucumber.api.java.en.When
import io.appium.java_client.AppiumDriver
import io.appium.java_client.InteractsWithApps
import io.appium.java_client.android.AndroidDriver


class CustomPersistenceStepDefs {

	@When("I close and reopen the application")
	def closeAndReopenApp() {
		println "INFO: A tentar terminar e reativar a aplicação..."

		try {
			// Obtém o driver atual (sem genéricos)
			AppiumDriver driver = MobileDriverFactory.getDriver()

			// Tenta obter o ID do pacote
			String appId = driver.getCapabilities().getCapability("appPackage")

			if (appId == null) {
				// FALLBACK IMPORTANTE: Se não detetar, usa o ID da tua app
				// Substitui "com.example.bisca" pelo ID real se souberes
				appId = "com.example.bisca"
			}

			println "INFO: App Package ID alvo: " + appId

			// 1. Terminar a app
			// Verifica se o driver suporta interações de apps (AndroidDriver suporta)
			if (driver instanceof InteractsWithApps) {
				((InteractsWithApps) driver).terminateApp(appId)
				println "INFO: Aplicação terminada via Appium."

				Mobile.delay(3)

				// 2. Ativar a app
				((InteractsWithApps) driver).activateApp(appId)
				println "INFO: Aplicação reativada via Appium."

				Mobile.delay(5)
			} else {
				throw new Exception("O driver atual não suporta terminateApp/activateApp")
			}
		} catch (Exception e) {
			println "AVISO: Método avançado falhou (" + e.getMessage() + "). A tentar método legacy..."

			// Método Legacy (mais seguro se o de cima falhar por versões)
			try {
				Mobile.closeApplication()
				Mobile.delay(5)
				String apkPath = com.kms.katalon.core.configuration.RunConfiguration.getProjectDir() + "/androidapp/app-debug.apk"
				Mobile.startApplication(apkPath, false)
			} catch (Exception e2) {
				KeywordUtil.markFailed("Falha crítica ao reabrir app: " + e2.getMessage())
			}
		}
	}
	

}
