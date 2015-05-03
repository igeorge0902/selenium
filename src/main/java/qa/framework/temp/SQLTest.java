package main.java.qa.framework.temp;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;

public class SQLTest extends TestBase implements WebElements {

	public static Logger Log = Logger.getLogger(Logger.class.getName());

	public static void main(String[] args) throws Exception {

		DOMConfigurator.configure("log4j.xml");

		dao.SetUpDataBase();
		dao.runSqlScript(create_db_sql);
		dao.insertReport();
		dao.generateMethodSummaryReport("test", "hello");

	}

}
