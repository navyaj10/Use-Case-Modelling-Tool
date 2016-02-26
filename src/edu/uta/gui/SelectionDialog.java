package edu.uta.gui;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;

public class SelectionDialog extends Dialog {
   private static final long serialVersionUID = 1L;

   public SelectionDialog(JFrame owner, Action[] actionArray) {
      super(owner);
      setTitle("Add Model Concept");

      for (int i = 0; i < actionArray.length; i++) {
         addButton(actionArray[i]);
      }
      create();
   }

   /*public void create() {
      Object[] options = {"OK", "Close"};
      JOptionPane optionPane = new JOptionPane(objectList.toArray(),
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
   }*/

}
