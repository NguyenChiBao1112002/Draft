import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


'Go to address book'
WebUI.enhancedClick(findTestObject('Object Repository/common/icon/user_icon'))
WebUI.delay(2)
WebUI.verifyElementPresent(findTestObject('Object Repository/pages/home_page/go_to_account'), 10)
WebUI.click(findTestObject('Object Repository/pages/home_page/go_to_account'))
CustomKeywords.'common.UtilKeyword.waitForPageLoadAndDelay'(20, 3)

WebUI.scrollToElement(findTestObject('Object Repository/common/a/a_text', [('text'): 'Thông tin tài khoản']), 10)
WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/common/a/a_text', [('text'): 'Sổ địa chỉ']))
WebUI.delay(5)

'==========Delete an address in the address book========'
WebUI.scrollToElement(findTestObject('Object Repository/common/button/button_contains_text', [('text'): 'Xóa']), 10)
WebUI.click(findTestObject('Object Repository/common/button/button_contains_text', [('text'): 'Xóa']))

'Handle Toast Message'
CustomKeywords.'common.UtilKeyword.handleToastMessage'("Xoá địa chỉ thành công!", 10)

'Take Screenshot'
CustomKeywords.'common.UtilKeyword.takeScreenShot'("delete_address_successfully")

'Close browser'
WebUI.delay(2)
WebUI.closeBrowser()


