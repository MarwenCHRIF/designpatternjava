package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.de.test.BaseDeTest;

public class AddToCartTest extends BaseDeTest{
@Test(priority=3)
	public void addToCartTest() {
try {
productsPage.addtoCartProduct();
Assert.assertEquals(productsPage.cartBadgeText(),"1","product not added to card");
reportManager.testPassed("product added successfully to cart");
}
catch(AssertionError e) {
	reportManager.testFailed(";product Add to cart failed: " + e.getMessage());
}
}
}
