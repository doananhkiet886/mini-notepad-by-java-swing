package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;

public class MyFile {
	public static File createNewFile(String fileName) throws IOException {
		File file = new File(fileName);
		boolean isSucccess = file.createNewFile();
		if (isSucccess) {
			return file;
		}
		return null;
	}
	
	public static String loadFromFile(File file) throws IOException {
		FileReader fw = new FileReader(file);
		BufferedReader br = new BufferedReader(fw);
		
		String content = "";
		while(true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			content = content + line + "\n";
		}
	
		br.close();
		fw.close();
		
		content = MyString.removeLastChar(content, '\n');
		return content;
	}
	
	public static void saveToFile(File file, String content) throws IOException {
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		fw = new FileWriter(file);
		bw = new BufferedWriter(fw);
		
		bw.write(content);

		bw.close();
		fw.close();
	}
}





