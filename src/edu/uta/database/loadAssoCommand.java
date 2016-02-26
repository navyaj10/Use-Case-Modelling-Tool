package edu.uta.database;

import java.sql.ResultSet;
import edu.uta.model.Association;

public class loadAssoCommand extends LoadCommand {
	LoadObject loadObj = new LoadObject();

	public loadAssoCommand(LoadObject loadObj) {
		this.loadObj = loadObj;
	}

	// @Override
	LoadObject execute() {

		int i = 0;
		ResultSet rs = null;
		int docId = this.loadObj.docId;
		try {
			String getAssoquery = "Select * from domainmodel.association where DocId="
					+ docId;

			rs = queryFetch(getAssoquery);
			while (rs.next()) {

				String assoName = rs.getString("AssoName");
				String class1 = rs.getString("Class1Name");
				String class2 = rs.getString("Class2Name");
				String multiplicity1 = rs.getString("MultiplicityClass1");
				String multiplicity2 = rs.getString("MultiplicityClass2");
				String role1 = rs.getString("RoleClass1");
				String role2 = rs.getString("RoleClass2");

				// System.out.println("1--"+class1+"2--"+class2);

				Association assoObj = new Association();
				for (i = 0; i < this.loadObj.ClassList.size(); i++) {
					String classname = this.loadObj.ClassList.get(i).getName();
					if (classname.equals(class1)) {
						assoObj.setSrcClass(this.loadObj.ClassList.get(i));
						// System.out.println("set asso source");
					}

					if (classname.equals(class2)) {
						assoObj.setDstClass(this.loadObj.ClassList.get(i));
						// System.out.println("set asso dest");
					}
				}
				assoObj.setSrcMultiplicity(multiplicity1);
				assoObj.setDestMultiplicity(multiplicity2);
				assoObj.setName(assoName);
				assoObj.setSrcRole(role1);
				assoObj.setDestRole(role2);

				loadObj.assoList.add(assoObj);
			}

			// System.out.println("set first classobj in asso");

			;
			// System.out.println("Fetched Association");
		} catch (Exception e) {
			System.out.println("Error fetching association data from DB");
			e.printStackTrace();

		}

		return loadObj;
	}

}
