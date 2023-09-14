package SeleniumPOM.capstone.utilities;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

//class having static method for generating driver object
public class NewDriver {
	//method returns driver object
	public static WebDriver getDriver() throws Exception {
		
		MyUtility utility=new MyUtility();
		WebDriver driver = null;
		
		//Cross Browser Test
		 if(utility.getDataFromProperties("browser").equals("chrome")) {
			ChromeOptions options=new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver=new ChromeDriver(options);
		 }else if(utility.getDataFromProperties("browser").equals("firefox")) {
			FirefoxOptions options=new FirefoxOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver=new FirefoxDriver(options);
		 }else if(utility.getDataFromProperties("browser").equals("edge")) {
			EdgeOptions options=new EdgeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver=new EdgeDriver(options);
		 }
		 return driver;
	}
	
}
