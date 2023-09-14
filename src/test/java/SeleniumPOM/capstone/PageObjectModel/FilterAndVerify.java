package SeleniumPOM.capstone.PageObjectModel;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterAndVerify {

	WebDriverWait wait;
	WebDriver driver;
	Actions act;

	//variables
	@FindBy(xpath = "/html/body/div[2]/div/div/button")
	WebElement cross;
	
	@FindBy(linkText = "Mobiles")
	WebElement mobile;
	
	@FindBy(xpath = "/html/body/div[1]/div/div[3]/div[3]/div[1]/div/section[3]/div[2]/div[1]/div[3]/div/label/div[2]")
	WebElement apple;
	
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div/div[2]/div[1]/div/div/div[2]/div[4]")
	WebElement newFirst;
	
	@FindBy(xpath = "/html/body/div/div/div[3]/div/div[2]/div[2]/div/div/div/a/div[2]/div[1]")
	WebElement iphone14;
	
	@FindBy(xpath = "/html/body/div[1]/div/div[3]/div[1]/div[2]/div[3]/div/div[1]/h1/span")
	WebElement productName;
	
	@FindBy(xpath = "/html/body/div[1]/div/div[3]/div[1]/div[2]/div[3]/div/div[4]/div[1]/div/div[1]")
	WebElement price;
	
	//constructor
	public FilterAndVerify(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		this.act=new Actions(driver);
	}
	//Methods

	public void clickCross() {
		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait1.until(ExpectedConditions.elementToBeClickable(cross));
		cross.click();
	}
	
	public void clickMobile() {
		wait.until(ExpectedConditions.elementToBeClickable(mobile));
		//act.moveToElement(mobile).perform();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", mobile);
		//this.mobile.click();
	}
	
	public void filterApple() {
		wait.until(ExpectedConditions.elementToBeClickable(apple));
		act.moveToElement(apple).perform();
		this.apple.click();
	}
	
	public void filterNewFirst() {
		wait.until(ExpectedConditions.elementToBeClickable(newFirst));
		act.moveToElement(newFirst).perform();
		this.newFirst.click();
	}
	
	public void clickIphone14() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(iphone14));
		iphone14.click();
	}
	
	public String getNameIphone14() {
		return productName.getText();
	}
	
	public String getPrice() {
		wait.until(ExpectedConditions.elementToBeClickable(price));
		return price.getText();
	}
		
}
