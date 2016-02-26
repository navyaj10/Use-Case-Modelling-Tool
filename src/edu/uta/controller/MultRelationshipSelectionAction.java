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

public class MultRelationshipSelectionAction extends AbstractAction {
        static final long serialVersionUID = 1L;

        public void actionPerformed(ActionEvent e) {
           /*
                String classes[] = new String[2];

                int relationshipIndex = gui.getMultiplicityRelationshipIndex();
                if (relationshipIndex >= aggregationList.size()) {
                        aggregation = null;
                        association = associationList.get(relationshipIndex - aggregationList.size());
                        classes[0] = association.getSrcClass().getName();
                        classes[1] = association.getDstClass().getName();
                }
                else {
                        association = null;
                        aggregation = aggregationList.get(relationshipIndex);
                        classes[0] = aggregation.getSrcClass().getName();
                        classes[1] = aggregation.getDstClass().getName();
                }
                gui.setMultiplicityClasses(classes);*/
        }
}
