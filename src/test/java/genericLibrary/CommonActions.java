package genericLibrary;

import org.openqa.selenium.WebElement;

public class CommonActions {
	
	public void EnterValue(WebElement ele,String val){
		ele.sendKeys(val);
		
	}
		
	public void Click(WebElement ele){
		ele.click();		
	}
	
	public String GetText(WebElement ele){
		return ele.getText();	
	}
	
	
	
	
	
	

}
