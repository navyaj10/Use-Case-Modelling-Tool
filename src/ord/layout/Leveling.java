package ord.layout;
import java.util.*;
import util.*;

class Leveling{
   LinkedList list[];
   int level[];
   boolean hasRelation[];
   int numVertices=0;
   Vector classes;
   Vector relations;

   private void _Leveling(int numVertices) {
      this.numVertices=numVertices;
      list=new LinkedList[numVertices];
      for(int i=0;i<numVertices;i++)
         list[i]=new LinkedList();
      level=new int[numVertices];
      hasRelation=new boolean[numVertices];
      for(int i=0;i<numVertices;i++){
         level[i]=1;
         hasRelation[i]=false;
      }
   }

   Leveling(Vector classes,Vector relations) {
      _Leveling(classes.size());
      this.classes=classes;
      this.relations=relations;
      Enumeration e=relations.elements();
      while(e.hasMoreElements()) {
         Relation r=(Relation)e.nextElement();
         if(r.type=='I' || r.type=='A') {
            int iFrom=r.from.getSerialNo();
            int iTo=r.to.getSerialNo();
            hasRelation[iFrom]=true;
            hasRelation[iTo]=true;
            list[iFrom].add(iTo);
            pushUpLevel(iFrom,iTo);
         }
      }
      e=relations.elements();
      while(e.hasMoreElements()) {
         Relation r=(Relation)e.nextElement();
         if(r.type=='F') {
            int iFrom=r.from.getSerialNo();
            int iTo=r.to.getSerialNo();
            hasRelation[iFrom]=true;
            hasRelation[iTo]=true;
            if(!isAncestor(iFrom,iTo)) {
               list[iFrom].add(iTo);
               pushUpLevel(iFrom,iTo);
            }
         }
      }
      updateClassesLevel();
   }

   private void updateClassesLevel() {
      boolean more;
      int lowestParent[]=new int[numVertices];
      for(int i=0;i<numVertices;i++)
         lowestParent[i]=65535;
      more=true;
      while(more) {
         more=false;
         for(int i=0;i<relations.size();i++) {
            Relation r=(Relation)relations.elementAt(i);
            if(lowestParent[r.from.getSerialNo()]>level[r.to.getSerialNo()])
               lowestParent[r.from.getSerialNo()]=level[r.to.getSerialNo()];
         }
         for(int i=0;i<numVertices;i++) {
            if(level[i]<lowestParent[i]-1 && lowestParent[i]!=65535){
               level[i]=lowestParent[i]-1;
               more=true;
            }
         }
      }
      boolean hasClassWithNoRelationship=false;
      for(int i=0;i<numVertices;i++) {
         if(!hasRelation[i]){
            hasClassWithNoRelationship=true;
            break;
         }
      }
      if(hasClassWithNoRelationship){
         for(int i=0;i<numVertices;i++) {
            CClass c=(CClass)classes.elementAt(i);
            if(hasRelation[i])
               c.setLevel(level[i]);
            else
               c.setLevel(0);
         }
      }else {
         for(int i=0;i<numVertices;i++) {
            CClass c=(CClass)classes.elementAt(i);
            c.setLevel(level[i]-1);
         }
      }
   }

   private void pushUpLevel(int pusher,int pushee){
      if(level[pusher]<level[pushee])
         return;
      level[pushee]=level[pusher]+1;
      Enumeration e=list[pushee].elements();
      while(e.hasMoreElements()){
         int next=((Integer)e.nextElement()).intValue();

         // Added 5/28/99 by WK. To avoid infinite recursion.
         if (pushee == next) {
           // Debug.println ("Error: Self-loop in Adjacency list");
            continue;
         }
         // End change WK.

        pushUpLevel(pushee,next);
      }
   }

   private boolean isAncestor(int ancestor,int offspring) {
      if(ancestor==offspring)
         return true;
      Enumeration e=list[offspring].elements();
      while(e.hasMoreElements()) {
         int next=((Integer)e.nextElement()).intValue();

	 // Added 5/28/99 by WK. To avoid infinite recursion.
         if (offspring == next) {
           // Debug.println ("Error: Self-loop in Adjacency list");
	    continue;
	 }
	 // End change WK.

         if(isAncestor(ancestor,next))
            return true;
      }
      return false;
   }



   public int getNumVertices() {
      return numVertices;
   }


   public void displayList() {
      for(int i=0;i<list.length;i++){
         Debug.print(i+":");
         Enumeration e=list[i].elements();
         while(e.hasMoreElements()) {
            int j=((Integer)e.nextElement()).intValue();
            Debug.print(j+" ");
         }
        // Debug.println("");
      }
   }
   public void displayLevel() {
      for(int i=0;i<numVertices;i++) ;
        // Debug.println(i+" level:"+level[i]);

   }
}


