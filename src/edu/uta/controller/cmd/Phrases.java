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
public class Phrases {
   private static Phrases instance;
   private Collection collection;
   public Phrases() {
      super();
      collection=new ArrayList();
      collection.add("list(usable).");
      collection.add("end_of_list.");
   }
   public static Phrases getInstance() {
      if (instance == null) instance=new Phrases();
      return instance;
   }
   public void setCollection(Collection c) {
      this.collection=c;
   }
   public Collection getCollection() {
      return collection;
   }
   public void add(Object element) {
      collection.add(element);
   }
   public void add(int position, Object element) {
      ((ArrayList)collection).add(position, element);
   }
   public boolean contains(Object element) {
      Iterator it=collection.iterator();
      while (it.hasNext()) {
         String str = (String) it.next();
         if (str.compareTo((String)element)==0)
            return true;
      }
      return false;
   }
   public void remove(Object element) {
      collection.remove(element);
   }
   public void removeAll() {
      collection.clear();
   }
   public Object getElement(String elemStr) {
      Iterator it=collection.iterator();
      while (it.hasNext()) {
         Object object=it.next();
         String str = (String) object;
         if (str.compareTo(elemStr)==0)
            return object;
      }
      return null;
   }
   public void save2TextFile(String fileName) {
      util.PersistenceMgr.write2TextFile((ArrayList)collection, fileName);
   }
   public void loadFromTextFile(String fileName) {
      collection=util.PersistenceMgr.loadFromTextFile(fileName);
   }
}
