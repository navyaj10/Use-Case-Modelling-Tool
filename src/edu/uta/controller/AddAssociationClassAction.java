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

public class AddAssociationClassAction extends AbstractAction {
   public AddAssociationClassAction() {
      super("Add Association Class");
   }
   public void actionPerformed(ActionEvent e) {
      String classes[] = DomainModel.getInstance().getClasses();
      Gui gui=Gui.getInstance();
      AssociationClassDialog associationClassDialog=new AssociationClassDialog(gui.getJFrame());
      AssociationClassOkAction ok=new AssociationClassOkAction(associationClassDialog);
    associationClassDialog.setOkAction(ok);
    associationClassDialog.setName(gui.getSelectedText());
    associationClassDialog.setClasses(classes);
    if (classes.length>1) {
       associationClassDialog.setSrcClass(classes[0]);
       associationClassDialog.setDestClass(classes[1]);
       associationClassDialog.display();
    } else {
       JOptionPane.showMessageDialog(gui.getJFrame(), "Not enough classes for a binary\n"+
          " association class link.");
       return;
    }
   }
}
