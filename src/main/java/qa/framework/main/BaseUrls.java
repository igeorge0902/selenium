package main.java.qa.framework.main;

import main.java.qa.framework.utils.PropertyUtils;


public enum BaseUrls implements WebElements {

	
	  GOOGLE("https://www.google.hu/"),
	  YAHOO("https://www.yahoo.com/"),
	  HBO("http://www.hbogo.hu/"),
	  PLAYER(PropertyUtils.getProperty("baseUrl"));

	  public String myUrls;

	  	BaseUrls (String url) {
	  		myUrls = url;
	  	}

	  	public String get() {
		  return myUrls;
		}
	  	
	  	public String toString(){
	  		if (this.equals(PLAYER)) {	  			
	  		}
	  		return PropertyUtils.getProperty("MainContents");  			  	
	  	}
}
