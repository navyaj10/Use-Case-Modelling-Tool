package edu.uta.controller;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import edu.uta.model.*;
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

public class AddAttributeAction extends AbstractAction {
   static final long serialVersionUID=1L;

   public AddAttributeAction() {
      super("Attribute");
   }
   public void actionPerformed(ActionEvent e) {
      DomainModel domainModel=DomainModel.getInstance();
      String classes[]=domainModel.getClasses();
      Gui gui=Gui.getInstance();
      AttributeDialog attributeDialog=new AttributeDialog(gui.getJFrame());
      AttributeOkAction ok=new AttributeOkAction(attributeDialog);
      attributeDialog.setOkAction(ok);
      attributeDialog.setName(gui.getSelectedText());
      attributeDialog.setClasses(classes);
      attributeDialog.display();
      //Gui.getInstance().highlightAttribute(gui.getSelectedText());
   }
}
