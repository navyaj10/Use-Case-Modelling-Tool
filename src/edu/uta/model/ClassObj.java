package edu.uta.model;

import java.util.*;
import java.io.*;

public class ClassObj extends ModelConcept implements Serializable {
   public ClassObj() {
      super();
   }

   public ClassObj(String name) {
      StringTokenizer st = new StringTokenizer(name);
      this.name="";
      while (st.hasMoreTokens()) {
         String t = st.nextToken();
         this.name += t.substring(0, 1).toUpperCase() +
            t.substring(1, t.length()) + " ";
      }
      this.name=this.name.trim();
   }
   ArrayList<Attribute> attributes = new ArrayList<Attribute> ();

   // @Override
   public String getName() {
      return name;
   }

   // @Override
   public void setName(String name) {
      this.name = "";
      StringTokenizer st = new StringTokenizer(name);
      while (st.hasMoreTokens()) {
         String t = st.nextToken();
         this.name += t.substring(0, 1).toUpperCase() +
            t.substring(1, t.length()) + " ";
      }
      this.name = this.name.trim();

   }

   public void addAttribute(Attribute attributeName) {
      for(int i=0; i<attributes.size(); i++) {
         Attribute attr=(Attribute)attributes.get(i);
         String aName=(String)attr.getName();
         String nName=(String)attributeName.getName();
         if (aName.compareTo(nName)==0) return;
      }
      attributes.add(attributeName);
   }

   public ArrayList<Attribute> getAttributes() {
      return attributes;
   }
   public String toString() {
      int i;

      String description = "(Class)" + getName() + "(";
      ArrayList<Attribute> attributeList = getAttributes();
      for (i = 0; i < (attributeList.size() - 1); i++) {
         description += attributeList.get(i).getName() + ", ";
      }

      if (attributeList.size() > 0) {
         description += attributeList.get(i).getName();
      }

      description += ")";

      return description;
   }
   public boolean equals(ModelConcept mc) {
      if (mc.getClass().getName().compareTo(this.getClass().getName())!=0) {
         return false;
      }
      ClassObj c=(ClassObj)mc;
      return c.getName().compareTo(name)==0;
   }
   public void writeFile() {
      DomainModel domainModel=DomainModel.getInstance();
      String name=getName();
      domainModel.classes.append("\""+name+"\" C 1 1 t\n");
      domainModel.attributes.append("\""+getName()+"\" <PACKAGE> "
         +getAttributes().size()+"\n");
      Iterator it_attr=getAttributes().iterator();
      while (it_attr.hasNext()) {
         String aName=((Attribute)it_attr.next()).getName();
         if (aName==null) continue;
         //aName=aName.replace(":", "");
         domainModel.attributes.append("\""+aName+"\"\n");
      }
   }
}
