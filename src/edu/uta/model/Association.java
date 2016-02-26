package edu.uta.model;
import java.io.*;
public class Association
   extends Relationship implements Serializable {

   String srcRole = "";
   String srcMultiplicity = "";
   String destRole = "";
   String destMultiplicity = "";
   String name = "";
   public Association(String associationName, String srcClass, String destClass,
      String srcRole, String destRole, String srcMultiplicity, String destMultiplicity) {
      this.name=associationName;
      this.srcClass=new ClassObj(srcClass);
      this.dstClass=new ClassObj(destClass);
      this.srcRole=srcRole;
      this.destRole=destRole;
      this.srcMultiplicity=srcMultiplicity;
      this.destMultiplicity=destMultiplicity;
   }
   // @Override
   public String getName() {
      return name;
   }

   // @Override
   public void setName(String name) {
      this.name = name;
   }

   public Association() {
      super();
   }

   public String getSrcRole() {
      return srcRole;
   }

   public void setSrcRole(String srcRole) {
      this.srcRole = srcRole;
   }

   public String getSrcMultiplicity() {
      return srcMultiplicity;
   }

   public void setSrcMultiplicity(String srcMultiplicity) {
      this.srcMultiplicity = srcMultiplicity;
   }

   public String getDestRole() {
      return destRole;
   }

   public void setDestRole(String destRole) {
      this.destRole = destRole;
   }

   public String getDestMultiplicity() {
      return destMultiplicity;
   }

   public void setDestMultiplicity(String destMultiplicity) {
      this.destMultiplicity = destMultiplicity;
   }

   public String toString() {
      String description = "(Association)" + getName() + "(";
      description += getSrcClass().getName() + "(" + getSrcRole() + ")(" +
         getSrcMultiplicity() + "), ";
      description += getDstClass().getName() + "(" + getDestRole() + ")(" +
         getDestMultiplicity() + "))";

      return description;
   }
   public boolean equals(ModelConcept mc) {
      if (mc.getClass().getName().compareTo(
         this.getClass().getName())!=0)
         return false;
      Association a=(Association)mc;
      if (a.getName().compareTo(name)==0 &&
         a.getSrcClass().getName().compareTo(
         srcClass.getName())==0 &&
         a.getDstClass().getName().compareTo(
         dstClass.getName())==0)
         return true;
      return false;
   }
   public void writeFile() {
      int temp=0;
      DomainModel.getInstance().relationships.append("\""+getSrcClass().getName()+"\" \""
         +getDstClass().getName()+"\" F "+temp+" \""+
         getSrcMultiplicity()+"\" \""+getDestMultiplicity()
         +"\" \""+getSrcRole()+"\" \""+getDestRole()+
         "\" \""+getName()+"\""+"\n");
   }
}
