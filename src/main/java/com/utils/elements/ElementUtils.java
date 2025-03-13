package com.utils.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {
	WebDriver driver;
	
 public ElementUtils(WebDriver driver){	 
	  
	 this.driver = driver; 
	 
      }
 	
	public void sendKeys(By locator, String keysToSend) {
		WebElement element = driver.findElement(locator);
		element.clear(); 
		element.sendKeys(keysToSend);
		
	}

	// V�rifier si un �l�ment est affich�
	public boolean isDisplayed(By locator) {
		return driver.findElement(locator).isDisplayed();
	}

	// V�rifier si un �l�ment est s�lectionn� (pour les cases � cocher ou les
	// boutons radio)
	public boolean isSelected(By locator) {
		return driver.findElement(locator).isSelected();
	}

	// Soumettre un �l�ment (par exemple, un formulaire)
	public void submit(By locator) {
		driver.findElement(locator).submit();
	}

	// V�rifier si un �l�ment est activ�
	public boolean isEnabled(By locator) {
		return driver.findElement(locator).isEnabled();
	}

	// Effacer le contenu d'un �l�ment
	public void clear(By locator) {
		driver.findElement(locator).clear();
	}

	// Cliquer sur un �l�ment
	public void click(By locator) {
		driver.findElement(locator).click();
	}

	// Obtenir la taille d'un �l�ment
	public Dimension getSize(By locator) {
		return driver.findElement(locator).getSize();
	}
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}

}
