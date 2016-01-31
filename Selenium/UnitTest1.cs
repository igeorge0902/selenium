using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using log4net;
using ByEvents;
using TestEvents;

namespace Selenium
{
    [TestClass]
    public class UnitTest1 : TestBase {

        private readonly ILog log = LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);

        public UnitTest1() : base(driver) {}
        
        [TestMethod]
        public void TestMethod1()
        {
            // Create a new list.
            ListWithChangedEvent by = new ListWithChangedEvent();

            // Create a class that listens to the list's change event.
            EventListener listener = new EventListener(by);

            driver.Navigate().GoToUrl("www.google.com");

            driver.FindElement(By.Id("hplogo"));

            log.Info("this is the first log message");

        }

    }
}
