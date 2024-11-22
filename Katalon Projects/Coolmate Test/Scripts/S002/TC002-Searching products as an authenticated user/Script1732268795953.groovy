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

String searchKeywords = 'Ao thun'

'Open Coolmate and authenticate'
CustomKeywords.'common.UtilKeyword.openURLAndAuthentication'(GlobalVariable.username_bao, GlobalVariable.password_bao)

'Close popup if present'
CustomKeywords.'common.UtilKeyword.closePopupIfPresent'()

'Click on Search box'
WebUI.click(findTestObject('Object Repository/common/ID/id', [('id'): 'search-input']))
WebUI.delay(4)

'Enter search keywords'
WebUI.sendKeys(findTestObject('Object Repository/common/ID/id', [('id'): 'input-spotlight']), 'searchKeywords')
WebUI.delay(10)



