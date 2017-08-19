package projectScripts;

import org.testng.annotations.Test;
import genericLibrary.Base;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageFactory.HomePage;
import pageFactory.LoginPage;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Script_Login extends Base {
	
	Logger loginLog = Logger.getLogger(Script_Login.class);
	
//	Invalid Login Test Script
	@Test(dataProvider="invalidLogin",dataProviderClass = dataProvider.CommonDataProvider.class,groups={"qa","uat"})
	public void invalidLogin(Map<String,String> hm) throws Exception{
		String tcid = hm.get("ID");
		String order = hm.get("Order");
		startTest = eReports.startTest(tcid + "_" + order);		 
		startTest.assignCategory(btype);
//		Home Page
		 HomePage hp = new HomePage(ff);
		 LoginPage lp = new LoginPage(ff);
//		 change to page factory
//		 ff.findElement(By.linkText("Sign In")).click();
		 Click(hp.lkSignIn);
		 
		 
//		 Enter user Credentials
//		 ff.findElement(By.name("logid")).sendKeys(hm.get("Username"));
//		 ff.findElement(By.name("pswd")).sendKeys(hm.get("Password"));
//		 ff.findElement(By.xpath("//input[@value='Login' and @type='submit']")).click();
		 
		 lp.enterUserCredetials(hm);	 
		 
		 
//		 WebElement errmsg = ff.findElement(By.xpath("//table[2]//table[1]//table[1]//table[1]//tr[2]//b"));
//		 String Act_errmsg = errmsg.getText();
		 
		 String Act_errmsg = GetText(lp.msgInvalidLoginError);
		 
		 
		 if(Act_errmsg.equals(hm.get("Message"))){
			 loginLog.info("passed as Invalid login is working");
			 startTest.log(LogStatus.PASS, "Invalid login validation","passed as Invalid login is working");
		 }else{
			 loginLog.error("Failed as Actual is " + Act_errmsg + " and expected was " + hm.get("Message"));
			 startTest.log(LogStatus.FAIL, "Invalid login validation","Failed as Actual is " + Act_errmsg + " and expected was " + hm.get("Message") + startTest.addScreenCapture(elementscreen(lp.msgInvalidLoginError)));
		 }	
	}
	
	
//	Valid Login Test Script
	@Test(dataProvider="validLogin",dataProviderClass = dataProvider.CommonDataProvider.class,groups={"qa","uat","prod"})
	public void validLogin(Map<String,String> hm) throws Exception{
		 
		String tcid = hm.get("ID");
		String order = hm.get("Order");		
		 startTest = eReports.startTest(tcid + "_" + order);
		 startTest.assignCategory(btype);
		 
		 HomePage hp = new HomePage(ff);
		 LoginPage lp = new LoginPage(ff);
//		 ff.findElement(By.linkText("Sign In")).click();
		 Click(hp.lkSignIn);
		 
//		 Enter user Credentials
//		 ff.findElement(By.name("logid")).sendKeys(hm.get("Username"));
//		 ff.findElement(By.name("pswd")).sendKeys(hm.get("Password"));
//		 ff.findElement(By.xpath("//input[@value='Login' and @type='submit']")).click();
		 lp.enterUserCredetials(hm);
		 
//		 String Actual_user = ff.findElement(By.id("username")).getText();
		 String Actual_user = GetText(hp.msgUsername);
		 
		 if(Actual_user.equals(hm.get("Message"))){
			 loginLog.info("passed as Valid login is working");
			 startTest.log(LogStatus.PASS, "Valid login validation","passed as Valid login is working");
		 }else{
			 loginLog.error("Failed as Actual is " + Actual_user + " and expected was " + hm.get("Message"));
			 startTest.log(LogStatus.FAIL, "Valid login validation","Failed as Actual is " + Actual_user + " and expected was " + hm.get("Message") + startTest.addScreenCapture(fullscreen()));
		 }
		 
		 
	}
	
	
	
	
	
	
	

}
