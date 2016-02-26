package util;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:
 * @author
 * @version 1.0
 */

import java.io.*;
import java.util.*;

public class QuickFixes
{

   public QuickFixes()
   {
   }
   public static void fixTemplateClassName()
   {
      try
      {
         BufferedReader br=new BufferedReader(new FileReader("tmp/OOT_Classname"));
         String line="";
         ArrayList al=new ArrayList();
         while ((line=br.readLine())!=null)
            al.add(line);
         br.close();
         for(int i=0; i<al.size(); i++)
         {
            line=(String)al.get(i);

         }
         PrintWriter pw=new PrintWriter(new FileWriter("tmp/OOT_Classname"));


      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
   }



   public static void main(String[] args)
   {
      QuickFixes quickFixes1 = new QuickFixes();
   }
}
