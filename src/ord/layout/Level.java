/**
 * Full File Name:   Level.java
 *
 * University of Texas at Arlington
 * Software Engineering Center for Telecommunications
 * Object Oriented Testing Project
 *
 * (c) Copyright 1994 University of Texas at Arlington
 * ALL RIGHTS RESERVED
 *
 * Input:
 * Output:
 * Supported Requirements:
 * Classes in this file  : Level
 * Related Documents     : Package layout
 *
 * Update History:
 * Date          Author         Changes
 * ----------    --------       --------------------
 * 09/28/1999    Unknown        creation of this class
 * 05/01/2000    Matt Darland   Added getMaxNodeHeight method
 *
 *************************************************************/

/*********************************************************
This class represent a set of nodes in a certain level.
The nodes in the same level will be layed out along a
horizontal line.
**********************************************************/
package ord.layout;
import java.util.*;
import java.awt.*;
import util.*;

class Level {
   int level;
   Vector nodes=new Vector();
   Vector priority=new Vector();
    // Matt Darland: Changed to 10 to make it look neater.
   static final int distBetweenNodes=10; // ORD distance between nodes
   static final int distBetweenLevels=60; // ORD distance between levels
   static int numOfLevels=0;

   Level(int level) {
      this.level=level;
   }


   public void addNode(Node node){
      nodes.addElement(node);
   }
   public Enumeration getNodes() {
      return nodes.elements();
   }
   public int getNumNodes() {
      return nodes.size();
   }

   public void setNodesYLocation(int y) {
      int n=nodes.size();
      for(int i=0;i<n;i++) {
         Node ntemp=(Node)nodes.elementAt(i);
         Point loc=ntemp.getLocation();
         loc.y=y;
         ntemp.setLocation(loc);
      }
   }


   public void exchangeNodes(Node n1,Node n2) {
      int temp=n1.getOrder();
      n1.setOrder(n2.getOrder());
      n2.setOrder(temp);
      nodes.setElementAt(n1,n1.getOrder());
      nodes.setElementAt(n2,n2.getOrder());
   }

   public void computeInitialX() {
      int n=nodes.size();
      int x=ORDLayout.margin.left;
      for(int i=0;i<n;i++) {
         Node ntemp=(Node)nodes.elementAt(i);
         Point loc=ntemp.getLocation();
         loc.x=x;
         ntemp.setLocation(loc);
         x+=Level.distBetweenNodes+ntemp.getSize().width;
      }
   }


   public void computePriority() {
      int n=nodes.size();
      priority=(Vector)nodes.clone();
      for(int i=1;i<n;i++) {
         for(int j=n-1;j>=i;j--) {
            Node n1=(Node)priority.elementAt(j-1);
            Node n2=(Node)priority.elementAt(j);
            if(n1.weight<n2.weight){
               priority.setElementAt(n1,j);
               priority.setElementAt(n2,j-1);
            }
         }
      }
   }


   public int checkNodeLocationX(Node node,int locX){
      int x;
      if((x=checkLeft(node,locX))!=locX)
         return x;
      else if((x=checkRight(node,locX))!=locX)
         return x;
      else
         return locX;
   }

   private int checkLeft(Node node,int locX) {
      int order=node.getOrder();
      int widthX=0;
      Node nodeTemp;
      for(int i=order-1;i>=0;i--) {
         nodeTemp=(Node)nodes.elementAt(i);
         widthX+=nodeTemp.size.width+Level.distBetweenNodes;
         if(nodeTemp.ifPlaced()){
            if(nodeTemp.loc.x+widthX<locX)
               return locX;
            else
               return nodeTemp.loc.x+widthX;
         }
      }
      if(widthX+ORDLayout.margin.left<locX)
         return locX;
      else
         return widthX+ORDLayout.margin.left;
   }

   private int checkRight(Node node,int locX) {
      int order=node.getOrder();
      int n=nodes.size();
      int widthX=node.getSize().width+Level.distBetweenNodes;
      Node nodeTemp;
      for(int i=order+1;i<n;i++) {
         nodeTemp=(Node)nodes.elementAt(i);
         if(!nodeTemp.ifPlaced()){
            widthX+=nodeTemp.size.width+Level.distBetweenNodes;
         }else{
            if(nodeTemp.loc.x-widthX>locX)
               return locX;
            else
               return nodeTemp.loc.x-widthX;
         }
      }
      return locX;
   }


   public int checkNeighbors(Node node,int locX) {
      int x;
      if((x=checkLeftNeighbor(node,locX))!=locX)
         return x;
      if((x=checkRightNeighbor(node,locX))!=locX)
         return x;
      return locX;
   }



   private int checkLeftNeighbor(Node node,int locX) {
      int order=node.getOrder();
      if(order!=0) {
         Node leftNode=(Node)nodes.elementAt(order-1);
         if(locX >= leftNode.loc.x+leftNode.size.width+Level.distBetweenNodes)
            return locX;
         else
            return leftNode.loc.x+leftNode.size.width+Level.distBetweenNodes;
      }else {
         if(locX >= ORDLayout.margin.left)
            return locX;
         else
            return ORDLayout.margin.left;
      }
   }


   private int checkRightNeighbor(Node node,int locX) {
      int n=nodes.size();
      int order=node.getOrder();
      if(order!=n-1) {
         Node rightNode=(Node)nodes.elementAt(order+1);
         if(locX <= rightNode.loc.x-node.size.width-Level.distBetweenNodes)
            return locX;
         else
            return rightNode.loc.x-node.size.width-Level.distBetweenNodes;
      }else
         return locX;
   }

    // Added by Matt Darland.  Retrieves the height of the node in
    // this level with the largest height value.
   public int getMaxNodeHeight()
   {
	int s = getNumNodes();
	int max = 0;

	for( int i = 0; i < s; ++i )
	{
	    Node n = (Node)nodes.elementAt( i );
	    if( n.getSize().height > max )
		max = n.getSize().height;
	}

	return( max );
   }

   /* this function is for debuging only */
   public void display() {
      //Debug.print("Level " + level +":");
      /*for(int i=0;i<getNumNodes();i++) {
         Node node=(Node)nodes.elementAt(i);
         if(node.cclass!=null)
            Debug.print(node.cclass.name+"   ");
         else
            Debug.print("null   ");
      }*/
     // Debug.println("");
   }
   public void displayPriority() {
      //Debug.print("Priority for level "+level+":");
      /*for(int i=0;i<getNumNodes();i++) {
         Node node=(Node)priority.elementAt(i);
         if(node.cclass!=null)
            Debug.print(node.cclass.name+"   ");
         else
            Debug.print("null   ");
      }*/
     // Debug.println("");
   }

}


