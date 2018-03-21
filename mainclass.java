import java.util.Scanner;



public class mainclass {

	public static void main(String[] args) throws Exception {
		// beginscherm aanmelden of registreren
		String gebruikersnaam;
		String boodschap;
		String paswoord;
		int confirm;
		Gebruiker actieveGebruiker;
		String reciever;
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

		if (actieveGebruiker.getGebruikersnaam().equals(IOManager.ReadLineFromFile("reciever"))) {
			System.out.println("You have a message: ");
	    	EncryptionController.openMessage(IOManager.LoadKeyPair("a", "RSA"),IOManager.LoadKeyPair("b", "RSA"),IOManager.ReadLineFromFile("AESIV"));
		}
		
		System.out.println("insert name of receiver:");
		System.out.println("type list to get list of receivers");
		reciever = scanner.nextLine();

		
		// bij 2de list pakt die deze als username.
		while (GebruikerManager.checkName(reciever) == false) {
			System.out.println("insert name of receiver:");
			reciever = scanner.nextLine();
		}
		
			System.out.println("Type the message you want to send:");
			boodschap = scanner.nextLine();

			// encrypteren en decrypteren enzo
			
			setupFileForEncryption();
	    	EncryptionController.sendMessageTo(boodschap,actieveGebruiker.getGebruikersnaam(), reciever,IOManager.LoadKeyPair("a", "RSA"),IOManager.LoadKeyPair("b", "RSA"),IOManager.ReadLineFromFile("AESKey"),IOManager.ReadLineFromFile("AESIV"));

	    	
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
				reciever = scanner.nextLine();

				while (GebruikerManager.checkName(reciever) == false) {
					System.out.println("insert name of receiver:");
					reciever = scanner.nextLine();
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
	
	private static void setupFileForEncryption() throws Exception {
    	KeyManager keymanager = new KeyManager();
    	IOManager.SaveKeyPair("a", keymanager.getRSAKeyPair(1));
    	IOManager.SaveKeyPair("b", keymanager.getRSAKeyPair(2));
    	IOManager.WriteFile("AESKey", keymanager.getAESKey());
    	IOManager.WriteFile("AESIV", keymanager.getAESIV());
	}

	
}
