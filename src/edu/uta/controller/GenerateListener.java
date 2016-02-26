package edu.uta.controller;

import java.awt.event.*;
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
public class GenerateListener
   implements ActionListener {
   public GenerateListener() { }
   /**
    * actionPerformed
    *
    * @param actionEvent ActionEvent
    * @todo Implement this java.awt.event.ActionListener method
    */
   public void actionPerformed(ActionEvent e) {
      ArrayList dm=DomainModel.getInstance().getConcepts();
      DomainModel.getInstance().writeFile();
      Gui.getInstance().displayDomainModel();
   }
}
