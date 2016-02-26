package ord.display;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/*
 * Full File Name:  OMTConnection.java
 *
 * University of Texas at Arlington
 * Software Engineering Center for Telecommunications
 * Object Oriented Testing Project
 *
 * (c) Copyright 1994 University of Texas at Arlington
 * ALL RIGHTS RESERVED
 *
 * Update History:
 * Date       Author   Changes
 * ---------- -------- --------------------
 * 09/28/1999 Bill F.  * Redesigned OMTConnection so it paints itself.
 *                       The ORDCanvas will call paint(g) from its
 *                       paint method.
 *
 * Functional Description:
 * Error Messages        :
 * Constraints           :
 * Assumptions           :
 */

public class OMTConnection {
   public static ArrayList labels=new ArrayList();
   public Point from;
   public Point to;
   public char type;
   public String fromRole="";
   public String toRole="";
   public String fromMultiplicity="";
   public String toMultiplicity="";
   public String assocName="";
   OMTConnection(Point from, Point to, char type,
      String fromRole, String toRole,
      String fromMultiplicity, String toMultiplicity, String assocName) {
      this.from=from;
      this.to=to;
      this.type=type;
      this.fromRole=fromRole;
      this.toRole=toRole;
      this.fromMultiplicity=fromMultiplicity;
      this.toMultiplicity=toMultiplicity;
      this.assocName=assocName;
   }

   public void paint(Graphics g) {
      /* Note: I didn't save the previous color then restore it for
       * performance reasons.  This gets called many times.  --Bill F.
       */
      if (type=='I') {
         g.setColor(Color.red);
      } else {
         if (type=='A') {
            g.setColor(Color.blue);
         } else {
            if (type=='F' || type=='D') {
               g.setColor(Color.black);
            } else {
               if (type=='R') {
                  g.setColor(Color.red);
                  double slope=(to.getY()-from.getY())/(to.getX()-from.getX());
                  int d=3;
                  for (int i=from.x; i<(int)to.x; i=i+2*d) {
                     int x1=i, y1=from.y+(int)((i-from.x)*slope);
                     int x2=i+d, y2=from.y+(int)(((i-from.x)+d)*slope);
                     g.drawLine(x1, y1, x2, y2);
                  }
                  return;
               }
            }
         }
      }
      // before inclusion of association class
      // g.drawLine(from.x, from.y, to.x, to.y);
      if (type=='D') { // draw dashline for association class
         drawDashLine(g, from.x, from.y, to.x, to.y);
      } else
         g.drawLine(from.x, from.y, to.x, to.y);

      FontMetrics fm=g.getFontMetrics();
      int fromX=from.x+4, fromY, toX=to.x+4, toY, assocX, assocY;
      if (type=='F'||type=='A') {
         if (from.y-to.y>30) {
            fromY=from.y-2;
            toY=to.y-9+fm.getAscent();
            assocY=(int)((from.y+to.y-11+fm.getAscent())/2);
         } else {
            fromY=from.y+2+fm.getAscent();
            toY=to.y+11-2;
            assocY=(int)((from.y+to.y+11+fm.getAscent())/2);
         }
         g.drawString(fromRole, fromX, fromY);
         g.drawString(toRole, toX, toY);
         g.drawString(fromMultiplicity,
            from.x-fm.stringWidth(fromMultiplicity)-2,
            fromY);
         g.drawString(toMultiplicity, to.x-fm.stringWidth(toMultiplicity)-2,
            toY);
         assocX=(int)((to.x+from.x)/2)+4;
         AssocLabel cur=new AssocLabel(assocName, assocX, assocY);
         if (labels.size()!=0) {
            for (int i=0; i<labels.size(); i++) {
               AssocLabel lab=(AssocLabel)labels.get(i);
               if (cur.intersect(lab)) {
                  cur.y=cur.y+12;
                  assocY=cur.y;
                  break;
               }
            }
         }
         labels.add(cur);
         if (type=='F') {
            g.drawString(assocName, assocX, assocY);
         }
      }
   }
   public void drawDashLine(Graphics g, int x0, int y0, int x1, int y1) {
      for(int i=Math.min(y0, y1); i<Math.max(y0,y1); i=i+8) {
            g.drawLine(x0, i, x0, i+4);
      }
      for(int i=Math.min(x0, x1); i<Math.max(x0,x1); i=i+8) {
         //if (y1>y0)
            g.drawLine(i, y1, i+4, y1);
         //else
            //g.drawLine(i, y1, i+4, y1);
      }
   }
   private class AssocLabel {
      int x, y;
      String label;
      public AssocLabel(String label, int x, int y) {
         this.label=label;
         this.x=x;
         this.y=y;
      }
      public boolean intersect(AssocLabel al) {
         if (al.y>y+9||y>al.y+9) {
            return false;
         }
         if (al.label.compareTo("")==0 || label.compareTo("")==0)
            return false;
         if (al.x+al.label.length()*6<x||x+label.length()*6<al.x) {
            return false;
         }
         return true;
      }
   }

}
