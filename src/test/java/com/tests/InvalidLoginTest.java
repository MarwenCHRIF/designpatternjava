package com.tests;

import org.testng.Assert;

import org.testng.annotations.Test;
import com.base.de.test.BaseDeTest;



public class InvalidLoginTest extends BaseDeTest {
		
	@Test(priority = 1)

	public void failedLoginTest() {
		try {
			loginPage.enterUsername(configReader.remplirInvalidUserName());
			loginPage.enterPassword(configReader.remplirInvalidPassword());
			loginPage.clickbuttonLogin();
			loginPage.waitforerrormsg(configReader.geterrormsg());
			Assert.assertTrue(elementUtils.isDisplayed(loginPage.errorMessage), "Error message not displayed!");
			reportManager.testPassed("Login failed as expected with an invalid username and password.");
		} catch (AssertionError e) {
			reportManager.testFailed("Error message not displayed: " + e.getMessage());
			
		}
	}
	
	

}
