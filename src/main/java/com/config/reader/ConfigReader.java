package com.config.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ConfigReader {
	
     Properties properties;
     
     
	// Constructeur pour charger le fichier properties
    public ConfigReader() {
        try (FileInputStream fis = new FileInputStream("C:/Users/nohar/Documents/eclipse/CoursSeleniumDesignPattern/src/test/resources/DataConfig/data.properties")) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Impossible de charger le fichier properties : " + e.getMessage());
        }
    }
    public void setProperty(String key, String defaultValue) {
        // Retourne la propriété correspondante à la clé ou une valeur par défaut si la clé n'existe pas
        properties.setProperty(key, defaultValue);
    }
   public String getProperty(String key) {
	   return properties.getProperty(key);
   }
    // Méthode pour obtenir la valeur d'une propriété
    public String getBrowserName() {
        return properties.getProperty("browser"); // "chrome" est la valeur par défaut si non spécifiée
    }
    public Boolean getBrowserMode() {
    	  String headlessValue = properties.getProperty("headless");
        return  Boolean.parseBoolean(headlessValue) ; // "chrome" est la valeur par défaut si non spécifiée
    }
    public int  getDurationTime() {
        // Utiliser Int.parseInt pour retourner la durée comme un long
        return Integer.parseInt(properties.getProperty("durationmax")); // "3" est la valeur par défaut si non spécifiée
    }
    
    public String getActualDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(properties.getProperty("date"));
        return dateFormat.format(new Date());
    }
    
    public String getDocumentTitle() {
        return properties.getProperty("DocumentTitle");
    }
    public String reportName() {
        return properties.getProperty("ReportName");
    }
    
    public String videoLocation() {
        return properties.getProperty("VIDEO_LOCATION");
    }
    
    public String remplirInvalidUserName() {
        return properties.getProperty("usernameinvalid");
    }
    
    public String remplirInvalidPassword() {
        return properties.getProperty("passwordinvalid");
    }
    public String remplirValidUserName() {
        return properties.getProperty("usernamevalid");
    }
    
    public String remplirValidPassword() {
        return properties.getProperty("passwordvalid");
    }
    public String geterrormsg() {
        return properties.getProperty("errormsgLogin");
    }
	public String getUrl() {
		
		return properties.getProperty("url");
	}
	public String screenshotLocation() {
		
		return properties.getProperty("SCREENSHOT_LOCATION");
	}
	public String reportLocation() {
		return properties.getProperty("REPORT_LOCATION");
	}
	public String sparkReportName () {
		return properties.getProperty("sparkReport_Name");
	}
	public String sparkReportType () {
		return properties.getProperty("sparkReport_Type");
	}
}
