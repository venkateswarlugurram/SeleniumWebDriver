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

public class FindingBrokenLinks {

	public static void main(String[] args) throws MalformedURLException, IOException {

		System.setProperty("webdriver.chrome.driver", "E:\\Selenium drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.goibibo.com/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("No fo links available on page --->" + links.size());

		List<WebElement> list = new ArrayList<WebElement>();
		
		for (int i = 0; i < links.size(); i++) {
			System.out.println(links.get(i).getAttribute("href"));
			if (links.get(i).getAttribute("href") != null &&( ! links.get(i).getAttribute("href").contains("javascript"))) {
				System.out.println(links.get(i).getAttribute("href"));
				list.add(links.get(i));
			}
		
		}

		System.out.println("active links lists-->"+list.size());
		

		for (int j = 0; j < list.size();j++) {

			HttpURLConnection connection=	(HttpURLConnection)	new URL(list.get(j).getAttribute("href")).openConnection();
		
		connection.connect();
		String response = connection.getResponseMessage();
		connection.disconnect();
		System.out.println(list.get(j).getAttribute("href")+"-->"+response);
		
	}

}}
