package SeleniumPOM.capstone.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//Class having a static method to capture screenshot
public class CaptureScreenShot {

	//capture screenshot in case of failure
	
	public static String getScreenShot(WebDriver driver,String screenshotName)throws Exception{
		MyUtility util=new MyUtility();
		String dateName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		//after execution ,you could see a folder "failedTestsScreenshots" under src folder
		String destination =util.getPathOfScreenShots()+"\\"+screenshotName+dateName+".jpg";
		File finalDestination=new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
				
	}
}
