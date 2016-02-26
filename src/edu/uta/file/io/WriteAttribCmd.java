package edu.uta.file.io;

import java.util.ArrayList;
import java.util.Iterator;

import edu.uta.model.Attribute;
import edu.uta.model.ClassObj;

public class WriteAttribCmd extends FileWriteCmd {
	ArrayList<ClassObj> classList;
	FileWrite fwr;

	public WriteAttribCmd(FileWrite fwr, ArrayList<ClassObj> classList) {
		this.fwr = fwr;
		this.classList = classList;
	}

	public void fileWrite() {
		StringBuilder data = new StringBuilder();
		Iterator it = classList.iterator();
		Iterator it_attr;
		while (it.hasNext()) {
			ClassObj obj = (ClassObj)it.next();
			data.append("\""+obj.getName() + "\" <PACKAGE> "
					+ obj.getAttributes().size() + "\n");
			it_attr = obj.getAttributes().iterator();
                        //System.out.println("attributes="+obj.getAttributes().size());
			while (it_attr.hasNext()) {
				data.append(((Attribute)it_attr.next()).getName() + "\n");
			}
		}
		//System.out.println(data);
		fwr.writeAttrib(data.toString());
	}
}
