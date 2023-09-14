package SeleniumPOM.capstone.testcases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import SeleniumPOM.capstone.PageObjectModel.SearchProduct;
import SeleniumPOM.capstone.PageObjectModel.Login;
import SeleniumPOM.capstone.PageObjectModel.FilterAndVerify;
import SeleniumPOM.capstone.PageObjectModel.AddToCart;
import SeleniumPOM.capstone.PageObjectModel.RemoveCart;
import SeleniumPOM.capstone.utilities.CaptureScreenShot;
import SeleniumPOM.capstone.utilities.MyUtility;
import SeleniumPOM.capstone.utilities.NewDriver;

public class NewTest {
	//variables
	//WebDriver object
	WebDriver driver;
	
	//POM Class Objects
	SearchProduct reg;
	AddToCart log;
	Login cp;
	FilterAndVerify filter;
	RemoveCart remove;
	
	//Utility class objects
	MyUtility utility;
	//HashMap for saving data
	HashMap<String,String> map;
	
	//extent Reports
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;
	
	//extent report configuration before executing tests
	@BeforeTest
	public void beforeTest() throws Exception {
		//to create the report file
		String dateName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		this.spark=new ExtentSparkReporter(this.utility.getPathOfReport()+"/"+dateName+"myReport.html");
		spark.config().setDocumentTitle("Automation Report");//title of the report
		spark.config().setReportName("Capstone_Project_Selenium");//name of the report
		spark.config().setTheme(Theme.DARK);
		
		//create entry in report 
		
		this.extent=new ExtentReports();
		extent.attachReporter(this.spark);
		extent.setSystemInfo("Host Name", this.utility.getDataFromProperties("host"));
		extent.setSystemInfo("os",this.utility.getDataFromProperties("os"));
		extent.setSystemInfo("Tester Name", this.utility.getDataFromProperties("tester"));
		extent.setSystemInfo("Browser", this.utility.getDataFromProperties("browser"));
		
	}
	//Logging Results to Extent Report
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Case Failed is "+result.getName());//to add name in extent report
			test.log(Status.FAIL, "Test Case Failed is "+result.getThrowable());//to add error/exception
			
			String screenshotpath=CaptureScreenShot.getScreenShot(driver, result.getName());
		
			test.addScreenCaptureFromPath(screenshotpath);
		}else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case Skipped is "+result.getName());
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case Passed is "+result.getName());
		}
	}
	
	  //It will execute before each test case
	  @BeforeMethod
	  public void beforeMethod() {
		  System.out.println("Executing Test...");
		  this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  this.reg=new SearchProduct(driver);
		  this.log=new AddToCart(driver);
		  this.cp=new Login(driver);
		  this.filter=new FilterAndVerify(driver);
		  this.remove=new RemoveCart(driver);
	  }
	  
	  //After all Test cases ,saving the report
	  @AfterTest
		public void afterTest() {
			extent.flush();
	  }
	  
	  //Execute before suite
	  @BeforeSuite
	  public void beforeSuite() throws Exception {
		  System.out.println("Register Test...");
		  this.utility=new MyUtility();
		  this.map= new HashMap<String, String>();
		  //getting driver on the basis of properties file
		  this.driver=NewDriver.getDriver();
		  //Navigating to URL
		  driver.get("https://www.flipkart.com/");
		  driver.manage().window().maximize();
	  }
	  
	  //After suite ,it will quit the browser
	  @AfterSuite
	  public void afterSuite() {
		  //this.driver.quit();
	  }
	  
	  //TestCases
	  
	  @Test(dataProvider = "dp",priority = 1)
	  public void searchProduct(String d1,String d2,String d3,String searchData,String exp) throws Exception {

		  this.test=extent.createTest("search Product");
		  //Automation code using POM class
		  //Sometime popup is not visible, so try block is there
		  this.driver.navigate().to("https://www.flipkart.com/");
		  try {
			  this.reg.clickCross();
		  }catch (Exception e) {
			  System.out.println("PopUp not found");
			  test.log(Status.PASS, "popup not found");
		  }
		  this.reg.sendDataToSearch(searchData);
		  this.reg.submitSearch();
		  test.log(Status.PASS, "Search Bar Found");
		  this.reg.clickIphone14();
		  Thread.sleep(5000);
		  //Handling Window
		  List<String> l1=new ArrayList<String>(driver.getWindowHandles());
		  if(l1.size()>1) {
			  driver.switchTo().window(l1.get(1));
		  }
		  test.log(Status.PASS, "Same Product Found");
		  test.createNode("Verify product name");
		  assertEquals(this.driver.getPageSource().contains(d1),Boolean.parseBoolean(exp));
		  test.createNode("Verify product price");
		  assertEquals(this.reg.getPrice().contains(d2),Boolean.parseBoolean(exp));
		  test.createNode("Verify page Title");
		  assertEquals(this.driver.getTitle(),d3);
		  map.put(reg.getNameIphone14(), reg.getPrice());
		  System.out.println(map.get(reg.getNameIphone14()));
		  
		  Thread.sleep(1000);
		  driver.manage().deleteAllCookies();
		  
	  }
	  
	  @Test(dataProvider = "dp1",priority = 2)
	  public void addToCart(String d1,String d2,String exp) throws Exception {
		  
		  this.test=extent.createTest("Add To Cart");
		  //Automation code using POM class
		  Thread.sleep(3000);
		  
		  this.driver.navigate().to("https://www.flipkart.com/");
		  try {
			  this.log.clickCross();
		  }catch (Exception e) {
			  System.out.println("PopUp not found");
			  test.log(Status.PASS, "popup not found");
		  }
		  this.log.sendDataToSearch("mobile");
		  this.log.submitSearch();
		  this.log.clickmobile();

		  //Handling Window
		  List<String> l1=new ArrayList<String>(driver.getWindowHandles());
		  if(l1.size()>2) {
			  driver.switchTo().window(l1.get(2));
		  }
		  this.log.selectAddToCart();
		  test.log(Status.PASS, "Add to cart button Found");
		  Thread.sleep(3000);
		  test.createNode("Verify URL");
		  assertEquals(this.driver.getCurrentUrl(),d1);
		  Thread.sleep(3000);
		  test.createNode("Verify Product Added to cart");
		  assertEquals(this.log.getPrice(),d2);
		  
		  Thread.sleep(5000);
		  driver.manage().deleteAllCookies();
		  
		  
	  }
	  
	  @Test(dataProvider = "dp2",priority = 3)
	  public void filterAndVerify(String d1,String d2,String d3,String exp) throws Exception {
		  
		  this.test=extent.createTest("Filter And Verify the Product");
		  //Automation code using POM class
		  
		  this.driver.navigate().to("https://www.flipkart.com/");
		  
		  try {
			  this.filter.clickCross();
		  }catch (Exception e) {
			  System.out.println("PopUp not found");
			  test.log(Status.PASS, "popup not found");
		  }
		  
		  this.filter.clickMobile();
		  test.log(Status.PASS, "product verified");
		  this.filter.filterApple();
		  this.filter.filterNewFirst();
		  test.log(Status.PASS, "Filter Working Properly");
		  Thread.sleep(2000);
		  this.filter.clickIphone14();
		  
		  Thread.sleep(5000);
		  //Handling Window
		  List<String> l1=new ArrayList<String>(driver.getWindowHandles());
		  if(l1.size()>3) {
			  driver.switchTo().window(l1.get(3));
		  }
		  test.createNode("Verify product name");
		  assertEquals(this.driver.getPageSource().contains(d1),Boolean.parseBoolean(exp));

		  test.createNode("Verify page Title");
		  assertEquals(this.driver.getTitle().contains(d3),Boolean.parseBoolean(exp));
		  
		  Thread.sleep(1000);
		  driver.manage().deleteAllCookies();
		  
	  }
	  
	  @Test(dataProvider = "dp3",priority = 0)
	  public void verifyLogin(String d1,String d2,String exp) throws Exception {
		  
		  this.test=extent.createTest("Verify Login Whether asking for otp or You are Human");
		  //Automation code using POM class
		  
		  try {
			  this.cp.clickCross();
		  }catch (Exception e) {
			  System.out.println("PopUp not found");
			  test.log(Status.PASS, "popup not found");		  }
		  
		  this.cp.clickLoginTab();
		  test.createNode("Verify Login Page");
		  assertEquals(this.driver.getCurrentUrl(),d1);
		  test.log(Status.PASS, "verify Login Button");
		  
		  this.cp.sendPhoneNumber(d2);
		  this.cp.clickRequestOtp();
		  test.log(Status.PASS, "Verify RequestOTP");
		  Thread.sleep(Duration.ofSeconds(15));
		  
		  
		  
		  test.createNode("Verifying Software Blocks Automated Action or Asking for OTP");
		  if(this.driver.getPageSource().contains("Please enter the OTP sent to")){
			  assertEquals(this.driver.getPageSource().contains("Please enter the OTP sent to"),Boolean.parseBoolean(exp));
		  }else {
			  assertEquals(this.driver.getPageSource().contains("Press"),Boolean.parseBoolean(exp));
		  }
		  Thread.sleep(1000);
		  driver.manage().deleteAllCookies();
		  
	  }
	  
	  @Test(dataProvider = "dp4",priority = 4)
	  public void removeProductFromCart(String d1,String d2,String exp) throws Exception {
		  
		  this.test=extent.createTest("Remove Product From Cart");
		  //Automation code using POM class
		  
		  Thread.sleep(3000);
		  
		  this.driver.navigate().to("https://www.flipkart.com/");
		  
		  try {
			  this.remove.clickCross();
		  }catch (Exception e) {
			  System.out.println("PopUp not found");
			  test.log(Status.PASS, "popup not found");
		  }
		  
		  this.remove.sendDataToSearch("mobile");
		  this.remove.submitSearch();
		  this.remove.clickmobile();
		  
		//Handling Window
		  List<String> l1=new ArrayList<String>(driver.getWindowHandles());
		  if(l1.size()>4) {
			  driver.switchTo().window(l1.get(4));
		  }
		  
		  this.remove.selectAddToCart();
		  /*System.out.println(this.log.getPrice());
		  System.out.println(this.driver.getCurrentUrl());*/
		  map.put(remove.getPname(), remove.getPprice());
		  if (map.containsKey(remove.getPname())) {
			   Object value = map.get(remove.getPname());
			 System.out.println("Pname : " + remove.getPname() +" price :"+ value);
			 }
		  
		  test.log(Status.PASS, "verify cart");
		  
		  JavascriptExecutor js=(JavascriptExecutor)driver;
		  js.executeScript("window.scrollBy(0,250)");
		  this.remove.clickremove();
		  /*Thread.sleep(3000);
		  Alert alert=driver.switchTo().alert();
		  alert.accept();*/
		  Thread.sleep(3000);
		  this.remove.clickremovepopup();
		  test.log(Status.PASS, "verify Remove Button");
		  test.createNode("Verify product is removed from cart");
		  assertEquals(this.remove.verifyCart(),d1);
		  test.createNode("Verify product price");
		  assertEquals(this.remove.getloginverify(),d2);
		  Thread.sleep(1000);
		  driver.manage().deleteAllCookies();
		  
	  }
	  
	  
	  //Data Provider

	  @DataProvider
	  public Object[][] dp() throws Exception {
		  FileInputStream file=new FileInputStream(this.utility.getPathOfXlsx());
	  	  XSSFWorkbook workbook =new XSSFWorkbook(file);
	  	  XSSFSheet sheet=workbook.getSheetAt(0);
	  	  int rowNum=sheet.getLastRowNum();
	  	  int colNum=sheet.getRow(0).getLastCellNum();
	  	  Object[][] myobj = new Object[rowNum][colNum];
	  	  for(int i=1;i<=rowNum;i++) {
	  		  XSSFRow row=sheet.getRow(i);
	  		  for(int j=0;j<colNum;j++) {
	  			  try {
	  			  String value=row.getCell(j).toString();
	  			  System.out.print(value+" ");
	  			  myobj[i-1][j]=value;
	  			  }catch(Exception e) {}
	  		  }
	  		  
	  		  System.out.println("");
	  	  }
	    return myobj;
	  }
	  
	  @DataProvider
	  public Object[][] dp1() throws Exception {
		  FileInputStream file=new FileInputStream(this.utility.getPathOfXlsx());
	  	  XSSFWorkbook workbook =new XSSFWorkbook(file);
	  	  XSSFSheet sheet=workbook.getSheetAt(1);
	  	  int rowNum=sheet.getLastRowNum();
	  	  int colNum=sheet.getRow(0).getLastCellNum();
	  	  Object[][] myobj = new Object[rowNum][colNum];
	  	  for(int i=1;i<=rowNum;i++) {
	  		  XSSFRow row=sheet.getRow(i);
	  		  for(int j=0;j<colNum;j++) {
	  			  try {
	  			  String value=row.getCell(j).toString();
	  			  System.out.print(value+" ");
	  			  myobj[i-1][j]=value;
	  			  }catch(Exception e) {}
	  		  }
	  		  
	  		  System.out.println("");
	  	  }
	    return myobj;
	  }
	  
	  @DataProvider
	  public Object[][] dp2() throws Exception {
		  FileInputStream file=new FileInputStream(this.utility.getPathOfXlsx());
	  	  XSSFWorkbook workbook =new XSSFWorkbook(file);
	  	  XSSFSheet sheet=workbook.getSheetAt(2);
	  	  int rowNum=sheet.getLastRowNum();
	  	  int colNum=sheet.getRow(0).getLastCellNum();
	  	  Object[][] myobj = new Object[rowNum][colNum];
	  	  for(int i=1;i<=rowNum;i++) {
	  		  XSSFRow row=sheet.getRow(i);
	  		  for(int j=0;j<colNum;j++) {
	  			  try {
	  			  String value=row.getCell(j).toString();
	  			  System.out.print(value+" ");
	  			  myobj[i-1][j]=value;
	  			  }catch(Exception e) {}
	  		  }
	  		  
	  		  System.out.println("");
	  	  }
	    return myobj;
	  }
	  
	  @DataProvider
	  public Object[][] dp3() throws Exception {
		  FileInputStream file=new FileInputStream(this.utility.getPathOfXlsx());
	  	  XSSFWorkbook workbook =new XSSFWorkbook(file);
	  	  XSSFSheet sheet=workbook.getSheetAt(3);
	  	  int rowNum=sheet.getLastRowNum();
	  	  int colNum=sheet.getRow(0).getLastCellNum();
	  	  Object[][] myobj = new Object[rowNum][colNum];
	  	  for(int i=1;i<=rowNum;i++) {
	  		  XSSFRow row=sheet.getRow(i);
	  		  for(int j=0;j<colNum;j++) {
	  			  try {
	  			  String value=row.getCell(j).toString();
	  			  System.out.print(value+" ");
	  			  myobj[i-1][j]=value;
	  			  }catch(Exception e) {}
	  		  }
	  		  
	  		  System.out.println("");
	  	  }
	    return myobj;
	  }
	  
	  @DataProvider
	  public Object[][] dp4() throws Exception {
		  FileInputStream file=new FileInputStream(this.utility.getPathOfXlsx());
	  	  XSSFWorkbook workbook =new XSSFWorkbook(file);
	  	  XSSFSheet sheet=workbook.getSheetAt(4);
	  	  int rowNum=sheet.getLastRowNum();
	  	  int colNum=sheet.getRow(0).getLastCellNum();
	  	  Object[][] myobj = new Object[rowNum][colNum];
	  	  for(int i=1;i<=rowNum;i++) {
	  		  XSSFRow row=sheet.getRow(i);
	  		  for(int j=0;j<colNum;j++) {
	  			  try {
	  			  String value=row.getCell(j).toString();
	  			  System.out.print(value+" ");
	  			  myobj[i-1][j]=value;
	  			  }catch(Exception e) {}
	  		  }
	  		  
	  		  System.out.println("");
	  	  }
	    return myobj;
	  }
}
