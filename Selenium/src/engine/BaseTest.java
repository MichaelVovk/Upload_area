package engine;

import java.awt.Desktop;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utilities.QTP;
import utilities.Selenium;
import datapool.ObjectMap;

public class BaseTest {

	public static WebDriver driver;
	public static WebDriverBackedSelenium webbrowser;
	
	public static QTP Browser = new QTP();
	public static Selenium selenium = new Selenium();
	public static String globalUrl = "https://www.google.com";
	public static ReportHandler log;
	
	public static String locator = "";
	public static String sBrowser = "";
	public static String recoveryHandler = "";
	public static boolean bTestResult = false;
	public static boolean bExeResult = false;

	public static Long timeout;
	public static Long br_timeout;
	public static String delimiter;
	public static String testDataFile;
	public static String objectRepoFile;
	public static String generateReports = "";
	
	@BeforeClass
	public static void setUp() throws Exception {

		final XmlReader config = new XmlReader("config.xml","Appconfig");
		delimiter = config.child("delimiter").content();
		br_timeout = Long.parseLong(config.child("timeout").content());
		sBrowser = config.child("browser").content();
   		
		if (sBrowser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if ((sBrowser.equalsIgnoreCase("Firefox")) || (sBrowser.equalsIgnoreCase("Mozilla")) || (sBrowser.equalsIgnoreCase("Mozilla Firefox"))) {
			driver = new FirefoxDriver();
		} else if (sBrowser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			driver = new FirefoxDriver();
		}
		webbrowser = new WebDriverBackedSelenium(driver, globalUrl);
		driver.manage().timeouts().setScriptTimeout(br_timeout, TimeUnit.SECONDS);		
		timeout = br_timeout;
	}

	@AfterClass
	public static void tearDown() throws Exception {
		selenium.quit();
		selenium.generateReport();
		driver.quit();
	}

	public static String locator(String sPage, String sElement, String sSelector) throws ClassNotFoundException, IOException {
		try {
			locator = ObjectMap.fetchActiveRecord(sSelector, "[ObjectRepo$]", "page='" + sPage + "' AND element='" + sElement + "'");
		} catch (SQLException e) {
			log.fail("SQL Exception occured:" + e.getMessage());
			e.printStackTrace();
		}
		return locator;
	}

	public static String dynamicLocator(String sPage, String sElement, String sSelector, int iIndex) throws ClassNotFoundException, SQLException, IOException {
		locator = locator(sPage, sElement, sSelector);
		if (locator.contains(delimiter)) {
			locator = locator.replace(delimiter, "" + iIndex);
		}
		return locator;
	}

}
