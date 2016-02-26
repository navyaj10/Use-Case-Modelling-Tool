package edu.uta.database;

import java.util.ArrayList;

import edu.uta.model.Aggregation;
import edu.uta.model.ClassObj;

public class saveAggrCommand extends Command {

	ArrayList<Aggregation> aggrList = null;
	LoadObject saveObj = null;

	public saveAggrCommand(ArrayList<Aggregation> aggrList, LoadObject saveObj) {
		this.saveObj = saveObj;
		this.aggrList = aggrList;

	}

	// @Override
	LoadObject execute() {
		int i = 0;
		String srcclassName = null;
		String destclassName = null;
		String multiplicity = null;

		int docId = saveObj.docId;
		while (i < aggrList.size()) {
			ClassObj srcClass = aggrList.get(i).getSrcClass();
			srcclassName = srcClass.getName();

			ClassObj destClass = aggrList.get(i).getDstClass();
			String aggrName = aggrList.get(i).getName();
			destclassName = destClass.getName();
			multiplicity = aggrList.get(i).getMultiplicity();
			i++;

			String insertAggrquery = "Insert into domainmodel.Aggregation(SrcClassName,DstClassName,Multiplicity,AggrName,DocId) values ('"
					+ srcclassName
					+ "','"
					+ destclassName
					+ "','"
					+ multiplicity + "','" + aggrName + "'," + docId + ");";

			queryExecute(insertAggrquery);
		}
		return saveObj;
	}

}
