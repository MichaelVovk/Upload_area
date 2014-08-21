package utilities;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import recovery.Recovery;

import com.thoughtworks.selenium.SeleniumCommandTimedOutException;

import engine.BaseTest;
import engine.ReportHandler;

public class Selenium extends BaseTest {
	public static String sValue;
	public static boolean flag;
	public static int count;
	public static int[] dimCount;
	
	public void open(String sUrl) throws IOException {
		log = new ReportHandler(generateReports);
		webbrowser.open(sUrl);
		sync();
		log.pass("Opened " + sUrl);
		bExeResult = true;
	}

	public void click(String slocator) throws IOException {
		try {
			waitUntilElementPresent(slocator);
			selenium.highlight(slocator);
			webbrowser.click(slocator);
			log.pass("Clicked " + slocator);
			bExeResult = true;
			sync();
		} catch (Exception e) {
			if(recoveryHandler != ""){
				Recovery recoveryHandlerObject = new Recovery(recoveryHandler);
				webbrowser.click(slocator);
				log.pass("Clicked " + slocator);
				bExeResult = true;
				sync();
			} else {
				log.fail("Exception while clicking on the locator " + slocator);
				bExeResult = false;
			}
		}
	}

	public boolean waitUntilElementPresent(String slocator) throws IOException {
		boolean bResult = false;
		Long maxWait = timeout;
		int secsWaited = 0;
		try {
			do {
				Thread.sleep(100);
				secsWaited++;
				if (isElementPresent(slocator)) {
					bResult = true;
					break;
				}
			} while (secsWaited < (maxWait * 10));
			Thread.sleep(100);
		} catch (Exception e) {
			log.warning("Exception caught while waiting for the page to load ");
			bResult = false;
		}
		return bResult;
	}

	public boolean sync() throws IOException {
		boolean bResult = false;
		Long maxWait = timeout;
		int secsWaited = 0;
		try {
			do {
				Thread.sleep(100);
				secsWaited++;
				if (isBrowserLoaded()) {
					bResult = true;
					break;
				}
			} while (secsWaited < (maxWait * 10));
			Thread.sleep(100);
			log.info("Waited for " + secsWaited + " secs to complete the request ");
		} catch (Exception e) {
			log.fail("Exception caught while waiting for the page to load ");
			bResult = false;
		}
		return bResult;
	}

	public boolean isBrowserLoaded() throws Exception {
		try {
			if ((Boolean.parseBoolean(webbrowser.getEval("(\"complete\" == selenium.browserbot.getCurrentWindow().document.readyState)")))) {
				return true;
			} else {
				log.pass("Syncing for page to load.");
				return false;
			}
		} catch (SeleniumCommandTimedOutException e) {
			e.getMessage();
			return false;
		}
	}

	public void type(String slocator, String value) throws IOException {
		webbrowser.type(slocator, value);
		log.pass("Typed " + value + " at " + slocator);
	}

	public String storeText(String slocator) throws IOException {
		sValue = webbrowser.getText(slocator);
		log.pass("Stored " + sValue);
		return sValue;
	}

	public void waitForPageToLoad(String timeout) throws IOException {
		webbrowser.waitForPageToLoad(timeout);
		log.pass("Waited " + timeout + "for page to load");
	}

	public boolean isElementPresent(String slocator) {
		if (webbrowser.isElementPresent(slocator)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean isTextPresent(String slocator) {
		if (webbrowser.isTextPresent(slocator)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public String getText(String slocator) throws IOException {
		sValue = webbrowser.getText(slocator);
		log.pass("Extracted text " + sValue + " from locator " + slocator);
		return sValue;
	}

	public String getTitle() throws IOException {
		sValue = webbrowser.getTitle();
		log.pass("Extracted title " + sValue);
		return sValue;
	}

	public String getSelectedLabel(String selectLocator) throws IOException {
		sValue = webbrowser.getSelectedLabel(selectLocator);
		log.pass("Extracted selected label " + sValue);
		return sValue;
	}

	public int getXpathCount(String xpath) throws IOException {
		count = Integer.parseInt(String.valueOf(webbrowser.getXpathCount(xpath)));
		log.pass("Extracted xpath count " + count);
		return count;
	}

	public int[] getTableDimCount(String xpath) throws IOException {
		dimCount[0] = Integer.parseInt(String.valueOf(webbrowser.getXpathCount(xpath)));
		dimCount[1] = Integer.parseInt(String.valueOf(webbrowser.getXpathCount(xpath + "/td")));
		log.pass("Extracted xpath count " + count);
		return dimCount;
	}

	public void waitForCondition(String condition, String timeout) throws IOException {
		webbrowser.waitForCondition(condition, timeout);
		log.pass("Timed out after waiting for condition");
	}

	public void submit(String formLocator) throws IOException {
		webbrowser.submit(formLocator);
		log.pass("Submitted form with name " + formLocator);
	}

	public void clickLinkWithText(String linkText) throws IOException {
		driver.findElement(By.linkText(linkText));
		log.pass("Clicked on " + linkText);
	}

	public void highlight(String sValue) throws IOException {
		webbrowser.highlight(sValue);
		log.pass("Highlighting " + sValue);
	}

	public void maximize() throws IOException {
		try {
			driver.manage().window().maximize();
			log.pass("Maximized the browser window");
		} catch (Exception e) {
			log.fail("Sorry could not maximze the window - " + e);
		}

	}

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight," + "document.body.scrollHeight,document.documentElement.clientHeight));");
	}

	public void select(String selectLocator, String optionLocator) throws IOException {
		webbrowser.select(selectLocator, optionLocator);
		log.pass("Selected option " + optionLocator + " on drop-down " + selectLocator);
	}

	public void check(String slocator, String status) {

		if (status.compareToIgnoreCase("yes") == 0) {
			webbrowser.check(slocator);
		}
	}

	public void dropdown(String slocator, String arg) {
		webbrowser.select(slocator, arg);
	}

	public void choose(String slocator, String arg, String a) {

		if (slocator.contains(delimiter)) {
			String value;
			value = slocator.replaceAll(delimiter, arg);
			webbrowser.click(value);
			webbrowser.type(value, a);
		}
	}

	public void goback() throws IOException {
		webbrowser.goBack();
		log.pass("click on back");
	}
	
	public void quit() throws IOException{
		log.closeLogs();
	}
	
	public void generateReport() throws MalformedURLException, IOException{
		log.generateReport();
	}
}
