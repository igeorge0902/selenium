package main.java.qa.framework.pageObjects;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.ElementScreenshot;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;
import main.java.qa.framework.utils.PropertyUtils;
import main.java.qa.framework.utils.SampleDataProvider;
import main.java.qa.framework.utils.WaitTool;
import main.java.qa.framework.utils.deleteLines;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
@Listeners({ TestListeners.class,
		main.java.qa.framework.main.CaptureScreenshotOnFailureListener.class,
		TestMethodListener.class })
public class SelectSeries extends TestBase implements WebElements {

	public SelectSeries(WebDriver driver) {
		super(driver);
	}

	public SelectSeries selectSeries() throws Exception {

		Actions actions = new Actions(driver);
		PlayMainContent PlayMainContent = new PlayMainContent(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		WaitTool.waitForElement(driver, By.id(HeaderButton), 10);
		driver.findElement(By.id(HeaderButton)).click();

		// TestBase.isElementPresent(By.id(Series));

		TestBase.clickLinkByHref(Series);

		// wait for page to load
		WaitTool.waitForElement(driver, By.id("normalView"), 10);
		String url = driver.getCurrentUrl();
		Log.info(url);

		// get url list of all series'
		List<String> urslList = TestBase.contentsList(NormalView);

		TestBase.deleteFile(seriesUrlsFile);
		TestBase.createFile(seriesUrlsFile);
		TestBase.writeFile(seriesUrlsFile, urslList);

		PropertyUtils.loadPropertyFile(proprtyFile);
		String series = PropertyUtils.getProperty("series");
		driver.get(BaseUrls.PLAYER.get() + series);

		WaitTool.waitForElement(driver, By.id(EpisodeList), 10);

		// get url list of episodes of the actual season of the series
		// Iterator<String> list_ = TestBase.contents_(EpisodeList);
		List<String> urslListofEpisodes = TestBase.contentsList(EpisodeList);

		TestBase.deleteFile(episodeUrlsFile);
		TestBase.createFile(episodeUrlsFile);
		TestBase.writeFile(episodeUrlsFile, urslListofEpisodes);
		TestBase.deleteFile(urlsFile);

		// get url list of seasons of the actual series
		// Iterator<String> list_ = TestBase.contents_(EpisodeList);
		driver.findElement(By.id(SeasonChooser)).click();
		List<String> urslListofSeasons = TestBase.contentsList(SeasonContainer);

		TestBase.deleteFile(seasonUrlsFile);
		TestBase.createFile(seasonUrlsFile);
		TestBase.writeFile(seasonUrlsFile, urslListofSeasons);

		return new SelectSeries(driver);
	}

}
