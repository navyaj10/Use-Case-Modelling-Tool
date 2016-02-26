package edu.uta.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;

public class DomainModel extends ModelConcept implements ListModel {
   static StringBuilder classes;
   static StringBuilder attributes;
   static StringBuilder relationships;

   private static DomainModel instance;
   ArrayList concepts=new ArrayList<ModelConcept>();

   public static DomainModel getInstance() {
      if (instance==null) {
         instance=new DomainModel();
      }
      return instance;
   }
   public void clearAll() {
      concepts.clear();
   }
   public void add(ModelConcept concept) {
      if (!concepts.contains(concept)) {
         concepts.add(concept);
      }
   }
   public void add(int i, ModelConcept concept) {
      if (!concepts.contains(concept)) {
         concepts.add(i, concept);
      }
   }

   public void remove(int i) {
      concepts.remove(i);
      concepts.trimToSize();
   }
   public void remove(ModelConcept concept) {
      concepts.remove(concept);
      concepts.trimToSize();
   }
   public Object find(ModelConcept concept) {
      for (int i=0; i<concepts.size(); i++) {
         if (((ModelConcept)concepts.get(i)).equals(concept)) {
            return concepts.get(i);
         }
      }
      return null;
   }
   public boolean findAssoc(String name) {
      for (int i=0; i<concepts.size(); i++) {
         ModelConcept mc=(ModelConcept)concepts.get(i);
         if ((mc.getClass().getName().indexOf("Association")
            >=0 && mc.getName().compareTo(name)==0)) {
            return true;
         }
      }
      return false;
   }
   public int getIndex(ModelConcept concept) {
      return concepts.indexOf(concept);
   }
   public int size() {
      return concepts.size();
   }
   public String[] getClasses() {
      ArrayList al=new ArrayList();
      for (int i=0; i<concepts.size(); i++) {
         ModelConcept mc=(ModelConcept)concepts.get(i);
         String name=mc.getClass().getName();
         if (name.indexOf("ClassObj")>=0) {
            al.add(((ClassObj)concepts.get(i)).getName());
         }
      }
      String[] nameStr=new String[al.size()];
      for (int j=0; j<nameStr.length; j++) {
         nameStr[j]=(String)al.get(j);
      }
      return nameStr;
   }
   public ArrayList getConcepts() {
      return concepts;
   }
   public String[] getConceptList() {
      String[] mcArray=new String[concepts.size()];
      for (int i=0; i<concepts.size(); i++) {
         mcArray[i]=((ModelConcept)concepts.get(i)).toString();
      }
      return mcArray;
   }
   public boolean equals(ModelConcept mc) {
      return false;
   }
   public String toString() {
      String result="";
      for (int i=0; i<concepts.size(); i++) {
         result+=(String)concepts.get(i)+"\n";
      }
      return result;
   }
   public void writeFile() {
      classes=new StringBuilder();
      attributes=new StringBuilder();
      relationships=new StringBuilder();
      for (int i=0; i<concepts.size(); i++) {
         ((ModelConcept)concepts.get(i)).writeFile();
      }
      save2File(classes, "tmp/OOT_Classname");
      save2File(attributes, "tmp/OOT_Attrib");
      save2File(relationships, "tmp/OOT_Relation_File");
   }
   public void save2File(StringBuilder data, String fileName) {
      File file=new File(fileName);
      FileOutputStream fos;
      DataOutputStream dos;
      try {
         fos=new FileOutputStream(file);
         dos=new DataOutputStream(fos);
         dos.writeBytes(data.toString());
         fos.flush();
         fos.close();
         dos.flush();
         dos.close();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void setConcepts(ArrayList concepts) {
      this.concepts=concepts;
   }
   public void addListDataListener(ListDataListener l) {

   }
   public Object getElementAt(int index) {
      return concepts.get(index);
   }
   public int getSize() {
      return concepts.size();
   }
   public void removeListDataListener(ListDataListener l) {

   }

}
