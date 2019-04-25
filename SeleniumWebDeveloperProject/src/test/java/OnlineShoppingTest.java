import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class OnlineShoppingTest {

	WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports reports;
	ExtentTest logger;

	@BeforeClass
	public void beforClass() {
		driver = DriverUtility.getDriver("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void testRegistration() throws InterruptedException {

		driver.findElement(By.partialLinkText("SignUp")).click();

		driver.findElement(By.id("userName")).sendKeys("JayaReddy124");
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

		driver.findElement(By.id("address")).sendKeys("1-15,anantapur.");

		Select question = new Select(driver.findElement(By.name("securityQuestion")));

		question.selectByIndex(1);
		driver.findElement(By.id("answer")).sendKeys("red");
		driver.findElement(By.name("Submit")).click();
		
		logger=reports.createTest("Registration");
		logger.log(Status.INFO, " Registration succesfull");
		
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//form[@role='form']/fieldset/div[8]")).getText()
				.contains("User Registered Succesfully!!! Please login"));

	}

	@Test(dataProvider = "dp1", priority = 2)
	public void testLogIn(String username, String password) {

		driver.findElement(By.id("userName")).sendKeys(username);

		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.name("Login")).click();

		// Assert.assertEquals(driver.getTitle(), "Home");

		Assert.assertTrue(driver.findElement(By.xpath("//header[@id=\"header\"]/div[1]/div/div/div[2]/div/ul"))
				.getText().contains("JayaReddy"));

		logger = reports.createTest("Login");
		logger.log(Status.INFO, "Login is succusefull");

	}

	@DataProvider(name = "dp1")
	public Object[][] getdata() {

		Object[][] obj = { { "JayaReddy124", "Jayachandra123" }

		};
		return obj;

	}

	@Test(priority = 3)
	public void testCart() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.partialLinkText("All Categories"))).perform();
		action.moveToElement(driver.findElement(By.partialLinkText("Electronics"))).click().perform();
		action.moveToElement(driver.findElement(By.partialLinkText("Head Phone"))).click().perform();
		driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();

		logger = reports.createTest("Cart");
		logger.log(Status.INFO, "Product added to cart succesfully");

		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();

		driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();

		logger = reports.createTest("Cart");
		logger.log(Status.INFO, "Dilevery address shown correctly");

		driver.findElement(By.cssSelector("input[value='Proceed to Pay']")).click();

		Thread.sleep(5000);

	}

	@Test(priority = 4)
	public void testPayment() {

		Assert.assertEquals(driver.getTitle(), "Payment Gateway");

		driver.findElement(By.xpath("//label[contains(text(),'Andhra Bank')]")).click();

		driver.findElement(By.id("btn")).click();

		logger = reports.createTest("Bank");
		logger.log(Status.INFO, "Bank selected succesfully");

		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("123456");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Pass@456");
		driver.findElement(By.cssSelector("input[value='LOGIN']")).click();
		driver.findElement(By.cssSelector("input[value='PASSWORD']")).sendKeys("Trans@456");
		driver.findElement(By.cssSelector("input[value='PayNow']")).click();

		logger = reports.createTest("payment");
		logger.log(Status.INFO, "payment completed succesfully");

		Assert.assertTrue(driver.findElement(By.xpath("//div/div/div/div[2]/p")).getText().contains("confirmed"));

		logger = reports.createTest("payment");
		logger.log(Status.INFO, "order placed  succesfully");

	}

	@BeforeTest
	public void startReportBeforeTest() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms");
		String path = System.getProperty("user.dir") + "/extent-reports/" + sdf.format(new Date()) + ".html";
		htmlreporter = new ExtentHtmlReporter(path);
		reports = new ExtentReports();
		reports.attachReporter(htmlreporter);
		reports.setSystemInfo("username", "Jayachandra reddy");
		reports.setSystemInfo("Environment", "Test Environment");
		htmlreporter.config().setReportName("Test Me App Report");
		htmlreporter.config().setTheme(Theme.DARK);

	}

	@AfterMethod
	public void getResultAfterMethod(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, "THE TEST IS FAILED");
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {

			logger.log(Status.PASS, "THE TEST IS Passed");
		}

		else if (result.getStatus() == ITestResult.SKIP) {

			logger.log(Status.SKIP, "THE TEST IS SKIPPED");
		}

	}

	@AfterTest
	public void endReportAfterTest() {
		reports.flush();
		driver.close();
	}

}
