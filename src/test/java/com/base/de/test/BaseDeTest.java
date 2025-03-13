package com.base.de.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.config.reader.ConfigReader;
import com.driver.factory.DriverFactory;
import com.pages.CartPage;
import com.pages.LoginPage;
import com.pages.ProductsPage;
import com.report.config.ReportConfig;
import com.utils.screenshot.ScreenShotUtils;
import com.utils.elements.ElementUtils;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class BaseDeTest {

    public static ReportConfig reportManager;
    protected WebDriver driver;

    public static ConfigReader configReader;
    public static ScreenShotUtils screenShot;
    public static DriverFactory driverfactory;
    public static ElementUtils elementUtils;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    @BeforeClass
    public void setup() throws IOException, ATUTestRecorderException {
        // Initialisation de DriverFactory et de ConfigReader
    	driverfactory = new DriverFactory();
        configReader = new ConfigReader();

        // Initialisation du driver
        driver = driverfactory.initializeBrowser(configReader.getBrowserName(), configReader.getBrowserMode());
        
        
        driver.manage().window().maximize();
        driver.get(configReader.getUrl());

        // Initialisation d'autres utilitaires
        elementUtils = new ElementUtils(driver);
        reportManager = new ReportConfig(driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @BeforeMethod
    public void setUpTest(ITestResult result) throws ATUTestRecorderException {
        // Initialisation de ScreenShotUtils avant chaque méthode de test
        screenShot = new ScreenShotUtils(driver);
        screenShot.startRecording();

        // Initialisation de LoginPage
    

        // Log de test dans le rapport
        reportManager.getExtent();
        reportManager.logTestName(result.getMethod().getMethodName());
        screenShot.setupBeforeMethod(result);
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) throws IOException {
        // Traitement après chaque méthode de test
        reportManager.afterMethod(result);
        // Ajoutez ici d'autres opérations de nettoyage si nécessaire
    }
@AfterTest
public void tearDowntest() throws ATUTestRecorderException {
	  // Arrêt de l'enregistrement vidéo et nettoyage
    screenShot.stopRecording();
    reportManager.flush();
    // Fermez le driver si nécessaire
  if (driver!=null) {
    	driver.quit();
    }

}
 }
