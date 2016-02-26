package edu.uta.model;

public abstract class ModelConcept implements java.io.Serializable {
   String name="";

   public String getName() {
      return name;
   }
   public String toString() {
      return "";
   }
   public void setName(String name) {
      this.name=name;
   }
   public abstract boolean equals(ModelConcept concept);
   public abstract void writeFile();
}
