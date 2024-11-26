import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.builtin.TakeScreenshotKeyword
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import common.UtilKeyword
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebElement as Keys

//String validSearchKeywords = 'Ao thun'
//String invalidSearchKeywords = 'invalid_keyword_V1Tdm6pxAkdcLfg=='

'Open Coolmate'
CustomKeywords.'common.UtilKeyword.openURLAndAuthentication'(GlobalVariable.username_bao, GlobalVariable.password_bao)

'==========================As an unauthenticated user, entering valid keys to search related products========================='

'Click on Search box'
WebUI.click(findTestObject('Object Repository/common/properties/Id/id', [('id'): 'search-input']))
WebUI.delay(4)

'Enter valid search keywords'
WebUI.sendKeys(findTestObject('Object Repository/common/properties/Id/id', [('id'): 'input-spotlight']), validSearchKeywords)
WebUI.delay(10)


'Verify that all results match the valid search keywords'
List<WebElement> searchResults = WebUI.findWebElements(findTestObject('Object Repository/home_page/product_search_result'), 10)
String searchKeywordsText = CustomKeywords.'common.TextUtilKeyword.removeAccents'(validSearchKeywords.toLowerCase())

searchResults.eachWithIndex { WebElement element, int index ->
		KeywordUtil.logInfo("Search result No." + (index+1) + ": " + element.getText())
		String resultText = CustomKeywords.'common.TextUtilKeyword.removeAccents'(element.getText().toLowerCase())
		if(!resultText.contains(searchKeywordsText)) {
			KeywordUtil.markFailedAndStop("Something wrong in Search Function bacause there is a result not matching with the valid search keywords!")
		}
}
KeywordUtil.markPassed("All results match the valid search keyword \"searchKeywords\"")
//WebUI.takeScreenshot('results_match_valid_keyword')


'Return to Home Page'
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/common/properties/Class/rel-script_and_contains_class', [('rel-script'): 'spotlight-search-close', ('class'): 'homepage-search__submit']))
WebUI.delay(3)


'===============================Entering  invalid keys to search related products================================='

'Click on Search box one more time'
WebUI.click(findTestObject('Object Repository/common/properties/Id/id', [('id'): 'search-input']))
WebUI.delay(4)

'Enter invalid search keywords'
WebUI.sendKeys(findTestObject('Object Repository/common/properties/Id/id', [('id'): 'input-spotlight']), invalidSearchKeywords)
WebUI.delay(10)

'The system shows the message: "Không tìm thấy kết quả phù hợp!" when entering invalid search keywords'
if(WebUI.waitForElementVisible(findTestObject('Object Repository/common/properties/Text/contains_text', [('text'): 'Không tìm thấy kết quả phù hợp!']), 10)) {
	KeywordUtil.markPassed("The system showed the message: \"Không tìm thấy kết quả phù hợp!\"")
}else {
	KeywordUtil.markFailedAndStop("Something wrong in Search Function bacause the system didn't show the not found message")
	
}

'Return to Home Page'
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/common/properties/Class/rel-script_and_contains_class', [('rel-script'): 'spotlight-search-close', ('class'): 'homepage-search__submit']))
WebUI.delay(3)


'Logout and close browser'
CustomKeywords.'common.UtilKeyword.logOutAndClose'()

