package com.privacyfix.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.opera.core.systems.OperaDriver;

public class DriverMaster {
	
	private DriverMaster(){};
	
	public static WebDriver getDriver(String driverKey){
		BrowserType browser = BrowserType.get(driverKey);
		WebDriver driver;
		 System.setProperty("webdriver.chrome.driver", "C:/Users/Misha/.m2/repository/org/seleniumhq/selenium/selenium-chrome-driver/2.42.2/chromedriver.exe");
		 System.setProperty("webdriver.ie.driver", "C:/Users/Misha/.m2/repository/org/seleniumhq/selenium/selenium-ie-driver/2.42.2/IEDriverServer.exe");
		 switch (browser) {
		case FIREFOX:
			 driver = new FirefoxDriver();
			break;
		case CHROME:
		driver = new ChromeDriver();
			break;
		case IE:
		    driver = new InternetExplorerDriver();
		    break;
	    case OPERA:
	    	driver = new OperaDriver();
	    	break;
		default:
	   	 driver = new FirefoxDriver();
			break;
		} 
				return driver;
	}

}