package utils;

import org.testng.annotations.Test;

public class TestDataSample {
 
    //Constructor: this will be only invoked once, testng will utilize the same obj to call the test method as many times as the dataprovides content.
    public TestDataSample() {
        System.out.println("SampleTest2 Constructor : This is to demonstrate how testng initializes the test class while using dataproviders.");
    }
     
    //Declare the dataprovider class and the dataprovider we want to use. This way of keeping separate class
    //to keep all your dataproviders make it easy to manate them when the test system gets complicated over time.
    @Test(dataProviderClass=SampleDataProvider.class,dataProvider="groupDataProvider")
    public void testDataProvider(String line) {
        //This should print each of the file content one after the other
        //testng calls this method for each line.
        System.out.println("SampleTest2:testDataProvider: testData got from fileDataProvider: " + line);
    }
     
}