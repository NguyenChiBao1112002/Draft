import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.console.ui.SystemOutputInterceptor
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver


'Wait for elements to load before interaction'
WebUI.delay(5)
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

'Verify user is logged in and account info is present'
WebUI.click(findTestObject('Object Repository/common/icon/user icon'))
WebUI.verifyElementPresent(findTestObject('Object Repository/common/div/div_class', [('class'): 'account-information__name']), 10)
WebUI.delay(5)


