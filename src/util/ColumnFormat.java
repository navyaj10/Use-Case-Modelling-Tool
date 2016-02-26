/*


ColumnFormat.java


Who                  Date                     Note
Eric Becker          May 10,1999              Created



Create a ColumnFormat object, giving it the width of the
columns and the number of columns.  The string given
at the 'String format(String)' member function will  be tokenized
and sent back to meet the column constraints.

Unfortunatley, all columns are the same width.  Perhaps this
can be handled in future.

Exampe: ColumnFormat cf = new ColumnFormant(20,3);//20 char wide columns,
                                                  // 3 columns

        String s1=("Apples 20 39"
        String s2=("Pomegranets 14 99");
        String s3=cf(s1);
        String s4=cd(s2);
 
       Displaying s3 & s4 reveal:

        Apples              20                  39
        Pommegranets        14                  99


*/


package util;

import java.io.*;
import java.util.*;


public class ColumnFormat
{
//----------------------------------------------------------
protected int _width;
protected int _columns;


//----------------------------------------------------------
public ColumnFormat(int new_width,int new_columns)
{
  _width=new_width;
  _columns=new_columns;
}
//----------------------------------------------------------
private String _nthToken(StringTokenizer st,int field)
  {
    int column_count=0;
    String result=null;
    while(column_count<field)
    {
      if (st.hasMoreTokens())
        result=st.nextToken();
      column_count=column_count+1;
    }
    return result;
  }
//----------------------------------------------------------
public String format(String target)
{
  StringBuffer new_line=new StringBuffer();
  StringTokenizer st=new StringTokenizer(target);
  String token=null;

  int loop=0;

  for (loop=1;loop<=_columns;loop++)
  {
     token=_nthToken(st,loop);
     new_line.append(token);
     new_line.setLength(_width*loop);
  }
  new_line=_stuffWhiteSpace(new_line);
  return new_line.toString();
}
//----------------------------------------------------------
private StringBuffer _stuffWhiteSpace(StringBuffer inbuf)
{
  int buf_length=inbuf.length();
  int traverse_loop=0;
  char temp_ch='0';
  char blank=(char)32;

  for (traverse_loop=0;traverse_loop<buf_length;traverse_loop++)
  {
    temp_ch=inbuf.charAt(traverse_loop);
    if (temp_ch==0)
    {
      inbuf.setCharAt(traverse_loop,blank);
    }
  }
  return inbuf;
}
//----------------------------------------------------------
}
