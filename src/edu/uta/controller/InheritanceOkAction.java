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
public class InheritanceOkAction extends OkAction {
   InheritanceDialog dialog;
   public InheritanceOkAction(InheritanceDialog dialog) {
      super();
      this.dialog=dialog;
   }
   public void actionPerformed(ActionEvent e) {
      String baseName=dialog.getBaseClass();
      String derivedName=dialog.getDerivedClass();
      Inheritance inheritance=new Inheritance();
      ClassObj baseClass=new ClassObj(baseName);
      ClassObj derivedClass=new ClassObj(derivedName);
      inheritance.setSrcClass(derivedClass);
      inheritance.setDstClass(baseClass);
      DomainModel domainModel=DomainModel.getInstance();
      if (mcIndex>=0) {
         DomainModel.getInstance().remove(mcIndex);
         domainModel.getConcepts().trimToSize();
         domainModel.add(mcIndex, inheritance);
      } else
         domainModel.add(inheritance);
      updateList();
      //Gui.getInstance().highlightInheritance("is a");
   }
}
