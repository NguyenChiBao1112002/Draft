package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys

public class UtilKeyword {

	/*
	 *Regarding authentication*
	 */
	@Keyword
	static def openURLAndAuthentication(String username, String password) {
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
		if(WebUI.waitForElementVisible(findTestObject("Object Repository/home_page/ads_close_button"), 10)) {
			WebUI.click(findTestObject("Object Repository/home_page/ads_close_button"))
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
		WebUI.delay(3)
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
		if(WebUI.waitForElementVisible(findTestObject("Object Repository/home_page/ads_close_button"), 10)) {
			WebUI.click(findTestObject("Object Repository/home_page/ads_close_button"))
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
	static def takeScreenShot(String fileName) {
		WebUI.takeScreenshot("Screenshots/TestsuiteName/TestcaseName/" + fileName + ".png")
	}
	
}
