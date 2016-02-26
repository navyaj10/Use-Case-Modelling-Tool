package ord.layout;
class Queue {
   QueueNode head=new QueueNode();
   QueueNode tail=head;

   public void addElement(Object obj) {
      QueueNode qnode=new QueueNode();
      qnode.value=obj;
      qnode.next=null;
      tail.next=qnode;
      tail=tail.next;
   } 

   public Object nextElement() {
      if(head==tail) 
         return null;
      else {
         Object obj=head.value;
         head=head.next;
         return obj;
      }
   }       
}



