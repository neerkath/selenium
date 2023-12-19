package selenium;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;

public class openingwebsite {

	@SuppressWarnings("deprecation")//from implictwait
	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\neerkathalingam.v\\selenium-api-4.15.0.jar\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://talentx.kgisl.com/Account/Login?returnUrl=%2FHome%2FIndex");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("https://www.google.co.in/");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("star");  //Tagname[contains@attribute="value"]
        List<WebElement> implict = driver.findElements(By.xpath("//span[contains(@text(),'star')]"));
        implict.get(3).click();
        
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@maxlength='61']")).sendKeys("nkl");  //Tagname[contains@attribute="value"]
        
       List<WebElement> radio = driver.findElements(By.xpath("//input[@type='radio']"));
       radio.get(1).click();
       System.out.println(radio.get(1).isDisplayed());//is three are returns the boolean value true or false
       System.out.println(radio.get(1).isEnabled());
       System.out.println(radio.get(1).isSelected());
       
       
       List<WebElement> checkbox =driver.findElements(By.xpath("//input[@type='checkbox']"));
       checkbox.get(1).isSelected();
       
       
       WebElement select = driver.findElement(By.xpath("//select[contains(@name,'DOB')]"));
       Select day=new Select(select);
       day.selectByIndex(3);
       day.selectByValue("2");
       day.selectByVisibleText("12");
       //print and view the day in 12
       WebElement selectedday=day.getFirstSelectedOption(); 
       System.out.println(selectedday.getText());
       //day options 1 to 31
       List<WebElement> allvalues=day.getOptions();
       System.out.println(allvalues.size());
       for(int i=0;i<allvalues.size();i++) {
    	   System.out.println(allvalues.get(i).getText());
       }
       List<WebElement> dayall=driver.findElements(By.xpath("//select[contains(@name,'DOB')]//option"));
       for(int i=0;i<=dayall.size();i++) {
           System.out.println(dayall.get(i).getText());
           if(dayall.get(i).equals("4")) {
        	   dayall.get(i).click();
        	   break;//once click loop is break
           }

       }
       
       
       //Handle the web table-1st row 1st column
       WebElement webtable=driver.findElement(By.xpath("//table[@class='datatable']/tbody/tr[1]/td[1]"));
       System.out.println(webtable.getText());
       
       WebElement webtable1=driver.findElement(By.xpath("//table[@class='datatable']/tbody/tr[6]/td[3]"));
       System.out.println(webtable1.getText());
       
       //Handle the web table-1st row 
       List<WebElement> webtable2=driver.findElements(By.xpath("//table[@class='datatable']/tbody/td/tr[1]"));
       for(int i=0;i<=webtable2.size();i++) {
           System.out.println(webtable2.get(i).getText());
       }
       
       //print the  6th row ishu name from current price column
       List<WebElement> webtable3=driver.findElements(By.xpath("//table[@class='datatable']/tbody/td/tr[1]"));
       List<WebElement> webtable4=driver.findElements(By.xpath("//table[@class='datatable']/tbody/td/tr[4]"));
       for(int i=0;i<=webtable3.size();i++) {
           if(webtable3.get(i).getText().equalsIgnoreCase("ishu")) {
        	   System.out.println(webtable4.get(i).getText());
           }
       }
       
      Set<String> handle=driver.getWindowHandles();
      System.out.println(handle.size());
      Iterator<String> itr =handle.iterator();
      String mainwindow=itr.next();
      String window1=itr.next();
      String window2=itr.next();
      String window3=itr.next();
     
      driver.switchTo().frame(window1);
      System.out.println(driver.getTitle());
      driver.close();
      driver.switchTo().frame(window2);
      System.out.println(driver.getTitle());
      driver.switchTo().frame(window3);
      System.out.println(driver.getTitle());
      driver.switchTo().frame(mainwindow);
      System.out.println(driver.getTitle());
       
      //mouse hover action-handle menu and submenu
      Actions act=new Actions(driver);
      act.moveToElement(driver.findElement(By.xpath("//a[id='ct100_hyperlink']"))).build().perform();
      act.doubleClick(driver.findElement(By.linkText("Travel Agent login menu"))).build().perform();
      act.contextClick(driver.findElement(By.linkText("Airports"))).build().perform();
      
      //handle alerts
      driver.get("https://talentx.kgisl.com/Account/Login?returnUrl=%2FHome%2FIndex");
      driver.findElement(By.xpath("//button[@text()='alertbox']")).click();
      Alert first=driver.switchTo().alert();
      Thread.sleep(3000);
      System.out.println(first.getText());
      first.accept();//positive
      
      //conformation alert
      driver.findElement(By.xpath("//button[@text='confirmbox']")).click();
      Alert second=driver.switchTo().alert();
      Thread.sleep(3000);
      System.out.println(second.getText());
      second.dismiss();//negative
      
      //prompt alert 
      driver.findElement(By.xpath("//button[@text='promptbox']")).click();
      Alert third=driver.switchTo().alert();
      third.sendKeys("jack");
      Thread.sleep(3000);
      System.out.println(third.getText());
      second.accept();//positve
      
      //scroll web page using js
      JavascriptExecutor js=(JavascriptExecutor)driver;
      js.executeScript("window.scroll(0,1000)");
      Thread.sleep(3000);
      js.executeScript("window.scroll(0,-1000)");
      js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
      
      WebElement address=driver.findElement(By.xpath("//p[text()=''register address']"));
      js.executeScript("argument[0].scrollIntoView(true);",address);
      Thread.sleep(3000);
      js.executeScript("window.scroll(0,0)");
      driver.close();
       
      //screenshot 
      TakesScreenshot screenshot=(TakesScreenshot)driver;
      File sourcefile=screenshot.getScreenshotAs(OutputType.FILE);
      File destinationfile=new File("C:\\Users\\neerkathalingam.v\\Pictures\\Screenshots\\first.png");
      FileHandler.copy(sourcefile, destinationfile);
      
      
    //frames
  	driver.switchTo().frame(0);
  	String value=driver.findElement(By.xpath("//span[@id='news']")).getText();
  	System.out.println(value);
  	driver.switchTo().defaultContent();
  	driver.findElement(By.xpath("//li[@class='news']")).click();
  	
  	
  	//hard assertion
  	//String title=driver.getTitle();
  	//Assert.assertequals(title,"googlee");
  	//Assert.assertTrue(false);
  	//driver.quit();
  	
  	
    //soft assertion
  	//SoftAssert soft=new SoftAssert();
  	//String title=driver.getTitle();
  	//soft.assertequals(title,"googlee");
  	//soft.assertAll();--to throw the failure error.
  	//driver.quit();
  	
  	
  	//how to print the all the web links--uisng anchor tag in Tagname
    List<WebElement> alllinks =driver.findElements(By.tagName("a"));
  	System.out.println(alllinks.size()); //print the size in all urls
  	for(int i=0;i<alllinks.size();i++) {
  		System.out.println(alllinks.get(i).getAttribute("href"));//attribute name
  	}
  	
  	//to remove the chrome is being controlled by automate s/w using chromeoptions
  	//ChromeOptions opt=new ChromeOptions();
  	//opt.setExperimentalOption("excludeSwitches",new string[]("enable-automation"));
  	
  	
  	
  	WebElement a=driver.findElement(By.xpath("//input[@name='signin']"));
  	String s=a.getCssValue("backgroundcolor");
  	String n=Color.fromString(s).asHex();
  	System.out.println(n);
  	
  	//find the element is underline or not
  	WebElement b=driver.findElement(By.xpath("//a[@class='mw-disambig']")); 
	String c=b.getCssValue("text-decoration-line");
  	System.out.println(c);
  	Actions d=new Actions(driver);
  	d.moveToElement(b).build().perform();
	String e=b.getCssValue("text-decoration-line");
  	System.out.println(e);
  	
  	
  	//tooltip
	String ee=driver.findElement(By.xpath("//div[@class='icon mail']")).getAttribute("title");
	System.out.println(ee);
   
	//css selector
	driver.findElement(By.cssSelector("//button[@value='login']"));
	driver.quit();
	}	

}
