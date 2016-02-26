package edu.uta.gui;

import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class RoleDialog {
	private static final long serialVersionUID = 1L;
	private Dialog dialog;
	private JTextField roleTextField;
	private JComboBox associationComboBox;
	private JComboBox classComboBox;
	
	public RoleDialog(JFrame owner) {
		dialog = new Dialog(owner);

		dialog.setMinimumSize(new Dimension(300, 200));
		dialog.setTitle("Role");
		dialog.addLabel("Role Name");
		roleTextField = dialog.addTextField(null);
		dialog.addLabel("Association");
		associationComboBox = dialog.addComboBox();
		dialog.addLabel("Class");
		classComboBox = dialog.addComboBox();
	}
	
	public void setName(String name) {
		roleTextField.setText(name);
	}
	
	public String getName() {
		return roleTextField.getText();
	}
	
	public void setOkAction(AbstractAction ok) {
		dialog.setOkAction(ok);
	}

	public void display() {	
		dialog.create();
	}
	
	public void setAssociations(String associations[]) {
		associationComboBox.removeAllItems();
		dialog.addComboBoxList(associations, associationComboBox);		
	}
	
	public void setClasses(String classes[]) {
		classComboBox.removeAllItems();
		dialog.addComboBoxList(classes, classComboBox);		
	}

	public void setAssociationSelectionAction(AbstractAction associationSelection) {
		associationComboBox.addActionListener(associationSelection);		
	}
	
	public int getAssociationIndex() {
		return associationComboBox.getSelectedIndex();
	}
	
	public String getRoleClass() {
		return (String)classComboBox.getSelectedItem();
	}	
}
