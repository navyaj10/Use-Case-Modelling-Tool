package edu.uta.controller.cmd;

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
public class AutoDM {
   private static AutoDM instance;
   private ArrayList dm=new ArrayList();
   private AutoDM() {
   }
   public static AutoDM getInstance() {
      if (instance==null) instance=new AutoDM();
      return instance;
   }
   public void add(String element) {
      if (!dm.contains(element)) dm.add(element);
   }
   public void save() {
      util.PersistenceMgr.write2TextFile(dm, "derived/dm.txt");
   }
   public void clear() {
      dm.clear();
      dm.trimToSize();
   }
}
