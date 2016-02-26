package edu.uta.model;
import java.io.*;
public class Inheritance
   extends Relationship implements Serializable {
   public Inheritance() {
      super();
   }

   public String toString() {
      String description = "(Inheritance)(";
      description += srcClass.getName() + ", ";
      description += dstClass.getName() + ")";
      return description;
   }
   public void writeFile() {
      DomainModel.getInstance().relationships.append("\""+getSrcClass().getName()+"\" \""
         +getDstClass().getName()+"\""+" I\n");
   }
}
