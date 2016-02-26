package edu.uta.database;

import java.util.ArrayList;
import edu.uta.model.ClassObj;
import java.sql.*;

public class saveClassCommand extends Command {
	ArrayList<ClassObj> classList = null;
	ClassObj classObject = null;
	LoadObject saveObj = null;

	public saveClassCommand(ClassObj classObject, LoadObject saveObj) {
		this.classObject = classObject;
		this.saveObj = saveObj;
	}

	// @Override
	public LoadObject execute() {
		int i = 0;
		String insertClassquery = null;

		String selectDocID = "Select DocId from domainmodel.Document where DocName='Test.txt'";
		ResultSet rs = queryFetch(selectDocID);
		try {
			rs.next();
			// System.out.println(rs.getInt("DocId"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int docId = 0;
		try {
			docId = rs.getInt("DocId");
		} catch (SQLException e) {
			System.out.println("Error reading data from dataset");
			e.printStackTrace();
		}
		insertClassquery = "Insert into domainmodel.Class(classname,DocID) values ('"
				+ classObject.getName().toString() + "'," + docId + ");";
		queryExecute(insertClassquery);
		i++;
		return null;

	}
}
