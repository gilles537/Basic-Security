import java.nio.charset.StandardCharsets;

public class EncryptionController {
	
	private String file1;
	private byte[] file2;
	private String messageHashed;
	private byte[] file3;
	
	
	public void sendMessageTo(String message, String reciever,KeyManager keymanager) throws Exception  {
		file1 = AES_Encryptor.encrypt(keymanager.getAESKey(), keymanager.getAESIV(), message);
		IOManager.WriteFile("file1", file1);
		file2 = RSA_Encryptor.encrypt(keymanager.getRSAKeyPub(1),keymanager.getAESKey());
		IOManager.WriteByte("file2", file2);
		messageHashed = Hasher.sha256(message);
		System.out.println( "The hash is: " + messageHashed);
		file3 = RSA_Encryptor.encrypt(keymanager.getRSAKeyPrivate(2), messageHashed);
		IOManager.WriteByte("file3", file3);
	}
	
	public void openMessage(String reciever,KeyManager keymanager) throws Exception {
		String symKey = new String(RSA_Encryptor.decrypt(keymanager.getRSAKeyPrivate(1), IOManager.readByte("file2")));
		String message = AES_Encryptor.decrypt(symKey, keymanager.getAESIV(), IOManager.ReadLineFromFile("file1"));
		String hashOriginalFile = new String(RSA_Encryptor.decrypt(keymanager.getRSAKeyPub(2), IOManager.readByte("file3")));
		System.out.println("De hash van de file is: " + hashOriginalFile);
		System.out.println("de originele message is : " + message);
	}
}
