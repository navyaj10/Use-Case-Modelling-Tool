package ord.display;

import java.awt.*;
/*
 * Full File Name:     OMTSymbol.java
 *
 * University of Texas at Arlington
 * Software Engineering Center for Telecommunications
 * Object Oriented Testing Project
 *
 * (c) Copyright 1994 University of Texas at Arlington
 * ALL RIGHTS RESERVED
 *
 * Supported Requirements:
 * Classes in this file  :
 * Related Documents     :
 *
 * Update History:
 * Date       Author   Changes
 * ---------- -------- --------------------
 * 09/28/1999 BIll F.  Redesigned OMTSymbol so it inherits from Rectangle
 *
 * Functional Description:
 * Error Messages        :
 * Constraints           :
 * Assumptions           :
 */

public class OMTSymbol extends Rectangle
{
private char relationship;
private String orientation;
public OMTSymbol(char rel, Point loc, Dimension siz, String orient)
{
  super(loc,siz);
  relationship = rel;
  orientation = orient;
}

public void paint(Graphics g)
{
  Color previousColor = g.getColor();

  Dimension size = new Dimension(getSize());
  size.width--;
  size.height--;
  int xm = x + size.width/2;    // The "middle" of symbol in x direction
  int xr = x + size.width;      // The "right" of symbol in x direction
  int ym = y + size.height/2;   // The "middle" of symbol in y direction
  int yb = y + size.height;     // The "bottom" of symbol in y direction

  if(orientation.equals("UP") && relationship=='F')
    drawUpAssociation(g,xm,xr,ym,yb);
  else if(orientation.equals("DOWN") && relationship=='F')
    drawDownAssociation(g,xm,xr,ym,yb);
  else if(orientation.equals("UP") && relationship=='I')
    drawUpInheritance(g,xm,xr,ym,yb);
  else if(orientation.equals("DOWN") && relationship=='I')
    drawDownInheritance(g,xm,xr,ym,yb);
  else if(relationship=='A')
    drawAggregation(g,xm,xr,ym,yb);

  g.setColor(previousColor);
}

private void drawUpAssociation(Graphics g, int xm, int xr, int ym, int yb)
{
  g.setColor(Color.black);
  g.drawLine(xm,y,xr,ym);
  g.drawLine(xm,y,x,ym);
  g.drawLine(xm,y,xm,yb);
}

private void drawDownAssociation(Graphics g,int xm,int xr,int ym,int yb)
{
  g.setColor(Color.black);
  g.drawLine(xm,yb,xr,ym);
  g.drawLine(xm,yb,x,ym);
  g.drawLine(xm,yb,xm,y);
}

private void drawUpInheritance(Graphics g,int xm,int xr,int ym,int yb)
{
  g.setColor(Color.red);
  g.drawLine(xm,ym,xr,yb);
  g.drawLine(xr,yb,x,yb);
  g.drawLine(x,yb,xm,ym);
  g.drawLine(xm,y,xm,ym);
}

//---------------------------------------------------------------------2
private void drawDownInheritance(Graphics g,int xm,int xr,int ym,int yb)
{
  g.setColor(Color.red);
  g.drawLine(xm,ym,xm,yb);
  g.drawLine(xr,y,xm,ym);
  g.drawLine(xm,ym,x,y);
  g.drawLine(x,y,xr,y);
}

private void drawAggregation(Graphics g,int xm,int xr,int ym,int yb)
{
  g.setColor(Color.blue);
  g.drawLine(xm,y,xr,ym);
  g.drawLine(xr,ym,xm,yb);
  g.drawLine(xm,yb,x,ym);
  g.drawLine(x,ym,xm,y);
}

}
