package edu.uta.controller;

import java.util.*;

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
public class Cat2String {
   String originalText="";
   String words="";
   public Cat2String(String originalText) {
      super();
      this.originalText=originalText;
   }
   public String getWords(String phrase) {
      String cat="";
      if (phrase.indexOf("Cat2(") >= 0)
         cat=phrase.substring(phrase.indexOf("Cat2"),
            phrase.indexOf(")"));
      if (phrase.indexOf("Cat3(") >= 0)
         cat=phrase.substring(phrase.indexOf("Cat3"),
            phrase.indexOf(")"));
      StringTokenizer stz=new StringTokenizer(cat, "\"");
      String words="";
      while (stz.hasMoreTokens()) {
         String token=stz.nextToken();
         if (token.indexOf("Cat2(")<0 && token.indexOf("Cat3(")<0 &&
            token.indexOf(",")<0 && token.indexOf(")")<0) {
            words += token+" ";
         }
      }
      return words.trim();
   }
   public boolean validateWords(String words) {
      return originalText.indexOf(words)>=0;
   }
}
