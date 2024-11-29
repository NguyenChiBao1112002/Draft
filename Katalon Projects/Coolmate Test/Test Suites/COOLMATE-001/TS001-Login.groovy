import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.annotation.TearDownTestCase

import groovy.console.ui.SystemOutputInterceptor
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver


/**
 * Setup test suite environment.
 */
@SetUp(skipped = true)
def setUp() {
	// Put your code here.
}

/**
 * Clean test suites environment.
 */
@TearDown(skipped = true)
def tearDown() {
	// Put your code here.
}

/**
 * Run before each test case starts.
 */
@SetupTestCase(skipped = false)
def setupTestCase() {
	'Set up ChromeDriver with options'
	String basePath = RunConfiguration.getProjectDir()
	System.setProperty("webdriver.chrome.driver", basePath + "/Drivers/chromedriver131.exe")
	
	'Open in Incognito mode'
	ChromeOptions options = new ChromeOptions()
	options.addArguments("--incognito")
	
	'Set WebDriver for Katalon'
	WebDriver driver = new ChromeDriver(options)
	DriverFactory.changeWebDriver(driver)
	
	'Open the Coolmate website'
	driver.get(GlobalVariable.url)
	WebUI.waitForPageLoad(10)
	WebUI.maximizeWindow()
	
	'Handle Popup if present'
	if(WebUI.waitForElementVisible(findTestObject('Object Repository/pages/home_page/ads_close_button'), 10)) {
		WebUI.click(findTestObject('Object Repository/pages/home_page/ads_close_button'))
	}
}

/**
 * Run after each test case ends.
 */
@TearDownTestCase(skipped = false)
def tearDownTestCase() {
	'Close browser'
	WebUI.closeBrowser()
}
