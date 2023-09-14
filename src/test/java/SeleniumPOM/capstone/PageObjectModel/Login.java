package SeleniumPOM.capstone.PageObjectModel;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
	
	WebDriverWait wait;
	WebDriver driver;

	//variables
	
	@FindBy(xpath = "/html/body/div[2]/div/div/button")
	WebElement cross;
	
	@FindBy(linkText = "Login")
	WebElement loginTab;
	
	@FindBy(linkText = "Sign in")
	WebElement signIn;
	
	@FindBy(linkText = "My Profile")
	WebElement myProfile;
	
	@FindBy(xpath = "/html/body/div[1]/div/div[3]/div/div[2]/div/form/div[1]/input")
	WebElement phone;
	
	@FindBy(xpath = "/html/body/div[1]/div/div[3]/div/div[2]/div/form/div[3]/button")
	WebElement requestOtp;
	
	//constructor
	public Login(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	//Methods
	
	public void clickCross() {
		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait1.until(ExpectedConditions.elementToBeClickable(cross));
		cross.click();
	}
	
	public void clickLoginTab() {
		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			Actions act=new Actions(driver);
			act.moveToElement(loginTab).perform();
			wait1.until(ExpectedConditions.elementToBeClickable(myProfile));
			this.myProfile.click();
			
		}catch (Exception e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", signIn);
		}
	}	
	
	public void sendPhoneNumber(String phn) {
		wait.until(ExpectedConditions.elementToBeClickable(phone));
		phone.sendKeys(phn);
	}
	
	public void clickRequestOtp() {
		wait.until(ExpectedConditions.elementToBeClickable(requestOtp));
		this.requestOtp.click();
	} 
	
}
