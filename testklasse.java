import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class testklasse {
    
    public static void main(String [] args) throws Exception {
    	
    	KeyManager manager = new KeyManager();
    	RSA_Encryptor rsaEncryptor = new RSA_Encryptor();
        
        // encrypt the message
        byte [] encrypted = rsaEncryptor.encrypt(manager.getRSAKeyPrivate(1), "This is a secret message");     
        System.out.println(new String(encrypted));  // <<encrypted message>>
        
        // decrypt the message
        byte[] secret = rsaEncryptor.decrypt(manager.getRSAKeyPub(1), encrypted);                                 
        System.out.println(new String(secret));     // This is a secret message
    }
}