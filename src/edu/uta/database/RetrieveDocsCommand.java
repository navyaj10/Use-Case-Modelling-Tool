package edu.uta.database;

import java.sql.*;
import java.util.ArrayList;

public class RetrieveDocsCommand extends Command {
	String docName = null;
	ArrayList<String> result = new ArrayList<String>();

	ArrayList<String> getResult() {

		return result;

	}

	// @Override
	LoadObject execute() {
		int docId = 0;
		int i = 0;
		String selectDocument = "Select * from domainmodel.document;";
		ResultSet rs = queryFetch(selectDocument);
		try {
			while (rs.next()) {

				docId = rs.getInt("DocId");
				docName = rs.getString("DocName");

				System.out.println(docId + docName);
				result.add(docName);
				i++;
			}
			System.out.println("Fecthed Document");

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;

	}
}
