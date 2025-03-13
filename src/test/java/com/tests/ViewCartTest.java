package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.de.test.BaseDeTest;

public class ViewCartTest extends BaseDeTest {
@Test(priority=4)
public void viewCartTest() {
	try{
	productsPage.clickCartButton();
	Assert.assertTrue(elementUtils.isDisplayed(cartPage.cartTitle),"");
	reportManager.testPassed("successfully viewed cart");
	}
	catch(AssertionError e) {
		reportManager.testFailed("failed to view cart " + e.getMessage())	;
	}
}
}
