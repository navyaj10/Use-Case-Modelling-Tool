package edu.uta.controller;

import edu.uta.model.*;
import java.awt.event.*;
import javax.swing.*;
import edu.uta.gui.*;
import util.*;

public class RemoveListener implements ActionListener {
   JList domainConceptList;
   public RemoveListener(JList domainConceptList) {
      this.domainConceptList=domainConceptList;
   }
   public void actionPerformed(ActionEvent ae) {
      Gui gui=Gui.getInstance();
      int[] indexes=this.domainConceptList.getSelectedIndices();
      if (indexes.length==0) {
         MyErrorMsg.show("Please select one or more domain concepts to be removed.");
         return;
      }
      for (int i=indexes.length-1; i>=0; i--) {
         DomainModel.getInstance().remove(indexes[i]);
      }
      DomainModel.getInstance().getConcepts().trimToSize();
      gui.setModelConceptList(DomainModel.getInstance().getConceptList());
   }
}
