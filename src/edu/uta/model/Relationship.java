package edu.uta.model;

public abstract class Relationship
   extends ModelConcept implements java.io.Serializable {
   ClassObj srcClass;
   ClassObj dstClass;

   public ClassObj getSrcClass() {
      return srcClass;
   }

   public void setSrcClass(ClassObj srcClass) {
      this.srcClass = srcClass;
   }

   public ClassObj getDstClass() {
      return dstClass;
   }

   public void setDstClass(ClassObj dstClass) {
      this.dstClass = dstClass;
   }
   public boolean equals(ModelConcept mc) {
      if (mc.getClass().getName().compareTo(this.getClass().getName())!=0)
         return false;
      Relationship r=(Relationship)mc;
      if (r.getSrcClass().getName().compareTo(
         srcClass.getName())==0 &&
         r.getDstClass().getName().compareTo(
         dstClass.getName())==0)
         return true;
      return false;
   }
   public void writeFile() {
      // do nothing
   }

}
