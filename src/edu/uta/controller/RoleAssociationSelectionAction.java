package edu.uta.controller;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import edu.uta.gui.*;

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

public class RoleAssociationSelectionAction extends AbstractAction {
   Gui gui;
   public RoleAssociationSelectionAction(Gui gui) {
      this.gui=gui;
   }
   public void actionPerformed(ActionEvent e) {
      String classes[]=new String[2];
      /**
                      int associationIndex = gui.getRoleAssociationIndex();
                      association = associationList.get(associationIndex);
                      classes[0] = association.getSrcClass().getName();
                      classes[1] = association.getDstClass().getName();
                      gui.setRoleClasses(classes); */
   }
}
