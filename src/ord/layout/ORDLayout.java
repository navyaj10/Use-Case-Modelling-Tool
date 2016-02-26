/**
 * Full File Name:   ORDLayout.java
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
 * Classes in this file  : ORDLayout
 * Related Documents     : Package layout
 *
 * Update History:
 * Date          Author         Changes
 * ----------    --------       --------------------
 * 09/28/1999    Unknown        creation of this class
 * 05/01/2000    Matt Darland
 Khoan Le
 Dac Le
 Tu Phan        Modified to read the OOT_Attrib and OOT_Method files
 Modified to write the Attrib.dat and Method.dat files
 Modified to calculate layout taking attributes and
 methods to be displayed.
 *
 *************************************************************/
/********************************************************
 ORDLayout
 *********************************************************/
package ord.layout;

import util.*;
import java.util.*;
import java.awt.*;
import java.io.*;
import ord.display.OMTConnection;

public class ORDLayout {

   CClassHashtable hashClasses=new CClassHashtable();
   Vector vectorClasses;
   Vector relations;
   Vector nodes;
   Vector connections;
   Vector levels;
   static final Insets margin=new Insets(30, 60, 30, 30);
   int frame_option=0;
   String class_filename=null;
   String relation_filename=null;
   String attrib_filename=null;
   String method_filename=null;
   String classdat_filename=null;
   String connectdat_filename=null;
   String omtdat_filename=null;
   String attribdat_filename=null;
   String methoddat_filename=null;

   public void setFrameOption(int fo) {
      frame_option=fo;
   }

   public void doNameFiles() {

      if (frame_option==0) {
         class_filename=new String("tmp/OOT_Classname");
         relation_filename=new String("tmp/OOT_Relation_File");
         attrib_filename=new String("tmp/OOT_Attrib");
         method_filename=new String("tmp/OOT_Method");
         classdat_filename=new String("tmp/Class.dat");
         connectdat_filename=new String("tmp/Connection.dat");
         omtdat_filename=new String("tmp/Omt.dat");
         attribdat_filename=new String("tmp/Attrib.dat");
         methoddat_filename=new String("tmp/Method.dat");

      } else {
         if (frame_option==1) {
            class_filename=new String("tmp/Old_OOT_Classname");
            relation_filename=new String("tmp/Old_OOT_Relation_File");
            attrib_filename=new String("tmp/Old_OOT_Attrib");
            method_filename=new String("tmp/Old_OOT_Method");
            classdat_filename=new String("tmp/Old_Class.dat");
            connectdat_filename=new String("tmp/Old_Connection.dat");
            omtdat_filename=new String("tmp/Old_Omt.dat");
            attribdat_filename=new String("tmp/Old_Attrib.dat");
            methoddat_filename=new String("tmp/Old_Method.dat");
         } else {
            if (frame_option==2) {
               class_filename=new String("tmp/partialOOT_Classname");
               relation_filename=new String("tmp/partialOOT_Relation_File");
               attrib_filename=new String("tmp/OOT_Attrib");
               method_filename=new String("tmp/OOT_Method");
               classdat_filename=new String("tmp/partialClass.dat");
               connectdat_filename=new String("tmp/partialConnection.dat");
               omtdat_filename=new String("tmp/partialOmt.dat");
               attribdat_filename=new String("tmp/partialAttrib.dat");
               methoddat_filename=new String("tmp/partialMethod.dat");
            }
         }
      }
   }

   public void doReadAllFiles() throws Exception {
      try {
         readClassFile(new File(class_filename));
         readRelationFile(new File(relation_filename));
         // Next 2 lines added by Dac Le, and Khoan Le
         readAttributeFile(new File(attrib_filename));
         readMethodFile(new File(method_filename));
      } catch (Exception e) {
         throw e;
      }
   }

   public void doWriteAllFiles() throws Exception {
      try {
         writeClassNodeToFile(new File(classdat_filename));
         writeConnectionToFile(new File(connectdat_filename),
            new File(omtdat_filename));
         // Next 2 lines added by Tu Phan
         writeAllClassAttributes(new File(attribdat_filename));
         writeAllClassMethods(new File(methoddat_filename));
      } catch (Exception e) {
         throw e;
      }
   }

   public ORDLayout() {
      if (!CalCheck.getInstance().isValid()) {
         OOTErrorMsg.show(
            "Invalid license or license has expired. ORD will exit.");
         System.exit(1);
      }
   }

   // Khoan Le, Dac Le, Tu Phan, Matt Darland:
   // Calculate original layout.
   public void doLayout() throws Exception {
      try {

         hashClasses=new CClassHashtable();
         vectorClasses=new Vector();
         relations=new Vector();
         connections=new Vector();
         nodes=new Vector();
         levels=new Vector();

         //Debug.println("File Section");

         doNameFiles();
         doReadAllFiles();
         leveling();
         makeClassNodes();
         makeConnectionsAndNullnodes();
         makeLevels();
         rearrangeNodes();
         resizeNodes();
         computeNodesLocation();
         computeConnectionPointsLocation();
         fixupPoints();

         doWriteAllFiles();
      } catch (Exception x) {
         x.printStackTrace();
         throw x;
      }

   }

   // Khoan Le, Dac Le, Tu Phan, Matt Darland:
   // Calculate layout after right click.
   public void doLayout(String cname, String action) throws Exception {

      if (vectorClasses==null) {
         doLayout();
      }

      connections=new Vector();
      nodes=new Vector();
      levels=new Vector();

      if (cname.equals("")) {
         doForAll(action);
      } else {
         doForOne(cname, action);
      }

      leveling();
      makeClassNodes();
      makeConnectionsAndNullnodes();
      makeLevels();

      rearrangeNodes();

      resizeNodes();
      computeNodesLocation();
      computeConnectionPointsLocation();
      fixupPoints();

      writeClassNodeToFile(new File(classdat_filename));
      writeConnectionToFile(new File(connectdat_filename),
         new File(omtdat_filename));
      // Next 2 lines added by Tu Phan
      writeAllClassAttributes(new File(attribdat_filename));
      writeAllClassMethods(new File(methoddat_filename));
   }

   public void doForOne(String name, String action) {
      if (action.equalsIgnoreCase("SHOW ATTRIBUTES")) {
         if (hashClasses.get(name).getAttribs()!=null) {
            hashClasses.get(name).getAttribs().setVisible(true);
         }
      } else {
         if (action.equalsIgnoreCase("HIDE ATTRIBUTES")) {
            if (hashClasses.get(name).getAttribs()!=null) {
               hashClasses.get(name).getAttribs().setVisible(false);
            }
         } else {
            if (action.equalsIgnoreCase("SHOW METHODS")) {
               if (hashClasses.get(name).getMethods()!=null) {
                  hashClasses.get(name).getMethods().setVisible(true);
               }
            } else {
               if (action.equalsIgnoreCase("HIDE METHODS")) {
                  if (hashClasses.get(name).getMethods()!=null) {
                     hashClasses.get(name).getMethods().setVisible(false);
                  }
               } else {
                  if (action.equalsIgnoreCase("SHOW BOTH")) {
                     if (hashClasses.get(name).getAttribs()!=null) {
                        hashClasses.get(name).getAttribs().setVisible(true);
                     }
                     if (hashClasses.get(name).getMethods()!=null) {
                        hashClasses.get(name).getMethods().setVisible(true);
                     }
                  } else {
                     if (action.equalsIgnoreCase("HIDE BOTH")) {
                        if (hashClasses.get(name).getAttribs()!=null) {
                           hashClasses.get(name).getAttribs().setVisible(false);
                        }
                        if (hashClasses.get(name).getMethods()!=null) {
                           hashClasses.get(name).getMethods().setVisible(false);
                        }
                     } else {
                        //Debug.println("Unknown option from GUI: " + action);
                     }
                  }
               }
            }
         }
      }
   }

   // Khoan Le, Dac Le, Tu Phan, Matt Darland:
   // Set all classes attributes and/or methods for un/visibility.
   public void doForAll(String action) {
      if (action.equalsIgnoreCase("SHOW ALL ATTRIBUTES")) {
         doAllAttributes(true);
      } else {
         if (action.equalsIgnoreCase("HIDE ALL ATTRIBUTES")) {
            doAllAttributes(false);
         } else {
            if (action.equalsIgnoreCase("SHOW ALL METHODS")) {
               doAllMethods(true);
            } else {
               if (action.equalsIgnoreCase("HIDE ALL METHODS")) {
                  doAllMethods(false);
               } else {
                  if (action.equalsIgnoreCase("SHOW ALL")) {
                     doAllAttributes(true);
                     doAllMethods(true);
                  } else {
                     if (action.equalsIgnoreCase("HIDE ALL")) {
                        doAllAttributes(false);
                        doAllMethods(false);
                     } else {
                        //Debug.println("Unknown option from GUI: " + action);
                     }
                  }
               }
            }
         }
      }
   }

   public void doAllAttributes(boolean b) {
      Enumeration e=vectorClasses.elements();
      while (e.hasMoreElements()) {
         CClass mark=((CClass)e.nextElement());
         if (mark.getAttribs()!=null) {
            mark.getAttribs().setVisible(b);
         }
      }
   }

   public void doAllMethods(boolean b) {
      Enumeration e=vectorClasses.elements();
      while (e.hasMoreElements()) {
         CClass mark=((CClass)e.nextElement());
         if (mark.getMethods()!=null) {
            mark.getMethods().setVisible(b);
         }

      }
   }

   public void readRelationFile(File f) throws Exception {
      if (!f.exists()) {
         return;
      }
      AsciiDataInputStream in=null;
      String sFromClass, sToClass;
      char cRelationship;
      try {
         in=new AsciiDataInputStream(f);
         while (!in.EOF()) {
            sFromClass=in.readClassname();
            sToClass=in.readClassname();
            cRelationship=in.readChar();
            if (cRelationship=='F') {
               in.readInt();
            }
            String fromRole="";
            String toRole="";
            String fromMultiplicity="";
            String toMultiplicity="";
            String assocName="";
            try {
               if (cRelationship=='A') {
                  toMultiplicity=in.readString();
               } else {
                  if (cRelationship=='F') {
                     fromMultiplicity=in.readString();
                     toMultiplicity=in.readString();
                     fromRole=in.readString();
                     toRole=in.readString();
                     assocName=in.readString();
                  }
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
            CClass fromClass=hashClasses.get(sFromClass);
            CClass toClass=hashClasses.get(sToClass);
            if (fromClass!=null&&toClass!=null) {
               Relation rel=new Relation(fromClass, toClass,
                  cRelationship,
                  fromRole,
                  toRole, fromMultiplicity,
                  toMultiplicity, assocName);
               relations.addElement(rel);
               Connection con=new Connection(rel);
               connections.addElement(con);
            }
         }
         in.close();
      } catch (IOException err) {
         err.printStackTrace();
      }
   }
   public Connection find(String name) {
      for (int i=0; i<connections.size(); i++) {
         Connection c=(Connection)connections.get(i);
         if (c.relation.name.compareTo(name)==0) {
            return c;
         }
      }
      return null;
   }

   public void readClassFile(File f) throws Exception {
      if (!f.exists()) {
         return;
      }
      AsciiDataInputStream in=null;
      String sClassName, sClassFile;
      String classOrInterface;
      int iBeginNum, iEndNum;
      int serialNo=0;
      try {
         in=new AsciiDataInputStream(f);
         while (!in.EOF()) {
            sClassName=in.readClassname();
            classOrInterface=in.readString();
            iBeginNum=in.readInt();
            iEndNum=in.readInt();
            sClassFile=in.readString();
            CClass classTemp=new CClass(sClassName, serialNo++);
            hashClasses.put(classTemp.name, classTemp);
            vectorClasses.addElement(classTemp);
         }
      } catch (IOException err) {
         err.printStackTrace();
      } catch (Exception e) {
         throw e;
      }
   }

   /*---------------------------------------------------------------------------------------------
        Programmer    : Le, Dac Vo
        Function Name : readAttributeFile
        Return        : none
    Purpose       : parse the file OOT_Attrib and place info in right CClass
        ----------------------------------------------------------------------------------------------*/
   public void readAttributeFile(File afile) {
      if (!afile.exists()) {
         return;
      }
      String classname;
      String packagename;
      int n_attr;
      String attr_name;
      String attr_type;
      CClass c;
      int x=0;

      try {
         AsciiDataInputStream f=new AsciiDataInputStream(afile);

         while (!f.EOF()) {
            //Debug.println("Layout: tmp/OOT_Attribute: Processing record " + x++);
            classname=f.readString();
            packagename=f.readString();
            n_attr=f.readInt();

            c=hashClasses.get(classname);
            //Debug.println("Getting c");


            ORDListBox a=new ORDListBox();
            a.setVisible(true); // sTITUS

            for (int i=0; i<n_attr; ++i) {
               attr_name=f.readString();
               //attr_type=f.readString();
               a.addText(attr_name); //+": "+attr_type);
            }

            if (c!=null) { //if (c!=null)
               c.setAttribs(a);

               if (a.getLongestRow()!=-1) {
                  //Debug.println("Longest attr row: " + a.getLongestRow() + " str: " + a.getText(a.getLongestRow()));
               }
            } //if c!=null
         }

         f.close();
      } catch (Exception e) {
         //Debug.println("Error in processing tmp/OOT_Attrib file.");
      }

   }

   /*---------------------------------------------------------------------------
        Programmer    : Le, Khoan
        Function Name : readMethodFile
        Return        : none
    Purpose       : parse the file OOT_Method and place info in right CClass
    ---------------------------------------------------------------------------*/
   public void readMethodFile(File afile) {
      if (!afile.exists()) {
         return;
      }
      String classname;
      String packagename;
      int n_meths;
      String meth_name;
      String meth_type;
      int n_parms;
      String parm_type;
      String parm_name;
      CClass c;
      int x=0;

      try {
         AsciiDataInputStream f=new AsciiDataInputStream(afile);

         while (!f.EOF()) {
            //Debug.println("Layout: OOT_Method: Processing record " + x++);

            classname=f.readString();
            packagename=f.readString();
            n_meths=f.readInt();

            c=hashClasses.get(classname);
            ORDListBox m=new ORDListBox();

            for (int i=0; i<n_meths; ++i) {
               String parm_list="";

               meth_name=f.readString();
               meth_type=f.readString();
               n_parms=f.readInt();
               for (int j=0; j<n_parms; ++j) {
                  parm_type=f.readString();
                  parm_name=f.readString();
                  parm_list+=parm_name+": "+parm_type+", ";
               }

               int index=parm_list.lastIndexOf(", ");
               if (index!=-1) {
                  parm_list=parm_list.substring(0, index);
               }

               m.addText(meth_name+"( "+parm_list+
                  " ): "+meth_type);
            }
            ;
            if (c!=null) { //if (c!=null)
               c.setMethods(m);
               if (m.getLongestRow()!=-1) {
                  //Debug.println("Longest meth row: " + m.getLongestRow() + " str: " + m.getText(m.getLongestRow()));
               }
            } //if (c!=null)
         }

         f.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public void leveling() {
      Leveling leveling=new Leveling(vectorClasses, relations);
      leveling.displayList();
      leveling.displayLevel();
   }

   public void makeClassNodes() {
      Enumeration ootEnum=vectorClasses.elements();
      while (ootEnum.hasMoreElements()) {
         CClass cclass=(CClass)ootEnum.nextElement();
         Node n=new Node(cclass);
         nodes.addElement(n);
         cclass.setNode(n);
      }
   }

//New code piece inserted as of 3-8-1999
   /*
       Eric Becker

       Purpose: To position lines representing directed connections between
       nodes, which represend either classes or level markers.

       Preconditions: The begining class, end class, and levels of each
       need to be known. Also, the type of relationship involved.

       Algorithmic Notations:
       Given that there may be multiple levels between nodes,
       the line will stop at each node, and then redirect to
       make a line that doesn't intersect with a box.
       Also, the first and last points of these lines are noted,
       such that meaningful arrow markers may be correctly attached.

       Effects:
       A series of lines are described, and stored. Later,
       these are sent to file and called by the display routine.
       Display will then post the results to screent.  The most
       noticeble effects are:
       Drawing lines
       Drawing markers.
       Routing lines around class boxes.

       Miscellanious:
       Aggregation is a special case, where it's arrow is on the
       other end of the line.


    */
   public void makeConnectionsAndNullnodes() {
      //Remember  the first and last points of the linking
      ConnectionPoint firstpoint=null;
      ConnectionPoint lastpoint=null;

      Enumeration ootEnum=relations.elements();
      while (ootEnum.hasMoreElements()) {
         Relation rel=(Relation)ootEnum.nextElement();
         Connection con=new Connection(rel);
         int fromLevel=rel.from.getLevel();
         int toLevel=rel.to.getLevel();
         if (fromLevel<toLevel) {
            Node node1=rel.from.getNode();
            Node node2=null;
            ConnectionPoint cp1=node1.getConnectionPoint("TOP");
            ConnectionPoint cp2=null;
            ConnectionPointPair cpp=null;

            firstpoint=cp1;

            for (int level=fromLevel+1; level<toLevel; level++) {
               node2=new Node(level); //have a node
               nodes.addElement(node2); //add the nod
               cp2=node2.getConnectionPoint("BOTTOM"); //get other end
               cpp=new ConnectionPointPair(cp1, cp2); //Make new pairing
               cp1.setTheOtherSide(cp2); //Set as mirror
               cp2.setTheOtherSide(cp1); //Set as mirror
               con.addConnectionPointPair(cpp); //Store mirror
               node1=node2; //move to midpoint
               cp1=node1.getConnectionPoint("TOP"); //get the other end

               lastpoint=cp2;
            }

            node2=rel.to.getNode();
            cp2=node2.getConnectionPoint("BOTTOM");

            OMTSymbol omt=new OMTSymbol(rel.type, "UP", new Dimension(11, 11));
            if (rel.type=='A') {
               firstpoint.setOMTSymbol(omt);
            } else {
               cp2.setOMTSymbol(omt);
            }

            cpp=new ConnectionPointPair(cp1, cp2);
            con.addConnectionPointPair(cpp);
            cp1.setTheOtherSide(cp2);
            cp2.setTheOtherSide(cp1);
         } else {
            if (fromLevel>toLevel) {
               Node node1=rel.from.getNode();
               Node node2=null;
               ConnectionPoint cp1=node1.getConnectionPoint("BOTTOM");
               ConnectionPoint cp2=null;
               ConnectionPointPair cpp=null;

               firstpoint=cp1;
               for (int level=fromLevel-1; level>toLevel; level--) {
                  node2=new Node(level);
                  nodes.addElement(node2);
                  cp2=node2.getConnectionPoint("TOP");
                  cpp=new ConnectionPointPair(cp1, cp2);
                  con.addConnectionPointPair(cpp);
                  cp1.setTheOtherSide(cp2);
                  cp2.setTheOtherSide(cp1);
                  node1=node2;
                  cp1=node1.getConnectionPoint("BOTTOM");
               }
               node2=rel.to.getNode();
               cp2=node2.getConnectionPoint("TOP");

               lastpoint=cp2;

               OMTSymbol omt=new OMTSymbol(rel.type, "DOWN", new Dimension(11,
                  11));

               if (rel.type=='A') {
                  firstpoint.setOMTSymbol(omt);
               } else {
                  cp2.setOMTSymbol(omt);
               }

               cpp=new ConnectionPointPair(cp1, cp2);
               con.addConnectionPointPair(cpp);
               cp1.setTheOtherSide(cp2);
               cp2.setTheOtherSide(cp1);
            }
         }
         connections.addElement(con);
      }
   }

   public void makeLevels() {
      Hashtable ht=new Hashtable();

      Enumeration ootEnum=nodes.elements();
      while (ootEnum.hasMoreElements()) {
         Node node=(Node)ootEnum.nextElement();
         int l=node.level;
         if (!ht.containsKey(new Integer(l))) {
            Level newLevel=new Level(l);
            ht.put(new Integer(l), newLevel);
         }
         Level level=(Level)ht.get(new Integer(l));
         level.addNode(node);
      }
      for (int i=0; ht.containsKey(new Integer(i)); i++) {
         Level lev=(Level)ht.get(new Integer(i));
         levels.addElement(lev);
         Enumeration nodeEnum=lev.getNodes();
         int order=0;
         while (nodeEnum.hasMoreElements()) {
            Node n=(Node)nodeEnum.nextElement();
            n.setOrder(order++);
         }
      }
      Level.numOfLevels=levels.size();
   }

   public void rearrangeNodes() {
      for (int i=levels.size()-1; i>=0; i--) {
         Level lev=(Level)levels.elementAt(i);
         int n=lev.getNumNodes();
         for (int j=0; j<n; j++) {
            Node node=(Node)lev.nodes.elementAt(j);
            rearrangeConnectionPoints(node);
            node.computeTopMediumValue(levels);
         }
         bubbleSort(lev);
      }
      for (int i=0; i<levels.size(); i++) {
         Level lev=(Level)levels.elementAt(i);
         int n=lev.getNumNodes();
         for (int j=0; j<n; j++) {
            Node node=(Node)lev.nodes.elementAt(j);
            rearrangeConnectionPoints(node);
            node.computeBottomMediumValue(levels);
         }
         bubbleSort(lev);
      }
      for (int k=0; k<40; k++) {
         for (int i=levels.size()-1; i>=0; i--) {
            Level lev=(Level)levels.elementAt(i);
            int n=lev.getNumNodes();
            for (int j=0; j<n; j++) {
               Node node=(Node)lev.nodes.elementAt(j);
               rearrangeConnectionPoints(node);
               node.computeBothMediumValue(levels);
            }
            bubbleSort(lev);
         }
         for (int i=0; i<levels.size(); i++) {
            Level lev=(Level)levels.elementAt(i);
            int n=lev.getNumNodes();
            for (int j=0; j<n; j++) {
               Node node=(Node)lev.nodes.elementAt(j);
               rearrangeConnectionPoints(node);
               node.computeBothMediumValue(levels);
            }
            bubbleSort(lev);
         }
      }
   }

   private void bubbleSort(Level level) {
      int n=level.getNumNodes();
      for (int i=1; i<n; i++) {
         for (int j=n-1; j>=i; j--) {
            Node n1=(Node)level.nodes.elementAt(j-1);
            Node n2=(Node)level.nodes.elementAt(j);
            if (n1.medium>n2.medium) {
               level.exchangeNodes(n1, n2);
            }
         }
      }
   }

   public void rearrangeConnectionPoints(Node node) {
      rearrangeTopConnectionPoints(node);
      rearrangeBottomConnectionPoints(node);
   }

   private void rearrangeTopConnectionPoints(Node node) {
      int n=node.topConnectionPoints.size();
      for (int i=1; i<n; i++) {
         for (int j=n-1; j>=i; j--) {
            ConnectionPoint cp1=(ConnectionPoint)node.topConnectionPoints.
               elementAt(j-1);
            ConnectionPoint cp2=(ConnectionPoint)node.topConnectionPoints.
               elementAt(j);
            ConnectionPoint cpTheOtherSide1=(ConnectionPoint)cp1.
               getTheOtherSide();
            ConnectionPoint cpTheOtherSide2=(ConnectionPoint)cp2.
               getTheOtherSide();
            Node nodeTheOtherSide1=(Node)cpTheOtherSide1.getNode();
            Node nodeTheOtherSide2=(Node)cpTheOtherSide2.getNode();
            if (nodeTheOtherSide1.getOrder()>nodeTheOtherSide2.getOrder()||
               (nodeTheOtherSide1.getOrder()==nodeTheOtherSide2.getOrder()&&
               cpTheOtherSide1.getOrder()>cpTheOtherSide2.getOrder())) {
               node.exchangeTopConnectionPoints(cp1, cp2);
            }
         }
      }
   }

   private void rearrangeBottomConnectionPoints(Node node) {
      int n=node.bottomConnectionPoints.size();
      for (int i=1; i<n; i++) {
         for (int j=n-1; j>=i; j--) {
            ConnectionPoint cp1=(ConnectionPoint)node.bottomConnectionPoints.
               elementAt(j-1);
            ConnectionPoint cp2=(ConnectionPoint)node.bottomConnectionPoints.
               elementAt(j);
            Node nodeTheOtherSide1=(Node)cp1.getTheOtherSide().getNode();
            Node nodeTheOtherSide2=(Node)cp2.getTheOtherSide().getNode();
            if (nodeTheOtherSide1.getOrder()>nodeTheOtherSide2.getOrder()) {
               node.exchangeBottomConnectionPoints(cp1, cp2);
            }
         }
      }
   }

   public void resizeNodes() {
      //Debug.println("ORDLayout.resizeNodes()");
      Enumeration ootEnum=nodes.elements();
      while (ootEnum.hasMoreElements()) {
         Node node=(Node)ootEnum.nextElement();
         Dimension size=computeNodeSize(node);
         node.setSize(size);
      }
   }

   /*
    * Titus modiying this crap
    */

   private Dimension computeNodeSize(Node node) {
      //Debug.println("ORDLayout.computeNodeSize()");
      String className="";
      int numCPs;
      Font font=new Font("Dialog", Font.BOLD, 12);
      FontMetrics fm=java.awt.Toolkit.getDefaultToolkit().getFontMetrics(font);
      Insets insets=new Insets(3, 3, 3, 3);
      String longest_astr="";
      String longest_mstr="";
      String longest_string="";

      if (node.cclass!=null) {
         className=node.cclass.name+"   :  :  ";
      }

      // Limit to 5 attributes and 5 methods.
      // Also, limit width to class box size.
      // Matt Darland:
      // Get the longest string size in either class, attribute,
      // or method box.
      if (node.cclass!=null) {
         if (node.cclass.getAttribs()!=null) {
            //Debug.println("Node Check 1a11");
            int longest_arow=node.cclass.getAttribs().getLongestRow();
            if ((longest_arow!=-1)&&(node.cclass.getAttribs().isVisible())) {
               longest_astr=node.cclass.getAttribs().getText(longest_arow);
            }
            int longest_mrow=0;
            try {
               if (node.cclass.getMethods()==null) {
                  longest_mrow=0;
                  longest_mstr="";
               } else {
                  longest_mrow=node.cclass.getMethods().getLongestRow();
                  if ((longest_mrow!=-1)&&(node.cclass.getMethods().isVisible())) {
                     longest_mstr=node.cclass.getMethods().getText(longest_mrow);
                  }
               }
            } catch (NullPointerException e) {
               System.out.println("node.cclass.getMethods()="+
                  node.cclass.getMethods());
            }

            if (fm.stringWidth(longest_astr)>
               fm.stringWidth(longest_mstr)) {
               longest_string=longest_astr;
            } else {
               longest_string=longest_mstr;
            }
         }
      }

      if (fm.stringWidth(longest_string)<fm.stringWidth(className)) {
         longest_string=className;
      }
      //

      //Debug.println("Longest string is " + longest_string);

      // Modified to take longest string as potential width of box.
      int width=insets.left+fm.stringWidth(longest_string)+insets.right;
      int height=insets.top+fm.getHeight()+insets.bottom;

      if (node.cclass!=null) {
         if (node.cclass.getAttribs()!=null) { //if(node.cclass.getMethods()!=null)
            // Matt Darland: Set the attr/meth box height and width.
            if (node.cclass.getAttribs().isVisible()) {
               if (node.cclass.getAttribs().getNumRows()>5) {
                  node.cclass.getAttribs().setHeight(5*fm.getHeight());
               } else {
                  node.cclass.getAttribs().setHeight(
                     node.cclass.getAttribs().getNumRows()*fm.getHeight());
               }
            } else {
               node.cclass.getAttribs().setHeight(0);
            }

            node.cclass.getAttribs().setWidth(width);

//    if( node.cclass.getMethods().isVisible() )
//    {
//      if( node.cclass.getMethods().getNumRows() > 5 )
//        node.cclass.getMethods().setHeight( 5 * fm.getHeight() );
//      else
//        node.cclass.getMethods().setHeight(
//      node.cclass.getMethods().getNumRows() * fm.getHeight() );
//    }
//    else
//        node.cclass.getMethods().setHeight( 0 );

//    node.cclass.getMethods().setWidth( width );
            // Matt Darland:
            // Calculate the height of the total node.
            // This is done so layout calculations will be minimally
            // impacted by this change.  Don't forget to take this into
            // account when writing out the class name box information.
            if (node.cclass.getAttribs().isVisible()) {
               height+=node.cclass.getAttribs().getHeight();
            }

//    if( node.cclass.getMethods().isVisible() )
//        height += node.cclass.getMethods().getHeight();
//    //
         }
      }

      if (node.topConnectionPoints.size()>node.bottomConnectionPoints.size()) {
         numCPs=node.topConnectionPoints.size();
      } else {
         numCPs=node.bottomConnectionPoints.size();
      }
      if (width<15*numCPs) {
         width=15*numCPs;
      }

      return new Dimension(width, height);
   }

   public void computeNodesLocation() {
      computeNodesLocationY();
      computeNodesLocationX();
   }

   public void computeNodesLocationX() {
      locateNodeBaseOnWeightAndPriorityX();
      adjustNeighborX();
      packLeftX();
   }

   public void locateNodeBaseOnWeightAndPriorityX() {
      for (int i=0; i<nodes.size(); i++) {
         Node node=(Node)nodes.elementAt(i);
         node.computeWeight();
      }
      int n=levels.size();
      for (int i=0; i<n; i++) {
         Level lev=(Level)levels.elementAt(i);
         lev.computePriority();
         lev.computeInitialX();
      }
      for (int k=0; k<20; k++) {
         for (int i=1; i<n; i++) {
            Level lev=(Level)levels.elementAt(i);
            for (int h=0; h<lev.getNumNodes(); h++) {
               Node ntemp=(Node)lev.nodes.elementAt(h);
               ntemp.ifPlaced=false;
            }
            for (int j=0; j<lev.priority.size(); j++) {
               Node node=(Node)lev.priority.elementAt(j);
               int mx=node.computeBottomMediumX();
               int locX=lev.checkNodeLocationX(node, mx);
               node.loc.x=locX;
               node.ifPlaced=true;
            }
         }
         for (int i=n-2; i>=0; i--) {
            Level lev=(Level)levels.elementAt(i);
            for (int h=0; h<lev.getNumNodes(); h++) {
               Node ntemp=(Node)lev.nodes.elementAt(h);
               ntemp.ifPlaced=false;
            }
            for (int j=0; j<lev.priority.size(); j++) {
               Node node=(Node)lev.priority.elementAt(j);
               int mx=node.computeTopMediumX();
               int locX=lev.checkNodeLocationX(node, mx);
               node.loc.x=locX;
               node.ifPlaced=true;
            }
         }
      }

      for (int i=1; i<n; i++) {
         Level lev=(Level)levels.elementAt(i);
         for (int h=0; h<lev.getNumNodes(); h++) {
            Node ntemp=(Node)lev.nodes.elementAt(h);
            ntemp.ifPlaced=false;
         }
         for (int j=0; j<lev.priority.size(); j++) {
            Node node=(Node)lev.priority.elementAt(j);
            int mx=node.computeBothMediumX();
            int locX=lev.checkNodeLocationX(node, mx);
            node.loc.x=locX;
            node.ifPlaced=true;
         }
      }
      for (int i=n-2; i>=0; i--) {
         Level lev=(Level)levels.elementAt(i);
         for (int h=0; h<lev.getNumNodes(); h++) {
            Node ntemp=(Node)lev.nodes.elementAt(h);
            ntemp.ifPlaced=false;
         }
         for (int j=0; j<lev.priority.size(); j++) {
            Node node=(Node)lev.priority.elementAt(j);
            int mx=node.computeBothMediumX();
            int locX=lev.checkNodeLocationX(node, mx);
            node.loc.x=locX;
            node.ifPlaced=true;
         }
      }
   }

   private void adjustNeighborX() {
      Queue nq=new Queue();
      for (int i=0; i<nodes.size(); i++) {
         Node node=(Node)nodes.elementAt(i);
         node.inQ=true;
         nq.addElement(node);
      }
      Node anode;
      while ((anode=(Node)nq.nextElement())!=null) {
         anode.inQ=false;
         Level lev=(Level)levels.elementAt(anode.level);
         int mx=anode.computeBothMediumX();
         int locX=lev.checkNeighbors(anode, mx);
         if (anode.loc.x!=locX) {
            Point loc=anode.getLocation();
            loc.x=locX;
            anode.setLocation(loc);
            int order=anode.getOrder();
            if (order!=0) {
               Node leftNode=(Node)lev.nodes.elementAt(order-1);
               if (leftNode.inQ==false) {
                  leftNode.inQ=true;
                  nq.addElement(leftNode);
               }
            }
            if (order!=lev.getNumNodes()-1) {
               Node rightNode=(Node)lev.nodes.elementAt(order+1);
               if (rightNode.inQ==false) {
                  rightNode.inQ=true;
                  nq.addElement(rightNode);
               }
            }
         }
      }
   }

   public void packLeftX() {
      int n=levels.size();
      int numLevelUnfinished=n;
      int front[]=new int[n];
      for (int i=0; i<n; i++) {
         front[i]=0;
      }
      appendDummyNodeAtTheEndOfEachLevel();
      while (numLevelUnfinished!=0) {
         Level lev=(Level)levels.elementAt(0);
         Node frontNode=(Node)lev.nodes.elementAt(front[0]);
         int dist=frontNode.distToLeft(lev);
         for (int i=1; i<n; i++) {
            lev=(Level)levels.elementAt(i);
            frontNode=(Node)lev.nodes.elementAt(front[i]);
            if (dist>frontNode.distToLeft(lev)) {
               dist=frontNode.distToLeft(lev);
            }
         }

         dist-=Level.distBetweenNodes;
         if (dist>0) {
            for (int i=0; i<n; i++) {
               lev=(Level)levels.elementAt(i);
               for (int j=front[i]; j<lev.nodes.size()-1; j++) {
                  Node node=(Node)lev.nodes.elementAt(j);
                  Point loc=node.getLocation();
                  loc.x-=dist;
                  node.setLocation(loc);
               }
            }
         }
         int leftMostLevel=0;
         for (int i=1; i<n; i++) {
            Level lev1=(Level)levels.elementAt(leftMostLevel);
            Level lev2=(Level)levels.elementAt(i);
            Node node1=(Node)lev1.nodes.elementAt(front[leftMostLevel]);
            Node node2=(Node)lev2.nodes.elementAt(front[i]);
            if (node2.loc.x<node1.loc.x) {
               leftMostLevel=i;
            }
         }
         front[leftMostLevel]++;
         Level lmlevel=(Level)levels.elementAt(leftMostLevel);
         if (front[leftMostLevel]==lmlevel.nodes.size()-1) {
            numLevelUnfinished--;
         }
      }
      removeDummyNodeAtTheEndOfEachLevel();
   }

   private void appendDummyNodeAtTheEndOfEachLevel() {
      for (int i=0; i<levels.size(); i++) {
         Level lev=(Level)levels.elementAt(i);
         Node dummy=new Node(i);
         // Point loc=new Point(30000,0);

         // Changed 5/28/99 by WK.
         // Temporary fix to avoid fialure when comparing with
         // sentinel node at 30,000 pixel. Changed to 300,000 pixel.
         Point loc=new Point(300000, 0);
         // End change WK.

         dummy.setLocation(loc);
         lev.addNode(dummy);
      }
   }

   private void removeDummyNodeAtTheEndOfEachLevel() {
      for (int i=0; i<levels.size(); i++) {
         Level lev=(Level)levels.elementAt(i);
         lev.nodes.removeElementAt(lev.nodes.size()-1);
      }
   }

   public void computeNodesLocationY() {
      int n=levels.size();
      int y=ORDLayout.margin.top;
      for (int i=n-1; i>=0; i--) {
         Level lev=(Level)levels.elementAt(i);
         lev.setNodesYLocation(y);
         // Changed by Matt Darland.  Try to take variable node
         // height into account.
         y+=Level.distBetweenLevels+lev.getMaxNodeHeight();
      }
   }

   public void computeConnectionPointsLocation() {
      Enumeration ootEnum=nodes.elements();
      while (ootEnum.hasMoreElements()) {
         Node node=(Node)ootEnum.nextElement();
         node.adjustConnectionPointsLocation();
      }
   }

   // Matt Darland:
   // Computes the upper left corner points of the attribute
   // and method boxes based on their (already) computed height
   // and width.  It then modifies node elements( which originaly
   // represent the class name box ) back to its real size.
   public void fixupPoints() {
      //Debug.println("ORDLayout.fixupPoints()");
      Enumeration ootEnum=vectorClasses.elements();
      while (ootEnum.hasMoreElements()) {

         //Debug.println("Node n = ((CClass)enum.nextElement()).getNode();");
         Node n=((CClass)ootEnum.nextElement()).getNode();
         int cbox_h=0;
         cbox_h=n.size.height;
         ORDListBox attr=n.cclass.getAttribs();
         ORDListBox meth=n.cclass.getMethods();

         if (attr!=null) {
            cbox_h=cbox_h-attr.getHeight();
         }

         if (meth!=null) {
            cbox_h=cbox_h-meth.getHeight();
         }

         if (attr!=null) {
            attr.setULCorner(new Point(n.loc.x, n.loc.y+cbox_h));
         }

         if (meth!=null&&attr!=null) {
            meth.setULCorner(new Point(n.loc.x,
               attr.getULCorner().y+attr.getHeight()));
         }

         n.size.height=cbox_h;
      }
   }

   public void writeClassNodeToFile(File file) throws Exception {
      PrintWriter out=null;
      try {
         out=new PrintWriter(new FileOutputStream(file));
         Enumeration nodeEnum=nodes.elements();
         while (nodeEnum.hasMoreElements()) {
            Node node=(Node)nodeEnum.nextElement();
            if (node.cclass!=null) {
               out.println("\""+node.cclass.name+"\" "+node.loc.x+" "+
                  node.loc.y+" "+
                  node.size.width+" "+node.size.height);
            }
         }
         out.flush();
         out.close();
      } catch (IOException err) {
         err.printStackTrace();
      } catch (Exception e) {
         throw e;
      }

   }

   public void writeConnectionToFile(File conFile, File omtFile) throws
      Exception {
      PrintWriter conOut=null;
      PrintWriter omtOut=null;
      ArrayList underscores=new ArrayList(); // for association class links
      try {
         conOut=new PrintWriter(new FileOutputStream(conFile));
         omtOut=new PrintWriter(new FileOutputStream(omtFile));
         Enumeration conEnum=connections.elements();
         while (conEnum.hasMoreElements()) {
            Connection con=(Connection)conEnum.nextElement();
            if (con.relation.name.length()>0&&con.relation.name.charAt(0)=='_') {
               underscores.add(con);
            } else {
               oneConnectionToFile(conOut, omtOut, con);
            }
         }
         for (int i=0; i<underscores.size(); i++) {
            Connection con=(Connection)underscores.get(i);
            Vector v=con.connectionPointPair;
            if (v==null||v.size()==0) {
               continue;
            }
            ConnectionPointPair cpp=(ConnectionPointPair)v.get(0); // lowest segment
            Point p0=cpp.from.getLocation(); // from point
            Connection cony=find(con.relation.name.replace("_", ""));
            if (cony==null||cony.connectionPointPair.size()==0) {
               continue;
            }
            ConnectionPointPair cpp1=(ConnectionPointPair)cony.
               connectionPointPair.get(0);
            Point fromP=cpp1.from.getLocation();
            Point toP=cpp1.to.getLocation();
            int toX=(int)((fromP.x+toP.x)/2);
            int toY=(int)((fromP.y+toP.y)/2);
            conOut.println(p0.x+" "+p0.y+" "+
               toX+" "+toY+" "+"D"+" \""+
               ""+"\" \""+
               ""+"\" \""+
               ""+"\" \""+
               ""+"\" \""+
               ""+"\"");
         }

         conOut.flush();
         conOut.close();
         omtOut.flush();
         omtOut.close();
      } catch (IOException err) {
         err.printStackTrace();
      } catch (Exception e) {
         throw e;
      }
   }

   public void oneConnectionToFile(PrintWriter conOut,
      PrintWriter omtOut,
      Connection con) throws IOException {
      char type=con.relation.type;
      Enumeration pairEnum=con.connectionPointPair.elements();
      while (pairEnum.hasMoreElements()) {
         ConnectionPointPair cpp=(ConnectionPointPair)pairEnum.nextElement();
         conOut.println(cpp.from.getLocation().x+" "+
            cpp.from.getLocation().y+" "+
            cpp.to.getLocation().x+" "+
            cpp.to.getLocation().y+" "+type+" \""+
            con.relation.fromMultiplicity+"\" \""+
            con.relation.toMultiplicity+"\" \""+
            con.relation.fromRole+"\" \""+
            con.relation.toRole+"\" \""+
            con.relation.name+"\"");
         if (cpp.from.omtSymbol!=null) {
            OMTSymbol omt=cpp.from.omtSymbol;
            omtOut.println(omt.type+" "+omt.getLocation().x+" "+
               omt.getLocation().y+" "+
               omt.getSize().width+" "+
               omt.getSize().height+" "+
               omt.orientation);
         }
         if (cpp.to.omtSymbol!=null) {
            OMTSymbol omt=cpp.to.omtSymbol;
            omtOut.println(omt.type+" "+omt.getLocation().x+" "+
               omt.getLocation().y+" "+
               omt.getSize().width+" "+
               omt.getSize().height+" "+
               omt.orientation);
         }
      }
   }

   // writeAttributeList and writeMethodList added by Tu Phan.
   // These are helpers for the methods writeAllClassAttributes and
   // writeAllClassMethods
   public void writeAttributeList(AsciiDataOutputStream f,
      CClass c) {
      //Debug.println("writeAttributeList()");
      String classname=c.getName();
      ORDListBox attrbox=c.getAttribs();

      //Debug.println("attrbox is " + attrbox);
      if (attrbox!=null) {
         //Debug.println("attrbox is " + attrbox);

         int n_attr=attrbox.getNumRows();
         Point ul=attrbox.getULCorner();
         int width=attrbox.getWidth();
         int height=attrbox.getHeight();

         // Ask Kelvin about 0 size boxes.
         if (attrbox.isVisible()) {
            f.writeString("\""+classname+"\"");
            f.writeBlank();
            f.writeInt((int)ul.x);
            f.writeBlank();
            f.writeInt((int)ul.y);
            f.writeBlank();
            f.writeInt((int)(ul.x+width));
            f.writeBlank();
            f.writeInt((int)(ul.y+height));
            f.writeBlank();
            f.writeInt(attrbox.getVisCol());
            f.writeBlank();
            f.writeInt(attrbox.getVisRow());
            f.writeBlank();
            f.writeInt(n_attr);
            f.writeEOL();

            for (int line=0; line<n_attr; ++line) {
               f.writeString("\""+attrbox.getText(line)+"\"");
               f.writeEOL();

            }
         }
      }

   }

   public void writeMethodList(AsciiDataOutputStream f, CClass c) {
      String classname=c.getName();
      ORDListBox methbox=c.getMethods();
      if (methbox!=null) {
         int n_meths=methbox.getNumRows();
         Point ul=methbox.getULCorner();
         int width=methbox.getWidth();
         int height=methbox.getHeight();

         // Ask Kelvin about 0 size boxes.
         if (methbox.isVisible()) {
            f.writeString(classname);
            f.writeBlank();
            f.writeInt((int)ul.x);
            f.writeBlank();
            f.writeInt((int)ul.y);
            f.writeBlank();
            f.writeInt((int)(ul.x+width));
            f.writeBlank();
            f.writeInt((int)(ul.y+height));
            f.writeBlank();
            f.writeInt(methbox.getVisCol());
            f.writeBlank();
            f.writeInt(methbox.getVisRow());
            f.writeBlank();
            f.writeInt(n_meths);
            f.writeEOL();

            for (int line=0; line<n_meths; ++line) {
               f.writeString(methbox.getText(line));
               f.writeEOL();
            }

         }
      }
   }

   /*---------------------------------------------------------------------------
        Programmer    : Phan, Tu
        Function Name : writeAllClassAttributes
        Return        : none
        Purpose       : writes all of the attributes for all classes to a file
    ---------------------------------------------------------------------------*/
   public void writeAllClassAttributes(File afile) throws Exception {
      Enumeration cEnum=vectorClasses.elements();
      CClass c;

      try {
         AsciiDataOutputStream f=new AsciiDataOutputStream(afile);

         while (cEnum.hasMoreElements()) {
            c=(CClass)(cEnum.nextElement());
            writeAttributeList(f, c);
         }

         f.close();
         //f = null;
      } catch (IOException err) {
         err.printStackTrace();
      } catch (Exception e) {
         throw e;
      }

   }

   /*---------------------------------------------------------------------------
        Programmer    : Phan, Tu
        Function Name : writeAllClassMethods
        Return        : none
        Purpose       : writes all of the methods for all classes to a file
    ---------------------------------------------------------------------------*/
   public void writeAllClassMethods(File afile) throws Exception {
      Enumeration cEnum=vectorClasses.elements();
      CClass c;

      try {
         AsciiDataOutputStream f=new AsciiDataOutputStream(afile);

         while (cEnum.hasMoreElements()) {
            c=(CClass)(cEnum.nextElement());
            writeMethodList(f, c);
         }

         f.close();
         f=null;
      } catch (IOException err) {
         err.printStackTrace();
      } catch (Exception e) {
         throw e;
      }
   }
   public static void main(String[] args) {
      try {
         ORDLayout layout=new ORDLayout();
         layout.doLayout("", "SHOW ALL ATTRIBUTES");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
