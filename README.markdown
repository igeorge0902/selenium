## Selenium html project

# Upgraded to Selenium 3.0.1

# Tested on macOs with:

* Firefox 49.0.2
* Chrome 54.0.2840.87
* Safari 10.0

This contains the source code for running sample [Selenium](http://http://www.seleniumhq.org) tests using [TestNG](http://www.testng.org).

In order to run the tests, build or run with [Apache Maven](http://maven.apache.org)

To compile and run all tests with Maven, run:

    mvn -Dmaven.test.skip=true package

To run a single test, run:

    mvn -Dtest=your.test.java.class test

To run the build output from the output directory, run:

    java -jar app.jar

The jar file will run the tests in the referenced testng.xml. Please check the TestNG site for the testng.xml schema references!

## Please check the properties.properties file and set the properties to your needs, too.

## Data providing for tests with TestNG:

* the main.java.qa.framework.utils/SampleDataProvider.java class implements the @DataProvider annotation, which takes in a file as parameter as follows:

String inputFile = context.getCurrentXmlTest().getParameter("filenamePathWin"); 

It also uses a private static method to read the file line by line:

private static List<String> getFileContentList(String filenamePath) {}

Then you have to reference the "filenamePathWin" in the testng xml file as a parameter. The path is relative to your project root in this example:

<parameter name="filenamePathWin" value=".\lib\urls.txt" />

Then you have to call the data provider in your tests as follows:

@Test(dataProviderClass = SampleDataProvider.class, dataProvider = "fileDataProvider")

The name of the dataProvider matches the name of your data provider method.

Once it all has been done, you also need to pass the data as parameter to your page objects. In the example below you pass strings as parameter, which strings as we know now are coming from the text file line by line, which means your page object will run as many times as many lines you include in your text file, and this whole run makes up your test case:

    public PlayMainContent playMainContent(String urls) throws Exception,
            CustomException {
            ...
            return new PlayMainContent();
            }

## Tesseract:

The project includes OCR capabilities using Tesseract: https://github.com/tesseract-ocr/tesseract/wiki

To use it on Mac you need to install Tesseract:

* https://gist.github.com/henrik/1967035

To run it on Windows the neccessary dll files are included in the ${project}/lib/win32... folders. It shall work, but if you encounter any problem pls refer to the above mentioned Tesseract Wiki site.

To run a sample:

* use the main.java.qa.framework.utils/TesseractUtil.java class and run it as a Java application

To run it inside the Selenium app:

            String screenShot = CaptureScreenshotOnFailureListener.captureScreenShot(String filename);
            String[] arguments = new String[] {screenShot}; 
            TesseractExample.main(arguments);
        

or see it implemented in main.java.qa.framework.pageObjects/LanguageChange.java class.

