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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement as Keys

'Go to Coolmate website and authenticate'
CustomKeywords.'common.UtilKeyword.openURLAndAuthenticate'(GlobalVariable.username_bao, GlobalVariable.password_bao)

'===============Add products from the product page to cart and verify added products in cart==============='

HashMap<String, String> selectedProductInfoFisrt = new HashMap()

'Scroll to the product'
WebUI.scrollToElement(findTestObject('Object Repository/pages/home_page/product_section_index', [('section-pos'): 1, ('index'): 2]), 10)
WebUI.delay(3)

'Collect the first selected product infomation that will be added to cart'
String productName = WebUI.getText(findTestObject('Object Repository/pages/home_page/product_name_section_index', [('section-pos'): 1, ('index'): 2]))
selectedProductInfoFisrt.put('name', productName)

WebUI.click(findTestObject('Object Repository/pages/home_page/product_color_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('color-pos'): 1]))
String selectedColor = WebUI.getAttribute(findTestObject('Object Repository/pages/home_page/product_color_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('color-pos'): 1]), 'title')
selectedProductInfoFisrt.put('color', selectedColor)

String selectedSize = WebUI.getAttribute(findTestObject('Object Repository/pages/home_page/product_size_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('size-pos'): 1]), 'innerText')
selectedProductInfoFisrt.put('size', selectedSize)

KeywordUtil.logInfo(selectedProductInfoFisrt.toString())

'Add first product to cart'
WebUI.mouseOver(findTestObject('Object Repository/pages/home_page/product_section_index', [('section-pos'): 1, ('index'): 2]))
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/pages/home_page/product_size_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('size-pos'): 1]))

CustomKeywords.'common.UtilKeyword.handleToastMessage'("Đã thêm vào giỏ hàng!", 60)
CustomKeywords.'common.UtilKeyword.takeScreenShot'("added_first_product")

'Collect the second selected product infomation that will be added to cart'
HashMap<String, String> selectedProductInfoSecond = new HashMap()

'Collect the second selected product infomation that will be added to cart'
productName = WebUI.getText(findTestObject('Object Repository/pages/home_page/product_name_section_index', [('section-pos'): 1, ('index'): 3]))
selectedProductInfoSecond.put('name', productName)

WebUI.click(findTestObject('Object Repository/pages/home_page/product_color_option_pos', [('section-pos'): 1, ('index-pro-pos'): 3, ('color-pos'): 1]))
selectedColor = WebUI.getAttribute(findTestObject('Object Repository/pages/home_page/product_color_option_pos', [('section-pos'): 1, ('index-pro-pos'): 3, ('color-pos'): 1]), 'title')
selectedProductInfoSecond.put('color', selectedColor)

selectedSize = WebUI.getAttribute(findTestObject('Object Repository/pages/home_page/product_size_option_pos', [('section-pos'): 1, ('index-pro-pos'): 3, ('size-pos'): 1]), 'innerText')
selectedProductInfoSecond.put('size', selectedSize)

KeywordUtil.logInfo(selectedProductInfoSecond.toString())

'Add second product to cart'
WebUI.mouseOver(findTestObject('Object Repository/pages/home_page/product_section_index', [('section-pos'): 1, ('index'): 3]))
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/pages/home_page/product_size_option_pos', [('section-pos'): 1, ('index-pro-pos'): 3, ('size-pos'): 1]))

CustomKeywords.'common.UtilKeyword.handleToastMessage'("Đã thêm vào giỏ hàng!", 60)
CustomKeywords.'common.UtilKeyword.takeScreenShot'("added_second_product")

'Go to cart'
WebUI.click(findTestObject('Object Repository/common/icon/cart_icon'))
CustomKeywords.'common.UtilKeyword.waitForPageLoadAndDelay'(20, 3)

'Verify added first product in cart'
TestObject addedProduct = new TestObject()
addedProduct.addProperty('xpath', ConditionType.EQUALS, "//*[*[text()='" + selectedProductInfoFisrt.get("name") + "']]/following-sibling::div")

WebUI.verifyElementVisible(addedProduct)
KeywordUtil.logInfo(WebUI.getText(addedProduct))

if(WebUI.getText(addedProduct).contains(selectedProductInfoFisrt.get("color") + " / " + selectedProductInfoFisrt.get("size"))) {
	KeywordUtil.logInfo("Add first product to cart successfully")
}else {
	KeywordUtil.markFailedAndStop("Add product to cart failed")
}

'Verify added second product in cart'
addedProduct.addProperty('xpath', ConditionType.EQUALS, "//*[*[text()='" + selectedProductInfoSecond.get("name") + "']]/following-sibling::div")

WebUI.verifyElementVisible(addedProduct)
KeywordUtil.logInfo(WebUI.getText(addedProduct))

if(WebUI.getText(addedProduct).contains(selectedProductInfoSecond.get("color") + " / " + selectedProductInfoSecond.get("size"))) {
	KeywordUtil.logInfo("Add second product to cart successfully")
	'Take screenshot'
	CustomKeywords.'common.UtilKeyword.takeScreenShot'("verified_product_detail_page")
}else {
	KeywordUtil.markFailedAndStop("Add product to cart failed")
}

'Remove all products in cart'
CustomKeywords.'product.ProductDetail.removeAllProductFromCart'()

'Logout and close browser'
CustomKeywords.'common.UtilKeyword.logOutAndClose'()



