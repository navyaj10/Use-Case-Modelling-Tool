/*
 ClassName: ORDDisplay.java

 Author     Update      Description
 ------------------------------------------------------------------------
 B.Farrior  Aug 5 1999  Eliminated all references to FrameMain.
                       Instead, passed in a PanelWithInsets, and also
                         passed in a generic Frame to pass down to
                         OMTclass.setFrameState(Frame,int)
                       Eliminated addConnections(),
                         replaced with simpler in-line code.
 B.Farrior  Sep 29 1999 * Restructured so that the features on the ORD
                         class diagram are added to one canvas, not
                         a "PanelWithInsets".

 Tung Vu     April 17 2000  |Add readMethodFile(File, ORDCanvas) to be able
            read in all the "Method.dat" and "Attrib.dat"
            generate by layout and also set it to true in
            OMTClass.java

 E. Becker  May 25 2000     | Re-ordered functions
                             Removed coupling on the FrameMain
                             Fixed exceptions such that the
                               Test Schedule can be ignored
                             Fixed exceptions such that the
                               Ripple Effect can be ignored
                             Moved hardcoded names to naming function
                             Move load routines to load function
                             Moved set routines to set function
 */



package ord.display;

import util.AsciiDataInputStream;
import util.CalCheck;
import util.CalCheck;
import util.Debug;
import util.OOTErrorMsg;
import java.awt.*;
import java.util.*;
import java.io.*;
import ord.layout.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/*********************************************************************
 *********************************************************************/
public class ORDDisplay {

   Hashtable New_Status=new Hashtable();
   Hashtable Old_Status=new Hashtable();
   Hashtable ripple_effect=new Hashtable();
   Hashtable hashClasses=new Hashtable();

   Vector classes=new Vector();
   Vector connections=new Vector();
   Vector omtSymbols=new Vector();
   String temp_string;

   static Hashtable TO_table=new Hashtable();
   public static int MAXWIDTH=0; // Needed to resize canvas horizontally
   public static int MAXHEIGHT=0; //    "   "    "      "    vertically
   static int BORDER=25; // Whitespace on right and bottom sides

//----------------------------------------------------------------------
   String parser_class_filename=null;
   String ripple_effect_filename=null;
   String schedule_filename=null;
   String layout_class_filename=null;
   String layout_arrow_filename=null;
   String layout_connection_filename=null;
   String layout_attrib_filename=null;
   String layout_method_filename=null;
//----------------------------------------------------------------------

   public static void main(String[] args) {
      ORDDisplay ordDisplay=new ORDDisplay();
      ordDisplay.displayDomainModel();
   }
   public void displayDomainModel() {
      ORDLayout ordLayout=new ORDLayout();
      ORDCanvas ordCanvas=new ORDCanvas();
      ORDDisplay ordDisplay=new ORDDisplay();

      try {
         ordLayout.doLayout("", "SHOW ALL ATTRIBUTES");
      } catch (Exception e) {
         e.printStackTrace();
      }
      try {
         JFrame frame=new JFrame();
         ordDisplay.main(0, ordCanvas);
         ordCanvas.setPreferredSize(new Dimension((int)(MAXWIDTH*1.2), (int)(MAXHEIGHT*1.2)));
         JScrollPane scrollPane=new JScrollPane(ordCanvas,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollPane.getViewport().setOpaque(true);
      scrollPane.setBackground(Color.white);
      scrollPane.getViewport().setBackground(Color.white);
      ordCanvas.setBackground(Color.white);

         frame.add(scrollPane);
         frame.setBounds(0, 0, 600, 600);
         frame.setTitle("Domain Model Diagram");
         ordCanvas.repaint();
         frame.setVisible(true);
      } catch (Exception e1) {
         e1.printStackTrace();
      }
   }

   /*----------------------------------------------------------------------
      System here should be:

        0) Find the files needed for this display
        1) Load All Files for This Display
        2) Add Data to a Chosen Canvas
        3) As the program runs, set various booleans as necessary

      Summary: Manage the Cavnas
    ----------------------------------------------------------------------*/

   /***********************************************************************
     FrameMain calling version
    ***********************************************************************/
   public void main(int frame_option, ORDCanvas flat_canvas) throws Exception {
      if (!CalCheck.getInstance().isValid()) {
         OOTErrorMsg.show(
            "Invalid license or license has expired. ORD will exit.");
         System.exit(1);
      }
      // Debug.println( "ORDDisplay.main()" );
      try {
         flat_canvas.clear();
         MAXWIDTH=0;
         MAXHEIGHT=0;
         long startTime=(new Date()).getTime(); // get start time

         doSetFilenames(frame_option);
         doLoadFiles();
         addTestSchedule();
         doSetCanvas(flat_canvas);

         //modify by Kelvin Phan
         //4-17-00
         //take the code out below becuase it will call in
         //the FrameMain.java menuItemDisplay_Action(Action event)
         //flat_canvas.repaint();

         long stopTime=(new Date()).getTime(); // get end time
         // Debug.println("display time = "+(stopTime-startTime)+" msec");
      } catch (Exception e) {
         throw e;
      }
   }
//**********************************************************************



    /***********************************************************************
     ***********************************************************************/
    public void doSetCanvas(ORDCanvas flat_canvas) {
       // Debug.println( "ORDDisplay.doSetCanvas()" );
       flat_canvas.setSetOfClasses(classes);
       flat_canvas.setSetOfSymbols(omtSymbols);
       flat_canvas.setSetOfConnections(connections);
       flat_canvas.setSize(MAXWIDTH+BORDER, MAXHEIGHT+BORDER);
    }
//**********************************************************************



    /***********************************************************************
      RippleEffect Color Change for either old or new versions
      Function changeColor(FrameMain f,int FrameOption) added
      by yadi 8-19-98
      Altered by EWB on 5-25-2000

     ***********************************************************************/
    public void changeColor(int frame_option,
       ORDCanvas flat_canvas) throws Exception {
       try {
          main(frame_option, flat_canvas);
          addRippleEffect();
       } catch (Exception e) {
          throw e;
       }
       finally {
       }
       return;
    }
//**********************************************************************



    /***********************************************************************
      addRippleEffect()
      was Function getClassColor()
      added by yadi 8-19-98
      altered by becker 5-25-2000
     ***********************************************************************/
    public void addRippleEffect() {
       // Debug.println("ORDDisplay.addRippleEffect()");
       for (int i=0; i<classes.size(); i++) {
          OMTClass c=(OMTClass)classes.elementAt(i);
          /* add by yadi */
          StringTokenizer Token;
          String tmp=new String();
          String s1=new String();
          String s2=new String();
          s1=c.className;
          s2=(String)ripple_effect.get(c.className);
          if (s2!=null) {
             if (s2.equals("ChangedClass")) {
                c.setBackground(Color.red);
             }
             if (s2.equals("AffectedClass")) {
                c.setBackground(Color.orange);
             }
             if (s2.equals("AddedClass")) {
                c.setBackground(Color.pink);
             }
             if (s2.equals("DeletedClass")) {
                c.setBackground(Color.lightGray);
             }
          }
       }
    }
//**********************************************************************


    /***********************************************************************
     ***********************************************************************/
    public void addTestSchedule() {
       // Debug.println( "ORDDisplay.addTestSchedule()" );
       for (int i=0; i<classes.size(); i++) {
          OMTClass c=(OMTClass)classes.elementAt(i);
          {
             temp_string=(String)TO_table.get(c.className);
             c.test_sched=temp_string;
          }
       }
    }
//**********************************************************************



    /***********************************************************************
     ***********************************************************************/
    public void readTestSched(File input) {
       if (!input.exists()) {
          return;
       }
       // Debug.println("ORDDisplay.readTestSched(File)");
       String in_string=null;
       String in_token, sched=null;
       StringTokenizer tokens=null;
       try {
          AsciiDataInputStream adis=new AsciiDataInputStream(input);
          while (!adis.EOF()) {
             String minor=null;
             StringBuffer sb=null;
             in_token=adis.readClassname();
             int major=adis.readInt();
             minor=adis.readString();
             sb=new StringBuffer();
             sb.append(Integer.toString(major));
             sb.append(minor);
             sched=sb.toString();
             TO_table.put(in_token, sched);
          }
       } catch (IOException ioe) {
          ioe.printStackTrace();
       }
       return ;
    }
//**********************************************************************



    /***********************************************************************
     ***********************************************************************/
    public void readClassBeginEndLine(File file) {
       if (!file.exists()) {
          return;
       }
       // Debug.println( "ORDDisplay.readClassBeginENdline()" );
       AsciiDataInputStream in=null;
       String sClassName=null;
       String sClassFile=null;
       String classOrInterface=null;
       int iBeginNum=0;
       int iEndNum=0;
       try {
          in=new AsciiDataInputStream(file);
          while (!in.EOF()) {
             sClassName=in.readClassname();
             classOrInterface=in.readString();
             iBeginNum=in.readInt();
             iEndNum=in.readInt();
             sClassFile=in.readString();
             if (hashClasses.containsKey(sClassName)) {
                OMTClass cclass=(OMTClass)hashClasses.get(sClassName);
                cclass.begin=iBeginNum;
                cclass.end=iEndNum;
                cclass.fileName=sClassFile;
                cclass.classOrInterface=classOrInterface;
             }
          }
       } catch (IOException err) {
          err.printStackTrace();
       }
    }
//**********************************************************************



    /***********************************************************************
     ***********************************************************************/
    public void readOMTSymbols(File file) {
       if (!file.exists()) {
          return;
       }
       // Debug.println( "ORDDisplay.readOMTSymbolFromFile()" );
       int locX=0;
       int locY=0;
       int width=0;
       int height=0;
       char relationship;
       String orientation=null;
       AsciiDataInputStream in=null;
       try {
          in=new AsciiDataInputStream(file);
          while (!in.EOF()) {
             relationship=in.readChar();
             locX=in.readInt();
             locY=in.readInt();
             width=in.readInt();
             height=in.readInt();
             orientation=in.readString();
             OMTSymbol omt=new OMTSymbol(relationship,
                new Point(locX, locY),
                new Dimension(width, height),
                orientation);
             omtSymbols.addElement(omt);
          }
          in.close();
       } catch (IOException error) {
          error.printStackTrace();
       }
    }
//**********************************************************************



    /***********************************************************************
     ***********************************************************************/
    public void readConnections(File file)

    {
       if (!file.exists()) {
          return;
       }
       // Debug.println( "ORDDisplay.readConnectionFromFile()"+file.getName() );
       AsciiDataInputStream in=null;
       int fromX=0;
       int fromY=0;
       int toX=0;
       int toY=0;
       char type;
       try {
          in=new AsciiDataInputStream(file);
          while (!in.EOF()) {
             fromX=in.readInt();
             fromY=in.readInt();
             toX=in.readInt();
             toY=in.readInt();
             type=in.readChar();
             String fromMultiplicity=in.readString();
             String toMultiplicity=in.readString();
             String fromRole=in.readString();
             String toRole=in.readString();
             String assocName=in.readString();
             if (toX>MAXWIDTH) { // make sure connections don't get clipped
                MAXWIDTH=toX;
             }
             if (toY>MAXHEIGHT) {
                MAXHEIGHT=toY;
             }
             OMTConnection con=new OMTConnection(new Point(fromX, fromY),
                new Point(toX, toY), type, fromRole, toRole, fromMultiplicity,
                toMultiplicity, assocName);
             connections.addElement(con);
          }
          in.close();
       } catch (IOException error) {
          error.printStackTrace();
       }
    }

//**********************************************************************

   /***********************************************************************
    ***********************************************************************/
   public void readClasses(File file)

   {
      if (!file.exists()) {
         return;
      }
      // Debug.println("ORDDisplay.readClassFromFile()");
      AsciiDataInputStream in=null;
      String className=null;
      int locX=0;
      int locY=0;
      int width=0;
      int height=0;
      try {
         in=new AsciiDataInputStream(file);
         while (!in.EOF()) {
            className=in.readString();
            locX=in.readInt();
            locY=in.readInt();
            width=in.readInt();
            height=in.readInt();
            OMTClass omtclass=new OMTClass(className,
               new Point(locX, locY),
               new Dimension(width, height));
            classes.addElement(omtclass);
            hashClasses.put(omtclass.className, omtclass);
            if (locX+width>MAXWIDTH) {
               MAXWIDTH=locX+width;
            }
            if (locY+height>MAXHEIGHT) {
               MAXHEIGHT=locY+height;
            }
         }
         in.close();
      } catch (IOException error) {
         error.printStackTrace();
      }
   }
//**********************************************************************


    /***********************************************************************
     Function readRippleEffectIntoHashtable()
     added by yadi 8-19-98
     ***********************************************************************/
    public void readRippleEffect(File file)

    {
       if (!file.exists()) {
          return;
       }
       // Debug.println("ORDDisplay.readRippleEffectIntoHashtable()");
       String Buffer;
       AsciiDataInputStream input;
       StringTokenizer Token;
       String s1, s2;
       try {
          input=new AsciiDataInputStream(file);
          while (!input.EOF()) {
             s1=input.readClassname();
             s2=input.readString();
             ripple_effect.put(s1, s2);
          }
          input.close();
       } catch (IOException e) {
          e.printStackTrace();
       }
    }
//**********************************************************************



    /***********************************************************************
     ***********************************************************************/
    public void doLoadFiles() throws Exception {
       // Debug.println( "ORDDisplay.doLoadFiles()" );
       //------------------------------------------------------------------
       // Debug.println(          schedule_filename );
       // Debug.println(     ripple_effect_filename );
       // Debug.println(      layout_class_filename );
       // Debug.println( layout_connection_filename );
       // Debug.println(      layout_arrow_filename );
       // Debug.println(     layout_attrib_filename );
       // Debug.println(     layout_method_filename );
       // Debug.println(      parser_class_filename );
       //------------------------------------------------------------------
       try {
          readTestSched(new File(schedule_filename));
       } catch (Exception e) {
          // Debug.println("Not Needed if Not Exist");
       }
       finally {
          // Debug.println("Continue on");
       }
       //------------------------------------------------------------------
       try {
          readRippleEffect(new File(ripple_effect_filename));
       } catch (Exception e) {
          // Debug.println("Not Needed if Not Exist");
       }
       finally {
          // Debug.println("Continue on");
       }
       //------------------------------------------------------------------
       try {
          readClasses(new File(layout_class_filename));
          readConnections(new File(layout_connection_filename));
          readOMTSymbols(new File(layout_arrow_filename));
          readAttributes(new File(layout_attrib_filename));
          readMethods(new File(layout_method_filename));
          readClassBeginEndLine(new File(parser_class_filename));
       } catch (Exception e) {
          // Debug.println("These should exist");
          throw e;
       }
       finally {
          // Debug.println("Continue on");
       }
       //------------------------------------------------------------------
       return;
    }
//**********************************************************************

    /***********************************************************************
     ***********************************************************************/
    public void doSetFilenames(int frame_option) {
       // Debug.println( "ORDDisplay.doSetFilenames()" );

       if (frame_option==1) {
          parser_class_filename="tmp/Old_OOT_Classname";
          ripple_effect_filename="tmp/Old_OOT_RippleEffect";
          schedule_filename="tmp/Old_OOT_Sched";
          layout_class_filename="tmp/Old_Class.dat";
          layout_arrow_filename="tmp/Old_Omt.dat";
          layout_connection_filename="tmp/Old_Connection.dat";
          layout_attrib_filename="tmp/Old_Attrib.dat";
          layout_method_filename="tmp/Old_Method.dat";
       } else {
          if (frame_option==0) {
             parser_class_filename="tmp/OOT_Classname";
             ripple_effect_filename="tmp/OOT_RippleEffect";
             schedule_filename="tmp/OOT_Sched";
             layout_class_filename="tmp/Class.dat";
             layout_arrow_filename="tmp/Omt.dat";
             layout_connection_filename="tmp/Connection.dat";
             layout_attrib_filename="tmp/Attrib.dat";
             layout_method_filename="tmp/Method.dat";
          } else {
             if (frame_option==2) {
                parser_class_filename="tmp/partialOOT_Classname";
                ripple_effect_filename="tmp/OOT_RippleEffect";
                schedule_filename="tmp/OOT_Sched";
                layout_class_filename="tmp/partialClass.dat";
                layout_arrow_filename="tmp/partialOmt.dat";
                layout_connection_filename="tmp/partialConnection.dat";
                layout_attrib_filename="tmp/partialAttrib.dat";
                layout_method_filename="tmp/partialMethod.dat";
             }
          }
       }
    }
//**********************************************************************




    /*********************************************************************
     *
     *  Class: readMethodFile
     *
     *  Updated: 04/24/00        Name: Tung Vu
     *  readMethodFile(File, ORDCanvas) only read in the file format
     *  [ClassName x1 y1 x2 y2 h v count
     *   Method (1)
     *   Method (2)
     *   .
     *   .
     *   Method (count)]
     *   x1 and y1 are the Point for upper left corner of method dimension
     *   x2 and y2 are the Point for lower right corner of method dimension
     *   h = number of horizontal
     *   v = number of vertical
     *   count = number of methods
     *   set the paint method of OMTClass.java to true
     *********************************************************************/
    public void readMethods(File file)

    {
       if (!file.exists()) {
          return;
       }
       // Debug.println ("ORDDisplay.readMethodFile()");
       AsciiDataInputStream in=null;
       String className=null;
       String[] method;
       int x1=0;
       int y1=0;
       int x2=0;
       int y2=0;
       int h=0;
       int v=0;
       int count=0;
       try {
          in=new AsciiDataInputStream(file);
          while (!in.EOF()) {
             className=in.readString();
             x1=in.readInt();
             y1=in.readInt();
             x2=in.readInt();
             y2=in.readInt();
             h=in.readInt();
             v=in.readInt();
             count=in.readInt();
             method=new String[count];
             for (int i=0; i<count; i++) {
                method[i]=in.readString();
                while (!in.EOL()) {
                   method[i]+=" ";
                   method[i]+=in.readString();
                }
             }
             for (int i=0; i<classes.size(); i++) {
                if (className.equalsIgnoreCase(
                   ((OMTClass)
                   (classes.elementAt(i))).className)
                   ) {

                   ((OMTClass)
                      (classes.elementAt(i))).setMethods(
                      method, new Point(x1, y1), new Dimension(x2-x1, y2-y1));
                   ((OMTClass)
                      (classes.elementAt(i))).setShowMethod(true);
                }
             }

             if ((x1+(x2-x1))>MAXWIDTH) {
                MAXWIDTH=(x1+(x2-x1));
             }
             if ((y1+(y2-y1))>MAXHEIGHT) {
                MAXHEIGHT=(y1+(y2-y1));
             }

          }
          in.close();
       } catch (IOException error) {
          error.printStackTrace();
       }
    }
//**********************************************************************



    /*********************************************************************
     *
     *  Class: readAttributeFile
     *
     *  Updated: 04/24/00        Name: Tung Vu
     *  readAttributeFile(File, ORDCanvas) only read in the file format
     *  [ClassName x1 y1 x2 y2 h v count
     *   Attribute (1)
     *   Attribute (2)
     *   .
     *   .
     *   Attribute (count)]
     *   x1 and y1 are the Point for upper left corner of attribute dimension
     *   x2 and y2 are the Point for lower right corner of attribute dimension
     *   h = number of horizontal
     *   v = number of vertical
     *   count = number of attribute
     *   set the paint of attribute to true in OMTClass.java
     *********************************************************************/
    public void readAttributes(File file)

    {
       if (!file.exists()) {
          return;
       }
       // Debug.println ("ORDDisplay.readAttributeFile()");
       AsciiDataInputStream in=null;
       String className=null;
       String[] attributes;
       int x1=0;
       int y1=0;
       int x2=0;
       int y2=0;
       int h=0;
       int v=0;
       int count=0;
       try {
          in=new AsciiDataInputStream(file);
          while (!in.EOF()) {
             className=in.readClassname();
             x1=in.readInt();
             y1=in.readInt();
             x2=in.readInt();
             y2=in.readInt();
             h=in.readInt();
             v=in.readInt();
             count=in.readInt();
             attributes=new String[count];
             for (int i=0; i<count; i++) {
                attributes[i]=in.readString();
                while (!in.EOL()) {
                   attributes[i]+=" ";
                   attributes[i]+=in.readString();
                }
             }
             for (int i=0; i<classes.size(); i++) {
                if (className.equalsIgnoreCase(
                   ((OMTClass)
                   (classes.elementAt(i))).className)
                   ) {
                   ((OMTClass)
                      (classes.elementAt(i))).setAttributes(
                      attributes, new Point(x1, y1), new Dimension(x2-x1, y2-y1));
                   ((OMTClass)
                      (classes.elementAt(i))).setShowAttribute(true);
                }
             }

             if ((x1+(x2-x1))>MAXWIDTH) {
                MAXWIDTH=(x1+(x2-x1));
             }

             if ((y1+(y2-y1))>MAXHEIGHT) {
                MAXHEIGHT=(y1+(y2-y1));
             }

          }
          in.close();
       } catch (IOException error) {
          error.printStackTrace();
       }
    }
//**********************************************************************


}
