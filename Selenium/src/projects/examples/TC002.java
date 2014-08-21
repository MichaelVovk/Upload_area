package projects.examples;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import engine.BaseTest;

public class TC002 extends BaseTest {
	@Test
	public void yetAnotherImplimentation() throws ClassNotFoundException, SQLException, IOException {
		recoveryHandler = "Ads";
		Browser.Navigate("http://www.economictimes.com/");
		Browser.Maximize();
		Browser.Page("ET").Link("News").Click();
		Browser.Page("ET").Link("News By Company").Click();
		Browser.Page("ET").WebEdit("Search Box").Type("YAHOO");
		Browser.Page("ET").Link("Search Button").Click();
	}
}
