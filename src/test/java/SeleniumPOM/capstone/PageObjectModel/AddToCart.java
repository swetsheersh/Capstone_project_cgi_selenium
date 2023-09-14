package SeleniumPOM.capstone.PageObjectModel;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart {

	WebDriverWait wait;
	WebDriver driver;

	//variables
	@FindBy(xpath = "/html/body/div[2]/div/div/button")
	WebElement cross;
	
	@FindBy(name="q")
	WebElement search;
	
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[2]/div[1]/div[2]")
	WebElement mobile;
	
	@FindBy(xpath="/html/body/div[1]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button")
	WebElement addtocart;
	
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div/div[1]/div/div[4]/div/form/button/span")
	WebElement placeorder;
	
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div/div[2]/div[1]/div/div/div/div[4]/div/div[1]/div")
	WebElement price;
	
	
	//constructor
	public AddToCart(WebDriver driver) {
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

	public void clickmobile() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(mobile));
		mobile.click();
	}
	
	public void selectAddToCart() {
		wait.until(ExpectedConditions.elementToBeClickable(addtocart));
		addtocart.click();
	}
	
	public String getNameplaceorder() {
		return placeorder.getText();
	}
	
	public String getPrice() {
		wait.until(ExpectedConditions.elementToBeClickable(price));
		return price.getText();
	}
		
}
