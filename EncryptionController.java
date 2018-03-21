import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

public class EncryptionController {
	
	
	public static void sendMessageTo(String message,String sender, String reciever,KeyPair keypair1,KeyPair keypair2,String AESKey,String AESIV) throws Exception  {
		
		String file1;
		byte[] file2;
		String messageHashed;
		byte[] file3;
		
		IOManager.WriteFile("sender", sender);
		IOManager.WriteFile("reciever", reciever);
		file1 = AES_Encryptor.encrypt(AESKey, AESIV, message);
		IOManager.WriteFile("file1", file1);
		file2 = RSA_Encryptor.encrypt(keypair1.getPublic(),AESKey);
		IOManager.WriteByte("file2", file2);
		messageHashed = Hasher.sha256(message);
		System.out.println( "The original hash is: " + messageHashed);
		file3 = RSA_Encryptor.encrypt(keypair2.getPrivate(), messageHashed);
		IOManager.WriteByte("file3", file3);
	}
	
	public static String openMessage(KeyPair keypair1,KeyPair keypair2,String AESIV) throws Exception {
		String symKey = new String(RSA_Encryptor.decrypt(keypair1.getPrivate(), IOManager.readByte("file2")));
		String message = AES_Encryptor.decrypt(symKey,AESIV, IOManager.ReadLineFromFile("file1"));
		String hashOriginalFile = new String(RSA_Encryptor.decrypt(keypair2.getPublic(), IOManager.readByte("file3")));
		System.out.println("The message is from the user: " + IOManager.ReadLineFromFile("sender"));
		System.out.println("The hash of the decrpted file is: " + hashOriginalFile);
		System.out.println("The decrypte message is: " + message);
		//System.out.println("De reciever of the file is: " + IOManager.ReadLineFromFile("reciever"));
		return message;
	}
}
