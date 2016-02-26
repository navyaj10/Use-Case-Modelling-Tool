/**********************************************************
A Connection is used to represent a route between two 
classes of a relationship.   
***********************************************************/
package ord.layout;
import java.util.*;
class Connection {
   Relation relation;
   Vector connectionPointPair=new Vector();
   Connection(Relation relation) {
      this.relation=relation;
   }
   public void addConnectionPointPair(ConnectionPointPair cpp) {
      connectionPointPair.addElement(cpp);
   }
}



