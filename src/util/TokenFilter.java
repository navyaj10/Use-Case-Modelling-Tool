package util;

import java.util.Enumeration;
import java.util.Vector;
import java.util.StringTokenizer;

public class TokenFilter
{

  protected String current_string=null;
  protected Vector filter_vector=null;
  protected String delim=null;

  public TokenFilter(String q,String new_delim)
  {
     current_string=q;
     delim=new_delim;
     filter_vector=new Vector(1);
     fillVector();
  }

  public TokenFilter(String q)
  {
     current_string=q;
     delim=" ";
     filter_vector=new Vector(1);
     fillVector();
  }

  public TokenFilter()
  {
    current_string=null;
    delim=" ";
    filter_vector=new Vector(1);
  }


  public void setDelim(String q)
  {
     delim=q;
     fillVector();
  }



  public void setString(String q)
  {
     current_string=q;
     fillVector();
  }

  private void fillVector()
  {
    StringTokenizer st=new StringTokenizer(current_string,delim,false);
    while (st.hasMoreTokens())
    {
      filter_vector.addElement(st.nextToken());
    }
  }


  public Enumeration elements()
  {
      Enumeration result=filter_vector.elements();
      return result;
  }


}
