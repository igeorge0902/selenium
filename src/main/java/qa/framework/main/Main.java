package main.java.qa.framework.main;


import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class Main extends TestBase {

	public static void main(String[] args) {

		List<String> suites = new ArrayList<String>();

		suites.add(testngXml);

		TestNG tng = new TestNG();
		tng.setTestSuites(suites);
		tng.run();

	}

}
