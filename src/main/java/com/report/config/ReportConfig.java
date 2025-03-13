package com.report.config;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.config.reader.ConfigReader;
import com.utils.screenshot.ScreenShotUtils;

public class ReportConfig {
	private ExtentReports extent;
	private ExtentSparkReporter spark;
	private WebDriver driver;
	ConfigReader configReader = new ConfigReader();
	ScreenShotUtils capture = new ScreenShotUtils(driver);
	ITestResult result;
    private static ExtentTest test;

	public ReportConfig(WebDriver driver) throws IOException {
		this.driver=driver;
		this.capture=new ScreenShotUtils(driver);

		//String reportName = "target/Spark/SparkReport_" + configReader.getActualDateTime() + ".html";
		String reportName = configReader.reportLocation()+configReader.sparkReportName()
		+ configReader.getActualDateTime() + configReader.sparkReportType();
		spark = new ExtentSparkReporter(reportName);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle(configReader.getDocumentTitle());
		spark.config().setReportName(configReader.reportName());
		spark.config().setTimeStampFormat(configReader.getActualDateTime());

		extent = new ExtentReports();
		extent.attachReporter(spark);
		
	}

	public ExtentReports getExtent() {
		return extent;
	}
	
	public ExtentTest logTestName(String methodName) {
	    // Initialiser 'test' avec le nom de la m�thode
	    test = extent.createTest(methodName);
	    return test;
	}

	public ExtentTest logTestName() {
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		return extent.createTest(methodName);
		 
	}
	public ExtentTest testPassed(String message) {
		return test.pass(message);
	}
	public ExtentTest testFailed(String message) {
		return test.fail(message);
	}
	public Status afterMethod(ITestResult result) throws IOException {
	    // Assurez-vous que 'test' est bien initialis�
	    if (test == null) {
	        logTestName(result.getMethod().getMethodName());
	    }

	    // Prendre la capture d'�cran et l'ajouter au rapport
	    test.addScreenCaptureFromPath(capture.takeScreenShot(result.getMethod().getMethodName()));

	    // Log de la vid�o ajout�e au rapport
	    test.log(Status.INFO, "Video ajoutee au rapport : <a href='" + configReader.videoLocation() + "'>Cliquez ici pour voir la video</a>");

	    // V�rification du statut du test et retour du statut
	    if (result.getStatus() == ITestResult.SUCCESS) {
	        test.log(Status.PASS, "Test passed.");
	        return Status.PASS; // Retourne le statut de succ�s
	    } else if (result.getStatus() == ITestResult.FAILURE) {
	        test.log(Status.FAIL, "Test failed: " + result.getThrowable().getMessage());
	        return Status.FAIL; // Retourne le statut d'�chec
	    } else {
	        test.log(Status.SKIP, "Test skipped.");
	        return Status.SKIP; // Retourne le statut de saut
	    }
	}

	public void flush() {
		extent.flush();
	}

}
