using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using OpenQA.Selenium;
using log4net;
using System.Xml;


namespace Selenium
{
    public class TestBase
    {

        protected static IWebDriver Driver;
        protected static XmlDocument Doc;
        private readonly ILog _log = LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);

        public TestBase(IWebDriver driver)
        {
            TestBase.Driver = WebDriverManager.driver;
            log4net.Config.XmlConfigurator.Configure();
        }

        [TestInitialize()]
        public void SetUp()
        {
            //TODO: use the loaded xml to decide which webdriver to instantiate
            //TODO: pass some parameters to methods
            XmlDocument Doc = new XmlDocument();
            Doc.Load(@"c:\users\igeorge1982\documents\visual studio 2012\Projects\Selenium\Selenium\TestSuites\Books.xml");

            XmlNodeList nodes = Doc.DocumentElement.SelectNodes("/catalog/book");

            List<Book> books = new List<Book>();

            foreach (XmlNode node in nodes)
            {
                Book book = new Book();

                book.author = node.SelectSingleNode("author").InnerText;
                book.title = node.SelectSingleNode("title").InnerText;
                book.id = node.Attributes["id"].Value;

                books.Add(book);
            }

            _log.Info("Total books: " + books.Count);

            if (books.Count == 3)
            {

                Driver = WebDriverManager.startDriver();
                _log.Info("Driver got under way!");
            }
        }

        [TestCleanup]
        public void Close()
        {
            Driver.Quit();
        }

        class Book
        {
            public string id;
            public string title;
            public string author;
        }
    }
}
