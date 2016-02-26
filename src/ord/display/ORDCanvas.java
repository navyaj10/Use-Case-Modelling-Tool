/**********************************************************
 * Full File Name:   ORDCanvas.java
 *
 * University of Texas at Arlington
 * Software Engineering Center for Telecommunications
 * Object Oriented Testing Project
 *
 * (c) Copyright 1994 University of Texas at Arlington
 * ALL RIGHTS RESERVED
 * Input:
 * Output:
 * Supported Requirements:
 * Classes in this file  : ORDCanvas
 * Related Documents     : package display
 *
 * Update History:
 * Date       Author   Changes
 * ---------- -------- --------------------
 * 09/28/1999 Bill F.  creation of this class
 *
 * Functional Description:
 * Error Messages        :
 * Constraints           :
 * Assumptions           :
 *
 *- 4/7/00  Huan Nguyen
 *- Feature: Adding ActionListener for canvasPopup and classPopup menus
 *- When the user right click on the class dimension, the classPopup menu
 *- will display with a choice of "Show Attributes", "Show Methods", "Show Both",
 *- "Hide Attributes", "Hide Methods", and "Hide All".
 *- When user right click on the canvas without hitting any class, classPopup
 *- menu will be display with a choice of "Show All Attributes", "Show All Methods",
 *- "Show All", "Hide Attributes", "Hide Methods", and "Hide All".
 *- When user left click on the class dimension, source code will be display
 *- for that particular classname.
 * Functions added:
 * classMenuAdd()---Add menu item class
 * canvasMenuAdd()---Add menu item canvas
 * All the getMethod to get all attributes in this class read only
 * getSetOfClasses()---return setOfClasses
 * getSetOfSymbols()---return setOfSymbols
 * getSetOfConnection()---return setOfConnections
 * getCurrentTarget()---return currentTarget
 * Point getPoint()---return point
 * getClassMenu()---return classMenu
 * getCanvasMenu()---return canvasMenu
 * All setMethod to set all attributes in this class
 * mousePressed()Modify Mouse Press so that the last class display correctly.
 * Error Messages        :None
 * Constraints           :None
 * Assumptions           :None
 ************************************************************/


package ord.display;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ORDCanvas extends JPanel implements MouseListener {

   public Vector setOfClasses;
   private Vector setOfSymbols;
   private Vector setOfConnections;
   private OMTClass currentTarget=new OMTClass("", new Point(0, 0),
      new Dimension(0, 0));

   /* Added by: Huan Nguen 4-7--00functions added information required for
     Popup menu*/

   private Point point;
   private PopupMenu classMenu;
   private PopupMenu canvasMenu;
   public void classMenuAdd(MenuItem menuItem) {
      classMenu.add(menuItem);
   }
   public void canvasMenuAdd(MenuItem menuItem) {
      canvasMenu.add(menuItem);
   }

   //All the getMethod to get all attributes in this class
   //read only
   public Vector getSetOfClasses() {
      return setOfClasses;
   }
   public Vector getSetOfSymbols() {
      return setOfSymbols;
   }
   public Vector getSetOfConnection() {
      return setOfConnections;
   }
   public OMTClass getCurrentTarget() {
      return currentTarget;
   }
   public Point getPoint() {
      return point;
   }
   public PopupMenu getClassMenu() {
      return classMenu;
   }
   public PopupMenu getCanvasMenu() {
      return canvasMenu;
   }

   //All setMethod to set all attributes in this class
   //write only
   public void setCurrentTarget(OMTClass aOMTClass) {}
   public void setPoint(Point aPoint) {
      point=new Point(aPoint);
   }
   public void setSetOfSymbols(Vector set) {
      setOfSymbols=set;
   }
   public void setSetOfClasses(Vector set) {
      setOfClasses=set;
   }
   public void setSetOfConnections(Vector set) {
      setOfConnections=set;
   }
   public void addClass(OMTClass c) {
      setOfClasses.addElement(c);
   }
   /********end by Huan Nguyen*********************/


   public int FrameOption;

   //-----------------------------------------------------------------------
   /**
    * Constructs a new <code>ORDCanvas</code> with a white background, and
    * empty sets of classes, symbols, and connections.
    **/
   public ORDCanvas() {
      setOfClasses=new Vector();
      setOfSymbols=new Vector();
      //Modify by Kelvin
      setOfConnections=new Vector();
      //////////////////////////


      //currentTarget = null;

      /*Added by Huan Nguyen 4/17/00 to initiate the Popup Menu for class
        and canvas*/
      classMenu=new PopupMenu("Class Popup Menu");
      canvasMenu=new PopupMenu("Canvas Popup Menu");
      /******end by Huan Nguyen************/

      FrameOption=0;
      setBackground(Color.white);
      addMouseListener(this);
   }

   //-----------------------------------------------------------------------
   /**
    * Empties all sets of classes, symbols, and connections, and repaints.
    **/
   public void clear() {
      setOfClasses.removeAllElements();
      setOfSymbols.removeAllElements();
      setOfConnections.removeAllElements();
      repaint();
   }
   //-----------------------------------------------------------------------
   /**
    * Paints this <code>ORDCanvas</code>.  This paint method will invoke
    * paint() methods for the classes, the symbols, then the connections.
    **/
   public void paint(Graphics g) {
      super.paintComponent(g);
      Enumeration e=setOfSymbols.elements();
      while (e.hasMoreElements()) {
         ((OMTSymbol)(e.nextElement())).paint(g);
      }
      Hashtable assocHash=new Hashtable();
      OMTConnection.labels.clear();
      e=setOfConnections.elements();
      while (e.hasMoreElements()) {
         OMTConnection connection=(OMTConnection)e.nextElement();
         Line2D.Double line=(Line2D.Double)assocHash.get(connection.assocName);
         boolean set2Blank=false;
         if (line!=null&&(line.getP1().equals(connection.from)||
            line.getP2().equals(connection.to)||
            line.getP2().equals(connection.from)||
            line.getP1().equals(connection.to))) {
            set2Blank=true;
         }
         assocHash.put(connection.assocName,
            new Line2D.Double(connection.from, connection.to));
         if (set2Blank) {
            connection.assocName="";
         }
         connection.paint(g);
      }
      e=setOfClasses.elements();
      while (e.hasMoreElements()) {
         ((OMTClass)(e.nextElement())).paint(g, FrameOption);
      }

   }

   /**
    * Methods for catching MouseListener events defined here.
    **/



   //Modify by Huan Nguyen and Kelvin Phan to detect the mouse press
   //4-17-00 to 4-28-00
   public void mousePressed(MouseEvent e) {
      point=e.getPoint();
      int mods=e.getModifiers();
      boolean clickedClass=false;

      int i=0;
      while (i<setOfClasses.size()&&!clickedClass) {
         currentTarget=(OMTClass)setOfClasses.elementAt(i);
         if (currentTarget.contains(point)) {
            clickedClass=true;
            //Added by Kelvin Phan
            util.Debug.println("mouseClicked() on "+currentTarget.className);

            if ((mods&InputEvent.BUTTON1_MASK)!=0) {
               currentTarget.showEditor();
            } else {
               if ((mods&InputEvent.BUTTON3_MASK)!=0) {
                  classMenu.show(this, point.x, point.y);
               }
            }
            //======kp============
            return;
         } else {
            if (currentTarget.containAttribute(point)) {
               clickedClass=true;
               //Added by Huan Nguyen
               util.Debug.println("mouseClicked() on "+currentTarget.className);
               if ((mods&InputEvent.BUTTON1_MASK)!=0) {
                  currentTarget.showAttributeEditor();
               } else {
                  if ((mods&InputEvent.BUTTON3_MASK)!=0) {
                     classMenu.show(this, point.x, point.y);
                  }
               }
               //======Huan Nguyen============
               return;
            } else {
               if (currentTarget.containMethod(point)) {
                  clickedClass=true;
                  //Added by Kelvin Phan
                  util.Debug.println("mouseClicked() on "+
                     currentTarget.className);
                  if ((mods&InputEvent.BUTTON1_MASK)!=0) {
                     currentTarget.showMethodEditor();
                  } else {
                     if ((mods&InputEvent.BUTTON3_MASK)!=0) {
                        classMenu.show(this, point.x, point.y);
                     }
                  }
                  //======kp============
                  return;
               }
            }
         }

         i++;
      }
      if (i==setOfClasses.size()) {
         //Added by Huan Nguyen
         if (currentTarget==null) {
            currentTarget=new OMTClass("", new Point(0, 0), new Dimension(0, 0));
         }
         if ((mods&InputEvent.BUTTON3_MASK)!=0) {
            currentTarget.className="";
            canvasMenu.show(this, point.x, point.y);
         }
      }
      //=====end by Huan Nguen and Kelvin Phan==========
   }

   public void mouseClicked(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   public void mouseReleased(MouseEvent e) {}
   //-----------------------------------------------------------------------
}
