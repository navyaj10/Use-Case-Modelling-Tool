package edu.uta.controller;

import edu.uta.model.Attribute;
import java.util.List;
import java.awt.event.ActionEvent;
import edu.uta.model.*;
import javax.swing.AbstractAction;
import edu.uta.gui.*;
import java.util.ArrayList;

public class ClassOkAction extends OkAction {
   ClassDialog dialog;
   private static final long serialVersionUID=1L;
   public ClassOkAction(ClassDialog dialog) {
      super();
      this.dialog=dialog;
   }
   public void actionPerformed(ActionEvent e) {
      String className=dialog.getName();
      ClassObj classObj=new ClassObj(className);
      String attributes[]=dialog.getAttributes();
      for (int i=0; i<attributes.length; i++) {
         Attribute attribute=new Attribute();
         attribute.setName(attributes[i]);
         classObj.addAttribute(attribute);
      }
      DomainModel domainModel=DomainModel.getInstance();
      if (mcIndex>=0) {
         DomainModel.getInstance().remove(mcIndex);
         domainModel.getConcepts().trimToSize();
         domainModel.add(mcIndex, classObj);
      } else
         domainModel.add(classObj);
      updateList();
      //Gui.getInstance().highlightClass(className);
   }
}
