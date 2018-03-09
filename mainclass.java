
public class mainclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String test = "test5";
		AES_Encryptor encryptor = new AES_Encryptor();
		KeyManager keymanager = new KeyManager();
		
		String encryptedTest =  encryptor.encrypt(keymanager.getAESKey(), keymanager.getAESIV(), "Pannekoek");
	}

}
