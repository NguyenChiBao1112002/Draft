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
import org.openqa.selenium.JavascriptExecutor

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
		WebUI.delay(3)

//		'Disable advertisement or other popups if present'
//		JavascriptExecutor js = (JavascriptExecutor) driver
//		String script = """
//		    var popups = document.querySelectorAll('[id^="popup-member"]');
//		    popups.forEach(function(popup) {
//		        popup.style.display = 'none';
//		    });
//		"""
//		js.executeScript(script)
//		WebUI.delay(3)
		
		'Handle Ads Popup if present'
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/pages/home_page/ads_close_button'), 5, FailureHandling.OPTIONAL)) {
			WebUI.enhancedClick(findTestObject('Object Repository/pages/home_page/ads_close_button'))
		}

		'Open Login Page'
		WebUI.click(findTestObject('Object Repository/common/icon/user_icon'))

		'Enter login credentials'
		WebUI.delay(2)
		WebUI.sendKeys(findTestObject('Object Repository/common/div/div_id_with_input_name', [('id'):'app', ('name'): 'username']), GlobalVariable.username_bao)
		WebUI.delay(2)
		WebUI.setEncryptedText(findTestObject('Object Repository/common/div/div_id_with_input_name', [('id'):'app', ('name'): 'password']), GlobalVariable.password_bao)

		'Click login button and wait for page load'
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/common/button/button_class', [('class'):'login-btn']))
		WebUI.waitForPageLoad(10)
		WebUI.delay(2)

//		'Disable advertisement or other popups if present'
//		js.executeScript(script)
		
		'Handle pop-up elements or any other actions after login'
		TestObject popUpElement = findTestObject('Object Repository/component/close_popup_button')
		TestObject adPopUpElement = findTestObject('Object Repository/pages/home_page/ads_close_button')
		while(WebUI.waitForElementVisible(popUpElement, 10) ) {
			WebUI.delay(2)
			WebUI.click(popUpElement)
			WebUI.delay(2)
			while(WebUI.waitForElementVisible(adPopUpElement, 10) ) {
				WebUI.delay(2)
				WebUI.click(adPopUpElement)
				WebUI.delay(2)
			}
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
		WebUI.delay(3)

//		'Disable advertisement or other popups if present'
//		JavascriptExecutor js = (JavascriptExecutor) driver
//		String script = """
//		    var popups = document.querySelectorAll('[id^="popup-member"]');
//		    popups.forEach(function(popup) {
//		        popup.style.display = 'none';
//		    });
//		"""
//		js.executeScript(script)
		'Handle Ads Popup if present'
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/pages/home_page/ads_close_button'), 5, FailureHandling.OPTIONAL)) {
			WebUI.enhancedClick(findTestObject('Object Repository/pages/home_page/ads_close_button'))
		}
		
	}

	@Keyword
	static def logOut() {
		WebUI.click(findTestObject('Object Repository/common/icon/user_icon'))
		WebUI.delay(3)
		WebUI.click(findTestObject('Object Repository/common/properties/Text/contains_text', [('text'): 'Đi đến tài khoản']))
		WebUI.waitForPageLoad(10)

		WebUI.scrollToElement(findTestObject('Object Repository/common/img/img_alt', [('alt'): 'Đăng xuất']), 10)
		WebUI.delay(2)
	}

	@Keyword
	static def logOutAndClose() {
		WebUI.click(findTestObject('Object Repository/common/icon/user_icon'))
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

	@Keyword
	static def handleToastMessage(String expectedMessage, int timeout) {
		Boolean isDisplayedMessage =  WebUI.waitForElementVisible(findTestObject('Object Repository/common/div/div_class_contains_class', [('class_1'): 'notify', ('class_2'): 'notify__message']), timeout)
		String actualMessage = WebUI.getText(findTestObject('Object Repository/common/div/div_class_contains_class', [('class_1'): 'notify', ('class_2'): 'notify__message']))

		if(expectedMessage.equals(actualMessage) && isDisplayedMessage) {
			KeywordUtil.logInfo("Hanlde Toast Message successfully: " + actualMessage)
		}else {
			KeywordUtil.markFailedAndStop("Expected Message: \"" + expectedMessage + "\"" + "not matches Actual Message: \"" + actualMessage + "\"")
		}
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
