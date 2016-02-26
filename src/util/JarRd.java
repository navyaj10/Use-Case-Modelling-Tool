package util;

import java.util.jar.*;
import java.io.*;
import java.util.*;
//import config.*;

public class JarRd
{
   public static BufferedReader getBufferedReader(String fileName)
   {
      BufferedReader br=null;
      try
      {
         JarFile jf=new JarFile("ootworks_ord.jar");
         if (jf==null)
            return getFromFile(fileName);
         JarEntry je=new JarEntry(fileName);
         br=new BufferedReader (new InputStreamReader(jf.getInputStream(je)));
      }
      catch (NullPointerException e0)
      {
         br=getFromFile(fileName);
      }
      catch (IOException e)
      {
         br=getFromFile(fileName);
      }
      return br;
   }
   public static BufferedReader getFromFile(String fileName)
   {
      BufferedReader br=null;
         try
         {
            br=new BufferedReader (new FileReader(fileName));
         }
         catch (IOException e1)
         {
            util.OOTErrorMsg.show("Error reading "+fileName);
         }
         return br;
   }
   /*public static void main(String[] args)
   {
      BufferedReader br=getBufferedReader(args[0]);
      if (br==null) System.out.println("br==null");
   }*/
}
