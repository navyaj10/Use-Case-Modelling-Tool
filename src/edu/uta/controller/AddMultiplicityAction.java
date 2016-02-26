package edu.uta.controller;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

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

public class AddMultiplicityAction extends AbstractAction {
   static final long serialVersionUID=1L;

   public AddMultiplicityAction() {
      super("Multiplicity");
   }
   public void actionPerformed(ActionEvent e) {
      /*
           String relationships[] = new String[aggregationList.size() + associationList.size()];
           for (int i = 0; i < aggregationList.size(); i++) {
                   relationships[i] = aggregationList.get(i).getName();
           }
           for (int i = aggregationList.size(); i < (aggregationList.size() + associationList.size()); i++) {
       relationships[i] = associationList.get(i - aggregationList.size()).getName();
           }
           gui.editMultiplicity(selectedText, relationships, new MultRelationshipSelectionAction(), new MultiplicityOkAction());
       */
   }
}
