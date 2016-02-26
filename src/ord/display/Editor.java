/**
*ClassName: Editor.java
*
*Author     Update      Description
*------------------------------------------------------------------------
*Kelvin Phan 4-17-2000  -Added readFromString(String[], String1, String2)
*            to display methods or attributes on the text
*            editor
*            .String[] is the list of Attributes or Methods
*            .String1 = className.java
*            .String2 = "Method(s)" or "Attributes(s)"
*            -Modify the Editor.java by adding the attribute
*            boolean flag.  When the object is instanstiate
*            the flat is set to true and when the object is
*            close or destroy.  This is done because of the OMTClass.java
*            The OMTClass.java use the Editor.java to display
*            the text Editor and when the user close the Editor,
*            the new code make sure the editor will be display
*            again.  The old code would not be able to do it
*/

package ord.display;
import java.awt.*;
import java.io.*;
import java.awt.event.*;


public class Editor extends Frame
                    implements WindowListener, ActionListener


{
   TextArea ta;
   MenuItem menuItemExit;
   MenuItem menuItemPrint;
   String fileName;
  public boolean flag;

   public void showEditor()
   {
     Toolkit tk=getToolkit();
     Dimension screensize=tk.getScreenSize();

     int new_width=screensize.width*500/1000;
     int new_height=screensize.height*500/1000;
     int new_xpos=screensize.width*275/1000;
     int new_ypos=screensize.height*275/1000;
     Point new_location=new Point(new_xpos,new_ypos);

     setTitle(fileName);
     /*Need this set of size/view/size to fix pc screen start*/
     setSize(10,10);
     setLocation(new_location);
     setVisible(true);
     setSize(new_width,new_height);
   }



   Editor(int rows,int columns)
   {
      setLayout(new BorderLayout());

    //Added by Kelvin Phan
    //4-28-00
    //set flag to true when initialize it
    flag = true;
      //Layout is BorderLayout, Display is resizeable

      ta=new TextArea(rows,columns);

ta.setEditable(false);
ta.setForeground(Color.black);
ta.setBackground(Color.white);

MenuBar mb=new MenuBar();
Menu menuFile=new Menu("File");
menuItemExit=new MenuItem("Exit");
menuItemPrint=new MenuItem("Print");
menuFile.add(menuItemExit);
menuItemExit.addActionListener(this);
mb.add(menuFile);
setMenuBar(mb);

      add("Center",ta);
      addWindowListener(this);
      setSize(400,400);
   }


   public void setColumns(int columns){
      ta.setColumns(columns);
   }
   public void setRows(int rows) {
      ta.setRows(rows);
   }
   public int getColumns() {
      return ta.getColumns();
   }
   public int getRows() {
      return ta.getRows();
   }

   public void gotoLine(int nth) {
      int lines=0;
      int n;
      String text=ta.getText();
      for(n=0;n<text.length();n++) {
         if(text.charAt(n)=='\n')
            lines++;
         if(lines>=nth-1)
            break;
      }
      ta.select(n+1,n+1);
   }
   public void readFromFile(File file) { //Gongjing liu
      int n;
      fileName=file.getAbsolutePath();
      String s;
      FileInputStream in=null;
    byte buffer[]=new byte[1024];
      try{

         in=new FileInputStream(file);
         while((n=in.read(buffer))!=-1){
            s=new String(buffer,0,n);
            ta.append(s);
         }
         in.close();
      }

//--Exception Set------------------------------------
catch (IOException err) //new exception handling installed Gongjing Liu
{
  err.printStackTrace();
   }
//---------------------------------------------------
   }



  /**Added by Kelvin Phan
   * readFromString(String[], String clsName, String ATTorMethName)
   * String[]= the list of String of methods or attributes
   * String clsName = ClassName
   * String ATTorMethName = "Method(s)" or "Attribute(s)"
   * Display Format is below
   * [ClassName.java
   * ================
   * ATTorMethName:
   *
   * String[] = list here
   * ]
   *
  */
    public void readFromString(String [] aString, String className, String name)
    {
      ta.append(className);
      ta.append(".java\n");
      for(int i=0; i < className.length()+ 4; i++)
      ta.append("=");
      ta.append("\n");
      ta.append(name);
      ta.append(":\n\n");
      for (int i=0; i< aString.length; i++)
      {
        ta.append(aString[i]);
        ta.append("\n");
      }
    }


   public void windowOpened(WindowEvent e){}
   public void windowClosed(WindowEvent e){}
   public void windowClosing(WindowEvent e){
      if(e.getSource()==this)
    {
    flag = false;
        dispose();

    }
   }
   public void windowIconified(WindowEvent e){}
   public void windowDeiconified(WindowEvent e){}
   public void windowActivated(WindowEvent e){}
   public void windowDeactivated(WindowEvent e){}


   public void actionPerformed(ActionEvent event)
   {
       if (event.getSource()==menuItemExit)
       {
            doMenuItemExitAction(event);
       }
   }

//355.78
   void doMenuItemExitAction(ActionEvent event)
   {
         dispose();
   }


}

