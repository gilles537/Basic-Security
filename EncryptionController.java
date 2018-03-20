import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

public class EncryptionController {
	
	
	public static void sendMessageTo(String message, String reciever,KeyManager keymanager,KeyPair keypair1,KeyPair keypair2,String AESKey,String AESIV) throws Exception  {
		
		String file1;
		byte[] file2;
		String messageHashed;
		byte[] file3;
		
		IOManager.WriteFile("reciever", reciever);
		file1 = AES_Encryptor.encrypt(AESKey, AESIV, message);
		//file1 = AES_Encryptor.encrypt(keymanager.getAESKey(), keymanager.getAESIV(), message);
		IOManager.WriteFile("file1", file1);
		file2 = RSA_Encryptor.encrypt(keypair1.getPublic(),keymanager.getAESKey());
		//file2 = RSA_Encryptor.encrypt(keymanager.getRSAKeyPub(1),keymanager.getAESKey());
		IOManager.WriteByte("file2", file2);
		messageHashed = Hasher.sha256(message);
		System.out.println( "De originele hash is: " + messageHashed);
		file3 = RSA_Encryptor.encrypt(keypair2.getPrivate(), messageHashed);
		//file3 = RSA_Encryptor.encrypt(keymanager.getRSAKeyPrivate(2), messageHashed);
		IOManager.WriteByte("file3", file3);
	}
	
	public static String openMessage(KeyManager keymanager,KeyPair keypair1,KeyPair keypair2,String AESIV) throws Exception {
		String symKey = new String(RSA_Encryptor.decrypt(keypair1.getPrivate(), IOManager.readByte("file2")));
		//String symKey = new String(RSA_Encryptor.decrypt(keymanager.getRSAKeyPrivate(1), IOManager.readByte("file2")));
		String message = AES_Encryptor.decrypt(symKey,AESIV, IOManager.ReadLineFromFile("file1"));
		String hashOriginalFile = new String(RSA_Encryptor.decrypt(keypair2.getPublic(), IOManager.readByte("file3")));
		//String hashOriginalFile = new String(RSA_Encryptor.decrypt(keymanager.getRSAKeyPub(2), IOManager.readByte("file3")));
		System.out.println("De hash van de file is: " + hashOriginalFile);
		System.out.println("de decrypte message is : " + message);
		System.out.println("De reciever of the file is: " + IOManager.ReadLineFromFile("reciever"));
		return message;
	}
}
