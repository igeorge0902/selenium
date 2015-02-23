package main;
/*
Copyright 2007-2009 Selenium committers

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Use this as super interface, if you want to implement a {@link WebDriverEventListener}.
 */
public interface AbstractWebDriverEventListener extends WebDriverEventListener {

  public void beforeNavigateTo(String url, WebDriver driver);

  public void afterNavigateTo(String url, WebDriver driver);

  public void beforeNavigateBack(WebDriver driver);

  public void afterNavigateBack(WebDriver driver);

  public void beforeNavigateForward(WebDriver driver);

  public void afterNavigateForward(WebDriver driver);

  public void beforeFindBy(By by, WebElement element, WebDriver driver);

  public void afterFindBy(By by, WebElement element, WebDriver driver);

  public void beforeClickOn(WebElement element, WebDriver driver);

  public void afterClickOn(WebElement element, WebDriver driver);

  public void beforeChangeValueOf(WebElement element, WebDriver driver);

  public void afterChangeValueOf(WebElement element, WebDriver driver);

  public void beforeScript(String script, WebDriver driver);

  public void afterScript(String script, WebDriver driver);

  public void onException(Throwable throwable, WebDriver driver);

void afterClickOn(By by, WebDriver driver);
}
