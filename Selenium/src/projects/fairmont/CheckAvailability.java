/**
 * ------------------------------------------------------
 * TCID		: TC002
 * TCName	: Check Availability
 * Desc.	: Check hotel availability
 * ------------------------------------------------------
 * Author	: 13463
 * Dated	: 19-Dec-2012
 * Pre-cond.: Navigate to the homepage and click on check availability link
 * Edited	:
 */
package projects.fairmont;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
//import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverCommandProcessor;
//import org.openqa.selenium.WebDriverCommandProcessor.setUpMethodMap();

import org.junit.Test;
import org.openqa.selenium.By;
import org.sikuli.script.FindFailed;

import engine.BaseTest;

public class CheckAvailability extends BaseTest {
	static {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void checkAvailability() throws InterruptedException, FindFailed, SQLException, ClassNotFoundException, IOException {

		/**
		 * --------------- Pre-conditions --------------- Author : 13463 Dated :
		 * 19-Dec-2012 Pre-cond.: Navigate to the homepage and click on Check
		 * Availability link Edited :
		 */
		generateReports = getClass().getSimpleName().toString();
		selenium.open("http://www.fairmont.com");
		selenium.maximize();		
		if (selenium.isElementPresent("id=" + locator("Fairmont", "Check Available", "id"))) {
			selenium.click("id=" + locator("Fairmont", "Check Available", "id"));
		} else {
			log.fail("Could not find the link Check Availability");
			bTestResult = false;
		}
		selenium.sync();

		// Read from test data
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con = DriverManager.getConnection("jdbc:odbc:complete");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM [TC002$]");

		while (rs.next()) {

			String runId = rs.getString(1);
			String destination = rs.getString(2);
			String hotel = rs.getString(3);
			String arrivalDate = rs.getString(4);
			String depDate = rs.getString(5);
			String title = rs.getString(6);
			String Fname = rs.getString(7);
			String Mname = rs.getString(8);
			String Lname = rs.getString(9);
			String AFname = rs.getString(10);
			String ALname = rs.getString(11);
			String telephone = rs.getString(12);
			String email = rs.getString(13);
			String offer = rs.getString(14);
			String mail = rs.getString(15);
			String Bsname = rs.getString(16);
			String add1 = rs.getString(17);
			String add2 = rs.getString(18);
			String add3 = rs.getString(19);
			String city = rs.getString(20);
			String country = rs.getString(21);
			String state = rs.getString(22);
			String zip = rs.getString(23);
			String Pcnum = rs.getString(24);
			String Fanum = rs.getString(25);
			String resplus = rs.getString(26);
			String iata = rs.getString(27);
			String airline = rs.getString(28);
			String airText = rs.getString(29);
			String Smokingpreference = rs.getString(30);
			String Bed = rs.getString(31);
			String Paperpreference = rs.getString(32);
			String BusinessNeeds = rs.getString(33);
			String Preferroomonlowfloor = rs.getString(34);
			String Preferoomnearelevator = rs.getString(35);
			String Preferfoampillow = rs.getString(36);
			String Hearingimpaired = rs.getString(37);
			String Visuallyimpaired = rs.getString(38);
			String Donotuseawheelchair = rs.getString(39);
			String Useawheelchair = rs.getString(40);
			String Specialrequest = rs.getString(41);
			String time = rs.getString(42);
			String amorpm = rs.getString(43);
			String creditcardno = rs.getString(44);
			String creditcardtype = rs.getString(45);
			String Expmonth = rs.getString(46);
			String Expyear = rs.getString(47);
			String Iagree = rs.getString(48);
			String ReceivedByemail = rs.getString(49);

			// System.out.print("");
			// Checkpoint 1 : Check for loading image
			log.pass("Executing for RunID:" + runId);
			selenium.dropdown("id=" + locator("Fairmont", "Destination", "id"), destination);
			selenium.dropdown("id=" + locator("Fairmont", "Hotel Name", "id"), hotel);
			selenium.click(locator("Fairmont", "Arrival Date", "id"));
			// selenium.clickLinkWithText("20");
			driver.findElement(By.linkText("23")).click();
			selenium.click(locator("Fairmont", "Departure Date", "id"));
			// selenium.clickLinkWithText("24");
			driver.findElement(By.linkText("25")).click();
			// selenium.waitForPageToLoad("30000");
			selenium.click(locator("Fairmont", "Check Availability", "id"));
			if (selenium.isTextPresent("We are searching for available rooms. Thank you for your patience.")) {
				bTestResult = true;
				log.pass("Successfully verified the loading image.");
			} else {
				log.fail("Could not find the loading image.");
				bTestResult = false;
			}

			// Checkpoint 2 : Check system navigates to Select Room page
			bTestResult = selenium.isElementPresent("id=" + locator("Select a room", "Select a room title", "id"));
			if (bTestResult) {
				log.pass("Found the title of the Select room page");
				bTestResult = selenium.isTextPresent("Select a Room");
				if (bTestResult) {
					log.pass("Successfully verified the text - Select a Room");
				} else {
					log.fail("Error occured while testing title in the Select room page");
					bTestResult = false;
				}
			}
			selenium.click("link=" + locator("Select a room", "Best Available Rate", "name"));
			selenium.click(locator("Select a room", "Select room & continue", "id"));
			selenium.click("id=" + locator("Enhance", "skip", "id"));
			selenium.select(locator("Complete Details", "Title", "id"), title);
			selenium.type(locator("Complete Details", "First Name", "id"), Fname);
			try {
				selenium.type(locator("Complete Details", "Middle Name", "id"), Mname);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("the error is" + e);
			}
			selenium.type(locator("Complete Details", "Last Name", "id"), Lname);
			try {
				selenium.type(locator("Complete Details", "Alternate First Name", "id"), AFname);
				selenium.type(locator("Complete Details", "Alternate Last Name", "id"), ALname);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("the error is" + e);

			}
			selenium.type(locator("Complete Details", "Telephone", "id"), telephone);
			selenium.type(locator("Complete Details", "Email", "id"), email);
			selenium.check(locator("Complete Details", "yes", "id"), offer);
			if (mail.compareToIgnoreCase("Home") == 0)
				selenium.click(locator("Complete Details", "Mail Address home", "id"));
			else
				selenium.click(locator("Complete Details", "Mail Address business", "id"));

			try {
				selenium.type(locator("Complete Details", "Business Name", "id"), Bsname);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("the error is" + e);
			}
			selenium.type(locator("Complete Details", "Address1", "id"), add1);
			try {
				selenium.type(locator("Complete Details", "Address2", "id"), add2);
				selenium.type(locator("Complete Details", "Address3", "id"), add3);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("the error is" + e);
			}
			selenium.type(locator("Complete Details", "City", "id"), city);
			try {
				selenium.select(locator("Complete Details", "Country", "id"), country);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("the error is" + e);

				selenium.select(locator("Complete Details", "State1", "id"), state);
			}
			selenium.type(locator("Complete Details", "State", "id"), state);
			// selenium.type(locator("Complete Details","zip","id"),zip);
			// selenium.type(locator("Complete Details","zip","id"),zip);
			selenium.type(locator("Complete Details", "zip", "id"), zip);

			try {
				selenium.type(locator("Complete Details", "President club #", "id"), Pcnum);
				selenium.type(locator("Complete Details", "Famous Agents #", "id"), Fanum);
				selenium.type(locator("Complete Details", "ResPlus #", "id"), resplus);
				selenium.type(locator("Complete Details", "IATA Code", "id"), iata);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("the error is" + e);
			}

			if (selenium.isTextPresent("Frequent flyer membership for stay credit ")) {

				try {
					selenium.choose(locator("Complete Details", "Airline", "id"), airline, null);
					selenium.choose(locator("Complete Details", "Airline text", "id"), airline, airText);
					// selenium.type(locator("Complete Details","Airline","id"),
					// );
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("the error is" + e);
				}
			} else {
				selenium.click(locator("Complete Details", "Airline Partner ", "id"));
				try {
					selenium.choose(locator("Complete Details", "Airline", "id"), airline, null);
					selenium.choose(locator("Complete Details", "Airline text", "id"), airline, airText);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("the error is" + e);
				}
			}
			// webbrowser.fireEvent(locator("Complete Details","Submit","id"),
			// "click");
			try {
				selenium.choose(locator("Complete Details", "Smoking preference", "id"), Smokingpreference, null);
				selenium.choose(locator("Complete Details", "Bed", "id"), Bed, null);
				selenium.choose(locator("Complete Details", "Paperpreference", "id"), Paperpreference, null);
				// System.out.println();
				selenium.check(locator("Complete Details", "Business Needs", "id"), BusinessNeeds);
				selenium.check(locator("Complete Details", "Prefer room on low floor", "id"), Preferroomonlowfloor);
				selenium.check(locator("Complete Details", "Prefer room near elevator", "id"), Preferoomnearelevator);
				selenium.check(locator("Complete Details", "Prefer foam pillow", "id"), Preferfoampillow);
				selenium.check(locator("Complete Details", "Hearing impaired", "id"), Hearingimpaired);
				selenium.check(locator("Complete Details", "Visually impaired", "id"), Visuallyimpaired);
				selenium.check(locator("Complete Details", "Do not use a wheelchair", "id"), Donotuseawheelchair);
				selenium.check(locator("Complete Details", "Use a wheelchair", "id"), Useawheelchair);
				selenium.type(locator("Complete Details", "Special request", "id"), Specialrequest);
				selenium.select(locator("Complete Details", "Time", "id"), time);
				selenium.select(locator("Complete Details", "amorpm", "id"), amorpm);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("the error is" + e);
			}
			selenium.select(locator("Complete Details", "CreditCardType", "id"), creditcardno);
			selenium.type(locator("Complete Details", "CreditCardNumber", "id"), creditcardtype);
			selenium.select(locator("Complete Details", "ExpMonth", "id"), Expmonth);
			selenium.select(locator("Complete Details", "ExpYear", "id"), Expyear);
			selenium.check(locator("Complete Details", "IAgree", "id"), Iagree);
			selenium.choose(locator("Complete Details", "ReciveByEmail", "id"), ReceivedByemail, null);

			selenium.highlight(locator("Complete Details", "Submit", "id"));

			selenium.click("css=#ctl00_ctl00_Content_FooterButton_btnSubmitDetails > span.left > span.right");
			if (webbrowser.isAlertPresent()) {
				// String a = webbrowser.getAlert();
				log.pass(webbrowser.getAlert());
				bTestResult = false;
			} else {

				// log.info(webbrowser.getAlert());
				// log.info(webbrowser.getSelectedLabel(selectLocator)
				if (selenium.isTextPresent("Your reservation was completed successfully.")) {
					// selenium.highlight(locator("Confirmation","success","name"));
					// System.out.println("reservation was completed successfully");
					System.out.println("Your reservation was completed successfully.");
				}
				bTestResult = true;
				// Selenium cls = new
				// WebDriverBackedSelenium(driver,"http://www.fairmont.com/reservations/survey/");
				// cls.close();

				// assertTrue(selenium.isTextPresent("Survey Thank you")
				/*
				 * if(selenium.isTextPresent(
				 * "Survey Thank you, your reservation has been confirmed and an email confirmation has been sent. If you have a moment, please complete our online survey. \n Please rate the ease of use of our website as it relates to locating promotions, finding locations and making reservations.\n poor 1 2 3 4 5 outstanding     Please rate your overall online booking experience\n poor 1 2 3 4 5 outstanding     Thank you for helping us to improve the quality of your online experience.\n Comments"
				 * )){ webbrowser.windowFocus();
				 * selenium.click("id=hrefClosePopUp"); }
				 */
				// selenium.getTitle();
				// webbrowser.windowFocus();
				/*
				 * webbrowser.selectWindow(""); // assertTrue(
				 * "Reservation Survey - Fairmont Hotels & Resorts - Fairmont"
				 * ,true); // webbrowser.windowFocus(); //
				 * selenium.click("id=hrefClosePopUp"); assertEquals(
				 * "Reservation Survey - Fairmont Hotels & Resorts - Fairmont - Mozilla Firefox"
				 * , selenium.getTitle()); //webbrowser.windowFocus();
				 * selenium.click("id=hrefClosePopUp");
				 */
				// webbrowser.selectWindow("Reservation Survey - Fairmont Hotels & Resorts - Fairmont - Mozilla Firefox");
				// webbrowser.getAllWindowIds();
				// webbrowser.getAllWindowNames();
				// .getTitle();
				// driver.switchTo().window("Reservation Survey - Fairmont Hotels & Resorts - Fairmont");
				// driver.findElement(By.id("hrefClosePopUp")).click();
				/*
				 * try {
				 * assertTrue(driver.findElement(By.cssSelector("BODY")).getText
				 * ().matches(
				 * "^[\\s\\S]*Survey Thank you, your reservation has been confirmed and an email confirmation has been sent\\. If you have a moment, please complete our online survey\\. \n Please rate the ease of use of our website as it relates to locating promotions, finding locations and making reservations\\.\n poor 1 2 3 4 5 outstanding     Please rate your overall online booking experience\n poor 1 2 3 4 5 outstanding     Thank you for helping us to improve the quality of your online experience\\.\n Comments[\\s\\S]*$"
				 * )); } catch (Error e) { System.out.println("error");
				 * //verificationErrors.append(e.toString()); } // ERROR: Caught
				 * exception [ERROR: Unsupported command [windowFocus | | ]]
				 * driver.findElement(By.id("hrefClosePopUp")).click(); //
				 * ERROR: Caught exception [ERROR: Unsupported command
				 * [windowFocus | | ]]
				 */
				String parentHandle = driver.getWindowHandle();

				// after you have pop ups
				for (String popUpHandle : driver.getWindowHandles()) {
					if (!popUpHandle.equals(parentHandle)) {
						driver.switchTo().window(popUpHandle);
						if (driver.getCurrentUrl().equalsIgnoreCase("http://www.fairmont.com/reservations/survey/")) {
							driver.findElement(By.id("hrefClosePopUp")).click();
							driver.switchTo().window(parentHandle);
							// do something here
						}
					}
				}

				/*
				 * String parentWindowHandle = driver.getWindowHandle(); // save
				 * the current window handle. WebDriver popup = null;
				 * Iterator<String> windowIterator = (Iterator<String>)
				 * driver.getWindowHandles(); while(windowIterator.hasNext()) {
				 * String windowHandle = windowIterator.next(); popup =
				 * driver.switchTo().window(windowHandle); if
				 * (popup.getTitle().equals
				 * ("Reservation Survey - Fairmont Hotels & Resorts - Fairmont"
				 * )) { break; } }
				 * 
				 * 
				 * //action in popup "Google".
				 * popup.findElement(By.id("hrefClosePopUp")).click(); //action
				 * in popup "Google". //
				 * popup.findElement(By.name("btnG")).submit();
				 * 
				 * 
				 * driver.close(); // close the popup.
				 * driver.switchTo().window(parentWindowHandle);
				 */

			}

			// webbrowser.submit("form-body");
			webbrowser.open("http://www.fairmont.com");
		}
		st.close();
		con.close();
		log.pass("Executed for all iterations; exiting the test case");
		assertTrue(bTestResult);
		// webbrowser.open("http://www.fairmont.com");

	}
}
