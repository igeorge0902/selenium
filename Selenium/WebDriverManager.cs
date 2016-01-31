using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Support.Events;

namespace Selenium
{
    public class WebDriverManager {

        public static IWebDriver driver;

        public static IWebDriver startDriver() {

        driver = new FirefoxDriver();
        driver = driverEventListener();

        return driver;
        }

        public static IWebDriver driverEventListener()
        {
            var firingDriver = new EventFiringWebDriver(driver);

            driver = firingDriver;

            return firingDriver;
        }

    }
}
