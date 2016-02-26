package edu.uta.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AttributeDialog {
	private static final long serialVersionUID = 1L;
	private Dialog dialog;
	private JComboBox classComboBox;
	private JTextField attributeTextField;

	public AttributeDialog(JFrame owner) {
		dialog = new Dialog(owner);

		dialog.setMinimumSize(new Dimension(300, 200));
		dialog.setTitle("Attribute");
		dialog.addLabel("Attribute Name");
		attributeTextField = dialog.addTextField(null);
		dialog.addLabel("Class");
		classComboBox = dialog.addComboBox();
		dialog.addButton(new edu.uta.controller.AddClassAction());
	}

	public void setName(String name) {
		attributeTextField.setText(name);
	}

	public String getName() {
		return attributeTextField.getText();
	}

	public void setOkAction(AbstractAction ok) {
		dialog.setOkAction(ok);
	}

	public void display() {
		dialog.create();
	}

	public void setClasses(String classes[]) {
		classComboBox.removeAllItems();
		dialog.addComboBoxList(classes, classComboBox);
	}

	public String getAttributeClass() {
		return (String)classComboBox.getSelectedItem();
	}

}
