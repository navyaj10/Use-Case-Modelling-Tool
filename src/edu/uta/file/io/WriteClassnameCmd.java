package edu.uta.file.io;

import java.util.ArrayList;
import java.util.Iterator;

import edu.uta.model.ClassObj;

public class WriteClassnameCmd extends FileWriteCmd {
	ArrayList<ClassObj> classList;
	FileWrite fwr;

	public WriteClassnameCmd (FileWrite fwr, ArrayList<ClassObj> classList) {
		this.fwr = fwr;
		this.classList = classList;
	}

	public void fileWrite() {
		StringBuilder data = new StringBuilder();
		Iterator it = classList.iterator();
		while (it.hasNext()) {
                   String name=((ClassObj)it.next()).getName();
                   data.append("\""+name + "\" C 1 1 t\n");
                   //System.out.println("WriteClass: name="+name);
		}
		fwr.writeClassname(data.toString());
	}
}
