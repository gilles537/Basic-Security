import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class IOManager {
	
	public static void WriteFile(String fileName , String message) {
		try {
			File file = new java.io.File("Files/" + fileName + ".txt");
			file.createNewFile();
			PrintWriter writer = new PrintWriter(file, "ISO-8859-1");
			writer.println(message);
			writer.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}
	
	public static void WriteByte(String fileName, byte[] bytearray) {
		File file = new java.io.File("Files/" + fileName);
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(bytearray);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static byte[] readByte(String fileName) {
		Path fileLocation = Paths.get("Files/" + fileName);
		try {
			return Files.readAllBytes(fileLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String ReadLineFromFile(String fileName) {
		
		try {
			// Construct BufferedReader from FileReader
			BufferedReader br = new BufferedReader(new FileReader("Files/" + fileName + ".txt"));
			String line = null;
			String returnString = "";
			int count = 0;
			
			while ((line = br.readLine()) != null) {
				if (count == 0) {
					returnString += line;
					count += 1;
				}
				else
					returnString += "\n" + line;
			}
				return returnString;
		 
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	public static void SaveKeyPair(String gebruiker, KeyPair keyPair) throws IOException {
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
 
		// Store Public Key.
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
				publicKey.getEncoded());
		FileOutputStream fos = new FileOutputStream("Files/" + "public" + gebruiker + ".key");
		fos.write(x509EncodedKeySpec.getEncoded());
		fos.close();
 
		// Store Private Key.
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
				privateKey.getEncoded());
		fos = new FileOutputStream("Files/" + "private" + gebruiker + ".key");
		fos.write(pkcs8EncodedKeySpec.getEncoded());
		fos.close();
	}
	
	public static KeyPair LoadKeyPair(String gebruiker, String algorithm)
			throws IOException, NoSuchAlgorithmException,
			InvalidKeySpecException {
		// Read Public Key.
		File filePublicKey = new File("Files/" + "public" + gebruiker + ".key");
		FileInputStream fis = new FileInputStream("Files/" + "public" + gebruiker + ".key");
		byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
		fis.read(encodedPublicKey);
		fis.close();
 
		// Read Private Key.
		File filePrivateKey = new File("Files/" + "private" + gebruiker + ".key");
		fis = new FileInputStream("Files/" + "private" + gebruiker + ".key");
		byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
		fis.read(encodedPrivateKey);
		fis.close();
 
		// Generate KeyPair.
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
				encodedPublicKey);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
 
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
				encodedPrivateKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
 
		return new KeyPair(publicKey, privateKey);
	}
	
}
