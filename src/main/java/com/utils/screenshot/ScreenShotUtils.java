package com.utils.screenshot;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils; // Utilitaire pour gérer les fichiers
import org.openqa.selenium.OutputType; // Type de sortie pour les captures d'écran
import org.openqa.selenium.TakesScreenshot; // Interface pour les captures d'écran
import org.openqa.selenium.WebDriver; // Contrôle du navigateur
import org.testng.ITestResult; // Résultat du test

import com.config.reader.ConfigReader; // Classe pour lire les configurations

import atu.testrecorder.ATUTestRecorder; // Bibliothèque pour enregistrer des vidéos
import atu.testrecorder.exceptions.ATUTestRecorderException; // Gestion des exceptions pour ATUTestRecorder

public class ScreenShotUtils {
	 private WebDriver driver; // Navigateur utilisé pour les tests
	    private ATUTestRecorder recorder; // Enregistreur de vidéo pour les tests
	    
	    private String methodName; // Nom de la méthode de test en cours
	    private ConfigReader configReader; // Lecteur de configurations

	    // *Constructeur* pour initialiser le WebDriver et ConfigReader
	    public ScreenShotUtils(WebDriver driver) {
	        this.driver = driver; // Initialisation du navigateur
	        this.configReader = new ConfigReader(); // Initialisation de la configuration
	    }

	    // Retourne le nom du fichier vidéo, basé sur la méthode en cours et la date/heure actuelle
	    public String getVideoFileName() {
	        return "Video_" + methodName + "_" + configReader.getActualDateTime();
	    }

	    // Démarre l'enregistrement vidéo pour un test
	    public void startRecording() throws ATUTestRecorderException {
	        recorder = new ATUTestRecorder(configReader.videoLocation(), getVideoFileName(), false); // Création de l'enregistreur
	        recorder.start(); // Démarre l'enregistrement
	    }

	    // Préparation avant chaque méthode de test : récupère le nom de la méthode et démarre l'enregistrement
	    public void setupBeforeMethod(ITestResult result) {
	        this.methodName = result.getMethod().getMethodName(); // Récupération du nom de la méthode
	        try {
	            startRecording(); // Démarrage de l'enregistrement
	        } catch (ATUTestRecorderException e) {
	            e.printStackTrace(); // Affiche l'erreur en cas de problème
	        }
	    }

	    // Arrête l'enregistrement vidéo après le test
	    public void stopRecording() throws ATUTestRecorderException {
	        if (recorder != null) { // Vérifie si l'enregistreur est actif
	            recorder.stop(); // Arrête l'enregistrement
	        }
	    }

	    // Capture une capture d'écran et retourne son chemin
	    public String takeScreenShot (String imageName)throws IOException{
	    	  // Définit le chemin complet de l'image
	        String actualImageName = configReader.screenshotLocation() + "\\" + imageName + "-" + configReader.getActualDateTime() + ".png";
	        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Prend une capture
	        File destFileImg = new File(actualImageName); // Crée le fichier de destination
	        FileUtils.copyFile(sourceFile, destFileImg); // Copie la capture d'écran dans le fichier cible

	    	return actualImageName;// Retourne le chemin de l'image

			}

}
