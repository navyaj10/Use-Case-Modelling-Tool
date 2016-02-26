package edu.uta.gui;

import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AssociationDialog {
	private static final long serialVersionUID = 1L;
	private Dialog dialog;
	private JTextField associationTextField;
	private JComboBox srcClassComboBox;
	private JTextField srcRoleTextField;
	private JTextField srcMultTextField;
	private JComboBox destClassComboBox;
	private JTextField destRoleTextField;
	private JTextField destMultTextField;
	
	public AssociationDialog(JFrame owner) {
		dialog = new Dialog(owner);

		dialog.setMinimumSize(new Dimension(300, 200));
		dialog.setTitle("Association");
		dialog.addLabel("Association Name");
		associationTextField = dialog.addTextField(null);
		dialog.addLabel("Source Class");
		srcClassComboBox = dialog.addComboBox();
		dialog.addLabel("Source Class Role");
		srcRoleTextField = dialog.addTextField(null);
		dialog.addLabel("Source Class Multiplicity");
		srcMultTextField = dialog.addTextField(null);
		dialog.addLabel("Destination Class");
		destClassComboBox = dialog.addComboBox();
		dialog.addLabel("Destination Class Role");
		destRoleTextField = dialog.addTextField(null);
		dialog.addLabel("Destination Class Multiplicity");
		destMultTextField = dialog.addTextField(null);
	}
	
	public void setName(String name) {
		associationTextField.setText(name);
	}
	
	public String getName() {
		return associationTextField.getText();
	}
	
	public void setOkAction(AbstractAction ok) {
		dialog.setOkAction(ok);
	}

	public void display() {
		dialog.create();
	}

	public void setClasses(String classes[]) {
		srcClassComboBox.removeAllItems();
		dialog.addComboBoxList(classes, srcClassComboBox);		
		destClassComboBox.removeAllItems();
		dialog.addComboBoxList(classes, destClassComboBox);		
	}

	public void setSrcClass(String srcClass) {
		srcClassComboBox.setSelectedItem(srcClass);
	}
	
	public void setDestClass(String destClass) {
		destClassComboBox.setSelectedItem(destClass);
	}
	
	public void setSrcRole(String srcRole) {
		srcRoleTextField.setText(srcRole);
	}
	
	public void setDestRole(String destRole) {
		destRoleTextField.setText(destRole);
	}
	
	public void setSrcMultiplicity(String srcMultiplicity) {
		srcMultTextField.setText(srcMultiplicity);
	}
	
	public void setDestMultiplicity(String destMultiplicity) {
		destMultTextField.setText(destMultiplicity);
	}
	
	public String getSrcRole() {
		return srcRoleTextField.getText();
	}

	public String getDestRole() {
		return destRoleTextField.getText();
	}

	public String getSrcMultiplicity() {
		return srcMultTextField.getText();
	}

	public String getDestMultiplicity() {
		return destMultTextField.getText();
	}

	public String getSrcClass() {
		return (String)srcClassComboBox.getSelectedItem();
	}

	public String getDestClass() {
		return (String)destClassComboBox.getSelectedItem();
	}
}
