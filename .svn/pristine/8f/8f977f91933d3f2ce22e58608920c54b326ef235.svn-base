package main.java.qa.framework.pageObjects;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.CaptureScreenshotOnFailureListener;
import main.java.qa.framework.main.ElementScreenshot;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebDriverManager;
import main.java.qa.framework.main.WebElements;
import main.java.qa.framework.testng.CustomException;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;
import main.java.qa.framework.utils.PropertyUtils;
import main.java.qa.framework.utils.WaitTool;

@SuppressWarnings("unused")
@Listeners({ TestListeners.class,
		main.java.qa.framework.main.CaptureScreenshotOnFailureListener.class,
		TestMethodListener.class })
public class PlaySingleMainContent extends TestBase implements WebElements {

	private final String Firefox = "Firefox";

	public PlaySingleMainContent playMainContent(String contents)
			throws Exception, CustomException {

		Actions action = new Actions(driver);
		String titletext = "";

		String content = PropertyUtils.getProperty(contents);

		driver.get(BaseUrls.PLAYER.get() + content);

		// start playback
		MouseHover MouseHover = new MouseHover();
		MouseHover.mouseHover();
		Thread.sleep(5000);

		// TODO: parental
		// TODO: proper playback start check

		String titleName = driver.findElement(By.id(playbackTitle)).getText();
		Log.info(titleName);

		// wait for title presence with regex
		Pattern title = Pattern.compile("(.)");
		Matcher matchertitle = title.matcher(titleName);

		if (matchertitle.find()) {

			String theGroup = matchertitle.group(1);

			titletext = String.format("%s", theGroup);
			WaitTool.waitForTextPresent(driver, By.id(playbackTitle), titletext, 20);

		}

		// wait for element, where second = nr. of attempts
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresentAndDisplay(By.id(play_pause)))
					break;
			} catch (Exception e) {
				Log.info(e.getCause());
			}
		}
		Thread.sleep(1000);

		// mousehover the play/pause button
		TestBase.MouseHoverByJavaScript(By.id(play_pause));

		// pause
		TestBase.playPause();
		Log.info("playback stopped");

		// TODO: player controller tests (volume, audio, subs track, fullscreen,
		// exit fullscreen, GUI goes away after 3 seconds)

		// TODO: if there is next or previous episode, it could be tested here

		try {
			// TODO: check if seek not succeeds, whether the content has reached
			// the end
			// mousehover the position seek dot, and drag&drop toward the given
			// direction, which is set by int (+-)
			WaitTool.waitForElement(driver, By.id(positionsSeek), 10);
			TestBase.MouseHoverByJavaScript(By.id(positionsSeek));

			//TODO: it fails now
			WebElement seekBar;
			seekBar = driver.findElement(By.id(positionsSeek));
			action.moveToElement(seekBar).build().perform();

			if (TestBase.isElementPresent(By.id(positionsSeek))) {

				action.dragAndDropBy(SeekDot, 0, -1100).build().perform();
				Reporter.log("Drag and drop action succeeded!");
				Log.info("Drag and drop action succeeded!");
			}

			else if (!TestBase.isElementPresent(By.id(positionsSeek))) {
				Reporter.log("Drag and drop action failed!");
				Log.info("Drag and drop action failed!");
				// throw new CustomException ();
			}
		} catch (CustomException e) {
			Log.info(e.getMessage());
			throw new CustomException(); // ?
		}

		// start
		TestBase.playPause();
		Log.info("playback is running");

		// while cycle for elapsed time check
		int x = 30;
		int second = x;

		while (second > 0) {

			try {

				JavascriptExecutor js = (JavascriptExecutor) driver;

				// runs javascript to get elapsed time
				WebElement elapsedTime;
				elapsedTime = driver.findElement(By.id(playbackElapsedTime));

				String text = TestBase.getText(driver, elapsedTime);

				// prints out elapsed time
				System.out.println("Elapsed time first: " + text);
				Thread.sleep(333L);

				// find and replace elapsed time string value
				Pattern replace = Pattern.compile("\\:+");
				Matcher matcher = replace.matcher(text);
				System.out.println(matcher.replaceAll(""));

				// runs javascript to get elapsed time
				WebElement elapsedTime2;
				elapsedTime2 = driver.findElement(By.id(playbackElapsedTime));

				// Thread goes to sleep for 5 seconds
				// so that the second elapsed time check will be after the first
				// one
				// this value could be parameter, too
				int sleep = 10000;
				Thread.sleep(sleep);

				// prints out elapsed time
				String text2 = TestBase.getText(driver, elapsedTime2);
				System.out.println("Elapsed time second: " + text2);

				// find and replace elapsed time string value
				Pattern replace2 = Pattern.compile("\\:+");
				Matcher matcher2 = replace2.matcher(text2);
				System.out.println(matcher2.replaceAll(""));

				// convert replaced string value to integer
				int elapsedtime = Integer.parseInt(matcher.replaceAll(""));
				int elapsedtime2 = Integer.parseInt(matcher2.replaceAll(""));

				TestBase.verifyNotSame(elapsedTime, elapsedTime2);
				Log.info("Current cycle is " + second + " out of " + x);

				// verify should work the way that if playback fails, next
				// content will be loaded to avoid loop for playback fails
				TestBase.assertTrue(elapsedtime2 > elapsedtime,
						"Playback stopped after " + sleep + " seconds!");

			} catch (Exception e) {
				CaptureScreenshotOnFailureListener.captureScreenShot(titletext);
				Reporter.log("Playback failed for content: "
						+ driver.findElement(By.id(playbackTitle)).getText());
				Log.info("Playback failed for content: "
						+ driver.findElement(By.id(playbackTitle)).getText());
				second--;
			}

			second--;
		}

		try {
			TestBase.MouseHoverByJavaScript(By.id(playbackClose));
			driver.findElement(By.id(playbackClose)).click();

		} catch (Exception e) {
			Reporter.log("Playback quit not succeeded...");
			Log.info("Playback quit not succeeded...");
		}

		Reporter.log("Playback works fine: "
				+ driver.findElement(By.id(playbackTitle)).getText());
		Log.info("Playback works fine");

		Thread.sleep(5000);

		return new PlaySingleMainContent();
	}
}
