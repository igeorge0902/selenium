package main.java.qa.framework.testng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class CreateTestNGXml {
	
    private static Map<String, String> parameters = new HashMap<String, String>();
	private List<String> listeners = new ArrayList<String>();

	private List<XmlInclude> constructIncludes (String... methodNames) {
        List<XmlInclude> includes = new ArrayList<XmlInclude> ();
        for (String eachMethod : methodNames) {
            includes.add (new XmlInclude (eachMethod));
        }
        return includes;
    }

    @Test public void generatedXMLfile () throws IOException {

        XmlSuite suite = new XmlSuite ();
        suite.setName ("Regression");
        suite.setParallel ("Tests");
        suite.setThreadCount (10);

        listeners.add("org.uncommons.reportng.HTMLReporter");
        listeners.add("org.uncommons.reportng.JUnitXMLReporter");
        suite.setListeners(listeners);

        XmlTest test = new XmlTest (suite);
        test.setName ("Test");
        test.setPreserveOrder ("true");
        parameters.put("operator", "hbo");        
        test.setParameters(parameters);

        XmlClass testClass = new XmlClass ();
        testClass.setName ("ClassName1");
    
        List<XmlInclude> methodsToRun = constructIncludes (new String[] { "Method01", "Method02", "Method03" });
        testClass.setIncludedMethods (methodsToRun);

        XmlClass testClass1 = new XmlClass ();
        testClass1.setName ("ClassName21");
        methodsToRun = constructIncludes (new String[] { "Method21", "Method22", "Method23" });
        testClass1.setIncludedMethods (methodsToRun);

        test.setXmlClasses (Arrays.asList (new XmlClass[] {testClass, testClass1 }));
        System.out.println ("Printing TestNG Suite Xml");
        System.out.println (suite.toXml ());
    }
}
