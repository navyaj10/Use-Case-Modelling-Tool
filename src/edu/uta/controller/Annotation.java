package edu.uta.controller;

public abstract class Annotation{

        // annotates the given tagged Text into domain model annotation
        public String Annotate(String taggedText) {
                String[] taggedWords = taggedText.split(" ");
                for(int i=0; i<taggedWords.length; i++){
                        String tag = GetTag(taggedWords[i]);
                        if(tag != null) // if the word has tag, try to annotate
                                taggedWords[i] = GetAnnotated(tag, taggedWords[i]);
                }

                String annotatedWord = JoinTaggedWords(taggedWords);
                return annotatedWord;

        }

        // sub class should override this to annotate each word to their type
        protected abstract String GetAnnotated(String tag, String taggedWord);

        // removes the PosTag from the word
        protected String RemoveTag(String taggedWord) {
                if(taggedWord.contains("/"))
                        return taggedWord.substring(0, taggedWord.indexOf('/'));
                return taggedWord;
        }

        // annotates the given word with the given char e.g. <N Car>
        protected String Annotate(String taggedWord, String annotationChar) {
                String actualWord = taggedWord.substring(0, taggedWord.indexOf('/'));
                //String camelCase = actualWord.toUpperCase().substring(0,1) + actualWord.substring(1);
                return "<" + annotationChar + "_" + actualWord + ">";
        }

        // returns the PosTag of the given word
        protected String GetTag(String taggedWord) {
                if(taggedWord.contains("/")){
                        return taggedWord.substring(taggedWord.indexOf('/'));
                }
                return null;
        }

        // combines array of string into one string (words[] -> sentence)
        protected String JoinTaggedWords(String[] tagged) {
                String joined = "";
                for(int i=0; i<tagged.length; i++)
                        joined += tagged[i] + " ";

                // remote the space between last word and dot (.... asdf . )
        //	if(joined.charAt(joined.length()-3) == ' ')
                //	return joined.substring(0, joined.length()-3)+".";
                return joined;
        }



}
