package pageFactory;

import genericLibrary.CommonActions;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonActions {
	
	
		@FindBy(name="logid")
		public WebElement txtUserid;
		
		@FindBy(name="pswd")
		public WebElement txtPwd;
		
		@FindBy(xpath="//input[@value='Login' and @type='submit']")
		public WebElement btnLogin;
		
		@FindBy(xpath="//table[2]//table[1]//table[1]//table[1]//tr[2]//b")
		public WebElement msgInvalidLoginError;
	
	
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	

//	Login method
	public void enterUserCredetials(Map<String,String> hm){				
//		txtUserid.sendKeys(username);
//		txtPwd.sendKeys(Password);
//		btnLogin.click();
				
		EnterValue(txtUserid,hm.get("Username"));
		EnterValue(txtPwd,hm.get("Password"));
		Click(btnLogin);
		
			
	}
	
	
	
	
}




