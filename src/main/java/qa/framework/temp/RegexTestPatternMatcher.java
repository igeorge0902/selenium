package main.java.qa.framework.temp;


import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class RegexTestPatternMatcher {
  //public static final String EXAMPLE_TEST = "This is my small example string which I'm going to use for pattern matching.";
  public static final String EXAMPLE_TEST = "1:3:59"; //a
  public static final String EXAMPLE_TEST2 = "1:4:59"; //b


  public static void main(String[] args) {
	  
	  
    Pattern pattern = Pattern.compile("\\w+");
    
    
    // in case you would like to ignore case sensitivity,
    // you could use this statement:
    // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(EXAMPLE_TEST);
    
    
    // check all occurance
    while (matcher.find()) {
    
      System.out.println(matcher.group());
    
    }  
    
    // now create a new pattern and matcher to replace whitespace with tabs
    Pattern replace = Pattern.compile("\\:+");
    Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
    System.out.println(matcher2.replaceAll(""));
    
    
    //*******************************************************//
    
    Pattern pattern_ = Pattern.compile("\\w+");
    
    
    // in case you would like to ignore case sensitivity,
    // you could use this statement:
    // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
    Matcher matcher_ = pattern_.matcher(EXAMPLE_TEST2);
    
    
    // check all occurance
    while (matcher_.find()) {

      System.out.println(matcher_.group());
    
    }
    
    
    // now create a new pattern and matcher to replace whitespace with tabs
    Pattern replace_ = Pattern.compile("\\:+");
    Matcher matcher2_ = replace_.matcher(EXAMPLE_TEST2);
    System.out.println(matcher2_.replaceAll(""));
    
    String a1 = matcher2.replaceAll("");
    String b2 = matcher2_.replaceAll("");
    
    System.out.println(a1);
    System.out.println(b2);
    
    
    //*********************************************//
    
    
    String stringToSearch = "http://player.mediaux.biz/content/unman-wittering-and-zigo--1375609806";
    
    // the pattern we want to search for
    Pattern p = Pattern.compile("(^[a-z]{4}\\W\\/\\/+[a-z]{6}\\.[a-z]{7}\\.[a-z]{3}\\/[content]+\\/.+-[0-9]+$)");
    Matcher m = p.matcher(stringToSearch);
 
    // if we find a match, get the group
    if (m.find())
    {
      // we're only looking for one group, so get it
      String theGroup = m.group(1);
       
      // print the group out for verification
      System.out.format("%s", theGroup);
      
      //Log.info(String.format("%s\n", theGroup));
      
    }
 
  
  }
  
  
} 

