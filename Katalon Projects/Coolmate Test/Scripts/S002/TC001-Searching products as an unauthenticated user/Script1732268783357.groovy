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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebElement as Keys

String searchKeywords = 'Ao thun'

'Open Coolmate'
CustomKeywords.'common.UtilKeyword.openURL'()

'Click on Search box'
WebUI.click(findTestObject('Object Repository/common/ID/id', [('id'): 'search-input']))
WebUI.delay(4)

'Enter search keywords'
WebUI.sendKeys(findTestObject('Object Repository/common/ID/id', [('id'): 'input-spotlight']), 'searchKeywords')
WebUI.delay(10)

//WebUI.findWebElement(findTestObject)

List<WebElement> searchResults = WebUI.findWebElements(findTestObject('Object Repository/home_page/product_search_result'), 10)

searchResults.each { WebElement element ->
	KeywordUtil.logInfo(element.getText())
}