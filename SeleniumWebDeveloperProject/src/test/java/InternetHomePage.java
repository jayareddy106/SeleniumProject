import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InternetHomePage {
@Test
public void testGoogleHomePage() {
	System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\Selenium\\IEDriverServer.exe");
	WebDriver driver=new InternetExplorerDriver();
	
	 driver.manage().window().maximize();
	 driver.get("http://www.google.com");
	 String title= driver.getTitle();
	 Assert.assertEquals(title, "Google");
	
}


}