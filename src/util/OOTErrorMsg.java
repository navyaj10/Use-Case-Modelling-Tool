package util;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
import javax.swing.*;

public class OOTErrorMsg {

  public OOTErrorMsg() {
  }
  public static void show(String msg)
  {
     JOptionPane.showMessageDialog(null,msg,"OOTWorks Error Message",
        JOptionPane.ERROR_MESSAGE);
  }
}
