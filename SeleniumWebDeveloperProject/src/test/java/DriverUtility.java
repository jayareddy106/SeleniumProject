import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

 public class DriverUtility
{
	static public WebDriver getDriver(String browser) {
		if (browser.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Java\\Selenium\\chromedriver.exe");
			return new ChromeDriver();
			
		}
		else if (browser.equals("ie")) {
			
			
			System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\Selenium\\IEDriverServer.exe");
			return new  InternetExplorerDriver();
			
		}
		else {
			return null;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
