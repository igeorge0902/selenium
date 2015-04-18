package temp;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import main.TestBase;
import main.WebElements;


public class SQLTest extends TestBase implements WebElements {
	
	public static Logger Log = Logger.getLogger(Logger.class.getName());

  public static void main(String[] args) throws Exception {
	  
	    DOMConfigurator.configure("log4j.xml");

    dao.SetUpDataBase();
    dao.runSqlScript(create_db_sql);
    dao.insertReport();
  }

} 


