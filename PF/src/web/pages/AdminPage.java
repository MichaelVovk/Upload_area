package com.avg.platform.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.avg.platform.web.WebPage;
import com.avg.platform.web.elements.Button;

public class AdminPage extends WebPage<AdminPage> {
	
	private static final String PAGE_URL = "https://test.platform.avg.com/login/";

	public AdminPage(WebDriver driver) {
		super(driver);
	
	}

	@Override
	public AdminPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
return getErrorNotification().isAvailable();
		
	}
public LogInPage loadAsAnonymousUser(){
	load();
	return new LogInPage(driver).waitUntilAvailible();
}
	
private Button getErrorNotification(){
	return new Button(driver, By.id("errorOkBtn"));
	
}
}
