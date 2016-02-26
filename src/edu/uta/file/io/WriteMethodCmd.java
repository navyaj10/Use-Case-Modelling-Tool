package edu.uta.file.io;

import java.util.ArrayList;
import java.util.Iterator;

import edu.uta.model.ClassObj;

public class WriteMethodCmd extends FileWriteCmd {
	FileWrite fwr;
	ArrayList<ClassObj> classList;

	public WriteMethodCmd (FileWrite fwr, ArrayList<ClassObj> classList) {
		this.fwr = fwr;
		this.classList = classList;
	}

	public void fileWrite() {
		StringBuilder data = new StringBuilder();
		System.out.println(data);
		Iterator it = classList.iterator();

		while (it.hasNext()) {
			data.append(((ClassObj)it.next()).getName() + " <PACKAGE> 0\n");
		}
		//System.out.println(data);
		fwr.writeMethod(data.toString());
	}
}
