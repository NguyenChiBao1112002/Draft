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
import org.openqa.selenium.Keys as Keys

'Go to Coolmate website and authenticate'
CustomKeywords.'common.UtilKeyword.openURLAndAuthenticate'(GlobalVariable.username_bao, GlobalVariable.password_bao)

'=============Add a product with many color and size pairs from the product page and verify added product in cart==================='
'Scroll to the product'
WebUI.scrollToElement(findTestObject('Object Repository/pages/home_page/product_section_index', [('section-pos'): 1, ('index'): 2]), 10)
WebUI.delay(3)

'Collect the first option that will be added to cart'
HashMap<String, String> selectedFirstOption = new HashMap()

String productName = WebUI.getText(findTestObject('Object Repository/pages/home_page/product_name_section_index', [('section-pos'): 1, ('index'): 2]))
selectedFirstOption.put('name', productName)

WebUI.click(findTestObject('Object Repository/pages/home_page/product_color_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('color-pos'): 1]))
String selectedColor = WebUI.getAttribute(findTestObject('Object Repository/pages/home_page/product_color_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('color-pos'): 1]), 'title')
selectedFirstOption.put('color', selectedColor)

String selectedSize = WebUI.getAttribute(findTestObject('Object Repository/pages/home_page/product_size_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('size-pos'): 1]), 'innerText')
selectedFirstOption.put('size', selectedSize)

KeywordUtil.logInfo(selectedFirstOption.toString())

'Add first product to cart'
WebUI.mouseOver(findTestObject('Object Repository/pages/home_page/product_section_index', [('section-pos'): 1, ('index'): 2]))
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/pages/home_page/product_size_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('size-pos'): 1]))


CustomKeywords.'common.UtilKeyword.handleToastMessage'("Đã thêm vào giỏ hàng!", 60)
CustomKeywords.'common.UtilKeyword.takeScreenShot'("added_first_option")
WebUI.delay(3)

'Collect the second option that will be added to cart'
HashMap<String, String> selectedSecondOption = new HashMap()

'Collect the second option that will be added to cart'
selectedSecondOption.put('name', productName)

WebUI.click(findTestObject('Object Repository/pages/home_page/product_color_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('color-pos'): 2]))
selectedColor = WebUI.getAttribute(findTestObject('Object Repository/pages/home_page/product_color_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('color-pos'): 2]), 'title')
selectedSecondOption.put('color', selectedColor)

selectedSize = WebUI.getAttribute(findTestObject('Object Repository/pages/home_page/product_size_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('size-pos'): 2]), 'innerText')
selectedSecondOption.put('size', selectedSize)

KeywordUtil.logInfo(selectedSecondOption.toString())

'Add second product to cart'
WebUI.mouseOver(findTestObject('Object Repository/pages/home_page/product_section_index', [('section-pos'): 1, ('index'): 2]))
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/pages/home_page/product_size_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('size-pos'): 2]))

CustomKeywords.'common.UtilKeyword.handleToastMessage'("Đã thêm vào giỏ hàng!", 60)
CustomKeywords.'common.UtilKeyword.takeScreenShot'("added_second_option")

'Go to cart'
WebUI.click(findTestObject('Object Repository/common/icon/cart_icon'))
CustomKeywords.'common.UtilKeyword.waitForPageLoadAndDelay'(20, 3)

'Verify the product that selected with first option is added in cart'
TestObject addedProduct = new TestObject()
addedProduct.addProperty('xpath', ConditionType.EQUALS, "//*[*[text()='" + selectedFirstOption.get("name") + "']]/following-sibling::div")

WebUI.verifyElementVisible(addedProduct)
KeywordUtil.logInfo(WebUI.getText(addedProduct))


if(WebUI.getText(addedProduct).contains(selectedFirstOption.get("color") + " / " + selectedFirstOption.get("size"))) {
	KeywordUtil.markPassed("Add product with first option to cart successfully")
}else {
	KeywordUtil.markFailedAndStop("Add product with first option to cart failed")
}

'Verify the product that selected with second option is added in cart'
addedProduct.addProperty('xpath', ConditionType.EQUALS, "(//*[*[text()='" + selectedSecondOption.get("name") + "']]/following-sibling::div)[2]")

WebUI.verifyElementVisible(addedProduct)
KeywordUtil.logInfo(WebUI.getText(addedProduct))

if(WebUI.getText(addedProduct).contains(selectedSecondOption.get("color") + " / " + selectedSecondOption.get("size"))) {
	KeywordUtil.logInfo("Add product with second option to cart successfully")
	'Take screenshot'
	CustomKeywords.'common.UtilKeyword.takeScreenShot'("added_product_multi_option")
}else {
	KeywordUtil.markFailedAndStop("Add product with second option to cart failed")
}

'Remove all products in cart'
CustomKeywords.'product.ProductDetail.removeAllProductFromCart'()

'Logout and close browser'
CustomKeywords.'common.UtilKeyword.logOutAndClose'()



