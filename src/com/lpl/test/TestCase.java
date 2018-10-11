package com.lpl.test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.lpl.browser.BrowserType;
import com.lpl.device.DeviceType;
import com.lpl.ostype.OsType;
import com.lpl.testframeworktype.TestFrameworkType;
import com.lpl.utils.impl.FlatFileImpl;
import com.lpl.driver.WebDriverType;

/**
 * @author sparasha
 *
 */
public class TestCase {

	@Test
	public void testBrowserType() {

		BrowserType browser = BrowserType.Chrome;
		assertEquals(browser, BrowserType.Chrome);

		browser = BrowserType.Firefox;
		assertEquals(browser, BrowserType.Firefox);

		browser = BrowserType.InternetExplorer;
		assertEquals(browser, BrowserType.InternetExplorer);
	}

	@Test
	public void testOSType() {

		OsType ostype = OsType.Linux;
		assertEquals(ostype, OsType.Linux);

		ostype = OsType.Mac;
		assertEquals(ostype, OsType.Mac);

		ostype = OsType.Win;
		assertEquals(ostype, OsType.Win);

		ostype = OsType.Android;
		assertEquals(ostype, OsType.Android);

		ostype = OsType.Ios;
		assertEquals(ostype, OsType.Ios);
	}

	@Test
	public void testDeviceType() {

		DeviceType deviceType = DeviceType.Desktop;
		assertEquals(deviceType, DeviceType.Desktop);

		deviceType = DeviceType.Mobile;
		assertEquals(deviceType, DeviceType.Mobile);

		deviceType = DeviceType.Tabs;
		assertEquals(deviceType, DeviceType.Tabs);

	}

	@Test
	public void testFrameworkType() {
		TestFrameworkType testType = new TestFrameworkType();
		testType.setTestType("BDD");
		assertEquals(testType.getTestType(), "BDD");

	}

	@Test
	public void testDriverType() {
		WebDriverType webDriverType = WebDriverType.ChromeDriver;
		assertEquals(webDriverType, WebDriverType.ChromeDriver);
		webDriverType = WebDriverType.FirefoxDriver;
		assertEquals(webDriverType, WebDriverType.FirefoxDriver);
		webDriverType = WebDriverType.InternetExplorerDriver;
		assertEquals(webDriverType, WebDriverType.InternetExplorerDriver);

	}
	
	@Test
	public void testConfigProperties() {
		String filename = "Config.properties";
		
		com.lpl.config.ConfigProperties prop = new com.lpl.config.ConfigProperties(filename);

	}
	
	@Test
	public void testAutomationService() {
		com.lpl.service.AutomationService service = new com.lpl.service.AutomationService();
		//service.initialize("");
	}
	
	@Test 
	public void testFlatFileImplementation() {
		FlatFileImpl impl = new FlatFileImpl() ;
		impl.getTestData(1, 2);
		impl.getObjects();
	}

}
