package edu.uta.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class loadDocCommand extends LoadCommand {

	String docName = null;
	LoadObject loadObj = new LoadObject();

	public loadDocCommand(String docName) {
		this.docName = docName;
	}

	// @Override
	LoadObject execute() {

		String selectDocument = "Select * from domainmodel.document where DocName='"
				+ docName + "';";
		ResultSet rs = queryFetch(selectDocument);
		try {
			// while(rs.next()){
			rs.next();
			loadObj.docId = rs.getInt("DocId");
			docName = rs.getString("DocName");
			System.out.println("DocID " + loadObj.docId);
			System.out.println("DocName " + docName);
			// }
			System.out.println("Fetched Document");

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return loadObj;
	}

}
