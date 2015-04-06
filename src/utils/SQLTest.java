package utils;

import main.WebElements;
import utils.SQLAccess;

public class SQLTest implements WebElements {
	
  public static void main(String[] args) throws Exception {
	  
	  PropertyUtils.loadPropertyFile(proprtyFile);
	  	
	  	String dbDriverClass = PropertyUtils.getProperty("dbDriverClass");
	  	String dbUrl = PropertyUtils.getProperty("dbUrl");
	  	String dbUserName = PropertyUtils.getProperty("dbUserName");
	  	String dbPassWord = PropertyUtils.getProperty("dbPassWord");
	  
    SQLAccess dao = new SQLAccess(dbDriverClass, dbUrl, dbUserName, dbPassWord);
    dao.readDataBase();
  }

} 


