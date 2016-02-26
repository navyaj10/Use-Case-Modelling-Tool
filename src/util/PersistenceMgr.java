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

public class PersistenceMgr
{
   public PersistenceMgr()
   {
   }
   public static void save(Hashtable ht, String fileName) {
      try {
         ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream
            (fileName));
         oos.writeObject(ht);
         oos.flush();
         oos.close();
      } catch (IOException e) { e.printStackTrace(); }
   }
   public static void save(String[] strArray, String fileName) {
      try {
         PrintWriter pw=new PrintWriter(new FileWriter(fileName));
         for(int i=0; i<strArray.length; i++)
            pw.println(strArray[i]);
         pw.flush();
         pw.close();
      } catch (IOException e) { e.printStackTrace(); }
   }
   //public static String[] load(String[] strArray, String fileName) {
   public static String load(String strLine, String fileName) {
      try {
    	  FileInputStream fstream = new FileInputStream(fileName);
    	  DataInputStream in = new DataInputStream(fstream);
    	  BufferedReader br = new BufferedReader(new InputStreamReader(in));

    	  String temp = "";

    	  while ((temp = br.readLine()) != null)   {
    		  strLine += temp;
    	  }

    	  fstream.close();
    	  in.close();
    	  br.close();
      } catch (IOException e) { e.printStackTrace(); return null; }
      return strLine;
   }
   public static Object load(ArrayList ht, String fileName) {
      try {
         ObjectInputStream ois=new ObjectInputStream(new
            FileInputStream(fileName));
         ht=(ArrayList)ois.readObject();
         ois.close();
      } catch (ClassNotFoundException e) { e.printStackTrace(); }
      catch (IOException e2) {
         ht=new ArrayList();
      }
      return ht;
   }
   public static void save(ArrayList ht, String fileName) {
      try {
         ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream
            (fileName));
         oos.writeObject(ht);
         oos.flush();
         oos.close();
      } catch (IOException e) { e.printStackTrace(); }
   }
   public static Object load(Hashtable ht, String fileName) {
      try {
         ObjectInputStream ois=new ObjectInputStream(new
            FileInputStream(fileName));
         ht=(Hashtable)ois.readObject();
         ois.close();
      } catch (ClassNotFoundException e) { e.printStackTrace(); }
      catch (IOException e2) {
         ht=new Hashtable();
      }
      catch (ClassCastException e3) {
         System.out.println("ClassCastException "+fileName);
      }
      return ht;
   }
   public static Object load(Vector ht, String fileName) {
      try {
         ObjectInputStream ois=new ObjectInputStream(new
            FileInputStream(fileName));
         ht=(Vector)ois.readObject();
         ois.close();
      } catch (ClassNotFoundException e) { e.printStackTrace(); }
      catch (IOException e2) {
         ht=new Vector();
      }
      return ht;
   }
   public static void save(Vector ht, String fileName) {
      try {
         ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream
            (fileName));
         oos.writeObject(ht);
         oos.flush();
         oos.close();
      } catch (IOException e) { e.printStackTrace(); }
   }
   public static ArrayList loadFromTextFile(String fileName) {
      ArrayList result=new ArrayList();
      try {
         BufferedReader br=new BufferedReader(new FileReader(fileName));
         String line="";
         while ((line=br.readLine())!=null) {
            result.add(line);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      return result;
   }
   public static void write2TextFile(ArrayList al, String fileName) {
      if (al==null || al.size()==0) return;
      try {
         PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
         for(int i=0; i<al.size(); i++)
            pw.println((String)al.get(i));
         pw.flush();
         pw.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
