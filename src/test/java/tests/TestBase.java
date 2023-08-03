package tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import utilities.Helper;

public class TestBase extends AbstractTestNGCucumberTests{

	public static WebDriver driver;
	static String DownloadPath= System.getProperty("user.dir")+"\\Downloads";
	
	public static ChromeOptions chromeOptions() {
		ChromeOptions options = new ChromeOptions();
		//WebDriverManager.chromedriver().browserVersion("114.0.1823.51").setup();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--no-sandbox");
		//System.setProperty("webdriver.chrome.whitelistedIps", "");
		HashMap<String, Object> ChromePrefs = new HashMap<String, Object>();
		ChromePrefs.put("profile.default.content_settings.popups", 0);
		ChromePrefs.put("download.default_directory", DownloadPath);
		options.setExperimentalOption("prefs", ChromePrefs);
		options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		return options;
	}
	public static FirefoxOptions firefoxOptions() {
	    String DownloadPath= System.getProperty("user.dir")+"\\Downloads";
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir",DownloadPath);
		return option;
	}
	public static EdgeOptions edgeOptions() {
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return options;
	}
	@BeforeSuite
	@Parameters("browser")
	public void startdriver(@Optional("chrome")String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
			driver = new ChromeDriver(chromeOptions());
		}else if (browserName.equalsIgnoreCase("firefox") ) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
			driver = new FirefoxDriver(firefoxOptions());
		}else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"/Drivers/msedgedriver.exe");
			driver = new EdgeDriver(edgeOptions());
		}
		driver.navigate().to("https://www.booking.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public static String getSelectedValue(WebElement element) {
	    Select select = new Select(element);
	    return select.getFirstSelectedOption().getText();
	}
	public static void switchToHandle( ) {
		String parentHandle = driver.getWindowHandle(); // get the current window handle
		//driver.findElement(By.xpath("//*[@id='someXpath']")).click(); // click some link that opens a new window

		for (String winHandle : driver.getWindowHandles()) {
		   if (winHandle != parentHandle) {
		   driver.switchTo().window(winHandle);} // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
	}
	public List<WebElement> getSelectedCells(String xpath) {
	    List<WebElement> selectedCells = new ArrayList<>();
	    List<WebElement> rows = driver.findElements(By.xpath(xpath));
	    for (WebElement row : rows) {
	        List<WebElement> cells = row.findElements(By.tagName("td"));
	        for (WebElement cell : cells) {
	            if (cell.getAttribute("class").contains("selected")) {
	                selectedCells.add(cell);
	            }
	        }
	    }
	    return selectedCells;
	}
	public static String getURL() {
		return driver.getCurrentUrl();
	}
	public static void wait_until_element_is_present(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	public static void wait_until_element_is_disappears(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOf(e));
	}
	public static void wait_until_element_is_clickable(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}
	public static void Scroll_To_Element(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", e);
	}
	public static void acceptPopUp() {
		driver.switchTo().alert().accept();
	}
	public static void declinePopUp() {
		driver.switchTo().alert().dismiss();
	}

	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) {
		
		if (result.getStatus()==ITestResult.FAILURE) {
			System.out.println("Faild!");
			System.out.println("Taking Screenshot.....");
			Helper.captureSceenshot(driver, result.getName());
		}
	}
	@AfterSuite
	public void stopDriver() {
		//driver.quit();
	}
}
