package edu.uta.model;
import java.io.*;
public class Aggregation
   extends Relationship implements Serializable {
   String multiplicity;
   public Aggregation() {
      super();
   }

   public String getMultiplicity() {
      return multiplicity;
   }

   public void setMultiplicity(String multiplicity) {
      this.multiplicity = multiplicity;
   }

   public String toString() {
      String description = "(Aggregation)" + getName() + "(";
      String srcName=(getSrcClass()==null)?"null":getSrcClass().getName();
      String destName=(getDstClass()==null)?"null":getDstClass().getName();
      description += srcName + ", ";
      description += destName + "(" + getMultiplicity() + "))";
      return description;
   }
   public void writeFile() {
      DomainModel.getInstance().relationships.append(
         "\""+getSrcClass().getName()+"\" \""
         +getDstClass().getName()+"\" A \""+getMultiplicity()+"\" \n");
   }
}
