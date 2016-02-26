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

public class AggregationOkAction extends OkAction {
   AggregationDialog dialog;
   public AggregationOkAction(AggregationDialog dialog) {
      super();
      this.dialog=dialog;
   }
   public void actionPerformed(ActionEvent e) {
      String aggregationName=dialog.getName();
      String wholeClass=dialog.getWholeClass();
      String partClass=dialog.getPartClass();
      String multiplicity=dialog.getMultiplicity();
      Aggregation aggregation=new Aggregation();
      aggregation.setName(aggregationName);
      aggregation.setMultiplicity(multiplicity);
      DomainModel model=DomainModel.getInstance();
      ClassObj whole=(ClassObj)model.find(new ClassObj(wholeClass));
      if (whole==null) {
         whole=new ClassObj(wholeClass);
         model.add(whole);
      }
      ClassObj part=(ClassObj)model.find(new ClassObj(partClass));
      if (part==null) {
         part=new ClassObj(partClass);
         model.add(part);
      }
      aggregation.setSrcClass(whole);
      aggregation.setDstClass(part);
      DomainModel domainModel=DomainModel.getInstance();
      if (mcIndex>=0) {
         DomainModel.getInstance().remove(mcIndex);
         domainModel.getConcepts().trimToSize();
         domainModel.add(mcIndex, aggregation);
      } else
         domainModel.add(aggregation);
      updateList();
      //Gui.getInstance().highlightAggregation(aggregationName);
   }
}
