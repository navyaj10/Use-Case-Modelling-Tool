package edu.uta.gui;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

public class PopupMenu extends JPopupMenu {
   static final long serialVersionUID=1L;
   private JMenu NounMenu=null;
   private JMenu AdjectiveMenu=null;
   private JMenu XofYMenu=null;
   private JMenu TransitiveVerbMenu=null;
   private JMenu NumericMenu=null;
   private JMenu PossessionMenu=null;
   private JMenu ConsistOfMenu=null;
   private JMenu ContainmentMenu=null;
   private JMenu XisaYMenu=null;
   private Action classAction=null;
   private Action attributeAction=null;
   private Action roleAction=null;
   private Action associationAction=null;
   private Action associationClassAction=null;
   private Action multiplicityAction=null;
   private Action aggregationAction=null;
   private Action inheritanceAction=null;

   public void createMenu() {
      this.add(getNounMenu());
      this.add(getXofYMenu());
      this.add(getTransitiveVerbMenu());
      this.add(getAdjectiveMenu());
      this.add(getNumericMenu());
      this.add(getPossessionMenu());
      this.add(getConsistOfMenu());
      this.add(getContainmentMenu());
      this.add(getXisaYMenu());
   }

   public void setClassAction(Action a) {
      classAction=a;
   }

   public void setAttributeAction(Action a) {
      attributeAction=a;
   }

   public void setRoleAction(Action a) {
      roleAction=a;
   }

   public void setAssociationAction(Action a) {
      associationAction=a;
   }
   public void setAssociationClassAction(Action a) {
      associationClassAction=a;
   }

   public void setMultiplicityAction(Action a) {
      multiplicityAction=a;
   }

   public void setAggregationAction(Action a) {
      aggregationAction=a;
   }

   public void setInheritanceAction(Action a) {
      inheritanceAction=a;
   }

   /**
    * This method initializes NounMenu
    *
    * @return javax.swing.JMenu
    */
   private JMenu getNounMenu() {
      if (NounMenu==null) {
         NounMenu=new JMenu();
         NounMenu.setText("Noun");
         NounMenu.add(classAction);
         NounMenu.add(attributeAction);
      }
      return NounMenu;
   }

   /**
    * This method initializes AdjectiveMenu
    *
    * @return javax.swing.JMenu
    */
   private JMenu getAdjectiveMenu() {
      if (AdjectiveMenu==null) {
         AdjectiveMenu=new JMenu();
         AdjectiveMenu.setText("Adjective");
         AdjectiveMenu.add(attributeAction);
      }
      return AdjectiveMenu;
   }

   /**
    * This method initializes XofYMenu
    *
    * @return javax.swing.JMenu
    */
   private JMenu getXofYMenu() {
      if (XofYMenu==null) {
         XofYMenu=new JMenu();
         XofYMenu.setText("X of Y");
         XofYMenu.add(attributeAction);
         XofYMenu.add(roleAction);
      }
      return XofYMenu;
   }

   /**
    * This method initializes TransitiveVerbMenu
    *
    * @return javax.swing.JMenu
    */
   private JMenu getTransitiveVerbMenu() {
      if (TransitiveVerbMenu==null) {
         TransitiveVerbMenu=new JMenu();
         TransitiveVerbMenu.setText("Transitive Verb");
         TransitiveVerbMenu.add(associationAction);
         TransitiveVerbMenu.add(associationClassAction);
      }
      return TransitiveVerbMenu;
   }

   /**
    * This method initializes NumericMenu
    *
    * @return javax.swing.JMenu
    */
   private JMenu getNumericMenu() {
      if (NumericMenu==null) {
         NumericMenu=new JMenu();
         NumericMenu.setText("Numeric");
         NumericMenu.add(attributeAction);
         NumericMenu.add(multiplicityAction);
      }
      return NumericMenu;
   }

   /**
    * This method initializes PossessionMenu
    *
    * @return javax.swing.JMenu
    */
   private JMenu getPossessionMenu() {
      if (PossessionMenu==null) {
         PossessionMenu=new JMenu();
         PossessionMenu.setText("Possession");
         PossessionMenu.add(aggregationAction);
         PossessionMenu.add(attributeAction);
      }
      return PossessionMenu;
   }

   /**
    * This method initializes ConsistOfMenu
    *
    * @return javax.swing.JMenu
    */
   private JMenu getConsistOfMenu() {
      if (ConsistOfMenu==null) {
         ConsistOfMenu=new JMenu();
         ConsistOfMenu.setText("Consist Of");
         ConsistOfMenu.add(aggregationAction);
      }
      return ConsistOfMenu;
   }

   /**
    * This method initializes ContainmentMenu
    *
    * @return javax.swing.JMenu
    */
   private JMenu getContainmentMenu() {
      if (ContainmentMenu==null) {
         ContainmentMenu=new JMenu();
         ContainmentMenu.setText("Containment");
         ContainmentMenu.add(associationAction);
         ContainmentMenu.add(aggregationAction);
      }
      return ContainmentMenu;
   }

   /**
    * This method initializes XisaYMenu
    *
    * @return javax.swing.JMenu
    */
   private JMenu getXisaYMenu() {
      if (XisaYMenu==null) {
         XisaYMenu=new JMenu();
         XisaYMenu.setText("X is a Y");
         XisaYMenu.add(inheritanceAction);
      }
      return XisaYMenu;
   }
}
