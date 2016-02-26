package ord.layout;
import util.Debug;
class Relation {
   CClass from;
   CClass to;
   char type;
    public String fromRole="";
    public String toRole="";
    public String fromMultiplicity="";
    public String toMultiplicity="";
    public String name="";
   Relation(CClass from,CClass to,char type,String fromRole,String toRole,
      String fromMultiplicity,String toMultiplicity, String assocName){
     this.name=name;
     this.from=from;
      this.to=to;
      this.type=type;
      this.fromRole=fromRole;
      this.toRole=toRole;
      this.fromMultiplicity=fromMultiplicity;
      this.toMultiplicity=toMultiplicity;
      this.name=assocName;
   }
   public void display() {
     // Debug.println("from:" + from.name + "   to:" + to.name + "  type:" + type);
   }
}


