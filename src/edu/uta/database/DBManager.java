package edu.uta.database;

import java.util.ArrayList;

import edu.uta.model.Aggregation;
import edu.uta.model.Association;
import edu.uta.model.Attribute;
import edu.uta.model.ClassObj;
import edu.uta.model.Inheritance;

public interface DBManager {

	void saveDocument(String docName);

	void saveClass(ClassObj classObject);

	void saveAttr(ArrayList<Attribute> attrList, String className);

	void saveAggr(ArrayList<Aggregation> aggrList);

	void saveAsso(ArrayList<Association> assoList);

	void saveInhr(ArrayList<Inheritance> inhrList);

	void loadDocument(String docName);

	void loadClass();

	void loadAttribute();

	void loadAggregation();

	void loadAssociation();

	void loadInheritance();

	ArrayList<String> RetrieveDocs();

	public LoadObject loadDomainModelInfo();
}
