package main.resources.com.example.tests;

                import com.thoughtworks.selenium.*;

                @SuppressWarnings("deprecation")
				public class GoogleTest extends SeleneseTestCase {
 
                         public DefaultSelenium selenium;
         
                         public void setUp() throws Exception {
                                selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.google.com/");
                                selenium.start();
                         }

                         public void testGoogleTest() throws Exception {
                                      selenium.open("/");
                                      selenium.type("q", "Cheese!");
                                      selenium.click("btnG");
                                      selenium.isTextPresent("Chesse");
                        }
              }