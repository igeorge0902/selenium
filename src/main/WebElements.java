package main;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Use this interface, if you want to implement elements for
 * {@link www.hbogo.hu} website.
 */

public interface WebElements {

	// playButton
	public static String playTrailer = "Előzetes megtekintése";
	public static String playContent = "Film megtekintése";
	public static String playEpisode = "Epizód megtekintése";

	public static String playEpisodeXpath = "(//a[contains(text(),'Epizód megtekintése')])[100]";

	public static String playFree = "PlayFree";
	public static String playLive = "PlayLive";
	public static String playExtra = "PlayExtra";
	public static String playInteractive = "PlayInteractive";
	public static String playFreeInteractive = "PlayFreeInteractive";

	// play states
	public static String playPause = "PlayerManager.playPause()";

	// ******Movie Categories
	// Action Category
	public static String[] actionGroup = new String[] { "GoogleHelper.track('CLICKSTAT','MENU_CATEGORIES','Akció, Kaland');" };
	public static String actionCategory = "Akció, Kaland";

	// WebPage String elements
	public static String SignInRegister_button = "headerButtonLogin";
	public static String HeaderButton = "headerButtonMenu";
	public static String Register_button = "div.settings_description_white > #headerButtonLogin";
	public static String Operator_button = "OperatorId";
	public static String HBO_GO_Vip_Club_Hungary = "OperatorId_f320aa2c-e40e-49c2-8cdd-1ebef2ac6f26";
	public static String Voucher_code = "SpecificData";
	public static String Next_Step = "button.button_submit.next";
	public static String EmailAddress = "EmailAddress";
	public static String Terms_of_use = "Terms_theme";
	public static String Movies = "categories_group_b84a7a5f-ff13-4854-956b-9bc1070457f1";
	public static String OffersScreen = "/group/offers";
	public static String Settings = "menuTabs_settings";
	public static String CustomerMenu = "menucat_settings_customerMenu";
	public static String language = "pages/settings/changelanguage.aspx";
	public static String HU = "hu-HU";
	public static String EN = "en-EN";

	public static String ContentUrl1 = "/content/game-of-thrones-iv.-1-two-swords-255061514";
	public static String PlayButton = "//img[starts-with(@id, 'play-button-')]";
	public static String ContentDetail = ".//*[@id='content-inner']/div[1]/div[1]/div[2]";

	// WebPage elements
	public WebElement playPuttony = WebDriverManager.driver.findElement(By
			.xpath(PlayButton));

	// Player elements
	public static String play_pause = "playbackControls";
	public WebElement play_Pause = WebDriverManager.driver.findElement(By
			.id(play_pause));

	public static String playbackTitle = "playbackTitle";
	public WebElement playback_Title = WebDriverManager.driver.findElement(By
			.id(playbackTitle));

	public static String playbackInfo = "playbackInfo";
	public WebElement playback_Info = WebDriverManager.driver.findElement(By
			.id(playbackInfo));

	public static String player = "player";
	public WebElement playeR = WebDriverManager.driver.findElement(By
			.id(player));

	public static String positionsSeek = "seekbar";
	public WebElement SeekDot = WebDriverManager.driver.findElement(By
			.id(positionsSeek));

	public static String playbackElapsedTime = "playbackElapsedTime";
	public WebElement playbackElapsed_Time = WebDriverManager.driver
			.findElement(By.id(playbackElapsedTime));

	public static String playbackClose = "playbackClose";
	public WebElement playBack_Close = WebDriverManager.driver.findElement(By
			.id(playbackClose));

	public static String player_mediaux_biz = "(^[a-z]{4}\\W\\/\\/+[a-z]{6}\\.[a-z]{7}\\.[a-z]{3}\\/[content]+\\/.+-[0-9]+$)";
	public static String huvip = "(^[a-z]{4}\\W\\/\\/+[a-z]{5}\\.[a-z]{5}\\.[a-z]{2}\\/[content]+\\/.+-[0-9]+$)";
	public static String playbutton_css = "(play-button-[a-z0-9-]{36}$)";

	// files
	public Path textFile = Paths.get("lib/urls.txt");
	public Path textFile2 = Paths.get("lib/output.txt");
	public String urlsFile = "lib/urls.txt";
	public String outputFile = "lib/output.txt";
	public String proprtyFile = "properties.properties";
	public String create_db_sql = "sql/create_db.sql";


	// JavaScripts
	public static String refreshJS = "var scripts =  document.getElementsByTagName('script');"
			+ "var torefreshs = ['HBO.min.js'];"
			+ // list of js to be refresh
			"var key = 1;"
			+ // change this key every time you want force a refresh
			"for(var i=0;i<scripts.length;i++){"
			+ "for(var j=0;j<torefreshs;j++){"
			+ "if(scripts[i].src && (scripts[i].src.indexOf(torefreshs[j]) > -1)){"
			+ "new_src = scripts[i].src.replace(torefreshs[j],torefreshs[j] + 'k=' + key );"
			+ "scripts[i].src = new_src;" + // change src in order to refresh js
			"}" + "}" + "}";

	public static String reloadJS = "window.location.reload(true)";

}
