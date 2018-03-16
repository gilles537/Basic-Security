
public class mainclass {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String test = "Dirk";
		AES_Encryptor encryptor = new AES_Encryptor();
		KeyManager keymanager = new KeyManager();
		
		//String encryptedTest =  encryptor.encrypt(keymanager.getAESKey(), keymanager.getAESIV(), "Pannekoek");
		
		Hasher hasher = new Hasher();
		
		System.out.println(hasher.sha256("ik ben een test hash"));
		
	}

}
