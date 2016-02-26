package edu.uta.file.io;

import java.util.ArrayList;
import java.util.Iterator;

import edu.uta.model.Aggregation;
import edu.uta.model.Association;
import edu.uta.model.Inheritance;

public class WriteRelationFileCmd
    extends FileWriteCmd {
  FileWrite fwr;
  ArrayList<Inheritance> inheritanceList;
  ArrayList<Association> associationList;
  ArrayList<Aggregation> aggregationList;

  public WriteRelationFileCmd(FileWrite fwr,
                              ArrayList<Inheritance> inheritanceList,
                              ArrayList<Association> associationList,
                              ArrayList<Aggregation> aggregationList) {
    this.fwr = fwr;
    this.inheritanceList = inheritanceList;
    this.associationList = associationList;
    this.aggregationList = aggregationList;
  }

  public void fileWrite() {
    StringBuilder data = new StringBuilder();
    Iterator it = inheritanceList.iterator();
    System.out.println(data);
    while (it.hasNext()) {
      Inheritance inh = (Inheritance) it.next();
      data.append("\"" + inh.getSrcClass().getName() + "\" \""
                  + inh.getDstClass().getName() + "\"" + " I\n");
    }

    it = aggregationList.iterator();
    //for (Aggregation agg : aggregationList) {
    while (it.hasNext()) {
      Aggregation agg = (Aggregation) it.next();
      data.append("\"" + agg.getSrcClass().getName() + "\" \""
                  + agg.getDstClass().getName() + "\" A \"" + agg.getMultiplicity() + "\" \n");
    }

    it = associationList.iterator();
    while (it.hasNext()) {
      Association assoc = (Association) it.next();
      int temp = 0;
      data.append("\"" + assoc.getSrcClass().getName() + "\" \""
                  + assoc.getDstClass().getName() + "\" F " + temp + " \"" +
                  assoc.getSrcMultiplicity() + "\" \"" + assoc.getDestMultiplicity()
                  + "\" \"" + assoc.getSrcRole() + "\" \"" + assoc.getDestRole()+
                  "\" \""+assoc.getName()+"\"" + "\n");
    }
    //System.out.println(data);
    fwr.writeRelationFile(data.toString());
  }
}
