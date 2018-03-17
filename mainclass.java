import java.util.Scanner;

public class mainclass {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String gebruikersnaam;
		String boodschap;
		String paswoord;
		Scanner scanner= new Scanner(System.in);
		System.out.println("Gebruikersnaam:");
		gebruikersnaam = scanner.nextLine();
		System.out.println("geef paswoord op");
		paswoord= scanner.nextLine();
		new Gebruiker(gebruikersnaam,paswoord);
		
		System.out.println("geef boodschap op");
		boodschap = scanner.nextLine();	

		new AES_Encryptor();
		KeyManager keymanager = new KeyManager();
		AES_Encryptor.encrypt(keymanager.getAESKey(), keymanager.getAESIV(), boodschap);
		
		String test = "Dirk";
		AES_Encryptor encryptor = new AES_Encryptor();
		KeyManager keymanager1 = new KeyManager();
		
		//String encryptedTest =  encryptor.encrypt(keymanager.getAESKey(), keymanager.getAESIV(), "Pannekoek");
		
		Hasher hasher = new Hasher();
		
		System.out.println(hasher.sha256("ik ben een test hash"));
		
	}

}
