// events1.cs
using System;
using OpenQA.Selenium;

namespace MyCollections
{
    using System.Collections;

    // A delegate type for hooking up change notifications.
    public delegate void ChangedEventHandler(object sender, EventArgs e);

    // A class that works just like ArrayList, but sends event
    // notifications whenever the list changes.
    public class ListWithChangedEvent : ArrayList
    {
        // An event that clients can use to be notified whenever the
        // elements of the list change.
        public event ChangedEventHandler Changed;

        // Invoke the Changed event; called whenever list changes
        protected virtual void OnChanged(EventArgs e)
        {
            if (Changed != null)
                Changed(this, e);
        }

        // Override some of the methods that can change the list;
        // invoke event after each
        public override int Add(object value)
        {
            int i = base.Add(value);
            OnChanged(EventArgs.Empty);
            return i;
        }

        public override void Clear()
        {
            base.Clear();
            OnChanged(EventArgs.Empty);
        }

        public override object this[int index]
        {
            set
            {
                base[index] = value;
                OnChanged(EventArgs.Empty);
            }
        }
    }
}

namespace ByEvents
{
    using OpenQA.Selenium;
    using log4net;

    // A delegate type for hooking up change notifications.
    public delegate void ChangedEventHandler(object sender, EventArgs e);

    // Sends event notifications whenever a By search context has been done.
    public class ListWithChangedEvent : By
    {
        private readonly ILog log = LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);

        public event ChangedEventHandler Changes;

        protected virtual void OnChanged(EventArgs e)
        {
            if (Changes != null)
                Changes(this, e);
        }

        // Override some of the methods that can change the By;
        public override IWebElement FindElement (ISearchContext context)
        {
            IWebElement FindElement = base.FindElement(context);
            OnChanged(EventArgs.Empty);
            log.Info(context);
            return FindElement;
        }
    }
}

namespace TestEvents
{
    using ByEvents;

    class EventListener
    {
        private ListWithChangedEvent By;

        public EventListener(ListWithChangedEvent by)
        {
            By = by;
            // Add "ListChanged" to the Changed event on "List".
            by.Changes += new ChangedEventHandler(ListChanged);
        }

        // This will be called whenever the list changes.
        private void ListChanged(object sender, EventArgs e)
        {
            Console.WriteLine("This is called when the event fires.");
        }

        public void Detach()
        {
            // Detach the event
            By.Changes -= new ChangedEventHandler(ListChanged);
            By = null;
        }
    }
}