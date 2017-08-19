package genericLibrary;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Base extends CommonActions{
	
	public WebDriver ff ;
	public static ExtentReports eReports;
	public ExtentTest startTest;
	public String btype;
	
	@BeforeSuite(groups={"qa","uat","prod"})
	public void initSetup(){
		eReports = new ExtentReports("E:\\AugustReports\\Html\\Reports_" + getDatetimeStamp() + ".html");		
	}
	
	@Parameters("browser")
	@BeforeMethod(groups={"qa","uat","prod"})
	public void launchAPP(String brow){
		btype = brow;
		if(brow.equals("ff")){
			ff=new FirefoxDriver();
			
		}else if(brow.equals("ch")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
			ff = new ChromeDriver();
		}else if(brow.equals("ie")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\IEDriverServer.exe");
			ff = new InternetExplorerDriver();
		}

	 ff.get("http://books.rediff.com/");
	 ff.manage().window().maximize();
	 ff.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
	
	@AfterMethod(groups={"qa","uat","prod"})
	public void tearDown(ITestResult result) throws Exception{
		
	if(result.getStatus()==ITestResult.SUCCESS){
		startTest.log(LogStatus.PASS, "Test Completion","Test Case Successfully Validated");
	}else if(result.getStatus()==ITestResult.FAILURE){
		startTest.log(LogStatus.FAIL, "Test Completion", result.getThrowable() + startTest.addScreenCapture(fullscreen()));
	}
		
		
		
		
	eReports.endTest(startTest);
	eReports.flush();
	ff.quit();
	}
	
	
//	datetimestamp
	public String getDatetimeStamp(){	
		
		Date date = new Date();
		SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
		return sformat.format(date);		
	}
	
//	capture screenshot
	public String fullscreen() throws Exception{
		
		TakesScreenshot screen = (TakesScreenshot)ff;
		File screenshot = screen.getScreenshotAs(OutputType.FILE);
		String path = "E:\\screenshotss\\s1_" + getDatetimeStamp() + ".png";
		FileUtils.copyFile(screenshot, new File(path));
		return path;
		
	}
	
//	capture element screenshot
	public String elementscreen(WebElement ele) throws Exception{
		
		File screenshot1 = ((TakesScreenshot)ff).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot1);

		// Get the location of element on the page
		 int xcor = ele.getLocation().getX();
		 int ycor = ele.getLocation().getY();
		 
		 

		// Get width and height of the element
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();

		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot= fullImg.getSubimage(xcor, ycor,eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot1);
		String path = "E:\\screenshotss\\s1_" + getDatetimeStamp() + ".png";
		FileUtils.copyFile(screenshot1, new File(path));
		return path;
		
	}
	
	
	
	

}
