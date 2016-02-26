package edu.uta.controller.cmd;

import edu.uta.model.Attribute;
import edu.uta.gui.ClassDialog;
import javax.swing.JFrame;
import edu.uta.model.*;
import edu.uta.controller.ClassOkAction;

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
public class ClassObjCmd extends ActionCmd {
   public ClassObjCmd() {
      super();
   }
   /**
    * execute
    *
    * @todo Implement this edu.uta.controller.cmd.ActionCmd method
    */
   public void execute() {
      ClassObj co=(ClassObj)mc;
      String[] attributes=new String[co.getAttributes().size()];
      for (int i=0; i<attributes.length; i++) {
         attributes[i]=((Attribute)co.getAttributes().get(i)).getName();
      }
      ClassDialog classDialog=new ClassDialog(new JFrame());
      ClassOkAction ok=new ClassOkAction(classDialog);
      ok.setIndex(DomainModel.getInstance().getIndex(mc));
      classDialog.setOkAction(ok);
      classDialog.setName(co.getName());
      classDialog.setAttributes(attributes);
      classDialog.display();
   }
}
