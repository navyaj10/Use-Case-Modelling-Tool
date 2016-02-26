package edu.uta.model;

import java.io.Serializable;

public class Attribute extends ModelConcept implements Serializable {

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name=name;
   }
   public boolean equals(ModelConcept mc) {
      if (mc.getClass().getName().compareTo(this.getClass().getName())!=0)
         return false;
      Attribute a=(Attribute)mc;
      return a.getName().compareTo(this.name)==0;
   }
   public void writeFile() {
      // do nothing
   }
}
