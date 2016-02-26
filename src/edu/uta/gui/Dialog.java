package edu.uta.gui;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import edu.uta.model.ModelConcept;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Dialog extends JDialog implements PropertyChangeListener {
  public Dialog() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static final long serialVersionUID = 1L;
    List<Object> objectList = new ArrayList<Object>();
    private JOptionPane optionPane;
    private String okayButton = "Ok";
    private String cancelButton = "Cancel";
    private AbstractAction ok;

	/**
	 * @param owner
	 */
	public Dialog(JFrame owner) {
		super(owner, true);
        this.setLocationRelativeTo(owner);
	}

	public void setOkAction(AbstractAction ok) {
		this.ok = ok;
	}

	public void addLabel(String label) {
		objectList.add(new JLabel(label));
	}

	public JComboBox addComboBox() {
		JComboBox comboBox = new JComboBox();
		objectList.add(comboBox);
		return comboBox;
	}

	public JTextField addTextField(String defaultText) {
		JTextField textField = new JTextField(defaultText);
		objectList.add(textField);
		return textField;
	}

	public JButton addButton(Action a) {
		JButton button = new JButton(a);
		objectList.add(button);
		return button;
	}

	public JScrollPane addScrollPane() {
		JScrollPane scrollPane = new JScrollPane();
		objectList.add(scrollPane);
		return scrollPane;
	}

	public JList addList(JScrollPane scrollPane) {
		JList list = new JList();
		scrollPane.setViewportView(list);
		return list;
	}

	public String getTextField(JTextField textField) {
		return textField.getText();
	}

	public ModelConcept getComboBoxSelection(JComboBox comboBox) {
		return (ModelConcept)comboBox.getSelectedItem();
	}

	public void addComboBoxList(Object[] objArray, JComboBox comboBox) {
		for (int i = 0; i < objArray.length; i++) {
			comboBox.addItem(objArray[i]);
		}
	}

	public void create() {
		Object[] options = {okayButton, cancelButton};
		optionPane = new JOptionPane(objectList.toArray(),
				JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                null,
                options,
                options[0]);
		setContentPane(optionPane);

		//Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);

        this.pack();
        this.setVisible(true);
	}

    /** This method reacts to state changes in the option pane. */
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (isVisible()
         && (e.getSource() == optionPane)
         && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
             JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {

        	Object value = optionPane.getValue();
        	if (okayButton.equals(value)) {
                   if (ok!=null) ok.actionPerformed(null);
        	}
            setVisible(false);
        }
    }

  private void jbInit() throws Exception {
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"
