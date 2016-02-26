package edu.uta.database;

import java.sql.ResultSet;

import edu.uta.model.ClassObj;

public class loadClassCommand extends LoadCommand {
	int docId = 0;
	LoadObject loadObj = new LoadObject();

	public loadClassCommand(LoadObject loadObj) {
		this.loadObj = loadObj;
	}

	// @Override
	LoadObject execute() {
		docId = loadObj.docId;
		String getClassquery = "select * from domainmodel.class a where a.DocId = ("
				+ docId + ");";
		try {
			ResultSet rs = queryFetch(getClassquery);

			while (rs.next()) {
				String classname = rs.getString("classname");
				System.out.println(classname);
				ClassObj classObj = new ClassObj();
				classObj.setName(classname);
				this.loadObj.ClassList.add(classObj);
			}
			System.out.println("Fetched Class");
		} catch (Exception e) {
			System.out.println("Error fetching  Class data from DB");
			e.printStackTrace();
		}
		return this.loadObj;
	}

}
