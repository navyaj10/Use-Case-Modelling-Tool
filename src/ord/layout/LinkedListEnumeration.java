package ord.layout;
import java.util.*;
class LinkedListEnumeration implements Enumeration {
   LinkedList list;
   LinkedNode current;
   LinkedListEnumeration(LinkedList l){
      list=(LinkedList)l.clone();
      current=list.head;
   } 
   public boolean hasMoreElements() {
      if(current!=null)
         return true;
      else
         return false;
   }
   public Object nextElement() throws NoSuchElementException {
      if(current==null)
         throw new NoSuchElementException();
      else{
         int value=current.value();
         current=current.next();
         return new Integer(value);
      }
   }
}


