package edu.uta.file.io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileWrite {
	FileOutputStream fos;
	DataOutputStream dos;

	public FileWrite() {
	}

	public void writeClassname (String data) {
		File file = new File("tmp\\OOT_Classname");
		try {
			fos = new FileOutputStream(file);
			dos = new DataOutputStream(fos);
			dos.writeBytes(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeAttrib (String data) {
		File file = new File("tmp\\OOT_Attrib");
		try {
			fos = new FileOutputStream(file);
			dos = new DataOutputStream(fos);
			dos.writeBytes(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeMethod (String data) {
		File file = new File("tmp\\OOT_Method");
		try {
			fos = new FileOutputStream(file);
			dos = new DataOutputStream(fos);
			dos.writeBytes(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeRelationFile (String data) {
		File file = new File("tmp\\OOT_Relation_File");
		try {
			fos = new FileOutputStream(file);
			dos = new DataOutputStream(fos);
			dos.writeBytes(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
