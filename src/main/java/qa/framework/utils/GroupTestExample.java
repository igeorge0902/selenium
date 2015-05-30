package main.java.qa.framework.utils;


import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.temp.MessageUtil;
import main.java.qa.framework.testng.CustomReportListener;
import main.java.qa.framework.testng.TestListeners;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners({ TestListeners.class, CustomReportListener.class})

public class GroupTestExample extends TestBase {
	
   String message = ".com";
   MessageUtil messageUtil = new MessageUtil(message);


	@BeforeGroups
	  public void setUp(ITestContext context) throws Exception {
		/*
		  try {			  
		  // get the web driver parameters from the testng xml file
	      String browser = context.getCurrentXmlTest().getParameter("browser");
	      String url = context.getCurrentXmlTest().getParameter("url");
	      
	      driver = WebDriverManager.startDriver(browser, url, 40); 
	      TestBase.verifyNotNull(driver, "Driver setUp failed!");

		  } catch (Exception e) {
			
			  Log.info(e);
			  Log.info("Safari is reconnecting!");
			  // get the web driver parameters from the testng xml file
		      String browser = context.getCurrentXmlTest().getParameter("browser");
		      String url = context.getCurrentXmlTest().getParameter("url");
		      
		      driver = WebDriverManager.startDriver(browser, url, 40); 
		      TestBase.verifyNotNull(driver, "Driver setUp failed!");
		  }*/
		  
	}
   
   @Test(groups = { "functest", "checkintest" })
   
   public void testPrintMessage() {
      System.out.println("Inside testPrintMessage()");
      message = ".com";
      Assert.assertEquals(message, messageUtil.printMessage());
   }

   @Test(groups = { "checkintest" })
   
   public void testSalutationMessage() {
      System.out.println("Inside testSalutationMessage()");
      message = "tutorialspoint" + ".com";
      Assert.assertEquals(message, messageUtil.salutationMessage());
   }

   @Test(groups = { "functest" })
   
   public void testingExitMessage() {
      System.out.println("Inside testExitMessage()");
      message = "www." + "tutorialspoint"+".com";
      Assert.assertEquals(message, messageUtil.exitMessage());
   }  
}