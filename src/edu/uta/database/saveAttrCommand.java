package edu.uta.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uta.model.Attribute;

public class saveAttrCommand extends Command {

	ArrayList<Attribute> attrList = null;
	String className = null;

	// @Override
	LoadObject execute() {
		int classId = 0;
		String selectClassID = "Select ClassId from domainmodel.class where classname='"
				+ className + "'";
		ResultSet rs = queryFetch(selectClassID);
		try {
			rs.next();
			classId = rs.getInt("ClassId");
			System.out.println(classId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		int i = 0;
		while (i < attrList.size()) {
			String attrname = attrList.get(i).getName();
			System.out.println("attribute   " + attrList.get(i).getName());
			String insertAttrquery = "Insert into Attribute(AttrName,ClassId) values ('"
					+ attrname + "'," + classId + ");";
			queryExecute(insertAttrquery);
			i++;
		}
		return null;
	}

	public saveAttrCommand(ArrayList<Attribute> attrList, String className) {

		this.attrList = attrList;
		this.className = className;

	}

}
