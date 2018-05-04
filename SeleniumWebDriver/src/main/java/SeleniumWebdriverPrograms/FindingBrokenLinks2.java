package SeleniumWebdriverPrograms;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindingBrokenLinks2 {

	public static void main(String[] args) throws MalformedURLException, IOException {

		System.setProperty("webdriver.chrome.driver", "E:\\\\Selenium drivers\\\\chromedriver_win32\\\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in");
		
		//finding how many links and images are available in this particular page
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		links.addAll(driver.findElements(By.tagName("img")));
		
		//number links and images 
		System.out.println("number of links avilable --->" +links.size());
		
		List<WebElement> activelinks=new ArrayList<WebElement>();
		
		for (int i=0;i<links.size();i++) {	
			if(links.get(i).getAttribute("href") !=null) {
				activelinks.add(links.get(i));
			}
			
		}
		
		System.out.println("number of active links available on webpage is -->"+activelinks.size());
		
		
		for (int j=0;j<activelinks.size();j++) {
			
		HttpURLConnection connection = (HttpURLConnection)	new URL(activelinks.get(j).getAttribute("href")).openConnection();
			
		connection.connect();
	String response=	connection.getResponseMessage();
	connection.disconnect();
	System.out.println(activelinks.get(j).getAttribute("href")+"-->"+response);
		}
		
		
	}

}
