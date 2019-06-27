package fr.eni.papeterie.dal;

import java.io.IOException;
import java.util.Properties;

public class Settings {
	
	//Les propriétés étant commune à toute l'application, nous pouvons
	//rendre cet attribut static :
	private static Properties properties;

	static {
		//instanciation de l'atribut properties :
		properties = new Properties();
		try {
			//chargement du fichier settings.properties :
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperties(String key) {
		String parametre = properties.getProperty(key);
		return parametre;
	}
	
}
