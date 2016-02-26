package edu.uta.gui;

import java.util.List;

import edu.uta.model.ClassObj;

public abstract class ModelConceptDialog {
	private String name;
	
	public abstract void display();
	public abstract boolean dataValid();

	protected void setName(String name) {
		this.name = name;
	}

	protected String getName() {
		return name;
	}
	
	protected String[] getClassArray(List<ClassObj> classList) {
		String classArray[] = new String[classList.size()];
		for (int i = 0; i < classList.size(); i++) {
			classArray[i] = classList.get(i).getName();
		}	
		
		return classArray;
	}

}
