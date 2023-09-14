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

public class RemoveCart {

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
	
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div/div[1]/div/div[3]/div/div[1]/div[1]/div[1]/a")
	WebElement pname;
	
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div/div[1]/div/div[3]/div/div[1]/div[1]/span[2]")
	WebElement pprice;
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div/div/div/div[2]/div/div[1]")
	WebElement verifycart;
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div/div/div/div[2]/div/button")
	WebElement getlogin;
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div/div[1]/div/div[3]/div/div[3]/div[2]/div[2]")
	WebElement remov;
	@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div/div[3]/div/div[2]")
	WebElement removepopup;
	
	//constructor
	public RemoveCart(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	//Methods
	public void clickCross() {
		wait.until(ExpectedConditions.elementToBeClickable(cross));
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
	
	public String getPname() {
		wait.until(ExpectedConditions.elementToBeClickable(pname));
		return pname.getText();
	}
	public String verifyCart() {
		wait.until(ExpectedConditions.elementToBeClickable(verifycart));
		return verifycart.getText();
	}
	public String getloginverify() {
		wait.until(ExpectedConditions.elementToBeClickable(getlogin));
		return getlogin.getText();
	}
	
	public String getPprice() {
		wait.until(ExpectedConditions.elementToBeClickable(pprice));
		return pprice.getText();
	}
	public void clickremove() throws InterruptedException {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//Actions act=new Actions(driver);
		//act.moveToElement(remov).perform();
		wait.until(ExpectedConditions.elementToBeClickable(remov));
		js.executeScript("arguments[0].click()",remov);
		//remov.click();
	}
	public void clickremovepopup() throws InterruptedException {
		Actions act=new Actions(driver);
		act.moveToElement(removepopup).perform();
		wait.until(ExpectedConditions.elementToBeClickable(removepopup));
		removepopup.click();
		
	}
	
		
}
