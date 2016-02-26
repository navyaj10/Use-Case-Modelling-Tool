package edu.uta.gui;

import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class InheritanceDialog {
	private static final long serialVersionUID = 1L;
	private Dialog dialog;
	private JComboBox baseClassComboBox;
	private JComboBox derivedClassComboBox;
	
	public InheritanceDialog(JFrame owner) {
		dialog = new Dialog(owner);

		dialog.setMinimumSize(new Dimension(300, 200));
		dialog.setTitle("Inheritance");
		dialog.addLabel("Base Class");
		baseClassComboBox = dialog.addComboBox();
		dialog.addLabel("Derived Class");
		derivedClassComboBox = dialog.addComboBox();
}
	
	public void setOkAction(AbstractAction ok) {
		dialog.setOkAction(ok);
	}

	public void display() {
		dialog.create();
	}
	
	public void setClasses(String classes[]) {
		baseClassComboBox.removeAllItems();
		dialog.addComboBoxList(classes, baseClassComboBox);		
		derivedClassComboBox.removeAllItems();
		dialog.addComboBoxList(classes, derivedClassComboBox);		
	}

	public void setBaseClass(String baseClass) {
		baseClassComboBox.setSelectedItem(baseClass);
	}
	
	public void setDerivedClass(String derivedClass) {
		derivedClassComboBox.setSelectedItem(derivedClass);
	}
	
	public String getBaseClass() {
		return (String)baseClassComboBox.getSelectedItem();
	}

	public String getDerivedClass() {
		return (String)derivedClassComboBox.getSelectedItem();
	}
}
