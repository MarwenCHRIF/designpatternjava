package com.driver.factory;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
	// Le driver WebDriver utilis� pour les tests
    public WebDriver driver;

    // ThreadLocal stocke le WebDriver de mani�re isol�e pour chaque thread (utile pour l'ex�cution de tests en parall�le)
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
 

    public WebDriver initializeBrowser(String browserName, boolean headlessMode) {
        // 1. Utilisation de ConfigReader pour lire les propri�t�s depuis le fichier data.properties
        

        // 2. Condition pour Chrome
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions(); // Options pour configurer Chrome
            options.addArguments("--kiosk"); // Lance Chrome en plein �cran
            options.addArguments("--disable-namespace-sandbox"); // D�sactive le sandboxing
            options.addArguments("ignore-certificate-errors"); // Ignore les erreurs de certificats
            options.setExperimentalOption("excludeSwitches", Arrays.asList("test-type")); // D�sactive certains avertissements
            options.addArguments("--safebrowsing-disable-download-protection"); // D�sactive la protection de t�l�chargement
            options.addArguments("--no-sandbox"); // N�cessaire pour les environnements avec des ressources limit�es
            options.addArguments("--disable-dev-shm-usage"); // Surmonte les probl�mes de ressources sur certains syst�mes
            options.addArguments("--disable-search-engine-choice-screen");//desactiver l'ecran de selection moteurs de recherche
            options.addArguments("--disable-features=OptimazationGuideModelDownloading, OptimizationHintsFetching,OptimazationTargetPrediction");//desactiver le telechargement automatique de plugins de langue
            if (headlessMode) { // Si le mode headless est activ�
                options.addArguments("--headless"); // Active le mode headless
                options.addArguments("--window-size=1920,1080"); // D�finit la taille de la fen�tre en mode headless
            }

            // 3. Configuration du driver pour Chrome avec WebDriverManager
           
            tdriver.set(new ChromeDriver(options)); // Initialise le driver avec les options sp�cifi�es

        // 4. Condition pour Firefox
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions(); // Options pour configurer Firefox
            if (headlessMode) { // Si le mode headless est activ�
                options.addArguments("--headless"); // Active le mode headless pour Firefox
                options.addArguments("--window-size=1920,1080"); // D�finit la taille de la fen�tre en mode headless
            }

            // 5. Configuration du driver pour Firefox avec WebDriverManager
            
            tdriver.set(new FirefoxDriver(options)); // Initialise le driver avec les options sp�cifi�es

        // 6. Condition pour Edge
        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions option = new EdgeOptions(); // Options pour configurer Edge
            option.setAcceptInsecureCerts(true); // Accepte les certificats non s�curis�s
            option.addArguments("ignore-certificate-errors"); // Ignore les erreurs de certificats
            option.addArguments("--remote-allow-origins=*"); // Autorise les origines distantes
            if (headlessMode) { // Si le mode headless est activ�
                option.addArguments("--headless"); // Active le mode headless pour Edge
                option.addArguments("--window-size=1920,1080"); // D�finit la taille de la fen�tre en mode headless
            }

            // 7. Configuration du driver pour Edge avec WebDriverManager
            
            tdriver.set(new EdgeDriver(option)); // Initialise le driver avec les options sp�cifi�es

        // 8. Gestion du cas o� le navigateur n'est pas d�fini
        } else {
            System.out.println("****** BROWSER IS NOT DEFINED");
        }

        // 9. Retourne l'instance WebDriver correspondant au navigateur initialis�
        return tdriver.get();
    }
}


