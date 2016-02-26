package edu.uta.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ClassDialog {
   private static final long serialVersionUID=1L;
   private List<String> attrList=new ArrayList<String>();
   private Dialog dialog;
   private JList attributeList;
   private JTextField classTextField;

   public ClassDialog(JFrame owner) {
      dialog=new Dialog(owner);
      dialog.setMinimumSize(new Dimension(300, 200));
      dialog.setTitle("Class");
      dialog.addLabel("Class Name");
      classTextField=dialog.addTextField(null);
      JScrollPane scrollPane=dialog.addScrollPane();
      attributeList=dialog.addList(scrollPane);

      dialog.addButton(new AddAttributeAction());
      dialog.addButton(new RemoveAttributeAction());
   }

   public void setName(String name) {
      classTextField.setText(name);
   }

   public String getName() {
      return classTextField.getText();
   }

   public void setOkAction(AbstractAction ok) {
      dialog.setOkAction(ok);
   }

   public void display() {
      dialog.create();
   }

   public void setAttributes(String attributes[]) {
      attrList.clear(); // first clear everything
      for (int i=0; i<attributes.length; i++) {
         attrList.add(attributes[i]);
      }
      updateAttributeList();
   }

   public String[] getAttributes() {
      String attributes[]=new String[attrList.size()];
      return attrList.toArray(attributes);
   }

   private class AddAttributeAction extends AbstractAction {
      static final long serialVersionUID=1L;

      public AddAttributeAction() {
         super("Add Attribute");
      }
      public void actionPerformed(ActionEvent e) {
         String attribute=(String)JOptionPane.showInputDialog(
            dialog,
            "Add Attribute",
            "Attribute",
            JOptionPane.PLAIN_MESSAGE,
            null,
            null,
            null);

         attrList.add(attribute);
         updateAttributeList();
      }
   }

   private class RemoveAttributeAction extends AbstractAction {
      static final long serialVersionUID=1L;

      public RemoveAttributeAction() {
         super("Remove Attribute");
      }
      public void actionPerformed(ActionEvent e) {
         attrList.remove(attributeList.getSelectedValue());
         updateAttributeList();
      }
   }

   private void updateAttributeList() {
      attributeList.setListData(attrList.toArray());
   }
}
