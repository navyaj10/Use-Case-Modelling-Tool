package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.TextArea;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class ObserverDialog extends Dialog implements Observer, WindowListener
{
   int count;
   int option;
   java.awt.TextArea textarea;
   public static void main(String[] args)
   {
      Frame f=new Frame("Observer Dialog");
      ObserverDialog od=new ObserverDialog(f);
      f.setSize(800,600);
      f.validate();
      f.setVisible(true);
   }
   public ObserverDialog(Frame f, String title)
   {
   this(f);
   setLocation(300,200);
   setBackground(Color.getColor("Control"));
   setTitle(title);
  }
  public ObserverDialog(Frame f)
  {
  super(f);
  setSize(600,150);
  int width=f.getWidth();
  int height=f.getHeight();
  int x=f.getX();
  int y=f.getY();
  setLocation(x+(int)(width/2-300), y+(int)(height/3-75));
  setLayout(new BorderLayout());
  count=0;
  textarea=new TextArea(50,50);
  textarea.setBackground(Color.white);
  textarea.setForeground(Color.black);
  textarea.setVisible(true);
  add(textarea,"Center");
  option=0;
 // Debug.println("Observable adding window listener");
  addWindowListener(this);
  }
  public ObserverDialog(Frame f,int new_option)
  {
  super(f);
  setSize(300,150);
  setLayout(new BorderLayout());
  count=0;
  textarea=new TextArea();
  textarea.setBackground(Color.white);
  textarea.setForeground(Color.black);
    switch (option)
    {
     case 0:
       {
       textarea.setVisible(true);
       add(textarea,"Center");
       break;
       }
    }
  }
/***************************************************************
***************************************************************/
  public void update(Observable a,Object b)
  {
     try {
      if (textarea.isVisible()) textarea.append(b.toString()+"\n");
      repaint();
     }
     catch (StringIndexOutOfBoundsException e)
     {
       e.printStackTrace();
     }
  }
/***************************************************************
***************************************************************/
  public void windowOpened(WindowEvent e){}
  public void windowClosing(WindowEvent e)
  {
   // Debug.println("ObservableDialog Closing");
    setVisible(false);
  }
  public void windowClosed(WindowEvent e) {}
  public void windowIconified(WindowEvent e){}
  public void windowDeiconified(WindowEvent e){}
  public void windowActivated(WindowEvent e){}
  public void windowDeactivated(WindowEvent e) {}
}
