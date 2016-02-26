package edu.uta.controller;

import java.util.ArrayList;

//import edu.uta.database.Command;
import edu.uta.model.*;

public class GenerateDomainModelController {
   DomainModel domainModel;
   public GenerateDomainModelController(DomainModel dm) {
      domainModel=dm;
   }
   public void generateModel() {
      DomainModel.getInstance().writeFile();
   }
}
