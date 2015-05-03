package main.java.qa.framework.main;

public enum BaseUrls implements WebElements {
	  
	  GOOGLE("https://www.google.hu/"),
	  YAHOO("https://www.yahoo.com/"),
	  HBO("http://www.hbogo.hu/"),
	  PLAYER("http://huvip.hbogo.eu");

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
	  		return huvip;  			  	
	  	}
}
