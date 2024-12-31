package product

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import common.UtilKeyword
import internal.GlobalVariable

public class ProductDetail {

	@Keyword
	static def getProductName(){
		return WebUI.getText(findTestObject('Object Repository/pages/product_detail_page/product_name')).split("\n")[0]
	}
	
	@Keyword
	static def getProductColorWithPosition(int pos) {
		return WebUI.getText(findTestObject('Object Repository/pages/product_detail_page/color_with_pos', [('pos'): pos]))
	}
	
	@Keyword
	static def getProductSizeWithPosition(int pos) {
		return WebUI.getText(findTestObject('Object Repository/pages/product_detail_page/size_with_pos', [('pos'): pos]))
	}

	@Keyword
	static def selectProductColorWithPosition(int pos) {
		WebUI.verifyElementVisible(findTestObject('Object Repository/pages/product_detail_page/color_with_pos', [('pos'): pos]))
		WebUI.click(findTestObject('Object Repository/pages/product_detail_page/color_with_pos', [('pos'): pos]))
		WebUI.delay(2)
	}

	@Keyword
	static def selectProductSizeWithPosition(int pos) {
		WebUI.verifyElementVisible(findTestObject('Object Repository/pages/product_detail_page/size_with_pos', [('pos'): pos]))
		WebUI.click(findTestObject('Object Repository/pages/product_detail_page/size_with_pos', [('pos'): pos]))
		WebUI.delay(2)
	}

	@Keyword
	static def selectProductColorWithTitle(String title) {
	}

	@Keyword
	static def selectProductSizeWithTitle(String title) {
	}

	@Keyword
	static def removeAllProductFromCart() {
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/common/span/span_contains_text', [('text'): 'Xóa tất cả']))
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/common/button/button_contains_text', [('text'): 'Đồng ý']))
		WebUI.delay(2)
		UtilKeyword.handleToastMessage("Đã xóa giỏ hàng!", 60)
	}
}





