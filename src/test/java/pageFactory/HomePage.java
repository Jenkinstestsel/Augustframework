package pageFactory;

import genericLibrary.CommonActions;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonActions{
	
	@FindBy(linkText="Sign In")
	public WebElement lkSignIn;	
	
	@FindBy(id="username")
	public WebElement msgUsername;
	
//	search functionality
	@FindBy(id="srchword")
	public WebElement txtSearchfield;
	
	@FindBy(className="newsrchbtn")
	public WebElement btnSearch;
	
	@FindBy(id="find")
	public WebElement msgSearchCount;
	
	@FindBy(className="sorrymsg")
	public WebElement msgSearchErrormsg;
	
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);		
	}
	
	public void bookSearch(Map<String,String> hm){
		
		EnterValue(txtSearchfield, hm.get("Searchoption"));
		Click(btnSearch);
		
	}
	

}
