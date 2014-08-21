package projects.examples;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import engine.BaseTest;

public class TC001 extends BaseTest {
	@Test
	public void detailedNComplexTest() throws SQLException, ClassNotFoundException, IOException {
		recoveryHandler = "Ads";
		selenium.open("http://www.economictimes.com/");
		selenium.maximize();
		selenium.click(locator("ET", "News", "name"));
		selenium.click(locator("ET", "News By Company", "name"));
		selenium.type(locator("ET", "Search Box", "id"), "YAHOO");
		selenium.click(locator("ET", "Search Button", "id"));
	}
}
