import java.util.Random;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class KeyManager {

	private String AESKey;
	private String AESIV;
	private KeyPair RSAUser1;
	private KeyPair RSAUser2;
	
	public KeyManager() throws Exception {
		AESKey = getRandomHexString(32);
		AESIV = getRandomHexString(16);
		RSAUser1 = buildKeyPair();
		RSAUser2 = buildKeyPair();
	}
	
	
    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }
    
    public KeyPair getRSAKeyPair(int gebruiker) {
    	if (gebruiker == 1) 
    		return RSAUser1;
    	else
    		return RSAUser2;
    }
    
    public PublicKey getRSAKeyPub(int gebruiker) {
    	if (gebruiker == 1) 
    		return RSAUser1.getPublic();
    	else
    		return RSAUser2.getPublic();
    }
    
    public PrivateKey getRSAKeyPrivate(int gebruiker) {
    	if (gebruiker == 1)
    		return RSAUser1.getPrivate();
    	else
    		return RSAUser2.getPrivate();
    }
	
	private String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }
	
	public String getAESKey() {
		return AESKey;
	}

	public String getAESIV() {
		return AESIV;
	}
	
}
