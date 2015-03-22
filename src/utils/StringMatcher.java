package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatcher {
	
	  public static final String EXAMPLE_TEST = "1:3:59"; //a
	  public static final String EXAMPLE_TEST2 = "1:4:59"; //b
		
  // returns true if the string matches exactly "true"
  public boolean isTrue(String s){
    return s.matches("true");
  }
  // returns true if the string matches exactly "true" or "True"
  public boolean isTrueVersion2(String s){
    return s.matches("[tT]rue");
  }

  // returns true if the string matches exactly "true" or "True"
  // or "yes" or "Yes"
  public boolean isTrueOrYes(String s){
    return s.matches("[tT]rue|[yY]es");
  }

  // returns true if the string contains exactly "true"
  public boolean containsTrue(String s){
    return s.matches(".*true.*");
  }
  

  // returns true if the string contains of three letters
  public boolean isThreeLetters(String s){
    return s.matches("[a-zA-Z]{3}");
    // simpler from for
//    return s.matches("[a-Z][a-Z][a-Z]");
  }



  // returns true if the string does not have a number at the beginning
  public boolean isNoNumberAtBeginning(String s){
    return s.matches("^[^\\d].*");
  }
  // returns true if the string contains a arbitrary number of characters except b
  public boolean isIntersection(String s){
    return s.matches("([\\w&&[^b]])*");
  }
  // returns true if the string contains a number less then 300
  public boolean isLessThenThreeHundred(String s){
    return s.matches("[^0-9]*[12]?[0-9]{1,2}[^0-9]*");
  }

  
  // returns true if the string contains a number less then 300
  public boolean isPlayerHbo(String s){
    return s.matches("^[a-z]{4}\\W\\/\\/+[a-z]{6}\\.[a-z]{7}\\.[a-z]{3}\\/[content]+\\/.+-[0-9]+$");
  }
  
  public boolean isPlayButton(String s){
    return s.matches("^[a-z]{4}-[a-z]{6}-[a-z0-9\\.-]{36}");
  }
  
  public void printRegexValue (String s) {
	  
	  
	  String.valueOf(s);
	  System.out.println(String.valueOf(s));
  }
  
  public boolean regexTest (boolean condition) { 
	  	    	    
	    
	    // now create a new pattern and matcher to replace whitespace with tabs
	    Pattern replace = Pattern.compile("\\:+");
	    Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
	    System.out.println(matcher2.replaceAll(""));
	    
	    
	    //*******************************************************//
	    	    
	    
	    // now create a new pattern and matcher to replace whitespace with tabs
	    Pattern replace_ = Pattern.compile("\\:+");
	    Matcher matcher2_ = replace_.matcher(EXAMPLE_TEST2);
	    System.out.println(matcher2_.replaceAll(""));
	    
	    String a1 = matcher2.replaceAll("");
	    String b2 = matcher2_.replaceAll("");
	    
	    System.out.println(a1);
	    System.out.println(b2);

	    int a = Integer.parseInt(a1);
	    int b = Integer.parseInt(b2);
	    
	    if (condition == (a>b)) {
	    	
	    } else {
	    	
	    }
	    
	    return false;
	  
  } 
  
} 
