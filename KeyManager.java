import java.util.Random;

public class KeyManager {

	private String AESKey;
	private String AESIV;
	
	public KeyManager() {
		AESKey = getRandomHexString(32);
		AESIV = getRandomHexString(16);
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
