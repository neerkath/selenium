package selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class testng {
       
	
	
		 //using parallel execution given in parameter.xml using suite given in threadcount,parallel,not using negative then  <class name="com.selenium.parallel"/>
		@Test()
		 public void run1(){
			 System.out.println("hi"+Thread.currentThread().getId());
		 }
		 //both two test cases are skipping
		 @Test()
		 public void run2() {
			 System.out.println("hi"+Thread.currentThread().getId());
		 }
		 @Test(priority =-1,enabled=false)
		 public void run3(){
			 System.out.println("hi");
		 }
		 //order of execution to priority
		 @Test(priority =0,enabled=true)
		 public void demo(){
			 System.out.println("hello");
		 }
		 @Test(priority =-1)
		 public void example(){
			 System.out.println("hi");
		 }
		 
		 @Test(priority =5)
		 public void hello(){
			 System.out.println("hi");
		 }
		 
		 @Test(invocationCount = 5 ,invocationTimeOut = 2000)//5 times execution without using loop only one time will run becoz the timeseconds is finsihed
		 public void invocationcount(){
			System.setProperty("webdriver.chrome.driver","C:\\Users\\neerkathalingam.v\\selenium-api-4.15.0.jar\\chromedriver.exe");
		    WebDriver driver = new ChromeDriver();
			driver.get("https://talentx.kgisl.com/Account/Login?returnUrl=%2FHome%2FIndex");
		  			
		 }
		 
		 @Test
		 @Parameters({"name","email"})
		 public void passingparameter(String name,String email){
			 System.out.println("my name is"+ name +email);
		 }
		 
		 // login details with check multiple times using dataprovider
		 //@Test(dataProvider="login",dataProviderClass = Dataproviders.class)
        //using another class from dataproviderclass without using inheritance
		 @DataProvider(name="login",indices = {0})
		 public String[][] getData(){
			 String [][] data=new String[2][2];
			 data[0][0]="neerkath";
			 data[0][1]="neerkath123";
			 data[1][0]="neerkath";
			 data[1][1]="neerkath123";
			return data; 
		 }
		 
		 
		 @Test(dataProvider="login")
		 public void username(String username,String password){
			 System.setProperty("webdriver.chrome.driver","C:\\Users\\neerkathalingam.v\\selenium-api-4.15.0.jar\\chromedriver.exe");
			    WebDriver driver = new ChromeDriver();
				driver.get("https://talentx.kgisl.com/Account/Login?returnUrl=%2FHome%2FIndex");
				driver.findElement(By.xpath("//input[@id='username']")).sendKeys("username");
			  	driver.findElement(By.xpath("//input[@id='pwd']")).sendKeys("password");
			  	driver.findElement(By.xpath("//input[@id='btnlogin']")).click();
			  	Assert.assertTrue(driver.findElement(By.xpath("//a[@id='welcome']")).isDisplayed());
			  	}
		 
}



 
 
 

