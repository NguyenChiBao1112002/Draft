import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.regex.Pattern

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//String phoneNumber = ""
//String existingErrMess = ""

'Open and authenticate'
CustomKeywords.'common.UtilKeyword.openURLAndAuthenticate'(GlobalVariable.username_bao, GlobalVariable.password_bao)

'Go to account infomation page'
WebUI.enhancedClick(findTestObject('Object Repository/common/icon/user_icon'))
WebUI.delay(2)
WebUI.verifyElementPresent(findTestObject('Object Repository/pages/home_page/go_to_account'), 10)
WebUI.click(findTestObject('Object Repository/pages/home_page/go_to_account'))
CustomKeywords.'common.UtilKeyword.waitForPageLoadAndDelay'(20, 3)

'Update account with invalid phone number'
WebUI.click(findTestObject('Object Repository/pages/account_page/update_acc_info_button'))
WebUI.delay(2)

//Some forms may require a special event (like focus or blur) to activate or allow input.
WebUI.executeJavaScript("let input = document.getElementsByName('phone')[0]; input.focus(); input.value = arguments[0]; input.dispatchEvent(new Event('input'));", Arrays.asList(phoneNumber))
WebUI.click(findTestObject('Object Repository/common/button/button_class', [('class'): 'btn account-info__btn account-info__btn--full']))
WebUI.delay(2)

TestObject errMessObject = findTestObject('Object Repository/common/span/span_class', [('class'): 'error-text'])


WebUI.verifyElementVisible(errMessObject)
def actualErrMess = WebUI.getText(errMessObject)

'Verify error message for exsting phone number'
if(actualErrMess.equals(existingErrMess)) {
	KeywordUtil.logInfo("The error message was correct as expected to be \"" + existingErrMess + "\"")
	CustomKeywords.'common.UtilKeyword.takeScreenShot'("invalid_phone_err_mess")
}else {
	KeywordUtil.markFailedAndStop("The error message is not as expected \"" + existingErrMess + "\"")
}	

'Return account information page'
WebUI.verifyElementVisible(findTestObject('Object Repository/common/span/id_span_class', [('id'): 'info-tab', ('class'): 'close-popup']))
WebUI.click(findTestObject('Object Repository/common/span/id_span_class', [('id'): 'info-tab', ('class'): 'close-popup']))

'Logout and close'
CustomKeywords.'common.UtilKeyword.logOutAndClose'()






