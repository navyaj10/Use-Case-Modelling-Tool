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

public class GetStreamTokenizer
{
   public GetStreamTokenizer()
   {
   }
   public static StreamTokenizer getStreamTokenizer(String fileName)
   {
      StreamTokenizer st=null;
      try
      {
         FileReader fr=new FileReader(fileName);
         st=new StreamTokenizer(fr);
         st.wordChars(32,126);
      }
      catch (FileNotFoundException e)
      {
         return null;
      }
      return st;
   }
}
