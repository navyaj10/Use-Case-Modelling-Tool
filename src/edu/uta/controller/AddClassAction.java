package edu.uta.controller;

import edu.uta.gui.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.AbstractAction;
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

public class AddClassAction extends AbstractAction {
   public AddClassAction() {
      super("Add Class");
   }
   public void actionPerformed(ActionEvent e) {
      Gui gui=Gui.getInstance();
      ClassObj c=new ClassObj(
         (gui.getSelectedText()!=null)?gui.getSelectedText():""
      );
      String[] attributes=new String[] {};
      if (DomainModel.getInstance().find(c)!=null) {
         ClassObj c1=(ClassObj)DomainModel.getInstance().find(c);
         attributes=new String[c1.getAttributes().size()];
         for (int i=0; i<attributes.length; i++) {
            Attribute a=(Attribute)c1.getAttributes().get(i);
            attributes[i]=a.getName();
         }
      }
      ClassDialog classDialog=new ClassDialog(gui.getJFrame());
      ClassOkAction ok=new ClassOkAction(classDialog);
      classDialog.setOkAction(ok);
      classDialog.setName(gui.getSelectedText());
      classDialog.setAttributes(attributes);
      classDialog.display();
   }
}
