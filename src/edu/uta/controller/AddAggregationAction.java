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
public class AddAggregationAction extends AbstractAction {
   public AddAggregationAction() {
      super("Aggregation");
   }
   public void actionPerformed(ActionEvent e) {
      String classes[]=DomainModel.getInstance().getClasses();
      Gui gui=Gui.getInstance(); //Gui.getInstance();
      AggregationDialog aggregationDialog=new AggregationDialog(gui.getJFrame());
      AggregationOkAction ok=new AggregationOkAction(aggregationDialog);
      aggregationDialog.setOkAction(ok);
      aggregationDialog.setName("aggr");
      aggregationDialog.setClasses(classes);
      if (classes.length>1) {
         aggregationDialog.setWholeClass(classes[0]);
         aggregationDialog.setPartClass(classes[1]);
         aggregationDialog.setMultiplicity("");
         aggregationDialog.display();
      } else {
         javax.swing.JOptionPane.showMessageDialog(gui.getJFrame(),
            "Not enough classes for an aggregation relationship.");
         return;
      }
   }
}
