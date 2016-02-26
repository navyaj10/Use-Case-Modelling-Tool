package ord.layout;
import java.awt.*;

class ConnectionPoint {
   Node node;
   int order;
   Point loc=null;
   OMTSymbol omtSymbol=null;
   ConnectionPoint theOtherSide=null;

   ConnectionPoint(Node node,int order) {
      this.node=node;
      this.order=order;
   }
   public void setOMTSymbol(OMTSymbol omtSymbol) {
      this.omtSymbol=omtSymbol;
   }

//New set-location routine as of 3-8-1999  EWB
/*
Purpose: To set the location of an arrow marker
Preconditions: Need to know the point on the line to alter
Algorithmic Notation: N/A
Effects: A line will be longer or shorter by the size ofw a symbol.
Miscellanious: See OMTSymbol.java
*/
   public void setLocation(Point loc){
      if(omtSymbol==null)
      {
        this.loc=loc;
      }
      else
      {
         if(omtSymbol.type=='A')
         {
           omtSymbol.setAggregationLocation(loc);
           this.loc=omtSymbol.incomingLocation();
         }
         else
         {
           omtSymbol.setLocation(loc);
           this.loc=omtSymbol.outgoingLocation();
         }
      }
   }


   public void setLocation(int x,int y) {
      setLocation(new Point(x,y));
   }
   public Point getLocation() {
      return loc;
   }

   public Node getNode() {
      return node;
   }
   public void setOrder(int order){
      this.order=order;
   }
   public int getOrder(){
      return order;
   }
   public void setTheOtherSide(ConnectionPoint cp){
      theOtherSide=cp;
   }
   public ConnectionPoint getTheOtherSide() {
      return theOtherSide;
   }
}


