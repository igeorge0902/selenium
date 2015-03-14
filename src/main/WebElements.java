package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



/**
 * Use this interface, if you want to implement elements for {@link www.hbogo.hu} website.
 */

public interface WebElements { 
	
	//playButton
	public static String[] playTrailer = new String[]{"PlayTrailer"};
	public static String[] playContent = new String[]{"PlayContent"};
	public static String[] playFree = new String[]{"PlayFree"};
	public static String[] playLive = new String[]{"PlayLive"};
	public static String[] playExtra = new String[]{"PlayExtra"};
	public static String[] playInteractive = new String[]{"PlayInteractive"};
	public static String[] playFreeInteractive = new String[]{"PlayFreeInteractive"};
	
	//playButton also
    public static String[] playContents = new String[]{
    	"PlayTrailer",
    	"PlayContent", 
    	"PlayFree", 
    	"PlayLive", 
    	"PlayExtra", 
    	"PlayInteractive", 
    	"PlayFreeInteractive"};
    
    //play states
    public static String [] playPause = new String []{"PlayerManager.playPause()"};
    
    //******Movie Categories
    //Action Category
    public static String [] actionGroup = new String [] {"GoogleHelper.track('CLICKSTAT','MENU_CATEGORIES','Akció, Kaland');"};

    
    //WebPage String elements
    public static String SignInRegister_button = "headerButtonLogin";
    public static String Register_button = "div.settings_description_white > #headerButtonLogin";
    public static String Operator_button = "OperatorId";
    public static String HBO_GO_Vip_Club_Hungary = "OperatorId_f320aa2c-e40e-49c2-8cdd-1ebef2ac6f26";
    public static String Voucher_code = "SpecificData";
    public static String Next_Step = "button.button_submit.next";
    public static String EmailAddress = "EmailAddress";
    public static String Terms_of_use = "Terms_theme";
    
    public static String ContentUrl1 = "/content/the-dog-and-the-cat-from-here-to-there--1689771606";
    public static String OffersScreen = "/group/offers";
    public static String PlayButton1 = "play-button-07572899-1f5a-4f4e-9204-2fcc63a82775";
    
    
    //WebPage elements
    public WebElement playPuttony = WebDriverManager.driver.findElement(By.id(PlayButton1));
	public static String [] contentname = new String[]{"content/"};
	public static String [] hashmarks = new String [] {"#"};

    
    //Player elements
    public static String play_pause = "playbackControls";
    public WebElement play_Pause = WebDriverManager.driver.findElement(By.id(play_pause));

    public static String playbackTitle ="playbackTitle";
    public WebElement playback_Title = WebDriverManager.driver.findElement(By.id(playbackTitle));
    
    public static String playbackInfo ="playbackInfo";
    public WebElement playback_Info = WebDriverManager.driver.findElement(By.id(playbackInfo));
    
    public static String positionsSeek = "seekbar";
    public WebElement SeekDot = WebDriverManager.driver.findElement(By.id(positionsSeek));
    
    public static String playbackElapsedTime = "playbackElapsedTime";
    public WebElement playbackElapsed_Time = WebDriverManager.driver.findElement(By.id(playbackElapsedTime));
    


}
