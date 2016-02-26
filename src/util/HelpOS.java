package util;



import java.io.*;





public class HelpOS

{

  static String OpSystem;  // CT (client machine's operating system name)

  static String file;

  static

  {

    file=null;

  }

  public static void setFile(String s)

  {

    file=s;

  }

  public static void openHelp()

  {

    //String prog;

    Process p;

    Runtime runtime = Runtime.getRuntime();

    //String fs = System.getProperty("file.separator");

    //String ipath=SystemState.install_path;

    //String bpath=SystemState.browser_path;

    //String target=ipath

    // +fs+"doc"

    // +fs+file;

    try

    {

                p = runtime.exec("showhelp.bat");
    }

    catch (IOException e1)

    {

      e1.printStackTrace();

    }

  }

}





