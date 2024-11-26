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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
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


'=========================Login to the Coolmate Page with invalid username========================'

'Wait for elements to load before interaction'
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/common/icon/user icon'))

'Enter login credentials with invalid username'
WebUI.delay(3)
WebUI.sendKeys(findTestObject('Object Repository/common/div/div_id_with_input_name', [('id'):'app', ('name'): 'username']), GlobalVariable.username_bao + 'Wrong Text')
WebUI.delay(3)
WebUI.setEncryptedText(findTestObject('Object Repository/common/div/div_id_with_input_name', [('id'):'app', ('name'): 'password']), GlobalVariable.password_bao)

'Click login button and wait for page load'
WebUI.delay(3)
WebUI.click(findTestObject('Object Repository/common/button/button_class', [('class'):'login-btn']))
WebUI.waitForPageLoad(10)

'Handle error message'
if(WebUI.waitForElementVisible(findTestObject('Object Repository/common/div/div_class', [('class'): 'login-error']), 10)) {
	String errorMessage = WebUI.getText(findTestObject('Object Repository/common/div/div_class', [('class'): 'login-error']))
	KeywordUtil.logInfo("Error Message: " + errorMessage)
}else {
	KeywordUtil.markFailedAndStop("Error Message was displayed when entering invalid username")
}


