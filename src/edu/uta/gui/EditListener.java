package edu.uta.gui;

import java.awt.event.*;
import edu.uta.controller.*;
import edu.uta.controller.cmd.*;
import edu.uta.model.*;
import javax.swing.JList;
import javax.swing.JFrame;
import util.*;

public class EditListener implements ActionListener {
   ModelConcept mc;
   String mcName;
   JList list;
   public EditListener(JList list) {
      this.list=list;
   }
   public void actionPerformed(ActionEvent e) {
      int index=list.getSelectedIndex();
      if (index<0) {
         MyErrorMsg.show("Please select a domain concept to edit.");
         return;
      }
      mc=(ModelConcept)DomainModel.getInstance().getConcepts().get(index);
      mcName=mc.getClass().getName();
      try {
         String cmdName="edu.uta.controller.cmd"+
            mcName.substring(mcName.lastIndexOf("."), mcName.length());
         cmdName+="Cmd";
         ActionCmd cmd=(ActionCmd)Class.forName(cmdName).newInstance();
         cmd.setModelConcept(mc);
         cmd.execute();
      } catch (Exception e1) {
         e1.printStackTrace();
      }
   }
}
