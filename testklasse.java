import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class testklasse {
    
    public static void main(String [] args) throws Exception {
    	
    	KeyManager keymanager = new KeyManager();
    	IOManager.SaveKeyPair("a", keymanager.getRSAKeyPair(1));
    	IOManager.SaveKeyPair("b", keymanager.getRSAKeyPair(2));
    	IOManager.WriteFile("AESKey", keymanager.getAESKey());
    	IOManager.WriteFile("AESIV", keymanager.getAESIV());
    	EncryptionController.sendMessageTo("ik ben een kleine vis", "kobe",IOManager.LoadKeyPair("a", "RSA"),IOManager.LoadKeyPair("b", "RSA"),IOManager.ReadLineFromFile("AESKey"),IOManager.ReadLineFromFile("AESIV"));
    	EncryptionController.openMessage(IOManager.LoadKeyPair("a", "RSA"),IOManager.LoadKeyPair("b", "RSA"),IOManager.ReadLineFromFile("AESIV"));
    
    }
}