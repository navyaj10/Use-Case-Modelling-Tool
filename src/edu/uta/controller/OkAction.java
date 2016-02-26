package edu.uta.controller;

import java.awt.event.*;
import javax.swing.*;
import edu.uta.model.*;
import java.util.ArrayList;
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
public class OkAction extends AbstractAction {
   int mcIndex=-1;
   public OkAction() {
      super();
   }
   public OkAction(String string) {
      super(string);
   }
   public OkAction(String string, Icon icon) {
      super(string, icon);
   }
   /**
    * actionPerformed
    *
    * @param actionEvent ActionEvent
    * @todo Implement this java.awt.event.ActionListener method
    */
   public void actionPerformed(ActionEvent actionEvent) {
   }
   public void setIndex(int index) {
      mcIndex=index;
   }
   public void updateList() {
      ArrayList concepts=DomainModel.getInstance().getConcepts();
      String[] mcArray=new String[concepts.size()];
      for (int i=0; i<concepts.size(); i++) {
         mcArray[i]=((ModelConcept)concepts.get(i)).toString();
      }
      Gui.getInstance().setModelConceptList(mcArray);
   }
}
