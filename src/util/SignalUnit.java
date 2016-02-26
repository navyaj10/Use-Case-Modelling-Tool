package util;

import java.util.Observable;

public class SignalUnit extends Observable
{

public void signal(String s)
{
   setChanged();
   notifyObservers(s);
}


}
