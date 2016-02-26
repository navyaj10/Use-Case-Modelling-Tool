package edu.uta.database;

import java.sql.ResultSet;

import edu.uta.model.Aggregation;

public class loadAggrCommand extends LoadCommand {
	LoadObject loadObj = new LoadObject();

	public loadAggrCommand(LoadObject loadObj) {
		this.loadObj = loadObj;
	}

	// @Override
	LoadObject execute() {

		int i = 0;
		ResultSet rs = null;
		int docId = this.loadObj.docId;
		try {
			String getAggrquery = "Select * from domainmodel.aggregation where DocId="
					+ docId;

			rs = queryFetch(getAggrquery);
			while (rs.next()) {

				String aggrName = rs.getString("AggrName");
				String dstClass = rs.getString("DstClassName");
				String srcClass = rs.getString("SrcClassName");
				String multiplicity = rs.getString("Multiplicity");

				Aggregation aggrObj = new Aggregation();
				for (i = 0; i < this.loadObj.ClassList.size(); i++) {
					String classname = this.loadObj.ClassList.get(i).getName();
					if (classname.equals(srcClass)) {
						aggrObj.setSrcClass(this.loadObj.ClassList.get(i));
						// System.out.println("set source");
					}

					if (classname.equals(dstClass)) {
						aggrObj.setDstClass(this.loadObj.ClassList.get(i));
						// System.out.println("set dest");
					}
				}
				aggrObj.setMultiplicity(multiplicity);
				aggrObj.setName(aggrName);

				loadObj.aggreList.add(aggrObj);

			}

			// System.out.println("set first classobj in aggr");

			// System.out.println("Fetched Attribute");
		} catch (Exception e) {
			System.out.println("Error fetching  attr data from DB");
			e.printStackTrace();

		}
		return loadObj;
	}

}
