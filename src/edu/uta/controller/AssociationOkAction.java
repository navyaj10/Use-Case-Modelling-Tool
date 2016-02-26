package edu.uta.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;
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
public class AssociationOkAction extends OkAction {
   AssociationDialog dialog;
   public AssociationOkAction(AssociationDialog dialog) {
      super();
      this.dialog=dialog;
   }
   public void actionPerformed(ActionEvent e) {
      String associationName=dialog.getName(); // hhh null pointer exception
      String srcClass=dialog.getSrcClass();
      String destClass=dialog.getDestClass();
      String srcRole=dialog.getSrcRole();
      String destRole=dialog.getDestRole();
      String srcMultiplicity=dialog.getSrcMultiplicity();
      String destMultiplicity=dialog.getDestMultiplicity();
      Association association=new Association(associationName, srcClass,
         destClass, srcRole, destRole, srcMultiplicity, destMultiplicity);
      DomainModel domainModel=DomainModel.getInstance();
      if (mcIndex>=0) {
         DomainModel.getInstance().remove(mcIndex);
         domainModel.getConcepts().trimToSize();
         domainModel.add(mcIndex, association);
      } else
         domainModel.add(association);
      updateList();
      Gui.getInstance().
      highlightAssociation(associationName);
   }
}
