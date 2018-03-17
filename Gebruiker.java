import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Gebruiker {
	private String password;
	private String gebruikersnaam;
	private static ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
	
	public static void GetGebruikers(){
		try{
			FileReader fileReader = new FileReader("Files/Gebruikers.txt");
			BufferedReader reader = new BufferedReader(fileReader);
			String line= null;
			while ((line =reader.readLine())!=null){
				
			}
			reader.close();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void SaveGebruikers(){
		
		try{
			File file = new java.io.File("Files/Gebruikers.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			
			
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public Gebruiker(String password, String gebruikersnaam) {
		super();
		this.password = password;
		this.gebruikersnaam = gebruikersnaam;
	}
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	
	
	
}
