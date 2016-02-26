package edu.uta.controller;

import java.awt.event.*;
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

public class MyPopupMenuListener extends MouseAdapter {
   String selectedText;
   public void mouseReleased(MouseEvent e) {
      if (e.isPopupTrigger()) {
         selectedText=Gui.getInstance().getSelectedText();
         Gui.getInstance().showPopupMenu(e);
      }
   }
}
