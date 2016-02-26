/**
 * Full File Name: OMTClass.java
 *
 * University of Texas at Arlington
 * Software Engineering Center for Telecommunications
 * Object Oriented Testing Project
 *
 * (c) Copyright 1994 University of Texas at Arlington
 * ALL RIGHTS RESERVED
 *
 * Classes in this file  : 1
 *
 * Update History:
 * Date       Author   Changes
 * ---------- -------- --------------------
 * 09/28/1999 Bill F.  * Redesigned OMTClass so that it inherits Rectangle
 *
 * Functional Description:
 * Error Messages        :
 * Constraints           :
 * Assumptions           :
 *
 *
 * 04/28/2000 Kelvin Phan
 * Added
 * setShowAttribute(boolean)
 * setShowMethod (boolean)
 * setAttribute(String[], Point, Dimension)
 * setMethod (String[], Point, Dimension)
 * containAttribute(Point)
 * containMethod(Poin)
 * getAttributeLocation(Graphics)
 * getMethodLocation(Graphics)
 * paintAttribute(Graphics)
 * paintMethod(Graphics)
 * showAttributeEditor()
 * showMethodEditor()
 * Modified
 * showEditor()
 * paint(Graphics, int)
 *
 */

package ord.display;

import util.SystemState;
import java.awt.*;
import java.io.*;

public class OMTClass extends Rectangle
{

public String className = "";
public String test_sched;
private Color background;
private Color foreground;
public int begin = 0;
public int end = 0;
public String fileName = null;
public String classOrInterface = "C";
private Editor ed = null;


//added by Kelvin Phan
//4-28-00 to display Attribute and Method
private Editor edAttribute = null;
private Editor edMethod = null;
private Rectangle attributeRectangle;
private Rectangle methodRectangle;
private int numberOfAttribute;
private int numberOfMethod;
private Point attributePoint;
private Point methodPoint;
private Dimension attributeDimension;
private Dimension methodDimension;
private String []attribute;
private String []method;
private boolean showAttribute;
private boolean showMethod;



public void setShowAttribute(boolean drawIt)
{
	showAttribute = drawIt;

}

public void setShowMethod(boolean drawIt)
{
	showMethod = drawIt;
}

public void setAttributes(String [] att, Point loc, Dimension size)
{
	numberOfAttribute = att.length;
	attribute = new String[numberOfAttribute];
	for(int i=0; i < numberOfAttribute; i++)
		attribute[i] = att[i];
	attributePoint = loc;
	attributeDimension = size;
	attributeRectangle = new Rectangle();
	attributeRectangle.x = loc.x;
	attributeRectangle.y = loc.y;
	attributeRectangle.width = size.width;
	attributeRectangle.height = size.height;
}
public boolean containAttribute(Point point)
{
	if (showAttribute == false)
		return false;
	if(attributeRectangle.contains(point))
		return true;
	else return false;
}

public boolean containMethod(Point point)
{
	if(showMethod == false)
		return false;
	if(methodRectangle.contains(point))
		return true;
	else return false;
}

private Point getAttributeLocation(Graphics g)
{
  if( g.getFont() == null )
    g.setFont( new Font("Dialog",Font.BOLD,12) );
  FontMetrics fm=g.getFontMetrics();
  int x1=(attributePoint.x + 3);
  int y1=(attributePoint.y + 10)+(fm.getAscent()/2);
  return new Point(x1,y1);
}

private void paintAttribute(Graphics gc)
{

	if(attributeDimension == null || attributePoint == null)
		return;
	gc.setColor(background);
	gc.fillRect(attributePoint.x,attributePoint.y,attributeDimension.width,attributeDimension.height);
	gc.setColor(foreground);
	gc.drawRect(attributePoint.x,attributePoint.y,attributeDimension.width,attributeDimension.height);

  // Write the attributes name inside the rectangle
  Point attributeLoc = getAttributeLocation(gc);
  //gc.drawString(attribute[0],attributeLoc.x,attributeLoc.y);
  int tempy = attributeLoc.y;
  int index = numberOfAttribute;
  //if(numberOfAttribute > 5) index = 5;
  for(int i=0; i < index; i++)
  {
	gc.drawString(attribute[i],attributeLoc.x,tempy);
	tempy = tempy + 12;
  }
}


public void setMethods(String []meth, Point loc, Dimension s)
{
	numberOfMethod = meth.length;
	method = new String[numberOfMethod];
	for(int i=0; i < numberOfMethod; i++)
		method[i] = meth[i];
	methodPoint = loc;
	methodDimension = s;
	methodRectangle = new Rectangle();
	methodRectangle.x = loc.x;
	methodRectangle.y = loc.y;
	methodRectangle.width = s.width;
	methodRectangle.height = s.height;
}

private Point getMethodLocation(Graphics g)
{
  if( g.getFont() == null )
    g.setFont( new Font("Dialog",Font.BOLD,12) );
  FontMetrics fm=g.getFontMetrics();
  int x1=(methodPoint.x + 3);
  int y1=(methodPoint.y + 10)+(fm.getAscent()/2);
  return new Point(x1,y1);
}

private void paintMethod(Graphics gc)
{
	if(methodPoint == null || methodDimension == null)
		return;
	gc.setColor(background);
	gc.fillRect(methodPoint.x,methodPoint.y,methodDimension.width,methodDimension.height);
	gc.setColor(foreground);
	gc.drawRect(methodPoint.x,methodPoint.y,methodDimension.width,methodDimension.height);

  // Write the attributes name inside the rectangle
  Point methodLoc = getMethodLocation(gc);
  //gc.drawString(method[0],methodLoc.x,methodLoc.y);
  int tempy = methodLoc.y;
  int index = numberOfMethod;
  if(numberOfMethod > 5)
	  index = 5;
  for(int i=0; i < index; i++)
  {
	gc.drawString(method[i],methodLoc.x,tempy);
	tempy = tempy + 12;
  }
}


public void showAttributeEditor()
{
	if( (edAttribute == null) || (edAttribute.flag == false))
  {
    edAttribute = new Editor(50,50);
    edAttribute.readFromString(attribute, className,"Attribute(s)");

    edAttribute.gotoLine(begin);
    edAttribute.showEditor();
  }
  else
  {
    edAttribute.gotoLine(begin);
    edAttribute.toFront();
  }
}

public void showMethodEditor()
{
	if( (edMethod == null)|| (edMethod.flag == false) )
  {
    edMethod = new Editor(50,50);
    edMethod.readFromString(method, className, "Method(s)");

    edMethod.gotoLine(begin);
    edMethod.showEditor();
  }
  else
  {
    edMethod.gotoLine(begin);
    edMethod.toFront();
  }
}
public void showEditor()
{
  if( (ed == null) || (ed.flag == false) )
  {
    ed = new Editor(50,50);
    ed.readFromFile(new File(fileName));
    ed.gotoLine(begin);
    ed.showEditor();
  }
  else
  {
    ed.gotoLine(begin);
    ed.toFront();
  }
}
//-----------------------------------------------------------------------




//-----------------------------------------------------------------------
/**
 * Constructs a new <code>OMTClass</code> whose top-left corner is the
 * specified <code>Point</code> argument, and whose width and height are
 * specified by the specified by the <code>Dimension</code> argument,
 * and whose class name is specified by the given <code>String</code>
 * argument.
 **/
public OMTClass(String className, Point loc, Dimension size)
{
  super(loc,size);
  this.className = className;
  test_sched = null;
  background = Color.white;
  foreground = Color.black;
  showMethod = false;
  showAttribute = false;
  attributeRectangle = new Rectangle();
  methodRectangle = new Rectangle();
  attributePoint = new Point();
  methodPoint = new Point();
}
//=====================end by Kelvin Phan





//-----------------------------------------------------------------------
/**
 * Determines the location of the class name.  The class name gets centered
 * in the box, both horizontally and vertically.
 **/
private Point getClassNameLocation(Graphics g)
{
  Dimension size=getSize();
  if( g.getFont() == null )
    g.setFont( new Font("Dialog",Font.BOLD,12) );
  FontMetrics fm=g.getFontMetrics();
  int x=(size.width/2)-(fm.stringWidth(className)/2);
  int y=(size.height/2)+(fm.getAscent()/2);
  return new Point(x,y);
}
//-----------------------------------------------------------------------
/**
 * Paints the <code>OMTClass</code> by drawing the rectangle and then
 * drawing the class name inside the box.
 **/
public void paint(Graphics g, int frame_option)
{
  Color previousColor = g.getColor();

  // Draw rectangle
  g.setColor(background);
  g.fillRect(x,y,width,height);
  g.setColor(foreground);
  g.drawRect(x,y,width,height);

  // Write the class name inside the rectangle
  Point classNameLoc = getClassNameLocation(g);
  g.drawString(className,x+classNameLoc.x,y+classNameLoc.y);



  //Added by Kelvin Phan
  //4-28-00
  if(showAttribute == true)
	  paintAttribute(g);
  if(showMethod == true)
	  paintMethod(g);
  //end by Kelvin Phan

  // Write the test sched above the rectangle
  if(test_sched == null)
    return;
  if(!(    ((frame_option==1)&&(SystemState.old_show_test_sched))
       ||  ((frame_option!=1)&&(SystemState.show_test_sched    ))
       ) )
    return;
  paintTestSched(g);
  g.setColor(previousColor);
}
//-----------------------------------------------------------------------
/**
 * Paints the test sched for this class
 **/
private void paintTestSched(Graphics g)
{
  g.setColor(Color.blue);
  g.drawString(test_sched,x,y-2); // Up 2 pixels for looks
}
//-----------------------------------------------------------------------
/**
 * Sets the background color for this class to the specified
 * <code>Color</code>.
 **/
public void setBackground(Color c)
{
  background = c;
}
//-----------------------------------------------------------------------
/**
 * setFont was called on the old OMTClass.
 * This stub does nothing except give ORDDisplay something to call.
 **/
public void setFont(Font f)
{
}
//-----------------------------------------------------------------------
/**
 * Display an Editor frame that reveals this class's source code.
 **/

}
