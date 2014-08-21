package projects.examples;

import java.io.IOException;

import org.junit.Test;

import engine.BaseTest;

public class QuickTest extends BaseTest {
	@Test
	public void quickNDirtyTest() throws IOException {
		generateReports = getClass().getSimpleName().toString();
		// Paste your selenium code here, if any problem persists use
		// Ctrl+Shift+O and save.
		selenium.open("http://www.economictimes.com/");
		if (selenium.isElementPresent("id=navl")) {
			selenium.click("link=Juice");
		}
		selenium.sync();		
	}
}