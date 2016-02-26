package edu.uta.database;

import java.util.ArrayList;

import edu.uta.model.ClassObj;
import edu.uta.model.Inheritance;

public class saveInhrCommand extends Command {

	LoadObject saveObj = null;
	ArrayList<Inheritance> inhrList = null;

	// @Override
	LoadObject execute() {
		int i = 0;
		String srcclassName = null;
		String destclassName = null;
		int docId = saveObj.docId;
		while (i < inhrList.size()) {
			ClassObj srcClass = inhrList.get(i).getSrcClass();
			srcclassName = srcClass.getName();

			ClassObj destClass = inhrList.get(i).getDstClass();
			destclassName = destClass.getName();

			i++;

			String insertInheritance = "Insert into domainmodel.Inheritance (BaseClassName,DerivedClassName,DocId) values "
					+ "('"
					+ srcclassName
					+ "','"
					+ destclassName
					+ "',"
					+ docId + ");";

			queryExecute(insertInheritance);
		}
		return null;
	}

	public saveInhrCommand(ArrayList<Inheritance> inhrList, LoadObject saveObj) {
		this.inhrList = inhrList;
		this.saveObj = saveObj;

	}

}
