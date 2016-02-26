package edu.uta.file.io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {

	/**
	 * write the output to the file in the specific format
	 */
	public void writeFile() {
		FileOutputStream fos;
		DataOutputStream dos;

		try {
			File file = new File("C:\\classes_file.txt");
			fos = new FileOutputStream(file);
			dos = new DataOutputStream(fos);
			dos.writeUTF("string");
		} catch (IOException e) {
			System.out.println("Error writing to the file");
		}
	}

}
