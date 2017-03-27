package WebDriverSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DatabaseApplication {
	public static WebDriver driver;
	
	public void launchApplication(){
		System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\geckodriver-v0.11.1-win32\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://computer-database.herokuapp.com/computers");
		}
	public void closeApplication(){
		driver.close();
		//driver.quit();
	}
	//Add a new computer with all valid details
	public void addNewComputer() throws InterruptedException{
		driver.findElement(By.id("add")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='name']")).sendKeys("AAA");
		driver.findElement(By.id("introduced")).sendKeys("2017-03-24");
		driver.findElement(By.id("discontinued")).sendKeys("2017-03-24");		
		Select dropDown = new Select(driver.findElement(By.name("company")));
		dropDown.selectByVisibleText("Sony");		
		driver.findElement(By.xpath(".//*[@id='main']/form/div/input")).click();
		Thread.sleep(2000);				
	}
	//Edit first record
	public void updateThisComputer() throws InterruptedException{
		driver.findElement(By.xpath(".//*[@id='main']/table/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='name']")).clear();
		driver.findElement(By.xpath(".//*[@id='name']")).sendKeys("NewComp");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("2017-01-01");
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("2017-01-01");
		Select dropDown = new Select(driver.findElement(By.name("company")));
		dropDown.selectByVisibleText("Thinking Machines");
		driver.findElement(By.xpath(".//*[@id='main']/form[1]/div/input")).click();
		Thread.sleep(2000);
	}
	
	public void deleteThisComputer() throws InterruptedException{
		driver.findElement(By.xpath(".//*[@id='main']/table/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='main']/form[2]/input")).click();
		Thread.sleep(1000);
	}
	public void getCountOfRecord(){
		if (driver.findElement(By.xpath(".//*[@id='main']/h1")).isDisplayed()){
			String countRec = driver.findElement(By.xpath(".//*[@id='main']/h1")).getText();
			System.out.println("Count of Records : "+countRec +" - PASSED");
		}
		else{
			System.out.println("Count of records - FAILED");
		}
	}
	public void filterByName() throws InterruptedException{
		driver.findElement(By.id("searchbox")).sendKeys("ACE");
		driver.findElement(By.id("searchsubmit")).click();	
		Thread.sleep(2000);
	}
	//Computer name field validation for blank computer name while adding a new computer
	public void ComputerNameBlankOnAddCompPage() throws InterruptedException{	
		driver.findElement(By.id("add")).click();
		Thread.sleep(2000);		
		driver.findElement(By.xpath(".//*[@id='main']/form/div/input")).click();
		Thread.sleep(2000);
		
		String visibilityAfter = driver.findElement(By.xpath(".//*[@id='name']")).getCssValue("border-bottom-color");
		if(visibilityAfter.equals("rgb(200, 120, 114)")){
			System.out.println("Computer name field highlighted in red color" + " : PASSED");
			//Click cancel button
			driver.findElement(By.xpath(".//*[@id='main']/form/div/a")).click();
			Thread.sleep(2000);
		}
		else{
			System.out.println("Computer name field not highlighted in red color" + " : FAILED");
		}	
	}			
	
	//Introduced date field validation for invalid date format while adding a new computer
	public void invalidIntroDateFormatOnAddCompPage() throws InterruptedException{
		driver.findElement(By.id("add")).click();
		Thread.sleep(2000);		
		driver.findElement(By.xpath(".//*[@id='name']")).sendKeys("NewComp");
		driver.findElement(By.id("introduced")).sendKeys("2017/01/01");
		driver.findElement(By.xpath(".//*[@id='main']/form/div/input")).click();
		Thread.sleep(2000);
		
		//identify color after clicking "create this computer" button : rgb(200, 120, 114)
		String visibilityColor = driver.findElement(By.id("introduced")).getCssValue("border-bottom-color");
		if(visibilityColor.equals("rgb(200, 120, 114)")){
		System.out.println("Introduced date field highlighted in red color" + " : PASSED");
		//Click cancel button
		driver.findElement(By.xpath(".//*[@id='main']/form/div/a")).click();
		Thread.sleep(2000);
		}
		else{
		System.out.println("Introduced date field not highlighted in red color" + " : FAILED");
		}
				
	}
	//Discontinued date field validation for invalid date format while adding a new computer
	public void invalidDiscontDateFormatOnAddCompPage() throws InterruptedException{
		driver.findElement(By.id("add")).click();
		Thread.sleep(2000);		
		driver.findElement(By.xpath(".//*[@id='name']")).sendKeys("NewComp");
		driver.findElement(By.id("discontinued")).sendKeys("2017/01/01");
		driver.findElement(By.xpath(".//*[@id='main']/form/div/input")).click();
		Thread.sleep(2000);
		
		//identify color after clicking "create this computer" button : rgb(200, 120, 114)
		String visibilityColor = driver.findElement(By.id("discontinued")).getCssValue("border-bottom-color");
		if(visibilityColor.equals("rgb(200, 120, 114)")){
			System.out.println("discontinued date field highlighted in red color" + " : PASSED");
			//Click cancel button
			driver.findElement(By.xpath(".//*[@id='main']/form/div/a")).click();
			Thread.sleep(2000);
		}
		else{
		System.out.println("discontinued date field not highlighted in red color" + " : FAILED");
		}	
	}
	
	//Computer name field validation for blank computer name while Editing an existing record
	public void ComputerNameBlankOnEditCompPage() throws InterruptedException{	
		driver.findElement(By.xpath(".//*[@id='main']/table/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='name']")).clear();	
		driver.findElement(By.xpath(".//*[@id='main']/form[1]/div/input")).click();
		Thread.sleep(2000);
		
		//identify color after clicking "Save this computer" button : rgb(200, 120, 114)
		String visibilityAfter = driver.findElement(By.xpath(".//*[@id='name']")).getCssValue("border-bottom-color");
		if(visibilityAfter.equals("rgb(200, 120, 114)")){
			System.out.println("Computer name field highlighted in red color" + " : PASSED");
			//Click cancel button
			driver.findElement(By.xpath(".//*[@id='main']/form/div/a")).click();
			Thread.sleep(2000);
			}
		else{
		System.out.println("Computer name field not highlighted in red color" + " : FAILED");
		}
	}
		//Introduced date field validation for invalid date format while Editing an existing record
	public void invalidIntroDateFormatOnEditCompPage() throws InterruptedException{
		driver.findElement(By.xpath(".//*[@id='main']/table/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(2000);		
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("2017/12/12");
		driver.findElement(By.xpath(".//*[@id='main']/form[1]/div/input")).click();
		Thread.sleep(2000);
		
		String visibilityColor = driver.findElement(By.id("introduced")).getCssValue("border-bottom-color");
		if(visibilityColor.equals("rgb(200, 120, 114)")){
				System.out.println("Introduced date field highlighted in red color" + " : PASSED");
				//Click cancel button
				driver.findElement(By.xpath(".//*[@id='main']/form/div/a")).click();
				Thread.sleep(2000);
		}
		else{
		System.out.println("Introduced date field not highlighted in red color" + " : FAILED");
		}
		
	}
	//Discontinued date field validation for invalid date format while adding a new computer
	public void invalidDiscontDateFormatOnEditCompPage() throws InterruptedException{
		driver.findElement(By.xpath(".//*[@id='main']/table/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(2000);	
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("2017/11/11");
		driver.findElement(By.xpath(".//*[@id='main']/form[1]/div/input")).click();
		Thread.sleep(2000);
	
		//identify color after clicking "Save this computer" button : rgb(200, 120, 114)
		String visibilityColor = driver.findElement(By.id("discontinued")).getCssValue("border-bottom-color");
		if(visibilityColor.equals("rgb(200, 120, 114)")){
			System.out.println("discontinued date field highlighted in red color" + " : PASSED");
			//Click cancel button
			driver.findElement(By.xpath(".//*[@id='main']/form/div/a")).click();
			Thread.sleep(2000);
		}
		else{
			System.out.println("Introduced date field not highlighted in red color" + " : FAILED");
			}
		}
	
	public void nexTenRecords() throws InterruptedException{
		boolean nextButtonEnable = driver.findElement(By.xpath(".//*[@id='pagination']/ul/li[3]/a")).isEnabled();
		if(nextButtonEnable){
			driver.findElement(By.xpath(".//*[@id='pagination']/ul/li[3]/a")).click();
		}
		Thread.sleep(2000);
	}
	public void previousTenRecords(){
		boolean preButtonEnable = driver.findElement(By.xpath(".//*[@id='pagination']/ul/li[1]/a")).isEnabled();
		if(preButtonEnable){
			driver.findElement(By.xpath(".//*[@id='pagination']/ul/li[1]/a")).click();
		}
	}
	public static void main(String [] args) throws InterruptedException{
		DatabaseApplication da = new DatabaseApplication();
		String alertMsg;		
		//TestCase 1 Launch Application
		da.launchApplication();
		boolean applicationLink = driver.findElement(By.xpath("html/body/header/h1/a")).isDisplayed();
		boolean addComputerBtn = driver.findElement(By.id("add")).isDisplayed();
		boolean filterByName = driver.findElement(By.id("searchsubmit")).isDisplayed();
		
		if(applicationLink == true && addComputerBtn == true && filterByName == true){
			System.out.println("Test Case 1:"  +"Application link,add computer button and filter by name elements exist - PASSED");
		}
		else{
			System.out.println("Test Case 1:"  +"Application link,add computer button and filter by name elements does not exist - FAILED");
		}
		
		//TestCase 2 : Add a new computer		
		da.addNewComputer();
		if(driver.findElement(By.xpath(".//*[@id='main']/div[1]")).isDisplayed()){
		 	alertMsg = driver.findElement(By.xpath(".//*[@id='main']/div[1]")).getText();
			System.out.println("Test Case 2: " + alertMsg +"- PASSED");			
		}
		else{
			System.out.println("Test Case 2: " + "FAILED");
		}
		
		//Test Case 3 : Update This Computer (updating first record of database application)
		da.updateThisComputer();
		if(driver.findElement(By.xpath(".//*[@id='main']/div[1]")).isDisplayed()){
		   	alertMsg = driver.findElement(By.xpath(".//*[@id='main']/div[1]")).getText();
			System.out.println("Test Case 3: " + alertMsg +" - PASSED");			
		}
		else{
			System.out.println("Test Case 3: " + "Update this computer - FAILED");
		}
	
		//Test Case 4 : get count of records
		System.out.println("Test Case 4: Get Count of records");
		da.getCountOfRecord();
	
		//Test Case 5 : Filter by name
		da.filterByName();		
		if(driver.findElement(By.xpath(".//*[@id='main']/h1")).isDisplayed()){
			alertMsg =  driver.findElement(By.xpath(".//*[@id='main']/h1")).getText();
			System.out.println("Test Case 5: " + alertMsg +" - PASSED");
		}
		else{
			System.out.println("Test Case 5: " + "Filter By name - FAILED");
		}
		
		//TestCase 6 : Delete this Computer (deleting first record of database application)
		da.deleteThisComputer();
		if(driver.findElement(By.xpath(".//*[@id='main']/div[1]")).isDisplayed()){
			alertMsg = driver.findElement(By.xpath(".//*[@id='main']/div[1]")).getText();
			System.out.println("Test Case 6: " + alertMsg +" - PASSED");			
		}
		else{
			System.out.println("Test Case 6: " + "Delete this computer - FAILED");
		}
				
		//Test Case 7 : Check mandatory field Computer name while adding new computer
		System.out.println("Test Case 7 : To Check Mandatory field Computer_Name on Add a Computer page");
		da.ComputerNameBlankOnAddCompPage();		
		
		//Test Case 8 : Check Introduced date field for invalid date format
		System.out.println("Test Case 8 : To Check Introduced date for invalid date format on Add a Computer page");
		da.invalidIntroDateFormatOnAddCompPage();		
		
		//Test Case 9 : Check Discontinued date field for invalid date format
		System.out.println("Test Case 9 : To Check Discontinued date for invalid date format on Add a Computer page");
		da.invalidDiscontDateFormatOnAddCompPage();		
		
		//Test Case 10 : Check mandatory field Computer name while editing existing record
		System.out.println("Test Case 10 : To Check Mandatory field Computer Name on Edit Computer page");
		da.ComputerNameBlankOnEditCompPage();
		
		//Test Case 11 : Check Introduced date field for invalid date format on Edit Computer page
		System.out.println("Test Case 11 : To Check Introduced date for invalid date format on Edit Computer page");
		da.invalidIntroDateFormatOnEditCompPage();
		
		//Test Case 12: Check Discontinued date field for invalid date format on Edit Computer page
		System.out.println("Test Case 12 : To Check Discontinued date for invalid date format on Edit Computer page");
		da.invalidDiscontDateFormatOnEditCompPage();
		
		//da.nexTenRecords();
		//da.previousTenRecords();		
		da.closeApplication();
	}	

}
