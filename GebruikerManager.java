import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GebruikerManager {

	private static List<Gebruiker> gebruikers = new ArrayList<>();

	public static void GetGebruikers() {
		try {

			FileReader fileReader = new FileReader("Files/Gebruikers.txt");
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] gegevens = line.split(",");
				Gebruiker gebruikerinvoer = new Gebruiker(gegevens[0], gegevens[1]);
				gebruikers.add(gebruikerinvoer);

			}
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void SaveGebruikers() {

		try {
			File file = new java.io.File("Files/Gebruikers.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			gebruikers.forEach(b -> {
				try {
					writer.write(b.toString());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});
			writer.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}

	public static boolean LoginGebruiker(Gebruiker gebruiker) {
		for (int i = 0; i <= gebruikers.size(); i++) {

			if (gebruiker.getGebruikersnaam().equals(gebruikers.get(i).getGebruikersnaam())
					&& gebruiker.getPassword().equals(gebruikers.get(i).getPassword())) {
				System.out.println(gebruiker.getGebruikersnaam() + " logged in.");
				return true;
			}
		}
		System.out.println("Combination of username and password does not exist.");
		return false;

	}

	public static boolean RegisterGebruiker(Gebruiker gebruiker) {
		for (int i = 0; i <= gebruikers.size(); i++) {
			if ((gebruiker.getGebruikersnaam().equals(gebruikers.get(i).getGebruikersnaam()))) {
				System.out.println(gebruiker.getGebruikersnaam() + " is already taken.");
				return false;
			}

		}

		return true;

	}

	public static void AfdrukkenGebruikers() {
		gebruikers.forEach(b -> System.out.println(b.getGebruikersnaam()));
	}
}
