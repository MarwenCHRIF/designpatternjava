package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.utils.elements.ElementUtils;
import com.utils.wait.WaitUtils;

public class LoginPage {

	WebDriver driver;
	ElementUtils elementUtils;
	WaitUtils waitUtils;

	private By usernameField = By.id("user-name");
	final By passwordField = By.id("password");
	private By loginButton = By.id("login-button");
	public By errorMessage = By.xpath("//*[contains(text(),'Epic sadface: Username and password')]");

	public LoginPage(WebDriver tdriver) {
		this.driver = tdriver;
		this.elementUtils = new ElementUtils(driver); // Initialisation correcte après que le driver est passé
		this.waitUtils = new WaitUtils(driver); // Initialisation correcte de WaitUtils
	}

	public void enterUsername(String username) {
		waitUtils.visibilityOf(usernameField);
		elementUtils.sendKeys(usernameField, username);
	}

	public void enterPassword(String password) {
		
		elementUtils.sendKeys(passwordField, password);
	}

	public void clickbuttonLogin() {
		waitUtils.elementToBeClickable(loginButton);
		elementUtils.click(loginButton);
	}

	public void waitforerrormsg(String errormsg) {
		waitUtils.Textispresent(errorMessage, errormsg);

	}

}