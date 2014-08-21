package utilities;

import java.io.IOException;
import java.sql.SQLException;

import engine.BaseTest;

public class QTP extends BaseTest {

	private String page;
	private String element;
	private String selector;

	public QTP Page(String sPageName) {
		this.page = sPageName;
		return this;
	}

	public QTP Link(String sElement) {
		this.element = "link=" + sElement;
		return this;
	}

	public QTP WebElement(String sElement) {
		this.element = sElement;
		return this;
	}

	public QTP WebEdit(String sElement) {
		this.element = sElement;
		return this;
	}

	public void Navigate(String sUrl) throws IOException {
		selenium.open(sUrl);
	}

	public void Maximize() throws IOException {
		selenium.maximize();
	}

	public void Click() throws ClassNotFoundException, SQLException, IOException {
		selenium.click(locator(page, element, selector));
	}

	public void Type(String sValue) throws ClassNotFoundException, SQLException, IOException {
		selenium.type(locator(page, element, selector), sValue);
	}

}
