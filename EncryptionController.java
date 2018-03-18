
public class EncryptionController {
	
	private String file1;
	private byte[] file2;
	private String messageHashed;
	private byte[] file3;
	
	
	public void sendMessageTo(String message, String reciever,KeyManager keymanager) throws Exception  {
		file1 = AES_Encryptor.encrypt(keymanager.getAESKey(), keymanager.getAESIV(), message);
		file2 = RSA_Encryptor.encrypt(keymanager.getRSAKeyPub(1),keymanager.getAESKey());
		messageHashed = Hasher.sha256(message);
		System.out.println( "The hash is: " + messageHashed);
		file3 = RSA_Encryptor.encrypt(keymanager.getRSAKeyPrivate(2), messageHashed);
	}
	
	public void openMessage(String reciever,KeyManager keymanager) throws Exception {
		String symKey = new String(RSA_Encryptor.decrypt(keymanager.getRSAKeyPrivate(1), file2));
		String message = AES_Encryptor.decrypt(symKey, keymanager.getAESIV(), file1);
		String messageHashed2 = Hasher.sha256(message);
		String hashOriginalFile = new String(RSA_Encryptor.decrypt(keymanager.getRSAKeyPub(2), file3));
		System.out.println("De hash van de file is: " + hashOriginalFile);
		System.out.println("de originele message is : " + message);
	}
}
