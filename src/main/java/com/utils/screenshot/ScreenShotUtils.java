package com.utils.screenshot;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils; // Utilitaire pour g�rer les fichiers
import org.openqa.selenium.OutputType; // Type de sortie pour les captures d'�cran
import org.openqa.selenium.TakesScreenshot; // Interface pour les captures d'�cran
import org.openqa.selenium.WebDriver; // Contr�le du navigateur
import org.testng.ITestResult; // R�sultat du test

import com.config.reader.ConfigReader; // Classe pour lire les configurations

import atu.testrecorder.ATUTestRecorder; // Biblioth�que pour enregistrer des vid�os
import atu.testrecorder.exceptions.ATUTestRecorderException; // Gestion des exceptions pour ATUTestRecorder

public class ScreenShotUtils {
	 private WebDriver driver; // Navigateur utilis� pour les tests
	    private ATUTestRecorder recorder; // Enregistreur de vid�o pour les tests
	    
	    private String methodName; // Nom de la m�thode de test en cours
	    private ConfigReader configReader; // Lecteur de configurations

	    // *Constructeur* pour initialiser le WebDriver et ConfigReader
	    public ScreenShotUtils(WebDriver driver) {
	        this.driver = driver; // Initialisation du navigateur
	        this.configReader = new ConfigReader(); // Initialisation de la configuration
	    }

	    // Retourne le nom du fichier vid�o, bas� sur la m�thode en cours et la date/heure actuelle
	    public String getVideoFileName() {
	        return "Video_" + methodName + "_" + configReader.getActualDateTime();
	    }

	    // D�marre l'enregistrement vid�o pour un test
	    public void startRecording() throws ATUTestRecorderException {
	        recorder = new ATUTestRecorder(configReader.videoLocation(), getVideoFileName(), false); // Cr�ation de l'enregistreur
	        recorder.start(); // D�marre l'enregistrement
	    }

	    // Pr�paration avant chaque m�thode de test : r�cup�re le nom de la m�thode et d�marre l'enregistrement
	    public void setupBeforeMethod(ITestResult result) {
	        this.methodName = result.getMethod().getMethodName(); // R�cup�ration du nom de la m�thode
	        try {
	            startRecording(); // D�marrage de l'enregistrement
	        } catch (ATUTestRecorderException e) {
	            e.printStackTrace(); // Affiche l'erreur en cas de probl�me
	        }
	    }

	    // Arr�te l'enregistrement vid�o apr�s le test
	    public void stopRecording() throws ATUTestRecorderException {
	        if (recorder != null) { // V�rifie si l'enregistreur est actif
	            recorder.stop(); // Arr�te l'enregistrement
	        }
	    }

	    // Capture une capture d'�cran et retourne son chemin
	    public String takeScreenShot (String imageName)throws IOException{
	    	  // D�finit le chemin complet de l'image
	        String actualImageName = configReader.screenshotLocation() + "\\" + imageName + "-" + configReader.getActualDateTime() + ".png";
	        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Prend une capture
	        File destFileImg = new File(actualImageName); // Cr�e le fichier de destination
	        FileUtils.copyFile(sourceFile, destFileImg); // Copie la capture d'�cran dans le fichier cible

	    	return actualImageName;// Retourne le chemin de�l'image

			}

}
