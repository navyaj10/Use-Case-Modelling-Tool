package util;

/**
 * Title:        OOTWorks
 * Description:
 * Copyright:    Copyright (c) 1993-2003
 * Company:
 * @author David Kung
 * @version 1.0
 */
import java.io.*;
import java.util.*;

public class FileFormatConverter
{

   public FileFormatConverter()
   {
   }
   public static void main(String[] args)
   {
      FileFormatConverter.cpp2javaOOT_Method();
   }
   public static void java2cppOOT_Attrib()
   {
      try
      {
         BufferedReader br=new BufferedReader(new FileReader("tmp/OOT_Attrib"));
         String line="";
         StringTokenizer st=null;
         ArrayList<String> al=new ArrayList<String>();
         String type="", name="";
         while ((line=br.readLine())!=null)
         {
            if (line.indexOf("<PACKAGE>")>0)
            {
               al.add(line);
               continue;
            }
            if (line.compareTo("")==0)
            {
               al.add(line);
               continue;
            }
            st=new StringTokenizer(line);
            if (st.countTokens()!=2)
            {
               util.OOTErrorMsg.show("Error reading tmp/OOT_Attrib file.");
               continue;
            }
            type=(String)st.nextToken();
            name=(String)st.nextToken();
            al.add(name+" "+type);
         }
         br.close();
         PrintWriter pw=new PrintWriter(new FileWriter("tmp/OOT_Attrib"));
         for(int i=0; i<al.size(); i++)
            pw.println((String)al.get(i));
         pw.flush();
         pw.close();
      }
      catch(IOException e)
      {
         util.OOTErrorMsg.show("I/O error converting tmp/OOT_Attrib file. "+e.getMessage());
      }
   }
   public static void cpp2javaOOT_Method()
   {
      try
      {
         BufferedReader br=new BufferedReader(new FileReader("tmp/OOT_Method"));
         String line="";
         StringTokenizer st=null;
         ArrayList<String> al=new ArrayList<String>();
         String[] tmpArray=null;
         String tmpStr="";
         while ((line=br.readLine())!=null)
         {
            if (line.indexOf("<PACKAGE>")>0)
            {
               al.add(line);
               continue;
            }
            if (line.compareTo("")==0)
            {
               al.add(line);
               continue;
            }
            st=new StringTokenizer(line);
            tmpArray=new String[st.countTokens()];
            for(int i=0; i<tmpArray.length; i++) tmpArray[i]=(String)st.nextToken();
            for(int j=3; j<tmpArray.length; j=j+2)
            {
               tmpStr=tmpArray[j];
               tmpArray[j]=tmpArray[j+1];
               tmpArray[j+1]=tmpStr;
            }
            tmpStr="";
            for(int k=0; k<tmpArray.length; k++)
               tmpStr+=tmpArray[k]+" ";
            al.add(tmpStr);
         }
         br.close();
         PrintWriter pw=new PrintWriter(new FileWriter("tmp/OOT_Method"));
         for(int i=0; i<al.size(); i++)
            pw.println((String)al.get(i));
         pw.flush();
         pw.close();
      }
      catch(IOException e)
      {
         util.OOTErrorMsg.show("I/O error converting tmp/OOT_Method file. "+e.getMessage());
      }
   }
}
