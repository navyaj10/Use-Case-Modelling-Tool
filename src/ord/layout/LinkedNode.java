package ord.layout;
import java.util.*;

class LinkedNode {
   int value;
   LinkedNode nextNode;
   LinkedNode(int value) {
      nextNode=null;
      this.value=value;
   }
   public int value() {
      return value;
   }
   public LinkedNode next() {
      return nextNode;
   }
   public void setNext(LinkedNode nextNode){
      this.nextNode=nextNode;
   }
}



