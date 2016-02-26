package edu.uta.database;

import java.sql.*;

public class saveDocCommand extends Command {
	String docName = null;
	LoadObject saveObj = new LoadObject();

	public saveDocCommand(String docName) {
		this.docName = docName;

	}

	// @Override
	LoadObject execute() {

		String insertAggrquery = "Insert into domainmodel.document(DocName) values ('Test.txt');";
		String fetchDocId = "Select DocId from domainmodel.document where DocName='"
				+ docName + "'";
		ResultSet rs = queryFetch(fetchDocId);
		try {

			rs.next();
			saveObj.docId = rs.getInt("DocId");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queryExecute(insertAggrquery);
		clearTables("class");
		clearTables("attribute");
		clearTables("inheritance");
		clearTables("aggregation");
		clearTables("association");
		return saveObj;

	}

}
