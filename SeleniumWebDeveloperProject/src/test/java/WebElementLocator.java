import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebElementLocator 
{
   WebDriver driver;
   @BeforeMethod
   public void beforeMethod() {
	   
	   driver=DriverUtility.getDriver("chrome");
	   driver.manage().window().maximize();
   }
	@AfterMethod
	public void afterMethod() {
		//driver.close();
	}
	@Test
	public void testDemoWEbShop() {
		
		driver.get("http://demowebshop.tricentis.com/login");
		String url=driver.getCurrentUrl();
		Assert.assertTrue(url.contains("webshop"));
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
