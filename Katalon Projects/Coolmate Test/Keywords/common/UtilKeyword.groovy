package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.entity.global.GlobalVariableEntity
import com.kms.katalon.entity.testsuite.TestSuiteRunConfiguration

import internal.GlobalVariable
import junit.framework.TestListener

import org.openqa.selenium.Keys


public class UtilKeyword {

	/*
	 *Properties*
	 */

	//Time Zone
	private static ZoneOffset vnZoneOffset = ZoneOffset.of("+0700")

	// Date Time Formatter
	private static DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("ddMMyyyy HHmmss")


	/*
	 *Regarding authentication*
	 */
	@Keyword
	static def openURLAndAuthenticate(String username, String password) {
		'Set up ChromeDriver with options'
		String basePath = RunConfiguration.getProjectDir()
		System.setProperty("webdriver.chrome.driver", basePath + "/Drivers/chromedriver131.exe")

		'Open in Incognito mode'
		ChromeOptions options = new ChromeOptions()
		options.addArguments("--incognito")

		'Set WebDriver for Katalon'
		WebDriver driver = new ChromeDriver(options)
		DriverFactory.changeWebDriver(driver)

		'Open the Coolmate website'
		driver.get(GlobalVariable.url)
		WebUI.waitForPageLoad(10)
		WebUI.maximizeWindow()

		'Handle Ads Popup if present'
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/pages/home_page/ads_close_button'), 10)) {
			WebUI.click(findTestObject('Object Repository/pages/home_page/ads_close_button'))
		}
		WebUI.delay(3)

		'Open Login Page'
		WebUI.click(findTestObject('Object Repository/common/icon/user icon'))

		'Enter login credentials'
		WebUI.delay(3)
		WebUI.sendKeys(findTestObject('Object Repository/common/div/div_id_with_input_name', [('id'):'app', ('name'): 'username']), GlobalVariable.username_bao)
		WebUI.delay(3)
		WebUI.setEncryptedText(findTestObject('Object Repository/common/div/div_id_with_input_name', [('id'):'app', ('name'): 'password']), GlobalVariable.password_bao)

		'Click login button and wait for page load'
		WebUI.delay(3)
		WebUI.click(findTestObject('Object Repository/common/button/button_class', [('class'):'login-btn']))
		WebUI.waitForPageLoad(10)
		WebUI.delay(3)

		'Handle pop-up elements or any other actions after login'
		TestObject popUpElement = findTestObject('Object Repository/component/close_popup_button')
		while(WebUI.waitForElementVisible(popUpElement, 10)) {
			WebUI.delay(3)
			WebUI.click(popUpElement)
			WebUI.delay(3)
		}
	}

	@Keyword
	static def closePopupIfPresent() {
		'Handle pop-up elements or any other actions after login'
		TestObject updateSizePopup = new TestObject()
		updateSizePopup.addProperty("xpath", ConditionType.EQUALS, "//*[@id='popup-member'][1]/div[2]/span")
		WebUI.waitForElementVisible(updateSizePopup, 10)
		WebUI.delay(3)
		WebUI.click(updateSizePopup)
		WebUI.delay(3)

		TestObject newMemberPopup = new TestObject()
		newMemberPopup.addProperty("xpath", ConditionType.EQUALS, "//*[@id='popup-member'][2]/div[2]/span")
		WebUI.waitForElementVisible(newMemberPopup, 10)
		WebUI.delay(3)
		WebUI.click(newMemberPopup)
		WebUI.delay(5)
	}

	@Keyword
	static def openURL() {
		'Set up ChromeDriver with options'
		String basePath = RunConfiguration.getProjectDir()
		System.setProperty("webdriver.chrome.driver", basePath + "/Drivers/chromedriver131.exe")

		'Open in Incognito mode'
		ChromeOptions options = new ChromeOptions()
		options.addArguments("--incognito")

		'Set WebDriver for Katalon'
		WebDriver driver = new ChromeDriver(options)
		DriverFactory.changeWebDriver(driver)

		'Open the Coolmate website'
		driver.get(GlobalVariable.url)
		WebUI.waitForPageLoad(10)
		WebUI.maximizeWindow()

		'Handle Ads Popup if present'
		if(WebUI.waitForElementVisible(findTestObject("Object Repository/pages/home_page/ads_close_button"), 10)) {
			WebUI.click(findTestObject("Object Repository/pages/home_page/ads_close_button"))
		}
		WebUI.delay(3)
	}

	@Keyword
	static def logOut() {
		WebUI.click(findTestObject('Object Repository/common/icon/user icon'))
		WebUI.delay(3)
		WebUI.click(findTestObject('Object Repository/common/properties/Text/contains_text', [('text'): 'Đi đến tài khoản']))
		WebUI.waitForPageLoad(10)

		WebUI.scrollToElement(findTestObject('Object Repository/common/img/img_alt', [('alt'): 'Đăng xuất']), 10)
		WebUI.delay(2)
	}

	@Keyword
	static def logOutAndClose() {
		WebUI.click(findTestObject('Object Repository/common/icon/user icon'))
		WebUI.delay(3)
		WebUI.click(findTestObject('Object Repository/common/properties/Text/contains_text', [('text'): 'Đi đến tài khoản']))
		WebUI.waitForPageLoad(10)

		WebUI.scrollToElement(findTestObject('Object Repository/common/img/img_alt', [('alt'): 'Đăng xuất']), 10)
		WebUI.delay(2)
		WebUI.closeBrowser()
	}


	/*
	 *Regarding workflow*
	 */
	@Keyword
	static def takeScreenShot(String name) {
		Path projectDir = Paths.get(RunConfiguration.getProjectDir())
		Path baseDir = projectDir.resolve("Screenshots").resolve(GlobalVariable.testSuiteName).resolve(GlobalVariable.testCaseName)
		Files.createDirectories(baseDir)

		LocalDateTime now = LocalDateTime.now(vnZoneOffset)
		String dateTime = dtFormatter.format(now)
		Path p = baseDir.resolve(name + "_" + dateTime + ".png")

		WebUI.takeScreenshot(p.toString())
	}

	/*
	 *Regarding timer*
	 */
	@Keyword
	static def waitForPageLoadAndDelay(int timeToLoad, int timeToDelay) {
		WebUI.waitForPageLoad(timeToLoad)
		WebUI.delay(timeToDelay)
	}
}