package projectScripts;

import org.testng.annotations.Test;
import genericLibrary.Base;

import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageFactory.HomePage;

public class Script_Search extends Base{
	
	@Test(dataProvider="invalidSearch",dataProviderClass=dataProvider.CommonDataProvider.class,groups={"prod"})
	public void invalidSearch(Map<String, String> hm) throws Exception{
		String tcid =hm.get("ID");
		String order = hm.get("Order");
		startTest = eReports.startTest(tcid + "_" + order);		
		startTest.assignCategory(btype);
		if(ff.getTitle().toLowerCase().contains("books")){
			startTest.log(LogStatus.PASS, "Home Page Validation","Successfuly validated");
		}else{
			startTest.log(LogStatus.FAIL, "Home Page Validation","Fialed as not able to locate HomePage" + ff.getTitle()  + startTest.addScreenCapture(fullscreen()));
		}
		
		
		HomePage hp = new HomePage(ff);
		hp.bookSearch(hm);

		String Act_errormessage = GetText(hp.msgSearchErrormsg);
		if(Act_errormessage.equals(hm.get("Message"))){
			startTest.log(LogStatus.PASS, "Invalid Search Validation","Successfuly validated");			
		}else{
			startTest.log(LogStatus.FAIL, "Invalid Search Validation","Failed as Acutal is:" + Act_errormessage + " expected was " + hm.get("Message") + startTest.addScreenCapture(fullscreen()) );						
		}
		
	}
	
	@Test(dataProvider="validSearchCount",dataProviderClass=dataProvider.CommonDataProvider.class,groups={"qa"})
	public void validSearch(Map<String, String> hm) throws Exception{
		String tcid = hm.get("ID");
		String order = hm.get("Order");
		startTest = eReports.startTest(tcid + "_" + order);		
		startTest.assignCategory(btype);
		if(ff.getTitle().toLowerCase().contains("books")){
			startTest.log(LogStatus.PASS, "Home Page Validation","Successfuly validated");
		}else{
			startTest.log(LogStatus.FAIL, "Home Page Validation","Fialed as not able to locate HomePage" + ff.getTitle() + startTest.addScreenCapture(fullscreen()));
		}
		
		
		HomePage hp = new HomePage(ff);
		hp.bookSearch(hm);
		
		String Act_count = GetText(hp.msgSearchCount);
		if(Act_count.equals(hm.get("Message"))){
			startTest.log(LogStatus.PASS, "valid Search Validation","Successfuly validated");			
		}else{
			startTest.log(LogStatus.FAIL, "valid Search Validation","Failed as Acutal is:" + Act_count + " expected was " + hm.get("Message") + startTest.addScreenCapture(fullscreen()) );						
		}
		
	}
	
	
	
	
	
	
	
	

}
