/*************************************************************************
Class name : AsciiDataInputStream

Purpose:
   This class provide the ability to read a charactor, number, and string
   from a file.

Constructors:
   AsciiDataInputStream(String);
   AsciiDataInputStream(File);

Methods:
   public boolean EOL();
   public boolean EOF();
   public String readString() throws EOFException,IOException;
   public char readChar() throws EOFException,IOException;
   public int readInt() throws EOFException,IOException;
   public void close() throws IOException;

Update: 03/03/1998
     EOF() : check if EOF
     EOL() : check if EOL
     #EOL will not be returned as a token,it will be consumed automaticly.

Update: 03/04/1998
     Treat the following charactors as normal charactors:
         : / \ . _
Update: 03/29/1998
     resetEOL() : reset isEOL variable;

Update: 05/01/2000: Matt Darland
     Treat () and [] as normal characters
***************************************************************************/




package util;
import java.io.*;
public class AsciiDataInputStream
{
   StreamTokenizer in=null;
   FileReader reader=null;
   boolean isEOF,isEOL;

/* Constructor */
   public AsciiDataInputStream(String fileName) throws IOException{
      this(new File(fileName));
   }
   public AsciiDataInputStream(File file) throws IOException {
      reader=new FileReader(file);
      in=new StreamTokenizer(reader);
      in.quoteChar('"');
      in.eolIsSignificant(true);
      in.wordChars(':',':');
      in.wordChars('/','/');
      in.wordChars('\\','\\');
      in.wordChars('.','.');
      in.wordChars('_','_');
      in.wordChars('~','~');// added by kung

      in.wordChars('<','<');//OKAY, TRY THIS. EWB
      in.wordChars('>','>');//OKAY, TRY THIS. EWB
      in.wordChars(',',',');//OKAY, TRY THIS. EWB
      in.wordChars('*','*');//OKAY, TRY THIS. EWB
      in.wordChars('&', '&'); // added by kung
      in.wordChars('[', ']');// Matt Darland: CSE4311 Spring 2000
      in.wordChars('(', ')');// Ditto

      checkEolEof();
   }

   public boolean EOL() {
      return isEOL;
   }
   public boolean EOF() {
      return isEOF;
   }
   private void checkEolEof() throws IOException{
      isEOL=false;
      isEOF=false;
      if(in.nextToken()==StreamTokenizer.TT_EOL){
         isEOL=true;
         while(in.nextToken()==StreamTokenizer.TT_EOL);
      }
      in.pushBack();
      if(in.nextToken()==StreamTokenizer.TT_EOF) {
         isEOF=true;
      }
      in.pushBack();
   }
   public void resetEOL() {
      isEOL=false;
   }

   public String readString() throws EOFException,IOException
   {
      String returnValue;
      if(in.nextToken()==StreamTokenizer.TT_EOF) {
         throw new EOFException();
      }else if(in.ttype != StreamTokenizer.TT_WORD &&
          in.ttype != '"') {
         throw new IOException("Not a string!");
      }else {
         returnValue=in.sval;
         checkEolEof();
         return returnValue;
      }
   }

   public char readChar() throws EOFException,IOException {
      String temp;
      char returnValue;
      if(in.nextToken()==StreamTokenizer.TT_EOF) {
         throw new EOFException();
      } else if(in.ttype != StreamTokenizer.TT_WORD) {
         throw new IOException("Not a char!");
      } else {
         temp=in.sval;
         if(temp.length()!=1) {
            throw new IOException("Not a char!");
         } else{
            returnValue=temp.charAt(0);
            checkEolEof();
            return returnValue;
         }
      }
   }

   public int readInt() throws EOFException,IOException {
      int returnValue;
      if(in.nextToken()==StreamTokenizer.TT_EOF)
         throw new EOFException();
      else if(in.ttype != StreamTokenizer.TT_NUMBER)
         throw new IOException("Not a number!");
      else {
         Double d=new Double(in.nval);
         returnValue=d.intValue();
         checkEolEof();
         return returnValue;
      }
   }


/*****************************************************************/
/*****************************************************************/
  public String readClassname() throws IOException
  {
    boolean control_nesting_done=false;
    boolean nested=false;
    int matching_count=0;
    int current_mark=0;
    String result="FAKE";
    String mark=null;
    StringBuffer result_buffer=new StringBuffer();

    mark=readString();
    matching_count=getMatchingCount(mark);
    result_buffer.append(mark);

    while (matching_count!=0)
    {
      mark=readString();
      result_buffer.append(" ");
      result_buffer.append(mark);
      matching_count=matching_count+getMatchingCount(mark);
    }

    result=result_buffer.toString();

    return result;
   }

   int getMatchingCount(String mark)
   {
     int string_index=0;
     int string_max=mark.length();
     int matching_count=0;
     for (string_index=0;string_index<string_max;string_index++)
     {
       int test_char=mark.charAt(string_index);
       if (test_char=='>')
         matching_count--;
       else if (test_char=='<')
         matching_count++;
     }
     return matching_count;
   }

/*****************************************************************/
/*****************************************************************/

   public void close ()
      throws IOException
   {
      reader.close();
   }

   public void skipComments (boolean b)
   {
      in.slashSlashComments (true);
      in.slashStarComments (true);
   }
}
