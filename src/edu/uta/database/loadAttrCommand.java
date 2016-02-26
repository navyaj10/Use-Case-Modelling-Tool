package edu.uta.database;

import java.sql.ResultSet;

import edu.uta.model.Attribute;

public class loadAttrCommand extends LoadCommand {
	LoadObject loadObj = new LoadObject();

	public loadAttrCommand(LoadObject loadObj) {

		this.loadObj = loadObj;
	}

	// @Override
	LoadObject execute() {
		int i = 0;
		// int docId=loadObj.docId;
		ResultSet rs = null;

		while (i < loadObj.ClassList.size()) {
			String classname = loadObj.ClassList.get(i).getName();
			String fetchClassId = "Select ClassId from domainmodel.class where classname='"
					+ classname + "'";
			rs = queryFetch(fetchClassId);
			try {
				rs.next();
				int classId = rs.getInt("ClassId");
				String getAttrquery = "Select * from domainmodel.attribute a where ClassId="
						+ classId;

				rs = queryFetch(getAttrquery);
				while (rs.next()) {
					String attributeName = rs.getString("AttrName");
					// System.out.println(attributeName);
					Attribute attrObj = new Attribute();
					attrObj.setName(attributeName);
					loadObj.ClassList.get(i).addAttribute(attrObj);
				}

				// System.out.println("Fetched Attribute");
			} catch (Exception e) {
				System.out.println("Error fetching attr data from DB");
				e.printStackTrace();
			}
			i++;
		}
		System.out.println("Fetched Attribute");
		return loadObj;
	}

}
