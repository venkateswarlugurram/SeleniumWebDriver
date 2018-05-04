package SeleniumWebdriverPrograms;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class FindingBrokenLinks1 {

	public static void main(String[] args) throws MalformedURLException, IOException {

		System.setProperty("webdriver.chrome.driver", "E:\\Selenium drivers\\chromedriver_win32\\chromedriver.exe");
    WebDriver	driver = new ChromeDriver();
    driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

	driver.get("http://telugunations.com/banking/");
	driver.findElement(By.linkText("LOGIN")).click();
		driver.findElement(By.id("userId")).sendKeys("customer1");
		driver.findElement(By.id("password")).sendKeys("bank@customer");
		driver.findElement(By.id("submit")).click();
		
		//find the all links available on the page
	   List<WebElement> availablelinks = driver.findElements(By.tagName("a"));
	   System.out.println("No of links available on page is ---->"+ ""+  availablelinks.size());
	   
	   
	   	List<WebElement> activelinks=new ArrayList<WebElement>();
	   
		for ( int i=0;i<availablelinks.size();i++) {
			
			if(availablelinks.get(i).getAttribute("href")!=null) {
				activelinks.add(availablelinks.get(i));
				
			}
		}
		
	   
	   //number of active links 
		System.out.println("active links --->"+activelinks.size());
		
		//check active links with httpconnection api
		
		for ( int j=0;j<activelinks.size();j++) {
			
			HttpURLConnection connection = (HttpURLConnection)	new URL(activelinks.get(j).getAttribute("href")).openConnection();
			connection.connect();
	String response=		connection.getResponseMessage();
			connection.disconnect();
			
			
			
			System.out.println(activelinks.get(j).getAttribute("href")+"--->"+response);
		}
		
		
		
	   
	}

}
