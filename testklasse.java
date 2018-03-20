import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class testklasse {
    
    public static void main(String [] args) throws Exception {
    	
    	/*
    	KeyManager manager = new KeyManager();
    	RSA_Encryptor rsaEncryptor = new RSA_Encryptor();
        
        // encrypt the message
        byte [] encrypted = rsaEncryptor.encrypt(manager.getRSAKeyPub(1), "This is a secret message");     
        System.out.println(new String(encrypted));  // <<encrypted message>>
        
        // decrypt the message
        byte[] secret = rsaEncryptor.decrypt(manager.getRSAKeyPrivate(1), encrypted);                                 
        System.out.println(new String(secret));     // This is a secret message
        
    	
        encrypted = rsaEncryptor.encrypt(manager.getRSAKeyPrivate(2), "This is a secret FOR MEEEEEE");     
        System.out.println(new String(encrypted));  // <<encrypted message>>
        
        // decrypt the message
        secret = rsaEncryptor.decrypt(manager.getRSAKeyPub(2), encrypted);                                 
        System.out.println(new String(secret));     // This is a secret message
        */
        
    	
    	EncryptionController controller = new EncryptionController();
    	
    	KeyManager keymanager = new KeyManager();
    	
    	controller.sendMessageTo("ik ben een kleine vis", "kobe", keymanager);
    	controller.openMessage("kobe", keymanager);
    	
    	
    	/*
    	IOManager.WriteFile("testfile", "ik ben een kleine vis");
    	System.out.println(IOManager.ReadLineFromFile("testfile"));
    	*/
    }
}