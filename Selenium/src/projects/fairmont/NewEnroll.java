package projects.fairmont;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import engine.BaseTest;
import utilities.Selenium;

public class NewEnroll extends BaseTest {

	static {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public String screenshotFilePath = "";

	@Test
	public void test() throws InterruptedException, FindFailed, SQLException, ClassNotFoundException, IOException {
		generateReports = getClass().getSimpleName().toString();
		selenium.open("http://www.fairmont.com/");
		Thread.sleep(10000);
		System.out.println(driver.getTitle());

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

		Connection con = DriverManager.getConnection("jdbc:odbc:complete");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM [TC003$]");

		driver.findElement(By.id(locator("Fairmont", "Myprofile", "id"))).click();
		driver.findElement(By.id(locator("Fairmont", "Enroll", "id"))).click();

		while (rs.next()) {

			// String runId = rs.getString(1);
			String arts = rs.getString(2);
			String music = rs.getString(3);
			String familytravel = rs.getString(4);
			String food = rs.getString(5);
			String username = rs.getString(6);
			String password = rs.getString(7);
			String repassword = rs.getString(8);
			String question = rs.getString(9);
			String Answer = rs.getString(10);
			// String language=rs.getString(11);
			String title = rs.getString(12);
			String firstname = rs.getString(13);
			String middlename = rs.getString(14);
			String lastname = rs.getString(15);
			String suffix = rs.getString(16);
			String altfirstname = rs.getString(17);
			String altlastname = rs.getString(18);
			String mailaddress = rs.getString(19);
			String companyname = rs.getString(20);
			String jobtitle = rs.getString(21);
			String address1 = rs.getString(22);
			String address2 = rs.getString(23);
			String address3 = rs.getString(24);
			String city = rs.getString(25);
			String country = rs.getString(26);
			String state = rs.getString(27);
			String zipcode = rs.getString(28);
			// String homephone= rs.getString(29);
			// String businessphone= rs.getString(30);
			String mobilephone = rs.getString(31);
			String emailaddress = rs.getString(32);
			String confirmemailaddress = rs.getString(33);
			String property = rs.getString(34);
			// String enrollmentcode=rs.getString(35);
			// String promotioncode=rs.getString(36);
			String creditcardtype = rs.getString(37);
			// String creditcardno=rs.getString(38);
			// String expmonth=rs.getString(39);
			// String expyear=rs.getString(40);
			String birthmonth = rs.getString(41);
			String birthdate = rs.getString(42);
			String Iagree = rs.getString(43);
			// String submit=rs.getString(44);
			// String arg=rs.getString("44");

			selenium.check(locator("Enroll", "Arts and Entertainment", "id"), arts);
			selenium.check(locator("Enroll", "Music", "id"), music);
			selenium.check(locator("Enroll", "Family Travel", "id"), familytravel);
			selenium.check(locator("Enroll", "Food", "id"), food);
			selenium.type(locator("Enroll", "Username", "id"), username);
			selenium.type(locator("Enroll", "Password", "id"), password);
			selenium.type(locator("Enroll", "Repassword", "id"), repassword);
			selenium.dropdown(locator("Enroll", "Challenge Question", "id"), question);
			selenium.type(locator("Enroll", "Answer", "id"), Answer);
			selenium.dropdown(locator("Enroll", "Title", "id"), title);
			selenium.type(locator("Enroll", "FirstName", "id"), firstname);
			selenium.type(locator("Enroll", "Middlename", "id"), middlename);
			selenium.type(locator("Enroll", "Lastname", "id"), lastname);
			selenium.dropdown(locator("Enroll", "Suffix", "id"), suffix);
			selenium.type(locator("Enroll", "Alternate First Name", "id"), altfirstname);
			selenium.type(locator("Enroll", "Alternate Last Name", "id"), altlastname);
			if (mailaddress.compareToIgnoreCase("Home") == 0) {
				webbrowser.click(locator("Enroll", "Home Maililng Address ", "id"));
			} else {
				webbrowser.click(locator("Enroll", "Home Business Address ", "id"));

			}
			selenium.type(locator("Enroll", "Company Name", "id"), companyname);
			selenium.type(locator("Enroll", "JobTitle", "id"), jobtitle);
			selenium.type(locator("Enroll", "Address Line1", "id"), address1);
			selenium.type(locator("Enroll", "Address Line2", "id"), address2);
			selenium.type(locator("Enroll", "Address LIne3", "id"), address3);
			selenium.type(locator("Enroll", "City", "id"), city);
			selenium.dropdown(locator("Enroll", "Country", "id"), country);
			selenium.type(locator("Enroll", "State", "id"), state);
			selenium.type(locator("Enroll", "Postal code", "id"), zipcode);
			selenium.type(locator("Enroll", "Mobile Phone", "id"), mobilephone);
			selenium.type(locator("Enroll", "Email Address", "id"), emailaddress);
			selenium.type(locator("Enroll", "Confirm Email Address", "id"), confirmemailaddress);
			selenium.dropdown(locator("Enroll", "Property", "id"), property);
			selenium.dropdown(locator("Enroll", "Credit card Type", "id"), creditcardtype);
			selenium.dropdown(locator("Enroll", "Birth month", "id"), birthmonth);
			selenium.dropdown(locator("Enroll", "Birth Date", "id"), birthdate);
			selenium.check(locator("Enroll", "I Agree", "id"), Iagree);
			selenium.click("id=ctl00_ctl00_Content_Content_hypEnrollNow");

			if (webbrowser.isAlertPresent()) {
				log.fail(webbrowser.getAlert());
				bTestResult = false;
			} else if (selenium.getText("id=ctl00_ctl00_Content_Content_lblWelcome").contains("Welcome")) {
				selenium.getText("id=ctl00_ctl00_Content_Content_lblWelcome");
				bTestResult = true;
			} else if (selenium.getText("id=ctl00_ctl00_Content_Content_pnlErrorMsg").contains("Error")) {
				selenium.getText("id=ctl00_ctl00_Content_Content_pnlErrorMsg");
				bTestResult = false;
			}

			selenium.open("https://www.fairmont.com/fpc/enroll/");

		}
		st.close();
		con.close();
		log.pass("Executed for all iterations; exiting the test case");
		assertTrue(bTestResult);

	}

}
