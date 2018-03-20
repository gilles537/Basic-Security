import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
