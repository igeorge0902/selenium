package test.java;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import com.thoughtworks.selenium.*;

import java.util.regex.Pattern;

public class google extends SeleneseTestCase {
public void setUp() throws Exception {
    setUp("http://www.google.com/", "*firefox");
}
  public void testNew() throws Exception {
      selenium.open("/");
      
		if (selenium instanceof JavascriptExecutor) {
			((JavascriptExecutor) selenium)
				.executeScript("alert('hello world');");
		}
		
      selenium.type("q", "selenium rc");
      selenium.click("btnG");
      selenium.waitForPageToLoad("30000");
      assertTrue(selenium.isTextPresent("Results * for selenium rc"));
}
}