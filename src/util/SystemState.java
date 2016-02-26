package util;

public class SystemState
{
  //File Starts

  //Display Booleans
  public static boolean show_test_sched;
  public static boolean show_Ripple_effect;
  public static boolean old_show_test_sched;
  public static boolean old_show_Ripple_effect;

  public static String install_path;
  public static String browser_path;

  public static boolean newcolors;
 
  public static boolean javaselect;
  public static boolean isselected;
  public static boolean itemselected;

  static
  {
    javaselect=true;//Should be replace with config file loader
    isselected=false;//Has something been selected?
    itemselected=false;
  }


}
