/********************************************************
This class represent a node which will occupy a piece of
space on the output diagram.
*********************************************************/
package ord.layout;
import java.awt.*;
import java.util.*;
import util.*;
class Node {
   CClass cclass=null;
   int level;
   float  medium;
   int order=0;
   Point loc=new Point();
   Dimension size=null;
   Vector topConnectionPoints=new Vector();
   Vector bottomConnectionPoints=new Vector();
   int weight;
   boolean inQ;
   boolean ifPlaced=false;

   Node(CClass cclass) {
      this.cclass=cclass;
      this.level=cclass.getLevel();
   }

   Node(int level) {
      this.level=level;
   }

   public boolean ifPlaced() {
      return ifPlaced;
   }

   public void setOrder(int order) {
      this.order=order;
   }
   public int getOrder() {
      return order;
   }
   public ConnectionPoint getConnectionPoint(String topOrBottom) {
      int order;
      ConnectionPoint cp;
      if(topOrBottom.compareTo("TOP")==0) {
         order=topConnectionPoints.size();
         cp=new ConnectionPoint(this,order);
         topConnectionPoints.addElement(cp);
      }else{
         order=bottomConnectionPoints.size();
         cp=new ConnectionPoint(this,order);
         bottomConnectionPoints.addElement(cp);
      }
      return cp;
   }

   public void exchangeTopConnectionPoints(ConnectionPoint cp1,ConnectionPoint cp2) {
      int temp=cp1.getOrder();
      cp1.setOrder(cp2.getOrder());
      cp2.setOrder(temp);
      topConnectionPoints.setElementAt(cp1,cp1.getOrder());
      topConnectionPoints.setElementAt(cp2,cp2.getOrder());
   }

   public void exchangeBottomConnectionPoints(ConnectionPoint cp1,ConnectionPoint cp2) {
      int temp=cp1.getOrder();
      cp1.setOrder(cp2.getOrder());
      cp2.setOrder(temp);
      bottomConnectionPoints.setElementAt(cp1,cp1.getOrder());
      bottomConnectionPoints.setElementAt(cp2,cp2.getOrder());
   }

   public void adjustConnectionPointsLocation() {
      int nodeWidth=size.width;
      int nTop=topConnectionPoints.size();
      int nBot=bottomConnectionPoints.size();
      int distTop=nodeWidth/(nTop+1);
      int distBot=nodeWidth/(nBot+1);
      if(cclass!=null) {
         Enumeration e=topConnectionPoints.elements();
         while(e.hasMoreElements()) {
            ConnectionPoint cp=(ConnectionPoint)e.nextElement();
            cp.setLocation(loc.x+distTop*(cp.getOrder()+1),loc.y);
         }
         e=bottomConnectionPoints.elements();
         while(e.hasMoreElements()) {
            ConnectionPoint cp=(ConnectionPoint)e.nextElement();
            cp.setLocation(loc.x+distBot*(cp.getOrder()+1),loc.y+size.height);
         }
      }else {
         ConnectionPoint cp=(ConnectionPoint)topConnectionPoints.elementAt(0);
         cp.setLocation(loc.x+size.width/2,loc.y+size.height/2);
         cp=(ConnectionPoint)bottomConnectionPoints.elementAt(0);
         cp.setLocation(loc.x+size.width/2,loc.y+size.height/2);
      }
   }


   public void computeWeight() {
      ConnectionPoint cp=null;
      Node otherNode;
      int n=topConnectionPoints.size();
      weight=0;
      for(int i=0;i<n;i++) {
         cp=(ConnectionPoint)topConnectionPoints.elementAt(i);
         otherNode=cp.getTheOtherSide().getNode();
         if(cclass==null) {
            if(otherNode.cclass==null)
               weight+=8;
            else
               weight+=2;
         }else {
            if(otherNode.cclass==null)
               weight+=2;
            else
               weight+=1;
         }
      }
      n=bottomConnectionPoints.size();
      for(int i=0;i<n;i++) {
         cp=(ConnectionPoint)bottomConnectionPoints.elementAt(i);
         otherNode=cp.getTheOtherSide().getNode();
         if(cclass==null) {
            if(otherNode.cclass==null)
               weight+=8;
            else
               weight+=2;
         }else {
            if(otherNode.cclass==null)
               weight+=2;
            else
               weight+=1;
         }
      }
   }


   public void computeTopMediumValue(Vector levels) {
      int n=topConnectionPoints.size();
      if(n==0)
         medium=-1;
      else {
         int m=n/2;
         ConnectionPoint cp=(ConnectionPoint)topConnectionPoints.elementAt(m);
         Node mNode=(Node)cp.getTheOtherSide().getNode();
         Level lev=(Level)levels.elementAt(mNode.level);
         medium=(float)mNode.order/(float)lev.getNumNodes();
      }
   }
   public void computeBottomMediumValue(Vector levels) {
      int n=bottomConnectionPoints.size();
      if(n==0)
         medium=-1;
      else {
         int m=n/2;
         ConnectionPoint cp=(ConnectionPoint)bottomConnectionPoints.elementAt(m);
         Node mNode=(Node)cp.getTheOtherSide().getNode();
         Level lev=(Level)levels.elementAt(mNode.level);
         medium=(float)mNode.order/(float)lev.getNumNodes();
      }
   }
   public void computeBothMediumValue(Vector levels) {
      computeTopMediumValue(levels);
      float m1=medium;
      computeBottomMediumValue(levels);
      float m2=medium;
      if(m1!=-1 && m2!=-1)
         medium=(m1+m2)/2;
      else if(m1!=-1)
         medium=m1;
      else if(m2!=-1)
         medium=m2;
      else
         medium=-1;
   }
/*
   public int computeTopMediumX(){
      int n=topConnectionPoints.size();
      if(n==0)
         return -1;
      else {
         int m=n/2;
         if(n%2==1){
            ConnectionPoint cp=(ConnectionPoint)topConnectionPoints.elementAt(m);
            Node mNode=(Node)cp.getTheOtherSide().getNode();
            return mNode.loc.x;
         }else {
            ConnectionPoint cp1=(ConnectionPoint)topConnectionPoints.elementAt(m-1);
            ConnectionPoint cp2=(ConnectionPoint)topConnectionPoints.elementAt(m);
            Node mNode1=(Node)cp1.getTheOtherSide().getNode();
            Node mNode2=(Node)cp2.getTheOtherSide().getNode();
            return (mNode1.loc.x+mNode2.loc.x)/2;
         }
      }
   }
   public int computeBottomMediumX() {
      int n=bottomConnectionPoints.size();
      if(n==0)
         return -1;
      else {
         int m=n/2;
         if(n%2==1){
            ConnectionPoint cp=(ConnectionPoint)bottomConnectionPoints.elementAt(m);
            Node mNode=(Node)cp.getTheOtherSide().getNode();
            return mNode.loc.x;
         }else {
            ConnectionPoint cp1=(ConnectionPoint)bottomConnectionPoints.elementAt(m-1);
            ConnectionPoint cp2=(ConnectionPoint)bottomConnectionPoints.elementAt(m);
            Node mNode1=(Node)cp1.getTheOtherSide().getNode();
            Node mNode2=(Node)cp2.getTheOtherSide().getNode();
            return (mNode1.loc.x+mNode2.loc.x)/2;
         }
      }
   }
*/
   public int computeTopMediumX(){
      int n=topConnectionPoints.size();
      int sum=0;
      if(n==0)
         return -1;
      else {
         for(int i=0;i<n;i++) {
            ConnectionPoint cp=(ConnectionPoint)topConnectionPoints.elementAt(i);
            Node mNode=(Node)cp.getTheOtherSide().getNode();
            sum+=mNode.loc.x;
         }
         return sum/n;
      }
   }

   public int computeBottomMediumX() {
      int n=bottomConnectionPoints.size();
      int sum=0;
      if(n==0)
         return -1;
      else {
         for(int i=0;i<n;i++) {
            ConnectionPoint cp=(ConnectionPoint)bottomConnectionPoints.elementAt(i);
            Node mNode=(Node)cp.getTheOtherSide().getNode();
            sum+=mNode.loc.x;
         }
         return sum/n;
      }
   }

   public int computeBothMediumX() {
      int topX=computeTopMediumX();
      int bottomX=computeBottomMediumX();
      if(topX==-1)
         if(bottomX==-1)
            return -1;
         else
            return bottomX;
      else
         if(bottomX==-1)
            return topX;
         else
            return (topX+bottomX)/2;
   }

   public int distToLeft(Level level){
      int order=getOrder();
      int dist;
      if(order!=0) {
         Node leftNode=(Node)level.nodes.elementAt(order-1);
         dist=loc.x-leftNode.loc.x-leftNode.getSize().width;
      }else
         dist=loc.x-ORDLayout.margin.left;
      return dist;
   }

   public int distToRight(Level level){
      int n=level.getNumNodes();
      int order=getOrder();
      int dist;
      if(order!=n-1) {
         Node rightNode=(Node)level.nodes.elementAt(order+1);
         dist=rightNode.loc.x-loc.x-getSize().width;
      }else
         dist=-1;
      return dist;
   }


   public Point getLocation() {
      return loc;
   }
   public void setLocation(Point loc) {
      this.loc=loc;
   }
   public void setSize(Dimension size) {
      this.size=size;
   }
   public Dimension getSize() {
      return size;
   }
   /* This function is for debug only */
   public void display() {
      /**
      if(cclass!=null)
         Debug.print("Class name:" + cclass.name);
      else
         Debug.print("Class name:null");
      Debug.print("   Level:"+ level+ "  Order:"+order);
      if(size!=null)
         Debug.print("  size_X:"+size.width+"  size_Y:"+size.height);
      if(loc!=null)
        // Debug.println("  loc_X:"+loc.x+"  loc_Y:"+loc.y);
      else
        // Debug.println(""); */
   }

}



