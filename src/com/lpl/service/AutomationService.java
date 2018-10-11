package com.lpl.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.lpl.browser.BrowserType;
import com.lpl.config.ConfigProperties;
import com.lpl.device.DeviceType;
import com.lpl.driver.WebDriverType;
import com.lpl.ostype.OsType;
import com.lpl.testdata.TestData;
import com.lpl.testframeworktype.TestFrameworkType;

import cucumber.api.cli.Main;

/**
 * @author sparasha
 *
 */
public class AutomationService {
	TestFrameworkType type = new TestFrameworkType();
	BrowserType browswerType;
	DeviceType deviceType;
	WebDriverType webdriverType;
	OsType ostype;
	TestData testDataType = new TestData();
	WebDriver driver;
	private byte exitstatus;

	public WebDriver initialize(String configFileName) {
		String uiTest = "uitest";
		ConfigProperties properties = new ConfigProperties(configFileName);
		type.setTestType(properties.getTestFrameworkType());

		if (properties.getBrowserType().equalsIgnoreCase("Chrome")) {
			browswerType = BrowserType.Chrome;
			webdriverType = WebDriverType.ChromeDriver;
			System.out.println("-------------40---------------C");
		} else if (properties.getBrowserType().equalsIgnoreCase("Firefox")) {
			browswerType = BrowserType.Firefox;
			webdriverType = WebDriverType.FirefoxDriver;
			System.out.println("-------------40---------------F");
		} else if (properties.getBrowserType().equalsIgnoreCase("InternetExplorer")) {
			browswerType = BrowserType.InternetExplorer;
			webdriverType = WebDriverType.InternetExplorerDriver;
		} else if (properties.getBrowserType().equalsIgnoreCase("htmlunit")) {
			browswerType = BrowserType.htmlunit;
			webdriverType = WebDriverType.HtmlUnitDriver;
		} else if (properties.getBrowserType().equalsIgnoreCase("headless")) {
			browswerType = BrowserType.headless;
			webdriverType = WebDriverType.ChromeDriver;
		}

		if (properties.getDevice().equalsIgnoreCase("desktop")) {
			deviceType = DeviceType.Desktop;
		} else if (properties.getDevice().equalsIgnoreCase("Mobile")) {
			deviceType = DeviceType.Mobile;
		} else if (properties.getDevice().equalsIgnoreCase("tabs")) {
			deviceType = DeviceType.Tabs;
		}

		if (properties.getOS().equalsIgnoreCase("mac")) {
			ostype = OsType.Mac;
		} else if (properties.getOS().equalsIgnoreCase("Android")) {
			ostype = OsType.Android;
		} else if (properties.getOS().equalsIgnoreCase("Ios")) {
			ostype = OsType.Ios;
		} else if (properties.getOS().equalsIgnoreCase("Linux")) {
			ostype = OsType.Linux;
		} else if (properties.getOS().equalsIgnoreCase("Win")) {
			ostype = OsType.Win;
		}

		if (ostype == OsType.Win && type.getTestType().equalsIgnoreCase(uiTest) && browswerType == BrowserType.Chrome) {
			System.setProperty("webdriver.chrome.driver", "C:\\BrowserServers\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (ostype == OsType.Win && type.getTestType().equalsIgnoreCase(uiTest)
				&& browswerType == BrowserType.Firefox) {
			System.setProperty("webdriver.firefox.bin",
					"C:\\Users\\bkhimani\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			System.setProperty("webdriver.gecko.driver", "C:\\BrowserServers\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (ostype == OsType.Win && type.getTestType().equalsIgnoreCase(uiTest)
				&& browswerType == BrowserType.htmlunit) {
			driver = new HtmlUnitDriver();
			System.out.println("---------86-------");
		} else if (ostype == OsType.Win && type.getTestType().equalsIgnoreCase(uiTest)
				&& browswerType == BrowserType.headless) {

			System.setProperty("webdriver.chrome.driver", "C:\\BrowserServers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1200x600");
			driver = new ChromeDriver(options);
		}

		
		  driver.manage().window().maximize(); driver.manage().deleteAllCookies();
		  driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 
		System.out.println("Browser is " + browswerType);
		return driver;

	}

	public WebDriver execute() {

		if (ostype == OsType.Win && type.getTestType().equalsIgnoreCase("restapi")) {
			// invoke a test script
			String[] argv = new String[] { "-g", "", "./Feature/Feature.feature" };
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			try {
				exitstatus = Main.run(argv, contextClassLoader);
			} catch (IOException e) {

				e.printStackTrace();
			}

		} else if (ostype == OsType.Win && type.getTestType().equalsIgnoreCase("uitest")) {
			System.setProperty("webdriver.chrome.driver", "BrowserServers\\chromedriver.exe");
			driver = new ChromeDriver();

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		return driver;
	}

}
