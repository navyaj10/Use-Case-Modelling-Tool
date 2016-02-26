/**
*  File Name: ORDBox.java
*
*  The University of Texas at Arlington
*  Software Engineering Center for Telecommunications
*  Object Oriented Testing Project
*
*  (c) Copyright 2000 University of Texas at Arlington
*  ALL RIGHTS RESERVED
*

*  Supported Requirements       : Layout's view of a generic box object that is                                   to be drawn on screen. 
*  Classes in this file         : ORDBox 
*
*  Update History:
*
*  Date         Author                          Changes
*  --------------------------------------------------------
*  04/22/2000   Matt Darland                    Initial version
*
*
*  Functional Description       : Layout's view of a generic box object that is                                   to be drawn on screen.
*
**/

package ord.layout;

import java.awt.Point;

public class ORDBox
{
    private Point ul;
    int width;
    int height;
    boolean visible;

    public ORDBox()
    {
	ul = new Point( 0, 0 );
	width = height = 0;
	visible = false;
    }

    public ORDBox(
       Point uln
    )
    {
	setULCorner( uln );
	height = width = 0;
	visible = false;
    }

    public ORDBox(
	Point uln,
	int h,
	int w
    )
    {
	setULCorner( uln );
	height = h;
	width = w;
	visible = false;
    }

    public ORDBox( ORDBox b )
    {
	ul = b.getULCorner();
	height = b.getHeight();
	width = b.getWidth();
	visible = b.isVisible();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public void setWidth( int w ) { width = w; }
    public void setHeight( int h ) { height = h; }
    public Point getULCorner() { return( new Point( ul ) ); }
    public void setULCorner( Point ulnew ) {ul = new Point( ulnew );}
    public boolean isVisible() { return visible; }
    public void setVisible( boolean v ) { visible = v; }

    public String toString()
    {
	return( "ul: " + ul.toString() + " height: " + height
		+ " width: " + width );
    }
}


