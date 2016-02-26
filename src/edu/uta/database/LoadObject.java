package edu.uta.database;

import java.util.ArrayList;

import edu.uta.model.Aggregation;
import edu.uta.model.Association;
import edu.uta.model.ClassObj;
import edu.uta.model.Inheritance;

public class LoadObject {
	int docId = 0;
	int ClassId = 0;
	ArrayList<ClassObj> ClassList = new ArrayList<ClassObj>();
	ArrayList<Aggregation> aggreList = new ArrayList<Aggregation>();
	ArrayList<Association> assoList =new ArrayList<Association>();
	ArrayList<Inheritance> inhrList=new ArrayList<Inheritance>();
}
