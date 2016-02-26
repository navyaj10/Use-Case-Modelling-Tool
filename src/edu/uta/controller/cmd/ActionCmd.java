package edu.uta.controller.cmd;
import edu.uta.model.*;
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
public abstract class ActionCmd {
   ModelConcept mc;
   public ActionCmd() {}
   public void setModelConcept(ModelConcept mc) {
      this.mc=mc;
   }
   public abstract void execute();
}
