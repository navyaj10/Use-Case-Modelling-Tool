package util;

import java.io.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:
 * @author
 * @version 1.0
 */

public class ORDCPPLogger
{
   private PrintWriter pw;
   private static ORDCPPLogger instance;
   private ORDCPPLogger()
   {
      try
      {
         pw=new PrintWriter(new FileWriter("tmp/OOT_Cpplog_File", true));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
   public static ORDCPPLogger getInstance()
   {
      if (instance == null) instance=new ORDCPPLogger();
      return instance;
   }
   public void writeMsg(String msg)
   {
      pw.println(msg);
      pw.flush();
      pw.close();
   }
}
