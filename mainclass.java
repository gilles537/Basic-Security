import java.util.Scanner;

import com.sun.swing.internal.plaf.basic.resources.basic_ja;

public class mainclass {

	public static void main(String[] args) throws Exception {
		// beginscherm aanmelden of registreren
		String gebruikersnaam;
		String boodschap;
		String paswoord;
		int confirm;
		Gebruiker actieveGebruiker;
		String receiver;
		GebruikerManager.GetGebruikers();

		Scanner scanner = new Scanner(System.in);
// exception waneer het geen 0 of 1 is
		
		System.out.println("log in: 0, register: 1");
		confirm = Integer.parseInt(scanner.nextLine());

		while (confirm != 0 && confirm != 1) {
			System.out.println("typ 1 or 0 to proceed");
			System.out.println("log in: 0, register: 1");
			confirm = Integer.parseInt(scanner.nextLine());
		}

		System.out.println("Username:");
		gebruikersnaam = scanner.nextLine();
		System.out.println("Password");
		paswoord = scanner.nextLine();
		actieveGebruiker = new Gebruiker(gebruikersnaam, paswoord);

		if (confirm == 0) {
			while (GebruikerManager.LoginGebruiker(actieveGebruiker) == false) {

				System.out.println("Username:");
				gebruikersnaam = scanner.nextLine();
				System.out.println("Password");
				paswoord = scanner.nextLine();
				actieveGebruiker = new Gebruiker(gebruikersnaam, paswoord);
			}
		} else if (confirm == 1) {
			while (GebruikerManager.RegisterGebruiker(actieveGebruiker) == false) {
				System.out.println("Username:");
				gebruikersnaam = scanner.nextLine();
				System.out.println("Password");
				paswoord = scanner.nextLine();
				actieveGebruiker = new Gebruiker(gebruikersnaam, paswoord);
			}

		}

		System.out.println("insert name of receiver:");
		System.out.println("type list to get list of receivers");
		receiver = scanner.nextLine();

		
		// bij 2de list pakt die deze als username.
		while (GebruikerManager.checkName(receiver) == false) {
			System.out.println("insert name of receiver:");
			receiver = scanner.nextLine();
		}
		
			System.out.println("Type the message you want to send:");
			boodschap = scanner.nextLine();

			// encrypteren en decrypteren enzo

			new AES_Encryptor();
			KeyManager keymanager = new KeyManager();
			AES_Encryptor.encrypt(keymanager.getAESKey(), keymanager.getAESIV(), boodschap);

			String test = "Dirk";
			AES_Encryptor encryptor = new AES_Encryptor();
			KeyManager keymanager1 = new KeyManager();

			// String encryptedTest = encryptor.encrypt(keymanager.getAESKey(),
			// keymanager.getAESIV(), "Pannekoek");

			Hasher hasher = new Hasher();

			System.out.println(hasher.sha256("ik ben een test hash"));

			// uitloggen of ander bericht sturen

			System.out.println("Do you want to log out or continue sending messages.");
			System.out.println("log out:0, continue:1");
			confirm = Integer.parseInt(scanner.nextLine());

			while (confirm != 0 && confirm != 1) {
				System.out.println("typ 1 or 0 to proceed");
				System.out.println("Do you want to log out or continue sending messages.");
				confirm = Integer.parseInt(scanner.nextLine());
			}

			while (confirm == 1) {
				System.out.println("insert name of receiver:");

				System.out.println("type list to get list of receivers");
				receiver = scanner.nextLine();

				while (GebruikerManager.checkName(receiver) == false) {
					System.out.println("insert name of receiver:");
					receiver = scanner.nextLine();
				}
				
					System.out.println("Type the message you want to send:");
					boodschap = scanner.nextLine();
				// encrypteren en decrypteren ofzo

				System.out.println("Do you want to log out or continue sending messages.");
				System.out.println("log out:0, continue:1");
				confirm = Integer.parseInt(scanner.nextLine());
			}

			System.out.println(actieveGebruiker.getGebruikersnaam() + " logged out.");

		}

	
}
