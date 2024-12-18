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

'======================Add a product from the product page================================='

HashMap<String, String> selectedProduct = new HashMap()

'Scroll to the product'
WebUI.scrollToElement(findTestObject('Object Repository/pages/home_page/product_section_index', [('section-pos'): 1, ('index'): 2]), 10)
WebUI.delay(3)

'Collect the first selected product infomation that will be added to cart'
String productName = WebUI.getText(findTestObject('Object Repository/pages/home_page/product_name_section_index', [('section-pos'): 1, ('index'): 2]))
selectedProduct.put('name', productName)

WebUI.click(findTestObject('Object Repository/pages/home_page/product_color_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('color-pos'): 1]))
String selectedColor = WebUI.getAttribute(findTestObject('Object Repository/pages/home_page/product_color_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('color-pos'): 1]), 'title')
selectedProduct.put('color', selectedColor)

String selectedSize = WebUI.getAttribute(findTestObject('Object Repository/pages/home_page/product_size_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('size-pos'): 1]), 'innerText')
selectedProduct.put('size', selectedSize)

KeywordUtil.logInfo(selectedProduct.toString())

'Add first product to cart'
WebUI.mouseOver(findTestObject('Object Repository/pages/home_page/product_section_index', [('section-pos'): 1, ('index'): 2]))
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/pages/home_page/product_size_option_pos', [('section-pos'): 1, ('index-pro-pos'): 2, ('size-pos'): 1]))


CustomKeywords.'common.UtilKeyword.handleToastMessage'("Đã thêm vào giỏ hàng!", 60)
CustomKeywords.'common.UtilKeyword.takeScreenShot'("Added first product successfully")

'============Go to the product detail page to add product to cart of the same color and size that was previously added and verify added product information in cart=========='
'Go to product detail page'
WebUI.click(findTestObject('Object Repository/pages/home_page/product_section_index', [('section-pos'): 1, ('index'): 2]))
CustomKeywords.'common.UtilKeyword.waitForPageLoadAndDelay'(20, 3)

'Check if the product in product detail page is previously added product'
KeywordUtil.logInfo(CustomKeywords.'product.ProductDetail.getProductName'())

KeywordUtil.logInfo(selectedProduct.get("name"))
if(selectedProduct.get("name").equals(CustomKeywords.'product.ProductDetail.getProductName'())) {
	KeywordUtil.logInfo("The product is previously added product")
}else {
	KeywordUtil.markFailedAndStop("The product is not previous added product")
}

'Add product to cart of the same color and size that was previously added'
CustomKeywords.'product.ProductDetail.selectProductColorWithPosition'(1)
CustomKeywords.'product.ProductDetail.selectProductSizeWithPosition'(1)
WebUI.click(findTestObject('Object Repository/pages/product_detail_page/add_to_cart_button'))

'Handle toast message after adding product to cart'
CustomKeywords.'common.UtilKeyword.handleToastMessage'("Đã thêm vào giỏ hàng!", 60)

'Take screenshot'
CustomKeywords.'common.UtilKeyword.takeScreenShot'('Adding from product detail page successfully')

'Go to cart'
WebUI.click(findTestObject('Object Repository/common/icon/cart_icon'))
CustomKeywords.'common.UtilKeyword.waitForPageLoadAndDelay'(20, 3)

'Verify added product information in cart'
TestObject addedProductProperties = new TestObject()
addedProductProperties.addProperty('xpath', ConditionType.EQUALS, "//*[*[text()='" + selectedProduct.get("name") + "']]/following-sibling::div")

WebUI.verifyElementVisible(addedProductProperties)
KeywordUtil.logInfo(WebUI.getText(addedProductProperties))

if(WebUI.getText(addedProductProperties).contains(selectedProduct.get("color") + " / " + selectedProduct.get("size"))) {
	KeywordUtil.logInfo("Add first product to cart successfully")
}else {
	KeywordUtil.markFailedAndStop("Add product to cart failed")
}

'Verify total quantity of added product'
String totalAddedQuantity = 2
String actualQuantity = WebUI.getAttribute(findTestObject('Object Repository/pages/product_detail_page/quantity_product', [('product-name'): selectedProduct.get("name"), ('same-product-order'): 1]), '_value')

if(actualQuantity.equals(totalAddedQuantity)) {
	KeywordUtil.logInfo("Quantity of added products is correctly updated in cart")
	'Take screenshot'
	CustomKeywords.'common.UtilKeyword.takeScreenShot'("Information added products matches in cart")
	
}else {
	KeywordUtil.markFailedAndStop("Quantity of added products is incorrectly updated in cart")
}

'Remove all products from cart'
CustomKeywords.'product.ProductDetail.removeAllProductFromCart'()

'Logout and close browser'
CustomKeywords.'common.UtilKeyword.logOutAndClose'()



