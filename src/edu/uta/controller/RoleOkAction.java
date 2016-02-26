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
public class RoleOkAction extends AbstractAction {
   Gui gui;
   public RoleOkAction(Gui gui) {
      this.gui=gui;
   }
   public void actionPerformed(ActionEvent e) {
      /**
      String roleName=gui.getRoleName();
      String roleClass=gui.getRoleClass();
      String roleSrcClass=association.getSrcClass().getName();
      String roleDestClass=association.getDstClass().getName();
      dmController.createRole(roleName, association.getName(), roleSrcClass,
         roleDestClass, roleSrcClass.matches(roleClass));
      updateList();
      gui.highlightRole(roleName); */
   }
}

