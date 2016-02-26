package util;
import java.io.*;


/*  Through a glass darkly 

  AsciiDataOutputStream-
     (Actually, it's a writer, not a stream.
      But then again, the AsciiDataInputStream is
      a StreamTokenizer, not a stream.
      
      This is just kept for mirror naming
      )


  Instructions for use:

       Create an AsciiDataOutputStream passing the
         filename, java.io.File, or java.io.FileOutputStream
         in the constructor.

         AsciiDataOutputStream throws an IOException.

       Once the AsciiDataOutputStream is instantiated,
         the following write commands are available.

        public void writeEOL()-Print an end-of-line character
                               to the file

        public void writeBlank()-Print a blank to the file.

        public void writeString(String value)-Print a string to file.

        public void writeClassname(String value)-Print a classname to
          a file: Duplicated to mirror AsciiDataInputStream.
                
        public void writeInt(int value)-write a single integer to file.

        public void writeChar(char value)-Write a single character to file.

      

    Finally, and importantly, the AsciiDataOutputStream inherits it's
       'close()' command from PrintWriter.  This close must be called
       in order to save the contents to disk.




*/
 



public class AsciiDataOutputStream extends PrintWriter
{
   public AsciiDataOutputStream(String fileName) throws IOException
   {
      this(new File(fileName));        
   }

   public AsciiDataOutputStream(File file) throws IOException 
   {
      super(new FileOutputStream(file));
   }

   public AsciiDataOutputStream(FileOutputStream file) throws IOException 
   {
      super(file);
   }

   public void writeEOL() 
   {
      println();
   }

   public void writeString(String value)
   {
      print(value);
   }

   public void writeChar(char value)
   { 
      print(value);
   }

   public void writeInt(int value)
   {
      print(value);
   }

  public void writeClassname(String value)
  {
     print(value);
  }

   public void writeBlank()
   {
      print(" ");
   }



  public static void main(String [] args)
  {

    try
    {
     AsciiDataOutputStream adow=new AsciiDataOutputStream("test.txt");
     adow.writeString("This is a string");
     adow.writeEOL();
     adow.writeInt(5);
     adow.writeEOL();
     adow.writeChar('?');
     adow.writeBlank();
     adow.writeClassname("Classname");
     adow.writeEOL();
     adow.writeEOL();
     adow.close();
    }
    catch (Exception e)
    {
       System.out.println(e.toString());
    }








  }




}
