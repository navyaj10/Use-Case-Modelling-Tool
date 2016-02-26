package edu.uta.database;

import java.util.ArrayList;

import edu.uta.model.Aggregation;
import edu.uta.model.Association;
import edu.uta.model.Attribute;
import edu.uta.model.ClassObj;
import edu.uta.model.Inheritance;

public class MySQLDbManager implements DBManager {

	private static MySQLDbManager instance;
	Command sc;
	LoadCommand lc;
	int docId;
	String[] result = null;
	LoadObject loadObj = new LoadObject();
	LoadObject saveObj = new LoadObject();

	private MySQLDbManager() {

	} // private constructor

	public static MySQLDbManager getInstance() {
		if (instance == null)
			instance = new MySQLDbManager();
		return instance;
	}

	// @Override
	public void saveClass(ClassObj classObject) {
		sc = new saveClassCommand(classObject, saveObj);
		sc.execute();
	}

	// @Override
	public void saveAttr(ArrayList<Attribute> attrList, String className) {
		sc = new saveAttrCommand(attrList, className);
		sc.execute();
	}

	// @Override
	public void saveAggr(ArrayList<Aggregation> aggrList) {
		sc = new saveAggrCommand(aggrList, saveObj);
		sc.execute();
	}

	// @Override
	public void saveAsso(ArrayList<Association> assoList) {
		sc = new saveAssoCommand(assoList, saveObj);
		sc.execute();
	}

	// @Override
	public void saveInhr(ArrayList<Inheritance> inhrList) {
		sc = new saveInhrCommand(inhrList, saveObj);
		sc.execute();
	}

	// @Override
	public void saveDocument(String docName) {
		sc = new saveDocCommand(docName);
		saveObj = sc.execute();
	}

	// @Override
	public ArrayList<String> RetrieveDocs() {

		sc = new RetrieveDocsCommand();
		sc.execute();
		ArrayList<String> result = ((RetrieveDocsCommand) sc).getResult();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result);
		}
		return result;
	}

	// @Override
	public void loadDocument(String docName) {
		lc = new loadDocCommand(docName);
		loadObj = lc.execute();

	}

	// @Override
	public void loadClass() {
		lc = new loadClassCommand(loadObj);
		loadObj = lc.execute();

	}

	// @Override
	public void loadAttribute() {
		lc = new loadAttrCommand(loadObj);
		loadObj = lc.execute();
	}

	// @Override
	public void loadAggregation() {
		lc = new loadAggrCommand(loadObj);
		loadObj = lc.execute();
	}

	// @Override
	public void loadAssociation() {
		lc = new loadAssoCommand(loadObj);
		loadObj = lc.execute();
	}

	// @Override
	public void loadInheritance() {
		lc = new loadInhrCommand(loadObj);
		loadObj = lc.execute();
	}

	public LoadObject loadDomainModelInfo() {
		return (this.loadObj);
	}
}
