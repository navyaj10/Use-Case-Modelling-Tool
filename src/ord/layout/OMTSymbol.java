/*

Author              Update               Description
-------------------+--------------------+-------------------------
E.W.Becker          March 8, 1999        Added routines to shorten
					 the other end of a connection



*/



package ord.layout;
import java.awt.*;
class OMTSymbol {
   char type;
   String orientation;
   Point loc;
   Dimension size;

   OMTSymbol(char type,String orientation,Dimension size) {
      this.type=type;
      this.orientation=orientation;
      this.size=size;
   }
   public void setLocation(Point loc) {
      if(orientation.compareTo("UP")==0)
         this.loc=new Point(loc.x-(size.width/2),loc.y);
      else
         this.loc=new Point(loc.x-(size.width/2),loc.y-size.height);
   }
   public Point getLocation() {
      return loc;
   }


/*
Purpose: To place an arrow-marker at the beginning of a line
Preconditions: Need to know the point on the line being altered.
Algorithmic Notations: None
Effects: A symbol will now appear at one end of the line.
Miscellanious: Only used to handle the reversed arrow in Aggregation.
*/

   public void setAggregationLocation(Point loc)
   {
      if(orientation.compareTo("UP")==0)
         this.loc=new Point(loc.x-(size.width/2),loc.y-size.height);
      else
         this.loc=new Point(loc.x-(size.width/2),loc.y);
   }


   public Point outgoingLocation() {
      Point opt;
      if(orientation.compareTo("UP")==0) 
         opt=new Point(loc.x+size.width/2 , loc.y+size.height); 
      else
         opt=new Point(loc.x+size.width/2 , loc.y);
      return opt;
   }

/*EWB
  New Line-shortening routine
Purpose: To shorten a line at the begining of its course.
Preconditions: Need to know the point on the line being altered.
Algorithmic Notations: None
Effects: The line will be shorter at the beginning of the first
segment.
Miscellanious: Only used to handle the reversed arrow in Aggregation.
*/
  public Point incomingLocation()
  {
    Point new_line_start=null;

    if(orientation.compareTo("UP")==0)
      new_line_start=new Point(loc.x+size.width/2, loc.y);
    else
      new_line_start=new Point(loc.x+size.width/2,loc.y-size.height);

    return new_line_start;
  }


   public Dimension getSize() {
      return size;
   }
}


