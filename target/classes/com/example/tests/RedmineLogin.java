package main.resources.com.example.tests;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

@SuppressWarnings({ "deprecation", "unused" })
public class RedmineLogin extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", "https://tokt.teba.gov.hu/");
		selenium.start();
	}

	@Test
	public void testRedmine_login() throws Exception {
		selenium.open("/redmine");
		selenium.type("id=username", "gygaspar");
		selenium.type("id=password", "Bugsbunny666");
		selenium.click("name=login");
		selenium.waitForPageToLoad("30000");
		selenium.waitForPageToLoad("");
		selenium.isTextPresent("Üdvözöljük a TÉBA hibakezelő rendszerében!");
		// selenium.();
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
