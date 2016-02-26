package edu.uta.database;

import java.util.ArrayList;

import edu.uta.model.Association;
import edu.uta.model.ClassObj;

public class saveAssoCommand extends Command {
	ArrayList<Association> assoList = null;
	LoadObject saveObj = null;

	public saveAssoCommand(ArrayList<Association> assoList, LoadObject saveObj) {
		this.assoList = assoList;
		this.saveObj = saveObj;
	}

	// @Override
	LoadObject execute() {
		int i = 0;
		String srcclassName = null;
		String dstclassName = null;
		String srcMultiplicity = null;
		String dstMultiplicity = null;
		String srcRole = null;
		String dstRole = null;

		while (i < assoList.size()) {
			ClassObj srcClass = assoList.get(i).getSrcClass();
			srcclassName = srcClass.getName();

			ClassObj destClass = assoList.get(i).getDstClass();
			dstclassName = destClass.getName();
			srcMultiplicity = assoList.get(i).getSrcMultiplicity();
			dstMultiplicity = assoList.get(i).getDestMultiplicity();
			srcRole = assoList.get(i).getSrcRole();
			dstRole = assoList.get(i).getDestRole();
			int docId = saveObj.docId;
			i++;

			String insertAssoquery = "Insert into domainmodel.Association (Class1Name,Class2Name,RoleClass1,RoleClass2,MultiplicityClass1,MultiplicityClass2,DocId) values "
					+ "('"
					+ srcclassName
					+ "','"
					+ dstclassName
					+ "','"
					+ srcRole
					+ "','"
					+ dstRole
					+ "','"
					+ srcMultiplicity
					+ "','" + dstMultiplicity + "'," + docId + ");";

			queryExecute(insertAssoquery);
		}
		return saveObj;
	}

}
