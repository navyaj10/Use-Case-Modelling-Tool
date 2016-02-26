package util;

/**
 * <p>Title: Agile Unified Modeler</p>
 *
 * <p>Description: An Integrated Development Environment for OO analysis,
 * design, and more.</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Atruya Systems, Inc.</p>
 *
 * @author David Kung
 * @version 1.0
 */
public class CopyFile {
   public CopyFile() {
      super();
   }
   public static void main(String[] args) {
      final ProcessBuilder pb = new ProcessBuilder(
         "C:\\WINDOWS\\system32\\cmd.exe", "/c",
         "copy", args[0], args[1]);
      try {
         final Process p = pb.start();
         p.waitFor();
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}
