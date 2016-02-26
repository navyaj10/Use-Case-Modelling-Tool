package ord.layout;
import java.util.*;

class LinkedList {
   LinkedNode head;
   int num; 
   LinkedList() {
      num=0;
      head=null;
   }
   public void add(int value) {
      LinkedNode newNode=new LinkedNode(value);
      newNode.setNext(head);
      head=newNode;
      num++;
   }
   /* return true if value is successfully removed,otherwase return false */
   public boolean remove(int value) {
      if(head==null)
         return false;
      if(head.value()==value){
         head=head.next();
         num--;
         return true;
      }
      for(LinkedNode temp=head;temp.next()!=null;temp=temp.next()) {
         if(temp.next().value==value) {
            temp.setNext(temp.next());
            num--;
            return true;
         }
      }
      return false;
   }
   public boolean exist(int value) {
      for(LinkedNode temp=head;temp!=null;temp=temp.next()) {
         if(temp.value()==value)
            return true;
      }
      return false;
   }
   public Object clone() {
      LinkedList tempList=new LinkedList();
      LinkedNode currentNode;
      for(currentNode=head;currentNode!=null;currentNode=currentNode.next())
         tempList.add(currentNode.value()); 
      return tempList;
   }

   public Enumeration elements() {
      LinkedListEnumeration e=new LinkedListEnumeration(this);
      return e;
   } 
}




