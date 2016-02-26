package util;

import java.util.jar.*;
import java.io.*;
import java.util.*;
//import config.*;

public class JarRd1
{
   public static InputStream getInputStream(String fileName)
   {
      InputStream is=null;
      try
      {
         JarFile jf=new JarFile("ootworks_ord.jar");
         JarEntry je=new JarEntry(fileName);
         is=jf.getInputStream(je);
      }
      catch (NullPointerException e0)
      {
         util.OOTErrorMsg.show("Error reading "+fileName);
      }
      catch (IOException e)
      {
         util.OOTErrorMsg.show("Error reading "+fileName);
      }
      return is;
   }
   public static byte[] getByteArray(String fileName)
   {
      InputStream in = null;
      ByteArrayOutputStream buffer = null;
      try
      {
         in = getInputStream(fileName);
         buffer = new ByteArrayOutputStream();
         int ch;
         while ((ch = in.read()) != -1)
         {
            byte b = (byte)ch;
            buffer.write(b);
         }
         in.close();
      }
      catch (IOException e)
      {
         OOTErrorMsg.show("Could not find file "+fileName);
      }
      return buffer.toByteArray();
   }
   /*public static void main(String[] args)
   {
      FileInputStream fis=getFileInputStream(args[0]);
      if (fis==null) System.out.println("fis==null");
   }*/
}
