/**
 * ------------------------------------------------------
 * TCID		: TC001
 * TCName	: Destination Maps
 * Desc.	: Check for flash map and fairmont locations
 * ------------------------------------------------------
 * Author	: 13463
 * Dated	: 18-Dec-2012
 * Pre-cond.: Navigate to the homepage and click on Destination maps link
 * Edited	:
 */
package projects.fairmont;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
//import org.openqa.selenium.JavascriptExecutor;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import engine.BaseTest;

public class DestinationMaps extends BaseTest {

	static {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public String screenshotFilePath = "";

	public boolean mapOnLoad() throws IOException {
		float similarity = (float) 0.7;
		boolean bActionResult = false;
		int uiTimeout = 20;
		Screen screen = new Screen();
		int clickCount = 0;
		Pattern onLoadImage = new Pattern("patterns/image_checkpoints/Fairmont/onLoad.png");
		if (screen.exists(onLoadImage.similar(similarity), uiTimeout) != null) {
			try {
				log.pass("Successfully verified the onload image");
				Pattern resetImage = new Pattern("patterns/image_checkpoints/Fairmont/resetImage.png");
				while (screen.exists(resetImage.similar(similarity), uiTimeout) == null) {
					Pattern zoomInButton = new Pattern("patterns/image_checkpoints/Fairmont/zoomIn.png");
					screen.click(zoomInButton, 0);
					clickCount++;
				}
				log.pass("Reset image loaded on " + clickCount + " click(s)");
				screen.click(resetImage, 0);
				bActionResult = true;
			} catch (FindFailed e) {
				e.printStackTrace();
				bActionResult = false;
				log.fail("Screenshot stored at " + screenshotFilePath);
			}
		}
		return bActionResult;
	}

	@Test
	public void checkMaps() throws InterruptedException, FindFailed, SQLException, ClassNotFoundException, IOException {

		/**
		 * --------------- Pre-conditions --------------- Author : 13463 Dated :
		 * 18-Dec-2012 Pre-cond.: Navigate to the homepage and click on
		 * Destination maps link Edited :
		 */
		generateReports = getClass().getSimpleName().toString();
		selenium.open("http://www.frhi.com/");
		selenium.maximize();
		if (selenium.isElementPresent("id=" + locator("Home", "Destination Maps", "id"))) {
			selenium.click("id=" + locator("Home", "Destination Maps", "id"));
		} else {
			log.fail("Could not find the link Destination Maps");
			bTestResult = false;
		}
		selenium.sync();

		// Read from test data
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con = DriverManager.getConnection("jdbc:odbc:TCManager");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM [TC001$]");

		while (rs.next()) {

			String runId = rs.getString(1);
			String checkMaps = rs.getString(2);
			String country = rs.getString(3);
			String state = rs.getString(4);
			String hotel = rs.getString(5);

			// Checkpoint 1 : Check for flash map
			log.pass("Executing for RunID:" + runId);
			if (checkMaps.compareToIgnoreCase("yes") == 0) {
				bTestResult = mapOnLoad();
				if (bTestResult) {
					log.pass("Tested the functionality of maps, executing locations checks");
				} else {
					log.fail("Error occured while testing functionality of maps");
					bTestResult = false;
				}
			}

			// Checkpoint 2 : Check for headline
			bTestResult = selenium.isTextPresent("An extraordinary collection of the most diverse\n& exciting hotels & destinations in the world.");
			if (bTestResult) {
				log.pass("Successfully verified the text - An extraordinary collection...");
			} else {
				log.fail("Error occured while testing functionality of maps; Aborting this test");
				bTestResult = false;
			}

			// Checkpoint 3 : Check for locations - Country
			bTestResult = selenium.isTextPresent(country);
			if (bTestResult) {
				log.pass("Found location " + country.toUpperCase() + ".");
				// Checkpoint 4 : Check for locations - State
				bTestResult = selenium.isTextPresent(state);
				if (bTestResult) {
					log.pass("Found location " + state + ".");
					// Checkpoint 5 : Check for locations - Hotel
					bTestResult = selenium.isTextPresent(hotel);
					if (bTestResult) {
						log.pass("Found hotel " + hotel + ".");
					} else {
						log.fail("Could not find the hotel " + hotel);
						bTestResult = false;
					}
				} else {
					log.fail("Could not find the state " + state);
					bTestResult = false;
				}
			} else {
				log.fail("Could not find the location " + country.toUpperCase());
				bTestResult = false;
			}

		}
		st.close();
		con.close();
		log.pass("Executed for all iterations; exiting the test case");
		assertTrue(bTestResult);
	}
}
