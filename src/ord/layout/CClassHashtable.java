package ord.layout;
import java.util.*;
class CClassHashtable extends Hashtable {
   public CClass get(String classname) {
      return (CClass)(super.get(classname));
   }
}


