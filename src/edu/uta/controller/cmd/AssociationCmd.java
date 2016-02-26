package edu.uta.controller.cmd;

import edu.uta.model.*;
import edu.uta.controller.AssociationOkAction;
import javax.swing.JFrame;
import edu.uta.gui.AssociationDialog;

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
public class AssociationCmd extends ActionCmd {
   public AssociationCmd() {
      super();
   }
   /**
    * execute
    *
    * @todo Implement this edu.uta.controller.cmd.ActionCmd method
    */
   public void execute() {
      Association association=(Association)mc;
      String classes[]=DomainModel.getInstance().getClasses();
      AssociationDialog associationDialog=new AssociationDialog(new JFrame());
      AssociationOkAction ok=new AssociationOkAction(associationDialog);
      ok.setIndex(DomainModel.getInstance().getIndex(mc));
      associationDialog.setOkAction(ok);
      associationDialog.setName(association.getName());
      associationDialog.setClasses(classes);
      associationDialog.setSrcClass(association.getSrcClass().getName());
      associationDialog.setDestClass(association.getDstClass().getName());
      associationDialog.setSrcRole(association.getSrcRole());
      associationDialog.setDestRole(association.getDestRole());
      associationDialog.setSrcMultiplicity(association.getSrcMultiplicity());
      associationDialog.setDestMultiplicity(association.getDestMultiplicity());
      associationDialog.display();
   }
}
