Selenium html project
---
# Upgraded to Selenium 3.0.1

# Tested on macOs with:
- Firefox 49.0.2
- Chrome 54.0.2840.87
- Safari 10.0


This contains the source code for running sample [Selenium](http://http://www.seleniumhq.org) tests using [TestNG](http://www.testng.org).

In order to run the tests, build or run with [Apache Maven](http://maven.apache.org)

To compile and run all tests with Maven, run:

    mvn -Dmaven.test.skip=true package

To run a single test, run:

    mvn -Dtest=your.test.java.class test
    
To run the build output from the output directory, run:

	java -jar app.jar
	
Webdrivers:
----
- Selenium is a cross-browser automation tool. All drivers are included in the ${project}/lib folder, except for safaridriver, which is now included in macOs Sierra.
	
Testng xml:
----
The jar file will run the tests in the referenced testng.xml. Please check the TestNG site for the testng.xml schema references! The example below shows how to set up test cases to run. You can exclude or include methods from your test classes:

			<class name="test.java.TestSearchEngines">
				<methods>
					<exclude name="Yahoo" />
					<exclude name="Google" />
				</methods>
			</class>

Properties:
----
Please check the properties.properties file and set the properties to your needs, too. The proprties are called in with the TestBase.java class.

@BeforeClass and @AfterClass annotations:
----
The TestBase class applies the TestNG @BeforeClass and @AfterClass annotations, which are going to run according the lifecycle of the TestBase class. You can set up these methods as you wish. I included database connection setup and reporting after test suite runs. 

TestNG listeners:
----
The listeners are used to collect the test results, capture screenshots and for reporting. You need to annotate your listeners in your test classes, each. ReportNG is a custom reporting tool for TestNG, which had to be declared in the testng xml file.

Data providing for tests with TestNG:
----
- the main.java.qa.framework.utils/SampleDataProvider.java class implements the @DataProvider annotation, which takes in a file as parameter as follows:

```java
String inputFile = context.getCurrentXmlTest().getParameter("filenamePathWin"); 
```

It also uses a private static method to read the file line by line:

private static List<String> getFileContentList(String filenamePath) {}

Then you have to reference the "filenamePathWin" in the testng xml file as a parameter. The path is relative to your project root in this example:

```xml
<parameter name="filenamePathWin" value=".\lib\urls.txt" />
```

Then you have to call the data provider in your tests as follows:

```java
@Test(dataProviderClass = SampleDataProvider.class, dataProvider = "fileDataProvider")
```

The name of the dataProvider matches the name of your data provider method.

Once it all has been done, you also need to pass the data as parameter to your page objects. In the example below you pass strings as parameter, which strings as we know now are coming from the text file line by line, which means your page object will run as many times as many lines you include in your text file, which means you will construct the same nr of test cases, as well:

	public PlayMainContent playMainContent(String urls) throws Exception,
			CustomException {
			...
			return new PlayMainContent();
			}
			
To go without external data source you can use iterators, as well. To call your data provider in your annotated tests happening the same way:

```java
@DataProvider(name = "colors")
	public static Iterator<Object[]> getColors() {
		Set<Object[]> result = new HashSet<Object[]>();
		result.add(new Object[] { "black" });
		result.add(new Object[] { "silver" });
		result.add(new Object[] { "gray" });
		result.add(new Object[] { "white" });
		result.add(new Object[] { "maroon" });
		result.add(new Object[] { "red" });
		result.add(new Object[] { "purple" });
		result.add(new Object[] { "fuchsia" });
		result.add(new Object[] { "green" });
		result.add(new Object[] { "lime" });
		result.add(new Object[] { "olive" });
		result.add(new Object[] { "yellow" });
		result.add(new Object[] { "navy" });
		result.add(new Object[] { "blue" });
		result.add(new Object[] { "teal" });
		result.add(new Object[] { "aqua" });
		return result.iterator();
	}
```

WebTestFactory:
----
With TestNG you have the option to run test cases multiple times. You can achieve it with the @Factory annotation. To use it you need to define it in a separate class:

	  @Factory
	  public static Object[] createInstances() {
	   Object[] result = new Object[2]; 
	   for (int i = 0; i < 2; i++) {
	      result[i] = new TestHboSignUp(i * 2);
	    }
	    return result;
	  }
	  
Then you have to set it up in the referencing test class file:

	public static int m_numberOfTimes;

	public TestHboSignUp(int numberOfTimes) {
		m_numberOfTimes = numberOfTimes;
	}
	
Finally, you have to include it in your testng xml file below your corresponding test class:

			<class name="test.java.TestSearchEngines">
				<methods>
					<exclude name="Yahoo" />
					<exclude name="Google" />
				</methods>
			</class>
			<class name="main.java.qa.framework.utils.WebTestFactory" />


Tesseract:
----
The project includes OCR capabilities using Tesseract: https://github.com/tesseract-ocr/tesseract/wiki

To use it on Mac you need to install Tesseract:
- https://gist.github.com/henrik/1967035

To run it on Windows the neccessary dll files are included in the ${project}/lib/win32... folders. It shall work, but if you encounter any problem pls refer to the above mentioned Tesseract Wiki site.

To run a sample:
- use the main.java.qa.framework.utils/TesseractUtil.java class and run it as a Java application

To run it inside the Selenium app:

			String screenShot = CaptureScreenshotOnFailureListener.captureScreenShot(String filename);
			String[] arguments = new String[] {screenShot}; 
			TesseractExample.main(arguments);
			
or see it implemented in main.java.qa.framework.pageObjects/LanguageChange.java class.

Useful links:
----
- http://toolsqa.com/selenium-webdriver/testng-tutorial/
