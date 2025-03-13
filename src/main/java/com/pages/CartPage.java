package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utils.elements.ElementUtils;
import com.utils.wait.WaitUtils;

public class CartPage {
	
	private WebDriver driver;
	 ElementUtils elementUtils;
	 WaitUtils waitUtils;
public final By cartTitle= By.xpath("//div[@id='cart_contents_container']");


public CartPage(WebDriver tdriver) {
	this.driver = tdriver;
	this.elementUtils = new ElementUtils(driver); // Initialisation correcte après que le driver est passé
	this.waitUtils = new WaitUtils(driver); // Initialisation correcte de WaitUtils
}

}
