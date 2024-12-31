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
import org.openqa.selenium.Keys as Keys

'Go to Coolmate website and authenticate'
CustomKeywords.'common.UtilKeyword.openURLAndAuthenticate'(GlobalVariable.username_bao, GlobalVariable.password_bao)

'=============Add a product with many color and size pairs from the product detail page and verify added product in cart================='
'Scroll to the product'
WebUI.scrollToElement(findTestObject('Object Repository/pages/home_page/product_section_index', [('section-pos'): 1, ('index'): 2]), 10)
WebUI.delay(3)

'Go to product detail page'
WebUI.click(findTestObject('Object Repository/pages/home_page/product_section_index', [('section-pos'): 1, ('index'): 2]))
CustomKeywords.'common.UtilKeyword.waitForPageLoadAndDelay'(20, 3)

'Add product to cart with the first color and size pair'
CustomKeywords.'product.ProductDetail.selectProductColorWithPosition'(1)
CustomKeywords.'product.ProductDetail.selectProductSizeWithPosition'(1)
WebUI.click(findTestObject('Object Repository/pages/product_detail_page/add_to_cart_button'))

'Handle toast message after adding product to cart'
CustomKeywords.'common.UtilKeyword.handleToastMessage'("Đã thêm vào giỏ hàng!", 60)

'Take screenshot'
CustomKeywords.'common.UtilKeyword.takeScreenShot'('added_product_first_option')
WebUI.delay(3)

'Add product to cart with the second color and size pair'
CustomKeywords.'product.ProductDetail.selectProductColorWithPosition'(2)
CustomKeywords.'product.ProductDetail.selectProductSizeWithPosition'(2)
WebUI.click(findTestObject('Object Repository/pages/product_detail_page/add_to_cart_button'))

'Handle toast message after adding product to cart'
CustomKeywords.'common.UtilKeyword.handleToastMessage'("Đã thêm vào giỏ hàng!", 60)

'Take screenshot'
CustomKeywords.'common.UtilKeyword.takeScreenShot'('added_product_second_option')

'Collect the first selected option pair'
HashMap<String, String> selectedFirstOption = new HashMap()

selectedFirstOption.put('name', CustomKeywords.'product.ProductDetail.getProductName'())

selectedFirstOption.put('color', CustomKeywords.'product.ProductDetail.getProductColorWithPosition'(1))

selectedFirstOption.put('size', CustomKeywords.'product.ProductDetail.getProductSizeWithPosition'(1))

//selectedFirstOption.put('size', CustomKeywords.'product.ProductDetail.getProductName'())


KeywordUtil.logInfo(selectedFirstOption.toString())



//HashMap<String, String> selectedFirstOption = new HashMap()



//'Go to cart'
//WebUI.click(findTestObject('Object Repository/common/icon/cart_icon'))
//CustomKeywords.'common.UtilKeyword.waitForPageLoadAndDelay'(20, 3)
//
//'Verify added product information in cart'
//TestObject addedProductProperties = new TestObject()
//addedProductProperties.addProperty('xpath', ConditionType.EQUALS, "//*[*[text()='" + selectedProduct.get("name") + "']]/following-sibling::div")
//
//WebUI.verifyElementVisible(addedProductProperties)
//KeywordUtil.logInfo(WebUI.getText(addedProductProperties))
//
//if(WebUI.getText(addedProductProperties).contains(selectedProduct.get("color") + " / " + selectedProduct.get("size"))) {
//	KeywordUtil.logInfo("Add first product to cart successfully")
//}else {
//	KeywordUtil.markFailedAndStop("Add product to cart failed")
//}
//
//'Verify total quantity of added product'
//String totalAddedQuantity = 2
//String actualQuantity = WebUI.getAttribute(findTestObject('Object Repository/pages/product_detail_page/quantity_product', [('product-name'): selectedProduct.get("name"), ('same-product-order'): 1]), '_value')
//
//if(actualQuantity.equals(totalAddedQuantity)) {
//	KeywordUtil.logInfo("Quantity of added products is correctly updated in cart")
//	'Take screenshot'
//	CustomKeywords.'common.UtilKeyword.takeScreenShot'("info_added_product_matches_in_cart")
//	
//}else {
//	KeywordUtil.markFailedAndStop("Quantity of added products is incorrectly updated in cart")
//}
//
//'Remove all products from cart'
//CustomKeywords.'product.ProductDetail.removeAllProductFromCart'()
//
//'Logout and close browser'
//CustomKeywords.'common.UtilKeyword.logOutAndClose'()




