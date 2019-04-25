import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestMeAppRegister {

	WebDriver driver;

	@BeforeClass
	public void beforClass() {
		driver = DriverUtility.getDriver("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp");
		driver.manage().window().maximize();

	}

	@AfterClass
	public void afterClass() {
		// driver.close();
	}

	@Test
	public void register() throws InterruptedException {

//		
//		  WebDriverWait wait=new WebDriverWait(driver,20);
//		  wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(
//		  "SignUp"))); driver.findElement(By.partialLinkText("SignUp")).click();;
		
//		 
		driver.findElement(By.partialLinkText("SignUp")).click();

		driver.findElement(By.id("userName")).sendKeys("Jaya1243");
		driver.findElement(By.id("firstName")).click();
		Assert.assertEquals(driver.findElement(By.id("err")).getText(), "Available");

		driver.findElement(By.id("firstName")).sendKeys("Jaya");

		driver.findElement(By.id("lastName")).sendKeys("Reddy");

		driver.findElement(By.id("password")).sendKeys("Jayachandra123");

		driver.findElement(By.id("pass_confirmation")).sendKeys("Jayachandra123");

		driver.findElement(By.cssSelector("input[value='Female']")).click();

		driver.findElement(By.cssSelector("input[value='Male']")).click();

		driver.findElement(By.id("emailAddress")).sendKeys("Reddy123@Email.com");

		driver.findElement(By.id("mobileNumber")).sendKeys("9640610766");

		driver.findElement(By.cssSelector("img[src='images/calendar.png']")).click();

		Select tmonth = new Select(driver.findElement(By.className("ui-datepicker-month")));

		tmonth.selectByVisibleText("Aug");

		Select tmonth1 = new Select(driver.findElement(By.className("ui-datepicker-year")));

		tmonth1.selectByVisibleText("1997");

		driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[5]/a")).click();

		// Thread.sleep(50000);*/

		driver.findElement(By.id("address")).sendKeys("1-15,anantapur.");

		Select question = new Select(driver.findElement(By.name("securityQuestion")));

		question.selectByIndex(1);

		driver.findElement(By.id("answer")).sendKeys("red");

		driver.findElement(By.cssSelector("input[value='Register']")).click();
		
		

	}

}
