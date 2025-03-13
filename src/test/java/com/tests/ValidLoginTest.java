package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.pages.ProductsPage;
import com.base.de.test.BaseDeTest;

public class ValidLoginTest extends BaseDeTest{
@Test(priority=2)
public void successfullLoginTest() {
	try {
	loginPage.enterUsername(configReader.remplirValidUserName());
	loginPage.enterPassword(configReader.remplirValidPassword());
	loginPage.clickbuttonLogin();
	productsPage.productsDisplay();
	Assert.assertTrue(elementUtils.isDisplayed(products), "products page not displayed!");
	reportManager.testPassed("Logged in successfully as a standard user.");
	}
	catch(	AssertionError e){
	reportManager.testFailed("Login failed: " + e.getMessage());
	
}}}



