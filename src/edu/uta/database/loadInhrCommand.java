package edu.uta.database;

import java.sql.ResultSet;
import edu.uta.model.Inheritance;

public class loadInhrCommand extends LoadCommand {

	LoadObject loadObj = new LoadObject();

	public loadInhrCommand(LoadObject loadObj) {
		this.loadObj = loadObj;
	}

	// @Override
	LoadObject execute() {
		int i = 0;
		ResultSet rs = null;
		int docId = this.loadObj.docId;
		try {
			String getInhrquery = "Select * from domainmodel.inheritance where DocId="
					+ docId;

			rs = queryFetch(getInhrquery);
			while (rs.next()) {

				String baseClass = rs.getString("BaseClassName");
				String drvClass = rs.getString("DerivedClassName");

				Inheritance inhrObj = new Inheritance();
				for (i = 0; i < this.loadObj.ClassList.size(); i++) {
					String classname = this.loadObj.ClassList.get(i).getName();
					if (classname.equals(baseClass)) {
						inhrObj.setSrcClass(this.loadObj.ClassList.get(i));
						// System.out.println("set source");
					}

					if (classname.equals(drvClass)) {
						inhrObj.setDstClass(this.loadObj.ClassList.get(i));
						// System.out.println("set dest");
					}
				}
				loadObj.inhrList.add(inhrObj);
			}
			// System.out.println("Fetched Inheritance");
		} catch (Exception e) {
			System.out.println("Error fetching  inhr data from DB");
			e.printStackTrace();

		}

		return loadObj;

	}

}
