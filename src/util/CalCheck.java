package util;
import java.util.*;
import java.io.*;

public final class CalCheck implements Serializable
{
   private static Calendar lock_date;
   private static Calendar start_date;
   private static Calendar current_date;
   private static Calendar lastRunDate;
   private static CalCheck instance;
  private CalCheck()
  {
    lock_date=(Calendar)(Calendar.getInstance().clone());
    current_date=(Calendar)Calendar.getInstance();
    start_date=(Calendar)(Calendar.getInstance().clone());
    start_date.set(2013,7,15);
    lock_date.set(2023,10,30);
  }
  public static CalCheck getInstance()
  {
   if (instance==null) instance = new CalCheck();
   return instance;
  }

  public static boolean isValid()
  {
    boolean front=current_date.before(lock_date);
    boolean behind=current_date.after(start_date);
    boolean goodClock=!Calendar.getInstance().before(lastRunDate);
    return (front&&behind&&goodClock);
  }
}
