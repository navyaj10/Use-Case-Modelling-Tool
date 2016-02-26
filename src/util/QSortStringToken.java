







/*



Given that a table is represented as a Vector of strings,

in which each column is deliminated by a blank,

create a sorter with the given column number (first=1,second=2,etc)

for sorting.



Example:



QSortStringToken qsst=new QSortStringToken(2);// Second Column



...



Vector v=new Vector(1);



...



v.addElement("Alpha 24");

v.addElement("Beta  6");

v.addElement("Gamma 12");



the Vector now is in the order of

["Alpha 24" "Beta  6" "Gamma 12"]





But after a call of

qsst.sort(v);



the Vector now has in the order of

["Beta  6" "Gamma 12" "Alpha 24"]







*/

















package util;//For use with ORD



import java.util.*;





public class QSortStringToken

{



//-------------------------------------------------------------

private int _field;



//-------------------------------------------------------------



public QSortStringToken(int new_field)

{

  _field=new_field;

}

//-------------------------------------------------------------



public void setField(int new_field)

{

  _field=new_field;

}

//-------------------------------------------------------------



  public String nthToken(StringTokenizer st)

  {

    int column_count=0;

    String result=null;

    while(column_count<_field)

    {

      if (st.hasMoreTokens())

        result=st.nextToken();

      column_count=column_count+1;

    }

    return result;

  }



//-------------------------------------------------------------

  private boolean _greaterThanOrEqual(Object A,Object B)

  {

    boolean result=false;



    String token1=A.toString();

    String token2=B.toString();

    String s1=null;

    String s2=null;



    StringTokenizer stA=new StringTokenizer(token1);

    StringTokenizer stB=new StringTokenizer(token2);



    s1=nthToken(stA);

    s2=nthToken(stB);



    if(s1.compareTo(s2)<=0)

      result=true;

    return result;

  }

//-------------------------------------------------------------

  private boolean _lessThanOrEqual(Object A,Object B)

  {

    boolean result=false;



    String token1=A.toString();

    String token2=B.toString();

    String s1=null;

    String s2=null;



    StringTokenizer st1=new StringTokenizer(token1);

    StringTokenizer st2=new StringTokenizer(token2);



    s1=nthToken(st1);

    s2=nthToken(st2);





    if(s1.compareTo(s2)>=0)

      result=true;

    return result;

  }

//-------------------------------------------------------------

  private void _swap(Vector A, int i,int j)

  {

    Object Ai=A.elementAt(i);

    Object Aj=A.elementAt(j);

    A.setElementAt(Aj,i);

    A.setElementAt(Ai,j);

  }

//-------------------------------------------------------------

  private int _partition(Vector A, int p, int r) // Partition(A,p,r)

  {



  Object x = A.elementAt(p);                   // 1 x = A[p]

  int i = p-1;                                 // 2 i = p - 1

  int j = r+1;                                 // 3 j = r + 1





  while (true)                                 // 4 while TRUE

  {



  while (true)                                 // 5  repeat

  {

    j = j -1;                                  // 6   j = j - 1

    if (_greaterThanOrEqual(A.elementAt(j),x)) // 7  until A[j]>= x

      break;

  }



  while (true)                                 // 8  repeat

  {

    i =i + 1;                                  // 9   i = i + 1

    if (_lessThanOrEqual(A.elementAt(i),x))    // 10  until A[i]<=  x

      break;

  }



  if (i < j)                                   // 11 if i < j

    _swap(A,i,j);                              // 12 then swap(A[i], A[j])

  else

    return j;                                  // 13 else return j



  }

  }

//-------------------------------------------------------------

  public void sort(Vector v,int p,int r)

  {

    if (p>=r)

    {

      return;

    }

    int q=0;

    q=_partition(v,p,r);

    sort(v,p,q);

    sort(v,q+1,r);

  }

//-------------------------------------------------------------

  public void display(Vector v)

  {

   // Debug.println("-------------------");

    Enumeration e1=v.elements();

    while (e1.hasMoreElements())

    {

      Object now=e1.nextElement();

     // Debug.println(now.toString());

    }

  }

//-------------------------------------------------------------

}

