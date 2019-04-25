import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestMEAppLogin {

	WebDriver driver;

	@BeforeClass
	public void beforClass() {
		driver = DriverUtility.getDriver("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/login.htm");
		driver.manage().window().maximize();

	}
@AfterClass
	public void afterClass() {
		driver.close();
	}
    @Test (dataProvider="dp1")
	public void testLogin(String username,String password) {
    	
    	driver.findElement(By.id("userName")).sendKeys(username);
    	
    	driver.findElement(By.id("password")).sendKeys(password);
    	driver.findElement(By.name("Login")).click();
    	WebDriverWait wait=new WebDriverWait(driver,100);
    	wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("SignOut")));
    	Assert.assertTrue(driver.getTitle().contains("Home"));
    	driver.findElement(By.partialLinkText("SignOut")).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("SignIn")));
    	driver.findElement(By.partialLinkText("SignIn")).click();;

	}
    @DataProvider(name="dp1")
    public Object[][] getdata(){
    	
    	//Object[][] obj= {
    		//	{"lalitha","Password123"},{"admin","Password123"}};
    			
    		return	ReadDataExcel.testReadExcel("Sheet2");
    			
    			
    	
    	
    }
    
    
}

