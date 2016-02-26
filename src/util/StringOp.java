package util;

import java.util.*;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:
 * @author
 * @version 1.0
 */

public class StringOp {

   public StringOp() {
   }
   public static String[] toArray(String s)
   {
      StringTokenizer str=new StringTokenizer(s);
      ArrayList al=new ArrayList();
      while (str.hasMoreTokens()) al.add(str.nextToken());
      String[] strArray=new String[al.size()];
      for(int i=0; i<al.size(); i++) strArray[i]=(String)al.get(i);
      return strArray;
   }
   public static void main(String[] args) {
      String[] strArray=StringOp.toArray(args[0]);
      System.out.println(strArray[0]+"       "+strArray[1]);
   }
   public static String substr(String str, String str1, String str2)
   {
      //System.out.println("Entered StringOp.substr");
      StringBuffer stringBuffer = new StringBuffer (str);
      int k = 0; // search begin index
      int b = 0; // str1 position
      int leng1 = str1.length();
      int leng2 = str2.length();
      String tmpStr=str;
      while ((b=tmpStr.indexOf(str1, k))>=0)
      {
         stringBuffer.replace(b,b+leng1,str2);
         tmpStr=stringBuffer.toString();
         k =b+leng2;
      }
      return stringBuffer.toString();
   }
}
