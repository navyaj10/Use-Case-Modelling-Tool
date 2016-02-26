package util;

import java.util.Hashtable;

public class IntHash extends Hashtable
{

  public int length=-1;

  public IntHash(int i)
  {
   super(i);
  }  

  public IntHash()
  {
   super();
  }  

  public void put(int key,Object value)
  {
    Integer obj_key=new Integer(key);
    super.put (obj_key,value);
    if (key>length) length=key+1;
  }

  public Object get(int key)
  {
    Object value=null;
    Integer obj_key=new Integer(key);
    value=super.get (obj_key);
    return value;
  }

  public int getInt(int key)
  {
    Object value=null;
    Integer obj_key=new Integer(key);
    value=super.get (obj_key);

    if (value==null) value=new Integer(0);

    Integer val1=(Integer)value;
    int int_value=val1.intValue();
    return int_value;
  }


  public void putInt(int key,int value)
  {
    Integer obj_key=new Integer(key);
    Integer obj_value=new Integer(value);
    super.put (obj_key,obj_value);
    if (key>length) length=key+1;
  }






}
