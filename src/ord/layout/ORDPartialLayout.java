/********************************************************
ORDLayout
*********************************************************/
package ord.layout;
import util.*;
import java.util.*;
import java.awt.*;
import java.io.*;



//OOC Convert the partial layout to a transform

public class ORDPartialLayout
{

  static Hashtable partialClasses=null;
  static char [] box_state;

  static
  {
    partialClasses=new Hashtable();
  }

  public static void main(String [] args) throws Exception
  {
    try
    {
      main();
    }
    catch (Exception e)
    {
      throw e;
    }

  }

  public static void byClassname() throws Exception
  {
    try
    {
       char [] new_box_state = new char[4];
       new_box_state[0]='I';
       new_box_state[1]='A';
       new_box_state[2]='F';
       new_box_state[3]='J';
       box_state=new_box_state;
       readPartialClasses(new File("tmp/partialClasses.dat"));
       filterClassnameFile(new File("tmp/OOT_Classname"),
                           new File("tmp/partialOOT_Classname"));
       filterRelationFile(new File("tmp/OOT_Relation_File"),
                          new File("tmp/partialOOT_Relation_File"));
    }
    catch (Exception e)
    {
      throw e;
    }
  }

  public static void byRelation(char [] new_box_state) throws Exception
  {
    try
    {
       box_state=new_box_state;
       readPartialClasses(new File("tmp/partialClasses.dat"));
       filterClassnameFile(new File("tmp/OOT_Classname"),
                           new File("tmp/partialOOT_Classname"));
       filterRelationFile(new File("tmp/OOT_Relation_File"),
                          new File("tmp/partialOOT_Relation_File"));
    }
    catch (Exception e)
    {
      throw e;
    }
  }


  public static void main() throws Exception
  {
    try
    {
       readPartialClasses(new File("tmp/partialClasses.dat"));
       filterClassnameFile(new File("tmp/OOT_Classname"),
                           new File("tmp/partialOOT_Classname"));
    }
    catch (Exception e)
    {
      throw e;
    }

  }


//======================================================================
   public static void readPartialClasses(File f) throws Exception
   {
    // //Debug.println("ORDPartialLayout.readPartialClasses()");

     AsciiDataInputStream in=null;
     String sClassName=null;

     try
     {
       in=new AsciiDataInputStream(f);
       partialClasses.clear();
       while(!in.EOF())
       {
         sClassName=in.readClassname();
        // //Debug.println(sClassName);
         partialClasses.put(sClassName,sClassName);
       }
     }
     catch(IOException err)
     {
       err.printStackTrace();
     }
   }
//----------------------------------------------------------------------
  public static void filterClassnameFile(File f,File g) throws Exception
  {
   // //Debug.println("ORDPartialLayout.filterClassnameFile()");

    AsciiDataInputStream in=null;
    AsciiDataOutputStream out=null;

    String sClassName,sClassFile;
    String classOrInterface;
    int iBeginNum,iEndNum;
    int serialNo=0;
    try
    {
      in =new AsciiDataInputStream(f);
      out=new AsciiDataOutputStream(g);
      while(!in.EOF())
      {
        sClassName       =in.readClassname();
        classOrInterface =in.readString();
        iBeginNum        =in.readInt();
        iEndNum          =in.readInt();
        sClassFile       =in.readString();

        Debug.print("["+sClassName+"]");
        if(partialClasses.containsKey(sClassName))
        {
          Debug.print("is found.");
          out.writeClassname(sClassName);
          out.writeBlank();
          out.writeString(classOrInterface);
          out.writeBlank();
          out.writeInt(iBeginNum);
          out.writeBlank();
          out.writeInt(iEndNum);
          out.writeBlank();
          out.writeString(sClassFile);
          out.writeEOL();
        }
        else
        {
          Debug.print("is not found.");
        }
     // Debug.println();
      }
      out.close();
    }
    catch(Exception error)
    {
      throw error;
    }
  }


public static void filterRelationFile(File f,File g) throws Exception
{
   // Debug.println("ORDPartialLayout.filterRelationFile()");

    AsciiDataInputStream in=null;
    AsciiDataOutputStream out=null;

    String sClassFrom,sClassTo;
    char rel_type;
    int rel_count=0;
    StringBuffer rel_temp=new StringBuffer();
    try
    {
      in =new AsciiDataInputStream(f);
      out=new AsciiDataOutputStream(g);
      while(!in.EOF())
      {
        sClassFrom       =in.readClassname();
        sClassTo         =in.readClassname();
        rel_type         =in.readChar();
        rel_temp=new StringBuffer();
        if(rel_type=='F')
        {
          rel_count        =in.readInt();
          for(int loop=0;loop<rel_count;loop++)
          {
            String temp=in.readString();
            rel_temp.append(" "+temp);
          }
        }

        for (int loop2=0;loop2<4;loop2++)
        {
          if (box_state[loop2]==rel_type)
          {
            out.write(sClassFrom);
            out.writeBlank();
            out.write(sClassTo);
            out.writeBlank();
            out.write(rel_type);
            out.writeBlank();
            if(rel_type=='F')
            out.writeInt(rel_count);
            if(rel_count>0)
              out.writeString(rel_temp.toString());
            out.writeEOL();
          }
          else
          {
            Debug.print("is not found.");
          }
        }

       // Debug.println();
      }
      out.close();
    }
    catch(Exception error)
    {
      throw error;
    }
  }

}




