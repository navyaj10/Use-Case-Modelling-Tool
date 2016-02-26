package edu.uta.controller.cmd;

import edu.uta.model.*;
import edu.uta.controller.AggregationOkAction;
import javax.swing.JFrame;
import edu.uta.gui.AggregationDialog;

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
public class AggregationCmd extends ActionCmd {
   public AggregationCmd() {
      super();
   }
   /**
    * execute
    *
    * @todo Implement this edu.uta.controller.cmd.ActionCmd method
    */
   public void execute() {
      Aggregation aggregation=(Aggregation)mc;
      String classes[]=DomainModel.getInstance().getClasses();
      AggregationDialog aggregationDialog=new AggregationDialog(new JFrame());
      AggregationOkAction ok=new AggregationOkAction(aggregationDialog);
      ok.setIndex(DomainModel.getInstance().getIndex(mc));
      aggregationDialog.setOkAction(ok);
      aggregationDialog.setName(aggregation.getName());
      aggregationDialog.setClasses(classes);
      aggregationDialog.setWholeClass(aggregation.getSrcClass().getName());
      aggregationDialog.setPartClass(aggregation.getDstClass().getName());
      aggregationDialog.setMultiplicity(aggregation.getMultiplicity());
      aggregationDialog.display();
   }
}
