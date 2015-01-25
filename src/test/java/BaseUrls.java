package test.java;

public enum BaseUrls {
	  
	  GOOGLE("https://www.google.hu/"),
	  YAHOO("https://www.yahoo.com/"),
	  HBO("http://www.hbogo.hu/");

private String myUrls;

BaseUrls (String url) {
	  myUrls = url;
	}
public String get() {
	  return myUrls;
	}

}
