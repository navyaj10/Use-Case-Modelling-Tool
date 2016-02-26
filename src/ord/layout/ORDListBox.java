/**
*  File Name : ORDListBox.java
*
*  The University of Texas at Arlington
*  Software Engineering Center for Telecommunications
*  Object Oriented Testing Project
*
*  (c) Copyright 2000 University of Texas at Arlington
*  ALL RIGHTS RESERVED
*
*  Supported Requirements       : Layout's view of a listbox that is 
                                  to be displayed.  The list box will
                                  contain the classes methods or attributes.

*  Classes in this file         : ORDListbox
*
*  Update History:
*
*  Date         Author                          Changes
*  --------------------------------------------------------
*  04/22/2000   Matt Darland                    Initial version
*
*
*  Functional Description       : The layout's view of a list box that will
                                  displayed on screen.  For this project
                                  the list box will contain only the object's
                                  attributes or method list.
*
**/

package ord.layout;

import java.awt.Point;
import java.util.Vector;

public class ORDListBox
    extends ORDBox
{
    int visible_col         = 32;
    int visible_row         = 4;
    Vector string_lst;

    public ORDListBox() { string_lst = new Vector(); }

    public ORDListBox( Point ul )
    {
	super( ul );
	string_lst = new Vector();
    }

    public ORDListBox( ORDListBox l )
    {
	super( l );
	visible_col = l.getVisCol();
	visible_row = l.getVisRow();
	string_lst = (Vector)l.string_lst.clone();
    }

    public int getVisCol() { return visible_col; }
    public int getVisRow() { return visible_row; }
    public void setVisCol( int c ) { visible_col = c; }
    public void setVisRow( int r ) { visible_row = r; }
    public int getNumRows() { return string_lst.size(); }

    public int getLongestRow()
    {
	int s = getNumRows();
	int largest = 0;

	if( s == 0 )
	    return( -1 );

	for( int i = 0; i < s; ++i )
	{
	    if( ((String)string_lst.elementAt( i )).length() >
		((String)string_lst.elementAt( largest )).length() )
		largest = i;
	}

	return( largest );
    }

    public void addText(
        String a
    )
    {
        string_lst.addElement( a );
    }

    public String getText(
	int row
    )
    {
        return( (String)string_lst.elementAt( row ) );
    }

    public String toString()
    {
        int i;
	int j    = getNumRows();
        String s = new String( super.toString() );

	if( j != 0 )
	    s += '\n';

        for( i = 0; i < j; ++i )
            s += i + ": " + getText( i ) + '\n';

        return( s );
    }
}


