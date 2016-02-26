package edu.uta.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.Highlighter;
import javax.swing.event.*;
import ord.display.*;
import ord.layout.*;
import edu.uta.controller.*;
import edu.uta.model.*;
import util.*;

public class Gui extends java.awt.event.WindowAdapter implements java.awt.event.
   WindowListener, CaretListener {
   private static Gui instance;
   ORDLayout ordLayout;
   ORDCanvas ordCanvas;
   ORDDisplay ordDisplay;
   JFrame frame;
   boolean stateSaved=true;
   private JFrame jFrame=null; //  @jve:decl-index=0:visual-constraint="20,15"
   private JPanel jContentPane=null;
   private JMenuBar jJMenuBar=null;
   private JLabel jlbClass=null;
   private JMenu fileMenu=null;
   private JMenu helpMenu=null;
   private JMenuItem exitMenuItem=null;
   private JMenuItem aboutMenuItem=null;
   private JMenuItem saveMenuItem=null;
   private JMenuItem saveStateMenuItem=null;
   private JDialog aboutDialog=null; //  @jve:decl-index=0:visual-constraint="35,704"
   private JPanel aboutContentPane=null;
   private JLabel aboutVersionLabel=null;
   private JScrollPane EditorScrollPane=null;
   private JTextPane EditorTextPane=null;
   private PopupMenu DomainConceptsPopupMenu=null; //  @jve:decl-index=0:visual-constraint="37,775"
   private AbstractAction classAction;
   private AbstractAction attributeAction;
   private AbstractAction roleAction;
   private AbstractAction associationAction;
   private AbstractAction associationClassAction;
   private AbstractAction multiplicityAction;
   private AbstractAction aggregationAction;
   private AbstractAction inheritanceAction;
   private JList DomainConceptList=null;
   private JScrollPane ListScrollPane=null;
   private JButton AddButton=null;
   private JButton EditButton=null;
   private JButton RemoveButton=null;
   private JMenuItem openMenuItem=null;
   private JButton GenerateButton=null;
   private JLabel ModelConceptListLabel=null;
   private JLabel EmptyLabel1=null;
   private JLabel EmptyLabel2=null;
   private JLabel RequirementsLabel=null;
   private JLabel EmptyLabel4=null;
   private JButton ClassifyButton=null;
   private JButton IdentifyButton=null;
   private JLabel EmptyLabel5=null;
   private JLabel EmptyLabel6=null;
   private ClassDialog classDialog=new ClassDialog(getJFrame());
   private AttributeDialog attributeDialog=new AttributeDialog(getJFrame());
   private AggregationDialog aggregationDialog=new AggregationDialog(getJFrame());
   private AssociationDialog associationDialog=new AssociationDialog(getJFrame());
   private InheritanceDialog inheritanceDialog=new InheritanceDialog(getJFrame());
   private MultiplicityDialog multiplicityDialog=new MultiplicityDialog(
      getJFrame());
   private RoleDialog roleDialog=new RoleDialog(getJFrame());
   public void showPopupMenu(MouseEvent e) {
      getDomainConceptsPopupMenu().show(e.getComponent(), e.getX(), e.getY());
   }

   public static Gui getInstance() {
      if (instance == null) {
         instance=new Gui();
      }
      return instance;
   }

   public void windowStateChanged() {
      this.ordCanvas.repaint();
   }

   public String getSelectedText() {
      return getEditorTextPane().getSelectedText();
   }

   public void setModelConceptList(String modelConcept[]) {
      getDomainConceptList().setListData(modelConcept);
      this.GenerateButton.setEnabled(true);
      this.EditButton.setEnabled(true);
      this.RemoveButton.setEnabled(true);
      stateSaved=false;
   }

   public int getModelConceptIndex() {
      return getDomainConceptList().getSelectedIndex();
   }

   public void highlightClass(String text) {
      highlightOneInstance(text, Color.YELLOW);
   }

   public void highlightAttribute(String text) {
      highlightOneInstance(text, new Color(204, 255, 255));
   }

   public void highlightAggregation(String text) {
      highlightOneInstance(text, new Color(192, 192, 192));
   }

   public void highlightAssociation(String text) {
      highlightOneInstance(text, new Color(255, 204, 229));
   }

   public void highlightInheritance(String text) {
      highlightOneInstance(text, Color.GREEN);
   }

   public void highlightMultiplicity(String text) {
      //highlightOneInstance(text, jlbMultiplicity.getForeground());
   }

   public void highlightRole(String text) {
      //highlightOneInstance(text, jlbRole.getForeground());
   }

   public void unhighlight(String text) {
      //unhighlightAllInstances(text);
   }

   public String getRequirementsText() {
      return getEditorTextPane().getText();
   }

   public void setRequirementsText(String input) {
      EditorTextPane.setText(input);
   }

   public void addPopupMenuListener(MouseAdapter ma) {
      getEditorTextPane().addMouseListener(ma);
   }

   public void addIdentifyListener(ActionListener al) {
      getIdentifyButton().addActionListener(al);
   }

   public void addClassifyListener(ActionListener al) {
      getClassifyButton().addActionListener(al);
   }

   public void addAddListener(ActionListener al) {
      getAddButton().addActionListener(al);
   }

   public void addEditListener(ActionListener al) {
      getEditButton().addActionListener(al);
   }

   public void addAddClassAction(AbstractAction aa) {
      classAction=aa;
   }

   public void addAddAttributeAction(AbstractAction aa) {
      attributeAction=aa;
   }

   public void addAddAggregationAction(AbstractAction aa) {
      aggregationAction=aa;
   }

   public void addAddAssociationAction(AbstractAction aa) {
      associationAction=aa;
   }

   public void addAddAssociationClassAction(AbstractAction aa) {
      associationClassAction=aa;
   }

   public void addAddInheritanceAction(AbstractAction aa) {
      inheritanceAction=aa;
   }

   public void addAddMultiplicityAction(AbstractAction aa) {
      multiplicityAction=aa;
   }

   public void addAddRoleAction(AbstractAction aa) {
      roleAction=aa;
   }

   public void editClass(String name, String attributes[], AbstractAction ok) {
      classDialog.setOkAction(ok);
      classDialog.setName(name);
      classDialog.setAttributes(attributes);
      classDialog.display();
   }

   public String getClassName() {
      return classDialog.getName();
   }

   public String[] getAttributes() {
      return classDialog.getAttributes();
   }

   public void editAttribute(String name, String classes[], AbstractAction ok) {
      attributeDialog.setOkAction(ok);
      attributeDialog.setName(name);
      attributeDialog.setClasses(classes);
      attributeDialog.display();
   }

   public String getAttributeName() {
      return attributeDialog.getName();
   }

   public String getAggregationWholeClass() {
      return aggregationDialog.getWholeClass();
   }

   public String getAggregationPartClass() {
      return aggregationDialog.getPartClass();
   }

   public String getAggregationMultiplicity() {
      return aggregationDialog.getMultiplicity();
   }

   public String getAttributeClass() {
      return attributeDialog.getAttributeClass();
   }

   public void editAssociation(String name, String classes[], String srcClass,
      String destClass,
      String srcRole, String destRole,
      String srcMultiplicity, String destMultiplicity,
      AbstractAction ok) {
      associationDialog.setOkAction(ok);
      associationDialog.setName(name);
      associationDialog.setClasses(classes);
      associationDialog.setSrcClass(srcClass);
      associationDialog.setDestClass(destClass);
      associationDialog.setSrcRole(srcRole);
      associationDialog.setDestRole(destRole);
      associationDialog.setSrcMultiplicity(srcMultiplicity);
      associationDialog.setDestMultiplicity(destMultiplicity);
      associationDialog.display();
   }

   public String getAggregationName() {
      return aggregationDialog.getName();
   }

   public String getAssociationName() {
      return associationDialog.getName();
   }

   public String getAssociationSrcClass() {
      return associationDialog.getSrcClass();
   }

   public String getAssociationDestClass() {
      return associationDialog.getDestClass();
   }

   public String getAssociationSrcRole() {
      return associationDialog.getSrcRole();
   }

   public String getAssociationDestRole() {
      return associationDialog.getDestRole();
   }

   public String getAssociationSrcMultiplicity() {
      return associationDialog.getSrcMultiplicity();
   }

   public String getAssociationDestMultiplicity() {
      return associationDialog.getDestMultiplicity();
   }

   public void editAggregation(String name, String classes[], String wholeClass,
      String partClass,
      String multiplicity, AbstractAction ok) {
      aggregationDialog.setOkAction(ok);
      aggregationDialog.setName(name);
      aggregationDialog.setClasses(classes);
      aggregationDialog.setWholeClass(wholeClass);
      aggregationDialog.setPartClass(partClass);
      aggregationDialog.setMultiplicity(multiplicity);
      aggregationDialog.display();
   }

   public void editInheritance(String name, String classes[], String baseClass,
      String derivedClass, AbstractAction ok) {
      inheritanceDialog.setOkAction(ok);
      //inheritanceDialog.setName(name);
      inheritanceDialog.setClasses(classes);
      inheritanceDialog.setBaseClass(baseClass);
      inheritanceDialog.setDerivedClass(derivedClass);
      inheritanceDialog.display();
   }

//	public String getInheritanceName() {
//		return inheritanceDialog.getName();
//	}

   public String getInheritanceBaseClass() {
      return inheritanceDialog.getBaseClass();
   }

   public String getInheritanceDerivedClass() {
      return inheritanceDialog.getDerivedClass();
   }

   public void editMultiplicity(String name, String relationships[],
      AbstractAction relationshipSelection,
      AbstractAction ok) {
      multiplicityDialog.setOkAction(ok);
      multiplicityDialog.setRelationshipSelectionAction(relationshipSelection);
      multiplicityDialog.setName(name);
      multiplicityDialog.setRelationships(relationships);
      multiplicityDialog.display();
   }

   public String getMultiplicityName() {
      return multiplicityDialog.getName();
   }

   public String getMultiplicityClass() {
      return multiplicityDialog.getMultiplicityClass();
   }

   public int getMultiplicityRelationshipIndex() {
      return multiplicityDialog.getRelationshipIndex();
   }

   public void setMultiplicityClasses(String classes[]) {
      multiplicityDialog.setClasses(classes);
   }

   public void editRole(String name, String associations[],
      AbstractAction associationSelection, AbstractAction ok) {
      roleDialog.setOkAction(ok);
      roleDialog.setAssociationSelectionAction(associationSelection);
      roleDialog.setName(name);
      roleDialog.setAssociations(associations);
      roleDialog.display();
   }

   public String getRoleName() {
      return roleDialog.getName();
   }

   public String getRoleClass() {
      return roleDialog.getRoleClass();
   }

   public int getRoleAssociationIndex() {
      return roleDialog.getAssociationIndex();
   }

   public void setRoleClasses(String classes[]) {
      roleDialog.setClasses(classes);
   }

   public void displayDomainModel() {
      ordLayout=new ORDLayout();
      ordCanvas=new ORDCanvas();
      ordDisplay=new ORDDisplay();

      try {
         ordLayout.doLayout("", "SHOW ALL ATTRIBUTES");
      } catch (Exception e) {
         e.printStackTrace();
      }
      try {
         frame=new JFrame();
         ordCanvas.setLayout(new BorderLayout());
         ordCanvas.setPreferredSize(new Dimension(5000, 4000));
         ordDisplay.main(0, ordCanvas);
         JScrollPane scrollPane=new JScrollPane(ordCanvas,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         scrollPane.getViewport().setOpaque(true);
         scrollPane.setBackground(Color.white);
         scrollPane.getViewport().setBackground(Color.white);
         scrollPane.setWheelScrollingEnabled(true);
         ordCanvas.setBackground(Color.white);
         frame.add(scrollPane);
         frame.setBounds(0, 0, 600, 600);
         frame.setTitle("Domain Model Diagram");
         this.ordCanvas.repaint();
         frame.setVisible(true);
      } catch (Exception e1) {
         e1.printStackTrace();
      }
   }

   /**
    * This method initializes jFrame
    *
    * @return javax.swing.JFrame
    */
   public JFrame getJFrame() {
      if (jFrame == null) {
         jFrame=new JFrame();
         jFrame.setDefaultCloseOperation(jFrame.DO_NOTHING_ON_CLOSE);
         jFrame.setJMenuBar(getJJMenuBar());
         Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
         int sw=d.width;
         int sh=d.height;
         jFrame.setSize((int) (sw / 2), (int) (sh * 0.8));
         jFrame.setLocation((int) (sw - jFrame.getWidth()) / 2,
            (int) (sh - jFrame.getHeight()) / 2);
         jFrame.setContentPane(getJContentPane());
         jFrame.setTitle("Domain Model Generator");
         jFrame.addWindowListener(this);
      }
      return jFrame;
   }

   /**
    * This method initializes jContentPane
    *
    * @return javax.swing.JPanel
    */
   private JPanel getJContentPane() {
      if (jContentPane == null) {
         GridBagConstraints gridBagConstraints23=new GridBagConstraints();
         gridBagConstraints23.insets=new Insets(7, 7, 37, 22);
         gridBagConstraints23.gridy=9;
         gridBagConstraints23.ipadx=331;
         gridBagConstraints23.ipady=16;
         gridBagConstraints23.gridx=2;
         GridBagConstraints gridBagConstraints22=new GridBagConstraints();
         gridBagConstraints22.insets=new Insets(15, 7, 22, 22);
         gridBagConstraints22.gridy=1;
         gridBagConstraints22.ipadx=331;
         gridBagConstraints22.ipady=16;
         gridBagConstraints22.gridx=2;
         GridBagConstraints gridBagConstraints21=new GridBagConstraints();
         gridBagConstraints21.insets=new Insets(7, 7, 7, 19);
         gridBagConstraints21.gridy=10;
         gridBagConstraints21.ipadx=29;
         gridBagConstraints21.ipady=5;
         gridBagConstraints21.gridx=3;
         GridBagConstraints gridBagConstraints20=new GridBagConstraints();
         gridBagConstraints20.insets=new Insets(22, 7, 7, 19);
         gridBagConstraints20.gridy=9;
         gridBagConstraints20.ipadx=12;
         gridBagConstraints20.ipady=5;
         gridBagConstraints20.gridx=3;
         GridBagConstraints gridBagConstraints19=new GridBagConstraints();
         gridBagConstraints19.fill=GridBagConstraints.BOTH;
         gridBagConstraints19.gridwidth= -1;
         gridBagConstraints19.gridx=1;
         gridBagConstraints19.gridy=1;
         gridBagConstraints19.ipadx= -453;
         gridBagConstraints19.ipady= -419;
         gridBagConstraints19.weightx=1.0;
         gridBagConstraints19.weighty=1.0;
         gridBagConstraints19.gridheight= -1;
         GridBagConstraints gridBagConstraints18=new GridBagConstraints();
         gridBagConstraints18.insets=new Insets(7, 15, 9, 90);
         gridBagConstraints18.gridy=15;
         gridBagConstraints18.ipadx=38;
         gridBagConstraints18.ipady=16;
         gridBagConstraints18.gridx=1;
         GridBagConstraints gridBagConstraints17=new GridBagConstraints();
         gridBagConstraints17.insets=new Insets(15, 15, 22, 37);
         gridBagConstraints17.gridy=1;
         gridBagConstraints17.ipadx=11;
         gridBagConstraints17.gridx=1;
         GridBagConstraints gridBagConstraints16=new GridBagConstraints();
         gridBagConstraints16.insets=new Insets(37, 7, 24, 19);
         gridBagConstraints16.gridx=3;
         gridBagConstraints16.gridy=14;
         gridBagConstraints16.ipadx=91;
         gridBagConstraints16.ipady=16;
         gridBagConstraints16.gridheight=2;
         GridBagConstraints gridBagConstraints15=new GridBagConstraints();
         gridBagConstraints15.insets=new Insets(7, 7, 7, 42);
         gridBagConstraints15.gridy=8;
         gridBagConstraints15.ipadx=68;
         gridBagConstraints15.ipady=16;
         gridBagConstraints15.gridx=3;
         GridBagConstraints gridBagConstraints14=new GridBagConstraints();
         gridBagConstraints14.insets=new Insets(7, 15, 37, 7);
         gridBagConstraints14.gridy=9;
         gridBagConstraints14.ipadx=13;
         gridBagConstraints14.gridx=1;
         GridBagConstraints gridBagConstraints13=new GridBagConstraints();
         gridBagConstraints13.insets=new Insets(7, 7, 7, 19);
         gridBagConstraints13.gridy=14;
         gridBagConstraints13.ipadx=5;
         gridBagConstraints13.ipady=5;
         gridBagConstraints13.gridx=3;
         GridBagConstraints gridBagConstraints12=new GridBagConstraints();
         gridBagConstraints12.insets=new Insets(7, 7, 7, 19);
         gridBagConstraints12.gridy=13;
         gridBagConstraints12.ipadx=11;
         gridBagConstraints12.ipady=5;
         gridBagConstraints12.gridx=3;
         GridBagConstraints gridBagConstraints11=new GridBagConstraints();
         gridBagConstraints11.insets=new Insets(7, 7, 7, 19);
         gridBagConstraints11.gridy=12;
         gridBagConstraints11.ipadx=36;
         gridBagConstraints11.ipady=5;
         gridBagConstraints11.gridx=3;
         GridBagConstraints gridBagConstraints10=new GridBagConstraints();
         gridBagConstraints10.insets=new Insets(7, 7, 7, 19);
         gridBagConstraints10.gridy=11;
         gridBagConstraints10.ipadx=35;
         gridBagConstraints10.ipady=5;
         gridBagConstraints10.gridx=3;
         GridBagConstraints gridBagConstraints9=new GridBagConstraints();
         gridBagConstraints9.fill=GridBagConstraints.BOTH;
         gridBagConstraints9.gridheight=7;
         gridBagConstraints9.gridwidth=2;
         gridBagConstraints9.gridx=1;
         gridBagConstraints9.gridy=9;
         gridBagConstraints9.ipadx=222;
         gridBagConstraints9.ipady=144;
         gridBagConstraints9.weightx=1.0;
         gridBagConstraints9.weighty=1.0;
         gridBagConstraints9.insets=new Insets(22, 15, 20, 7);
         GridBagConstraints gridBagConstraints8=new GridBagConstraints();
         gridBagConstraints8.insets=new Insets(7, 7, 7, 34);
         gridBagConstraints8.gridy=7;
         gridBagConstraints8.ipadx=16;
         gridBagConstraints8.gridx=3;
         GridBagConstraints gridBagConstraints7=new GridBagConstraints();
         gridBagConstraints7.insets=new Insets(7, 7, 7, 34);
         gridBagConstraints7.gridy=6;
         gridBagConstraints7.ipadx=51;
         gridBagConstraints7.gridx=3;
         GridBagConstraints gridBagConstraints6=new GridBagConstraints();
         gridBagConstraints6.insets=new Insets(7, 7, 7, 34);
         gridBagConstraints6.gridy=5;
         gridBagConstraints6.ipadx=12;
         gridBagConstraints6.gridx=3;
         GridBagConstraints gridBagConstraints5=new GridBagConstraints();
         gridBagConstraints5.insets=new Insets(7, 7, 7, 34);
         gridBagConstraints5.gridy=4;
         gridBagConstraints5.ipadx=7;
         gridBagConstraints5.gridx=3;
         GridBagConstraints gridBagConstraints4=new GridBagConstraints();
         gridBagConstraints4.insets=new Insets(7, 7, 7, 37);
         gridBagConstraints4.gridy=3;
         gridBagConstraints4.ipadx=6;
         gridBagConstraints4.gridx=3;
         GridBagConstraints gridBagConstraints3=new GridBagConstraints();
         gridBagConstraints3.insets=new Insets(7, 7, 7, 34);
         gridBagConstraints3.gridy=2;
         gridBagConstraints3.ipadx=27;
         gridBagConstraints3.gridx=3;
         GridBagConstraints gridBagConstraints2=new GridBagConstraints();
         gridBagConstraints2.insets=new Insets(30, 7, 7, 34);
         gridBagConstraints2.gridy=1;
         gridBagConstraints2.ipadx=44;
         gridBagConstraints2.gridx=3;
         GridBagConstraints gridBagConstraints1=new GridBagConstraints();
         gridBagConstraints1.fill=GridBagConstraints.BOTH;
         gridBagConstraints1.gridheight=8;
         gridBagConstraints1.gridwidth=2;
         gridBagConstraints1.gridx=1;
         gridBagConstraints1.gridy=1;
         gridBagConstraints1.ipadx=38;
         gridBagConstraints1.ipady=201;
         gridBagConstraints1.weightx=1.0;
         gridBagConstraints1.weighty=1.0;
         gridBagConstraints1.insets=new Insets(30, 15, 7, 7);
         EmptyLabel6=new JLabel();
         EmptyLabel6.setText("");
         EmptyLabel5=new JLabel();
         EmptyLabel5.setText("");
         EmptyLabel4=new JLabel();
         EmptyLabel4.setText("");
         RequirementsLabel=new JLabel();
         RequirementsLabel.setText("Requirements");
         EmptyLabel2=new JLabel();
         EmptyLabel2.setText("");
         EmptyLabel1=new JLabel();
         EmptyLabel1.setText("");
         ModelConceptListLabel=new JLabel();
         ModelConceptListLabel.setText("Model Concept List");
         jContentPane=new JPanel();
         jContentPane.setLayout(new GridBagLayout());
         jContentPane.add(getEditorScrollPane(), gridBagConstraints1);
         /** brainstorming rules */
         LayoutManager layout=new GridLayout(11, 1);
         JPanel jp=new JPanel(layout);
         jp.add(new JLabel("Highlight & right click"));
         jp.add(new JLabel("a domain specific:"));
         jp.add(new JLabel("1) Noun/noun phrase"));
         jp.add(new JLabel("2) X of Y expression"));
         jp.add(new JLabel("3) Transitive verb"));
         jp.add(new JLabel("4) Adjective"));
         jp.add(new JLabel("5) Numeric/enumeration"));
         jp.add(new JLabel("6) Possession expression"));
         jp.add(new JLabel("7) Part of expression"));
         jp.add(new JLabel("8) Containment expression"));
         jp.add(new JLabel("9) X is Y expression"));
         jContentPane.add(jp, gridBagConstraints2);
         jContentPane.add(getListScrollPane(), gridBagConstraints9);
         jContentPane.add(getAddButton(), gridBagConstraints10);
         jContentPane.add(getEditButton(), gridBagConstraints11);
         jContentPane.add(getRemoveButton(), gridBagConstraints12);
         jContentPane.add(getGenerateButton(), gridBagConstraints13);
         jContentPane.add(ModelConceptListLabel, gridBagConstraints14);
         jContentPane.add(EmptyLabel1, gridBagConstraints15);
         jContentPane.add(EmptyLabel2, gridBagConstraints16);
         jContentPane.add(RequirementsLabel, gridBagConstraints17);
         jContentPane.add(EmptyLabel4, gridBagConstraints18);
         jContentPane.add(getClassifyButton(), gridBagConstraints21);
         jContentPane.add(getIdentifyButton(), gridBagConstraints20);
         jContentPane.add(EmptyLabel5, gridBagConstraints22);
         jContentPane.add(EmptyLabel6, gridBagConstraints23);
      }
      return jContentPane;
   }

   /**
    * This method initializes jJMenuBar
    *
    * @return javax.swing.JMenuBar
    */
   private JMenuBar getJJMenuBar() {
      if (jJMenuBar == null) {
         jJMenuBar=new JMenuBar();
         jJMenuBar.add(getFileMenu());
         jJMenuBar.add(getHelpMenu());
      }
      return jJMenuBar;
   }

   /**
    * This method initializes jMenu
    *
    * @return javax.swing.JMenu
    */
   private JMenu getFileMenu() {
      if (fileMenu == null) {
         fileMenu=new JMenu();
         fileMenu.setText("File");
         fileMenu.add(getOpenMenuItem());
         fileMenu.add(getSaveMenuItem());
         fileMenu.add(getSaveStateMenuItem());
         fileMenu.add(getExitMenuItem());
      }
      return fileMenu;
   }

   /**
    * This method initializes jMenu
    *
    * @return javax.swing.JMenu
    */
   private JMenu getHelpMenu() {
      if (helpMenu == null) {
         helpMenu=new JMenu();
         helpMenu.setText("Help");
         helpMenu.add(getAboutMenuItem());
      }
      return helpMenu;
   }

   /**
    * This method initializes jMenuItem
    *
    * @return javax.swing.JMenuItem
    */
   private JMenuItem getExitMenuItem() {
      if (exitMenuItem == null) {
         exitMenuItem=new JMenuItem();
         exitMenuItem.setText("Exit");
         exitMenuItem.setToolTipText("Click to exit the application.");
         exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if (!stateSaved) {
                  int option=JOptionPane.showConfirmDialog(jFrame,
                     "Do you want to save the work?",
                     "Save Work Dialog", JOptionPane.YES_NO_OPTION,
                     JOptionPane.YES_OPTION);
                  if (option == JOptionPane.YES_OPTION) {
                     saveState();
                  }
               }
               System.exit(0);
            }
         });
      }
      return exitMenuItem;
   }

   /**
    * This method initializes jMenuItem
    *
    * @return javax.swing.JMenuItem
    */
   private JMenuItem getAboutMenuItem() {
      if (aboutMenuItem == null) {
         aboutMenuItem=new JMenuItem();
         aboutMenuItem.setText("About");
         aboutMenuItem.setToolTipText(
            "Click here to know the version of this tool!!!");
         aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               JDialog aboutDialog=getAboutDialog();
               aboutDialog.pack();
               Point loc=getJFrame().getLocation();
               loc.translate(20, 20);
               aboutDialog.setLocation(loc);
               aboutDialog.setVisible(true);
            }
         });
      }
      return aboutMenuItem;
   }

   /**
    * This method initializes aboutDialog
    *
    * @return javax.swing.JDialog
    */
   private JDialog getAboutDialog() {
      if (aboutDialog == null) {
         aboutDialog=new JDialog(getJFrame(), true);
         aboutDialog.setTitle("About");
         aboutDialog.setContentPane(getAboutContentPane());
      }
      return aboutDialog;
   }

   /**
    * This method initializes aboutContentPane
    *
    * @return javax.swing.JPanel
    */
   private JPanel getAboutContentPane() {
      if (aboutContentPane == null) {
         aboutContentPane=new JPanel();
         aboutContentPane.setLayout(new BorderLayout());
         JPanel jp=new JPanel(new BorderLayout());
         JPanel aboutPanel=new JPanel(new BorderLayout());
         aboutContentPane.add(jp, BorderLayout.CENTER);
         jp.add(aboutPanel, BorderLayout.CENTER);
         JLabel tool=new JLabel("  Manual Domain Model Editor 1.0  ");
         tool.setHorizontalAlignment(tool.CENTER);
         aboutPanel.add(tool, BorderLayout.NORTH);
         JLabel vendor=new JLabel("  Atruya Systems, Inc.  ");
         vendor.setHorizontalAlignment(vendor.CENTER);
         aboutPanel.add(vendor, BorderLayout.CENTER);
         JLabel copyright=new JLabel("  (c)2009-2014  ");
         copyright.setHorizontalAlignment(copyright.CENTER);
         aboutPanel.add(copyright, BorderLayout.SOUTH);
         JButton closeButton=new JButton("Close");
         JPanel buttonPanel=new JPanel(new FlowLayout());
         buttonPanel.add(closeButton);
         jp.add(buttonPanel, BorderLayout.SOUTH);
         closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               aboutDialog.setVisible(false);
            }
         }
         );
      }
      return aboutContentPane;
   }

   /**
    * This method initializes aboutVersionLabel
    *
    * @return javax.swing.JLabel
    */
   private JLabel getAboutVersionLabel() {
      if (aboutVersionLabel == null) {
         aboutVersionLabel=new JLabel();
         aboutVersionLabel.setText(
            "<HTML><BR /> Manual Domain Modeling Tool <BR/>" +
            " Release 1.0 <BR/>" +
            " Atruya Systems, Inc.<BR />(c)2009-2014 </HTML>");
         aboutVersionLabel.setHorizontalAlignment(SwingConstants.CENTER);
      }
      return aboutVersionLabel;
   }

   /**
    * This method initializes jMenuItem
    *
    * @return javax.swing.JMenuItem
    */
   private JMenuItem getSaveMenuItem() {
      if (saveMenuItem == null) {
         saveMenuItem=new JMenuItem();
         saveMenuItem.setText("Save Text Document");
         saveMenuItem.setToolTipText("Click to save the text document.");
         saveMenuItem.setEnabled(true);
         saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
               saveTextDocument();
            }
         });
      }
      return saveMenuItem;
   }

   private JMenuItem getSaveStateMenuItem() {
      if (saveStateMenuItem == null) {
         saveStateMenuItem=new JMenuItem();
         saveStateMenuItem.setText("Save");
         saveStateMenuItem.setToolTipText("Click to save the project.");
         saveStateMenuItem.setEnabled(true);
         saveStateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
               saveState();
            }
         });
      }
      return saveStateMenuItem;
   }

   private void saveState() {
      try {
         PrintWriter pw=new PrintWriter("tmp/texdoc.txt");
         pw.write(EditorTextPane.getText());
         pw.flush();
         pw.close();
         ArrayList dmConcepts=DomainModel.getInstance().getConcepts();
         PersistenceMgr.save(dmConcepts, "tmp/conceptlist.dat");
         stateSaved=true;
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * This method initializes EditorScrollPane
    *
    * @return javax.swing.JScrollPane
    */
   private JScrollPane getEditorScrollPane() {
      if (EditorScrollPane == null) {
         EditorScrollPane=new JScrollPane();
         EditorScrollPane.setHorizontalScrollBarPolicy(JScrollPane.
            HORIZONTAL_SCROLLBAR_NEVER);
         EditorScrollPane.setViewportView(getEditorTextPane());
      }
      return EditorScrollPane;
   }

   /**
    * This method initializes jepEditor
    *
    * @return javax.swing.JEditorPane
    */
   public JTextPane getEditorTextPane() {
      if (EditorTextPane == null) {
         EditorTextPane=new JTextPane();
         EditorTextPane.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
         EditorTextPane.setText("");
         EditorTextPane.addCaretListener(this);
      }
      return EditorTextPane;
   }

   public void caretUpdate(CaretEvent e) {
      if (e.getSource() == EditorTextPane) {
         stateSaved=false;
      }
   }

   /**
    * This method initializes DomainConceptsPopupMenu
    *
    * @return javax.swing.JPopupMenu
    */
   private PopupMenu getDomainConceptsPopupMenu() {
      if (DomainConceptsPopupMenu == null) {
         DomainConceptsPopupMenu=new PopupMenu();
         DomainConceptsPopupMenu.setClassAction(classAction);
         DomainConceptsPopupMenu.setAttributeAction(attributeAction);
         DomainConceptsPopupMenu.setRoleAction(roleAction);
         DomainConceptsPopupMenu.setAssociationAction(associationAction);
         DomainConceptsPopupMenu.setAssociationClassAction(
            associationClassAction);
         DomainConceptsPopupMenu.setMultiplicityAction(multiplicityAction);
         DomainConceptsPopupMenu.setAggregationAction(aggregationAction);
         DomainConceptsPopupMenu.setInheritanceAction(inheritanceAction);
         DomainConceptsPopupMenu.createMenu();
      }
      return DomainConceptsPopupMenu;
   }

   /**
    * This method initializes DomainConceptList
    *
    * @return javax.swing.JList
    */
   public JList getDomainConceptList() {
      if (DomainConceptList == null) {
         DomainConceptList=new JList(DomainModel.getInstance());
         DomainConceptList.setFont(new Font("Dialog", Font.PLAIN, 15));
      }
      DomainConceptList.setVisible(true);
      return DomainConceptList;
   }

   /**
    * This method initializes ListScrollPane
    *
    * @return javax.swing.JScrollPane
    */
   private JScrollPane getListScrollPane() {
      if (ListScrollPane == null) {
         ListScrollPane=new JScrollPane();
         ListScrollPane.setVisible(true);
         ListScrollPane.setViewportView(getDomainConceptList());
      }
      return ListScrollPane;
   }

   /**
    * This method initializes AddButton
    *
    * @return javax.swing.JButton
    */
   private JButton getAddButton() {
      if (AddButton == null) {
         AddButton=new JButton();
         AddButton.setText("Add");
         AddButton.setEnabled(true);
         AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
               addModelConcept();
            }
         });
      }
      return AddButton;
   }

   /**
    * This method initializes EditButton
    *
    * @return javax.swing.JButton
    */
   private JButton getEditButton() {
      if (EditButton == null) {
         EditButton=new JButton();
         EditButton.setText("Edit");
         EditButton.setEnabled(true);
         EditListener listener=new EditListener(this.getDomainConceptList());
         EditButton.addActionListener(listener);
      }
      return EditButton;
   }

   /**
    * This method initializes RemoveButton
    *
    * @return javax.swing.JButton
    */
   public JButton getRemoveButton() {
      if (RemoveButton == null) {
         RemoveButton=new JButton();
         RemoveButton.setText("Remove");
         RemoveButton.setEnabled(true);
         RemoveButton.addActionListener(new RemoveListener(this.
            DomainConceptList));
      }
      return RemoveButton;
   }

   /**
    * This method initializes openMenuItem
    *
    * @return javax.swing.JMenuItem
    */
   private JMenuItem getOpenMenuItem() {
      if (openMenuItem == null) {
         openMenuItem=new JMenuItem();
         openMenuItem.setText("Open Text Document");
         openMenuItem.setToolTipText(
            "Click here to select a requirement text file!!!");
         openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
               openTextDocument();
            }
         });
      }
      return openMenuItem;
   }

   /**
    * This method initializes GenerateButton
    *
    * @return javax.swing.JButton
    */
   private JButton getGenerateButton() {
      if (GenerateButton == null) {
         GenerateButton=new JButton();
         GenerateButton.setText("Generate");
         GenerateButton.setToolTipText(
            "Click here to generate the Domain Model Diagram!!!");
         GenerateButton.setEnabled(true);
      }
      return GenerateButton;
   }

   /**
    * This method initializes ClassifyButton
    *
    * @return javax.swing.JButton
    */
   private JButton getClassifyButton() {
      if (ClassifyButton == null) {
         ClassifyButton=new JButton();
         ClassifyButton.setText("Classify");
         ClassifyButton.setEnabled(false);
         ClassifyButton.setVisible(false);
         ClassifyButton.setToolTipText(
            "Click here to generate the domain concepts automatically!!!");
      }
      return ClassifyButton;
   }

   /**
    * This method initializes IdentifyButton
    *
    * @return javax.swing.JButton
    */
   private JButton getIdentifyButton() {
      if (IdentifyButton == null) {
         IdentifyButton=new JButton();
         IdentifyButton.setText("Identify");
         IdentifyButton.setEnabled(false);
         IdentifyButton.setVisible(false);
      }
      return IdentifyButton;
   }

   /**
    * Launches this application
    */
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            Gui gui=Gui.getInstance(); //new Gui();
            //Controller.getInstance().setGui(gui);
            gui.getJFrame().setVisible(true);
         }
      });
   }

   //http://java.sun.com/docs/books/tutorial/uiswing/components/filechooser.html
   //http://www.javazoid.com/foj_file.html
   private void openTextDocument() {
      JFileChooser fc=new JFileChooser(new File("."));
      int returnVal=fc.showOpenDialog(getJFrame());
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         File file=fc.getSelectedFile();
         try {
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String text="";
            String line="";
            while ((line=reader.readLine()) != null) {
               text+=line + "\n";
            }
            reader.close();
            getEditorTextPane().setText(text);
            getEditorTextPane().setCaretPosition(0);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   //http://java.sun.com/docs/books/tutorial/uiswing/components/filechooser.html
   //http://www.javazoid.com/foj_file.html
   private void saveTextDocument() {
      JFileChooser fc=new JFileChooser();
      int returnVal=fc.showSaveDialog(getJFrame());
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         File file=fc.getSelectedFile();
         try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(file.
               getAbsolutePath()));
            writer.write(getEditorTextPane().getText());
            writer.flush();
            writer.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   private void addModelConcept() {
      AbstractAction[] actionArray= {
         classAction, attributeAction, associationAction,
         associationClassAction,
         aggregationAction, inheritanceAction, roleAction, multiplicityAction};
      new SelectionDialog(getJFrame(), actionArray);
   }

   /**
    * highlights multiple occurrences of a model concept
    */
   public void highlightAllInstances(String searchFor, Color color) {
      // commented out to void highlighting
      /*
             String base = getEditorTextPane().getText().toLowerCase();
             int len = searchFor.length();
             int result = 0;
             int end = 0;
             int start = 0;

             if (len > 0) {

         while (base.indexOf(searchFor.toLowerCase(), start) >= 0) {
            result++;
            start = base.indexOf(searchFor.toLowerCase(), start);
            end = start + len;
            highlightWord(start, end, color);
            start = start + len;
         }
             } */
   }

   /**
    * highlights one occurrence of a model concept
    */
   public void highlightOneInstance(String searchFor, Color color) {
      if (searchFor==null || searchFor.length()==0) return;
      int start=EditorTextPane.getSelectionStart();
      int end=EditorTextPane.getSelectionEnd();
      highlightWord(start, end, color);
   }

   private void unhighlightAllInstances(String searchFor) {
      String base=getEditorTextPane().getText().toLowerCase();
      int len=searchFor.length();
      int result=0;
      int end=0;
      int start=0;

      if (len > 0) {

         while (base.indexOf(searchFor.toLowerCase(), start) >= 0) {
            result++;
            start=base.indexOf(searchFor.toLowerCase(), start);
            end=start + len;
            unhighlightWord(start, end);
            start=start + len;
         }
      }
   }

   class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
      public MyHighlightPainter(Color color) {
         super(color);
      }
   }
   private void highlightWord(int start, int end, Color color) {
      // An instance of the private subclass of the default highlight painter
      Highlighter.HighlightPainter myHighlightPainter=new MyHighlightPainter(
         color);

      Highlighter hilite=getEditorTextPane().getHighlighter();
      try {
         hilite.addHighlight(start, end, myHighlightPainter);
      } catch (BadLocationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private void unhighlightWord(int start, int end) {
      Highlighter hilite=getEditorTextPane().getHighlighter();
      Highlighter.Highlight[] highlights=hilite.getHighlights();
      for (int i=0; i < highlights.length; i++) {
         Highlighter.Highlight h=highlights[i];
         if ((h.getStartOffset() == start) && (h.getEndOffset() == end)) {
            hilite.removeHighlight(h);
         }
      }
   }

   private Gui() {
      addPopupMenuListener(new MyPopupMenuListener());
      addAddClassAction(new AddClassAction());
      addAddAttributeAction(new AddAttributeAction());
      addAddAggregationAction(new AddAggregationAction());
      addAddAssociationAction(new AddAssociationAction());
      addAddAssociationClassAction(new AddAssociationClassAction());
      addAddInheritanceAction(new AddInheritanceAction());
      addAddMultiplicityAction(new AddMultiplicityAction());
      addAddRoleAction(new AddRoleAction());
      //addClassifyListener(new ClassifyListener());
      //addIdentifyListener(new IdentifyListener());
      //addEditListener(new EditListener());
      getGenerateButton().addActionListener(new GenerateListener());

      File tmp=new File("tmp");
      if (!tmp.exists()) {
         if (!tmp.mkdir()) {
            String curDir=System.getProperty("cur.dir");
            MyErrorMsg.show("Cannot create " + curDir + "\tmp directory." +
               " Pease create the directory manually.");
         }
      }
      File texdocFile=new File("tmp/texdoc.txt");
      if (texdocFile.exists()) { // use it to initial editor pane
         try {
            BufferedReader br=new BufferedReader(new FileReader(
               "tmp/texdoc.txt"));
            String text="";
            String line="";
            while ((line=br.readLine()) != null) {
               text+=line + "\n";
            }
            EditorTextPane.setText(text);
            EditorTextPane.setCaretPosition(0);
            br.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
         texdocFile.delete();
      }

      File conceptListFile=new File("tmp/conceptlist.dat");
      if (conceptListFile.exists()) { // use it to initial domain concept list
         ArrayList concepts=(ArrayList) PersistenceMgr.load(new ArrayList(),
            "tmp/conceptlist.dat");
         DomainModel.getInstance().setConcepts(concepts);
         String[] mcArray=new String[concepts.size()];
         for (int i=0; i < concepts.size(); i++) {
            mcArray[i]=((ModelConcept) concepts.get(i)).toString();
         }
         setModelConceptList(mcArray);
      }
      conceptListFile.delete();
      if (this.DomainConceptList.getModel().getSize() == 0) {
         this.EditButton.setEnabled(false);
         this.RemoveButton.setEnabled(false);
         this.GenerateButton.setEnabled(false);
      }
   }

   public void windowClosing(WindowEvent e) {
      if (e.getID() == WindowEvent.WINDOW_CLOSING && !stateSaved) {
         int option=JOptionPane.showConfirmDialog(jFrame,
            "Do you want to save the work?",
            "Save Work Dialog", JOptionPane.YES_NO_OPTION,
            JOptionPane.YES_OPTION);
         if (option == JOptionPane.YES_OPTION) {
            saveState();
         }
      }
      jFrame.dispose();
      System.exit(0);
   }
}
