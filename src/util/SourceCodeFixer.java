package util;

import java.io.*;
import java.util.*;
import javax.swing.*;

/** This class adds a newline to the SINGLE_LINE_COMMENT that appear just
 *  before the EOF token and does not contain a newline.
 */
public class SourceCodeFixer
{
   private static ArrayList sourceCodes = new ArrayList();

   public static void fix (String fileName)
   {
      try
      {
         BufferedReader br = new BufferedReader (new FileReader (fileName));
         String line = "";
         while ((line = br.readLine()) != null)
            sourceCodes.add(line);
         br.close();
         line = (String)(sourceCodes.get(sourceCodes.size()-1));
         if (line.indexOf("//")>=0)
         {
            PrintWriter pw = new PrintWriter (new FileWriter (fileName, true));
            pw.println();
            pw.flush();
            pw.close();
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
   public static void main(String[] args)
   {
      fix (args[0]);
      System.exit(0);
   }
}
