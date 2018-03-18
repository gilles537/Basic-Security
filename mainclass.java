import java.util.Scanner;

public class mainclass {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String gebruikersnaam;
		String boodschap;
		String paswoord;
		int confirm;
		Gebruiker testGebruiker;
		String receiver;
		GebruikerManager.GetGebruikers();

		Scanner scanner = new Scanner(System.in);

		System.out.println("log in: 0, register: 1");
		confirm = Integer.parseInt(scanner.nextLine());

		while (confirm != 0 || confirm != 1) {
			System.out.println("typ 1 or 0 to proceed");
			System.out.println("log in: 0, register: 1");
			confirm = Integer.parseInt(scanner.nextLine());
		}

		System.out.println("Username:");
		gebruikersnaam = scanner.nextLine();
		System.out.println("Password");
		paswoord = scanner.nextLine();
		testGebruiker = new Gebruiker(gebruikersnaam, paswoord);

		if (confirm == 1) {
			while (GebruikerManager.LoginGebruiker(testGebruiker) == false) {
				System.out.println("Username:");
				gebruikersnaam = scanner.nextLine();
				System.out.println("Password");
				paswoord = scanner.nextLine();
				testGebruiker = new Gebruiker(gebruikersnaam, paswoord);
			}
		} else if (confirm == 0) {
			while (GebruikerManager.RegisterGebruiker(testGebruiker) == false) {
				System.out.println("Username:");
				gebruikersnaam = scanner.nextLine();
				System.out.println("Password");
				paswoord = scanner.nextLine();
				testGebruiker = new Gebruiker(gebruikersnaam, paswoord);
			}
			System.out.println("Succesfully registered " + testGebruiker.getGebruikersnaam());
		}

		System.out.println("insert name of receiver:");
		System.out.println("type list to get list of receivers");
		receiver = scanner.nextLine();

		if (receiver.equals("list")) {
			GebruikerManager.AfdrukkenGebruikers();
			System.out.println("insert name of receiver:");
			receiver = scanner.nextLine();
		}

		System.out.println("Type the message you want to send:");
		boodschap = scanner.nextLine();

		// tot hier heb ik alles gedaan

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

	}

}
