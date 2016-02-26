package edu.uta.controller;
import javax.swing.*;
import java.awt.event.*;
import edu.uta.gui.*;
import edu.uta.model.*;
/**
 * <p>Title: Agile Unified Modeler</p>
 *
 * <p>Description: An Integrated Development Environment for OO analysis,
 * design, and more.</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Atruya Systems, Inc.</p>
 *
 * @author David Kung
 * @version 1.0
 */

public class AddAssociationAction extends AbstractAction {
   static final long serialVersionUID=1L;

   public AddAssociationAction() {
      super("Association");
   }
   public void actionPerformed(ActionEvent e) {
      String classes[] = DomainModel.getInstance().getClasses();
      Gui gui=Gui.getInstance();
      AssociationDialog associationDialog=new AssociationDialog(gui.getJFrame());
      AssociationOkAction ok=new AssociationOkAction(associationDialog);
    associationDialog.setOkAction(ok);
    associationDialog.setName(gui.getSelectedText());
    associationDialog.setClasses(classes);
    if (classes.length>1) {
       associationDialog.setSrcClass(classes[0]);
       associationDialog.setDestClass(classes[1]);
       associationDialog.setSrcRole("");
       associationDialog.setDestRole("");
       associationDialog.setSrcMultiplicity("");
       associationDialog.setDestMultiplicity("");
       associationDialog.display();
    } else {
       JOptionPane.showMessageDialog(gui.getJFrame(), "Not enough classes for a binary\n"+
          " association. Only binary associations are currently supported.");
       return;
    }
   }
}
