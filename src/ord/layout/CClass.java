package ord.layout;
import java.awt.*;
import java.util.*;
import util.Debug;
/**********************************************************
A ClassNode represent a class.
***********************************************************/
class CClass{
   String name;
   int serialNo;
   int level;
   Node node=null;

   // Next 2 lines added by Matt Darland
   ORDListBox attribs=null;
   ORDListBox methods=null;

   CClass(String name,int serialNo) {
      this.name=name;
      this.serialNo=serialNo;
      level=0;
   }
   public int getSerialNo() {
      return serialNo;
   }
   public void setLevel(int level) {
      this.level=level;
   }
   public int getLevel() {
      return level;
   }
   public void setNode(Node n) {
      node=n;
   }
   public Node getNode(){
      return node;
   }

    // Next 5 functions added by Matt Darland
   public String getName(){
      return name;
   }
   public ORDListBox getAttribs(){
      return attribs;
   }
   public ORDListBox getMethods(){
      return methods;
   }
   public void setAttribs(ORDListBox a)
   {
      //Debug.println("CClass.setAttribs");
      attribs=a;
   }
   public void setMethods(ORDListBox m){
      methods=m;
   }
}


