package SeleniumPOM.capstone.PageObjectModel;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchProduct {

	WebDriverWait wait;
	WebDriver driver;

	//variables
	
	@FindBy(xpath = "/html/body/div[2]/div/div/button")
	WebElement cross;
	
	@FindBy(name="q")
	WebElement search;
	
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[2]/div[1]/div[1]")
	WebElement iphone14;
	
	//@FindBy(xpath = "/html/body/div[1]/div/div[3]/div[1]/div[2]/div[3]/div/div[1]/h1/span")
	@FindBy(xpath="/html/body/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/h1/span")
	WebElement productName;
	
	@FindBy(xpath = "/html/body/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div[4]/div[1]/div/div[1]")
	WebElement price;
	
	//constructor
	public SearchProduct(WebDriver driver) {
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
	
	public void sendDataToSearch(String res) {
		wait.until(ExpectedConditions.elementToBeClickable(search));
		search.sendKeys(res);
	}
	
	public void submitSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(search));
		search.submit();
	}
	
	public void clickIphone14() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(iphone14));
		iphone14.click();
	}
	
	public String getNameIphone14() {
		return productName.getText();
	}
	
	public String getPrice() {
		//wait.until(ExpectedConditions.elementToBeClickable(price));
		return price.getText();
	}
	
}
