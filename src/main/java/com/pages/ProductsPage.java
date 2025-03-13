package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utils.elements.ElementUtils;
import com.utils.wait.WaitUtils;

public class ProductsPage {
	private WebDriver driver;
	ElementUtils elementUtils;
	WaitUtils waitUtils;
	public final By products = By.xpath("//*[text()='Products']"); 
	private final By firstProduct = By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']");
	private final By cartBadge=  By.xpath("//span[contains(text(),'1')]");
	private final By filterDropdown =By.className("product_sort_container");
	private final By lowToHighOption= By.xpath("//option[contains(text(),'Price (low to high)')]");
	private final By productPrices = By.className("inventory_item_price");
	private final By secondProduct = By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/button[19]");
	private final By cartButton = By.className("shopping_cart_link");
	public ProductsPage(WebDriver tdriver) {
	this.driver = tdriver;
	this.elementUtils = new ElementUtils(driver); // Initialisation correcte après que le driver est passé
	this.waitUtils = new WaitUtils(driver); // Initialisation correcte de WaitUtils
}
public void productsDisplay() {
	waitUtils.visibilityOf(products);
}
public void addtoCartProduct() {
	waitUtils.elementToBeClickable(firstProduct);
	elementUtils.click(firstProduct);
}
public String cartBadgeText() {
	return elementUtils.getText(cartBadge);
}
public void clickCartButton() {
	waitUtils.elementToBeClickable(cartButton);
	elementUtils.click(cartButton);
}
}
