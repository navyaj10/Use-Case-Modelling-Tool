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

public final class CryptoLoader
{
   /**
      Constructs a crypto  loader.
      @param k the decryption key
   */
   public CryptoLoader(int k)
   {
    if (!util.CalCheck.getInstance().isValid())
    {
      util.OOTErrorMsg.show("Invalid license or license has expired. ORD will exit.");
      System.exit(1);
    }
      key = k;
   }

   /**
      Loads and decrypt the file bytes.
      @param name the class name
      @return an array with the file bytes
   */
   public byte[] loadBytes(String name)
      throws IOException
   {
      InputStream in = null;
      try
      {
         in = util.JarRd1.getInputStream(name);
         ByteArrayOutputStream buffer
            = new ByteArrayOutputStream();
         int ch;
         while ((ch = in.read()) != -1)
         {
            byte b = (byte)(ch - key);
            buffer.write(b);
         }
         in.close();
         return buffer.toByteArray();
      }
      finally
      {
         if (in != null)
            in.close();
      }
   }
   private int key;
}
