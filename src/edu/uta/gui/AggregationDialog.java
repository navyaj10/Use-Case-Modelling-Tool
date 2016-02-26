package edu.uta.gui;

import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AggregationDialog {
	private static final long serialVersionUID = 1L;
	private Dialog dialog;
	private JTextField aggregationTextField;
	private JComboBox wholeClassComboBox;
	private JComboBox partClassComboBox;
	private JTextField partMultTextField;
	
	public AggregationDialog(JFrame owner) {
		dialog = new Dialog(owner);

		dialog.setMinimumSize(new Dimension(300, 200));
		dialog.setTitle("Aggregation");
		dialog.addLabel("Aggregation Name");
		aggregationTextField = dialog.addTextField(null);
		dialog.addLabel("Whole Class");
		wholeClassComboBox = dialog.addComboBox();
		dialog.addLabel("Part Class");
		partClassComboBox = dialog.addComboBox();
		dialog.addLabel("Part Class Multiplicity");
		partMultTextField = dialog.addTextField(null);
	}
	
	public void setName(String name) {
		aggregationTextField.setText(name);
	}
	
	public String getName() {
		return aggregationTextField.getText();
	}
	
	public void setOkAction(AbstractAction ok) {
		dialog.setOkAction(ok);
	}

	public void display() {
		dialog.create();
	}

	public void setClasses(String classes[]) {
		wholeClassComboBox.removeAllItems();
		dialog.addComboBoxList(classes, wholeClassComboBox);		
		partClassComboBox.removeAllItems();
		dialog.addComboBoxList(classes, partClassComboBox);		
	}

	public void setWholeClass(String wholeClass) {
		wholeClassComboBox.setSelectedItem(wholeClass);
	}
	
	public void setPartClass(String partClass) {
		partClassComboBox.setSelectedItem(partClass);
	}
	
	public void setMultiplicity(String multiplicity) {
		partMultTextField.setText(multiplicity);
	}
	
	public String getMultiplicity() {
		return partMultTextField.getText();
	}

	public String getWholeClass() {
		return (String)wholeClassComboBox.getSelectedItem();
	}

	public String getPartClass() {
		return (String)partClassComboBox.getSelectedItem();
	}
}
