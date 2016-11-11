package test.java;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.pageObjects.PerformanceTest;
import main.java.qa.framework.pageObjects.iMDB;
import main.java.qa.framework.testng.LoggingListener;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;

import org.testng.annotations.*;

@Listeners({ TestListeners.class, TestMethodListener.class, LoggingListener.class })
public class TestiMDB extends TestBase {

	@Test( description = "iMDB")
	public void test_iMDB() throws Exception {
		
		iMDB imdb = new iMDB();
		imdb.selectMovies();
	}
	
	@Test( description = "performance")
	public void test_performance() throws Exception {
		
		PerformanceTest pt = new PerformanceTest();
		pt.performanceTest();
	}

}