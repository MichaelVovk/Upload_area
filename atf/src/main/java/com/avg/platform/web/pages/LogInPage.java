package com.avg.platform.web.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import com.avg.platform.web.WebPage;
import com.avg.platform.web.elements.Button;
import com.avg.platform.web.elements.TextInput;

public class LogInPage extends WebPage<LogInPage> {
	
	private static final String PAGE_URL = "https://test.platform.avg.com/login/";

	public LogInPage(WebDriver driver) {
		super(driver);
	
	}

	@Override
	public LogInPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
        
		return getUsernameInput().isAvailable() && 
				getPasswordInput().isAvailable() &&
				getLoginButton().isAvailable();
	}
	public AdminPage loginAs(String username, String password){
		getUsernameInput().inputText(username);
		getPasswordInput().inputText(password);
		getLoginButton().click();
		
		return new AdminPage(driver).waitUntilAvailible();
	}
	
private TextInput getUsernameInput (){
	return new TextInput (driver, By.id("id-username") );
	
}
private TextInput getPasswordInput (){
	return new TextInput (driver, By.id("id-password") );
	
}
private Button getLoginButton(){
	return new Button(driver, By.cssSelector("button.btn.btn-blue"));
}

}
