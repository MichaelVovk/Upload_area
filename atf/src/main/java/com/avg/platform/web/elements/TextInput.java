package com.avg.platform.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.avg.platform.web.WebComponent;

public class TextInput extends WebComponent<TextInput> {

	public TextInput(WebDriver driver, By findByMethod) {
		super(driver, findByMethod);
	
	}

	public TextInput inputText (String text){
		getWebElement().sendKeys(text);
		return this;
	}
	
}
