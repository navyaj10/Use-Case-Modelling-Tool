package util;

import javax.swing.*;

public class MyErrorMsg
{
  public static void show (String msg)
  {
     JOptionPane.showMessageDialog(null, msg, "Error Message", JOptionPane.ERROR_MESSAGE);
  }
}