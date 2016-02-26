package edu.uta.gui;

import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MultiplicityDialog {
	private static final long serialVersionUID = 1L;
	private Dialog dialog;
	private JTextField multiplicityTextField;
	private JComboBox relationshipComboBox;
	private JComboBox classComboBox;
	
	
	public MultiplicityDialog(JFrame owner) {
		dialog = new Dialog(owner);

		dialog.setMinimumSize(new Dimension(300, 200));
		dialog.setTitle("Multiplicity");
		dialog.addLabel("Multiplicity Name");
		multiplicityTextField = dialog.addTextField(null);
		dialog.addLabel("Association/Aggregation");
		relationshipComboBox = dialog.addComboBox();
		dialog.addLabel("Class");
		classComboBox = dialog.addComboBox();
	}
	
	public void setName(String name) {
		multiplicityTextField.setText(name);
	}
	
	public String getName() {
		return multiplicityTextField.getText();
	}
	
	public void setOkAction(AbstractAction ok) {
		dialog.setOkAction(ok);
	}

	public void display() {
		dialog.create();	
	}
	
	public void setRelationships(String relationships[]) {
		relationshipComboBox.removeAllItems();
		dialog.addComboBoxList(relationships, relationshipComboBox);		
	}

	public void setClasses(String classes[]) {
		classComboBox.removeAllItems();
		dialog.addComboBoxList(classes, classComboBox);		
	}

	public void setRelationshipSelectionAction(AbstractAction relationshipSelection) {
		relationshipComboBox.addActionListener(relationshipSelection);
	}

	public int getRelationshipIndex() {
		return relationshipComboBox.getSelectedIndex();
	}
	
	public String getMultiplicityClass() {
		return (String)classComboBox.getSelectedItem();
	}	
}
