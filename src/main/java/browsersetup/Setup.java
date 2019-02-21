package browsersetup;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import bsh.Capabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import testdata.ConfigurationData;

public class Setup extends BaseClass{

	static String baseurlEnvironmentOne=ConfigurationData.baseurlEnvironmentOne;
	static String baseurlEnvironmentTwo=ConfigurationData.baseurlEnvironmentTwo;
	static String baseurlEnvironmentThree=ConfigurationData.baseurlEnvironmentThree;
	static String baseurlEnvironmentFour=ConfigurationData.baseurlEnvironmentFour;
	
	
	// Mobile Application configuration data 
	static String nodeJSPath= ConfigurationData.nodJsPath;
	static String mobileDeviceName=ConfigurationData.mobileDeviceName;
	static String mobilePlatforVersion=ConfigurationData.mobilePlatformVersion;
	static String appPackageUAT1=ConfigurationData.appPackageUAT1;
	static String appActivityUAT1=ConfigurationData.appActivityUAT1;
	static String appPackageUAT2=ConfigurationData.appPackageUAT2;
	static String appActivityUAT2=ConfigurationData.appActivityUAT2;
	static String appPackageTEST1=ConfigurationData.appPackageTEST1;
	static String appActivityTEST1=ConfigurationData.appActivityTEST1;
	static String appPackageTEST3=ConfigurationData.appPackageTEST3;
	static String appActivityTEST3=ConfigurationData.appActivityTEST3;
	
	private WebDriver driver =null;
	private AndroidDriver androidDriver;
	
	static String driverPathIE=ConfigurationData.driverPathIE;
	static String driverpathChrome=ConfigurationData.driverPathChrome;
	static String driverPathFirefox=ConfigurationData.driverPathFirefox;


	public WebDriver setupBrowser(String browser,String environment, String clientName) throws MalformedURLException {
		
		
		if(browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver","driverPathFirefox");
			DesiredCapabilities capablities=DesiredCapabilities.firefox();
			capablities.setCapability("marionette", true);
	
		
		File pathToFirefoxBinary=new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary firefoxbin=new FirefoxBinary(pathToFirefoxBinary);
		FirefoxProfile firefoxProfile =new FirefoxProfile();
		firefoxProfile.setPreference("browser.startup.homepage", "about:blank");
		firefoxProfile.setPreference("startup.homepage_welcome_url", "about:blank");
		firefoxProfile.setPreference("startup.homepage_welcome_url.additional", "about:blank");
		firefoxProfile.setEnableNativeEvents(true);
		
		capablities.setCapability(FirefoxDriver.BINARY, firefoxbin);
		capablities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
		
		
		driver = new RemoteWebDriver(new URL(" "),capablities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		}else if (browser.equalsIgnoreCase("chrome")) {
			
			ChromeOptions options=new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");
			HashedMap<String, Object> prefs=new HashedMap<String,Object>();
			prefs.put("credential_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			
			options.setExperimentalOption("prefs", prefs);
			DesiredCapabilities capablities =DesiredCapabilities.chrome();
			capablities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", driverpathChrome);
			 driver=new ChromeDriver(options);
			
			
		//	driver= new RemoteWebDriver(new URL("http:127.0.0.1:4723/wd/hub"),capablities);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}else if (browser.equalsIgnoreCase("Internet Explorer")) {
			System.setProperty("webdriver.chrome.driver", driverPathIE);
		
		DesiredCapabilities dc=DesiredCapabilities.internetExplorer();
		dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		
		driver = new InternetExplorerDriver(dc);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		}
		
		switch(environment.toUpperCase()) {
		case "TEST 1":
			driver.navigate().to(baseurlEnvironmentOne);
			break;
		case "TEST 2":
			driver.navigate().to(baseurlEnvironmentTwo);
			break;
		case  "TEST 3":
			driver.navigate().to(baseurlEnvironmentThree);
			break;
		case "MOBILE APP UAT1":
			break;
		case "MOBILE APP UAT2":
		break;
		default :
			Assert.fail(environment+"dosen't exists");
		}
		
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
		
	}
	@SuppressWarnings({"rawtypes"})
	public AndroidDriver setupAndroidDevice(String browser,String environment,String clientName) {
		
		if(browser.equalsIgnoreCase("Android")) {
			DesiredCapabilities capablities =new DesiredCapabilities();
			
			capablities.setCapability(MobileCapabilityType.DEVICE_NAME, mobileDeviceName);
			capablities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
			capablities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
			capablities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
			capablities.setCapability(MobileCapabilityType.PLATFORM_VERSION, mobilePlatforVersion);
			capablities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.chrome");
			capablities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.apps.chrome.Main");
			capablities.setCapability(MobileCapabilityType.NO_RESET, false);
			capablities.setCapability(MobileCapabilityType.FULL_RESET, false);
			try {
				androidDriver= new AndroidDriver<>(new URL ("http://127.0.0.1:4723/wd/hub"),capablities);
				Thread.sleep(10000);
			}catch(Exception me) {
				me.printStackTrace();
			}
		}
		
		return androidDriver;
		
	}
}

