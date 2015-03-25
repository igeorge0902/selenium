package main;

public enum BaseUrls {
	  
	  GOOGLE("https://www.google.hu/"),
	  YAHOO("https://www.yahoo.com/"),
	  HBO("http://www.hbogo.hu/"),
	  PLAYER("http://player.mediaux.biz");

public String myUrls;

BaseUrls (String url) {
	  myUrls = url;
	}
public String get() {
	  return myUrls;
	}

}
