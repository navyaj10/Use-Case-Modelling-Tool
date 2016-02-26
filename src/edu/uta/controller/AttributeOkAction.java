package edu.uta.controller;
import javax.swing.AbstractAction;
import java.awt.event.*;
import edu.uta.gui.*;
import edu.uta.controller.*;
import edu.uta.model.*;
import java.util.ArrayList;
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

public class AttributeOkAction extends OkAction {
   AttributeDialog dialog;
   public AttributeOkAction(AttributeDialog dialog) {
      this.dialog=dialog;
   }
   public void actionPerformed(ActionEvent e) {
      Gui gui=Gui.getInstance();
      String attributeName=dialog.getName();
      String attributeClass=dialog.getAttributeClass();
      ClassObj c=new ClassObj(attributeClass);
      DomainModel dm=DomainModel.getInstance();
      ClassObj c1=(ClassObj)dm.find(c);
      if (c1==null) {
         c1=c;
         dm.add(c1);
      }
      Attribute a=new Attribute();
      a.setName(attributeName);
      c1.addAttribute(a);
      updateList();
      //gui.highlightAttribute(attributeName);
   }
}
