import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MozillaHomePage {
	@Test
	public void testGoogleHomePage() {
		//System.setProperty("webdriver.gecko.driver",);
		WebDriver driver=DriverUtility.getDriver("ie");
		
		 driver.manage().window().maximize();
		 driver.get("http://www.google.com");
		 String title= driver.getTitle();
		 Assert.assertEquals(title, "Google");
		
	}
}
