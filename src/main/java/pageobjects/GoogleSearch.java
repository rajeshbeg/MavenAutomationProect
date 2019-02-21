package pageobjects;


	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import browsersetup.BaseClass;
	import io.appium.java_client.android.AndroidDriver;

	public class GoogleSearch extends BaseClass {

		AndroidDriver driver ;
	    WebDriverWait wait;
		
	 
		// for android driver
	public GoogleSearch(AndroidDriver androiddriver)	{
		setDriver(androiddriver);
		PageFactory.initElements(new AjaxElementLocatorFactory(getAndroidDriver(), 120), this);
	}
	
//
//	public Login(WebDriver driver)	{
//		this.driver=driver;
//		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//		wait= new WebDriverWait(this.driver, 15);
//		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 120), this);
//	}


	@FindBy(xpath="//*[@id='tsf']/div[2]/div[1]/div[1]/div/div[1]/input")
	public WebElement searchField;


	



	public GoogleSearch SearchText(String email) throws InterruptedException {
		
		searchField.click();
		Thread.sleep(4000);
		searchField.sendKeys("WikiPedia");
		Thread.sleep(10000);
		
		return new GoogleSearch(driver);
		
		
	}


}
