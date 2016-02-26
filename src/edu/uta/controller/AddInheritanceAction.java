package edu.uta.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import edu.uta.gui.*;
import edu.uta.model.*;
import java.util.*;

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

public class AddInheritanceAction extends AbstractAction {
   static final long serialVersionUID=1L;

   public AddInheritanceAction() {
      super("Inheritance");
   }
   public void actionPerformed(ActionEvent e) {
      String classes[]=DomainModel.getInstance().getClasses();
      Gui gui=Gui.getInstance();
      InheritanceDialog inheritanceDialog=new InheritanceDialog(gui.getJFrame());
      InheritanceOkAction ok=new InheritanceOkAction(inheritanceDialog);
      inheritanceDialog.setOkAction(ok);
      //inheritanceDialog.setName(name);
      inheritanceDialog.setClasses(classes);
      if (classes.length>1) {
         inheritanceDialog.setBaseClass(classes[0]);
         inheritanceDialog.setDerivedClass(classes[1]);
         inheritanceDialog.display();
      } else {
         JOptionPane.showMessageDialog(gui.getJFrame(), "Number of classes is less than one.\n"+
            " It is not enough for an inheritance relationship.");
         return;
      }
   }
}
