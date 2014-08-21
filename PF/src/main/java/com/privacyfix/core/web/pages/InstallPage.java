package com.privacyfix.core.web.pages;

import org.openqa.selenium.WebDriver;

import com.privacyfix.core.web.WebPage;

public class InstallPage extends WebPage<InstallPage> {

	private static final String PAGE_URL = "http://privacyfix.com/start/install";
	
	public InstallPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InstallPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

}
