package browsersetup;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import com.beust.jcommander.Parameters;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
public class BaseClass {
public WebDriver driver;
public WebDriverWait wait;
private List<String> assertMessage;
private String Feature ;
private String clientName;
private AppiumDriverLocalService appiumDriverLocalService;
private AndroidDriver androidDriver;
private String DBRoleChange;
public static String DBRoleValue=null;

public String getDbRoleChange() {
	return DBRoleChange;
	
}
public void setDBRoleChange(String dbRoleChange) {
	DBRoleChange=dbRoleChange;
}
public AppiumDriverLocalService getAppiumDriverLocalService() {
	return appiumDriverLocalService;
	
}
public void setAppiumDriverLocalService(AppiumDriverLocalService appiumDriverLocalService) {
	this.appiumDriverLocalService=appiumDriverLocalService;
}

@BeforeMethod(timeOut=600000,alwaysRun=true)
@org.testng.annotations.Parameters({"browser","environment","clientName"})
public void setup(String browser,String environment,String clientName) throws MalformedURLException {
	this.setclientName(clientName);
	if(clientName.equals("CAP")) {
		Setup setup=new Setup();
		driver=setup.setupBrowser(browser, environment, clientName);
	}
	else if (clientName.equals("MAP")) {
		Setup setup=new Setup();
		androidDriver=setup.setupAndroidDevice(browser, environment, clientName);
	}else if (clientName.equals("E2E")) {
		Setup setup=new Setup();
		androidDriver=setup.setupAndroidDevice(browser, environment, clientName);
	}
	
}

public void setAssertMessage(String message,int verificationNumber) {
	try {
		assertMessage.add(message);
	}catch(Exception e) {
		assertMessage= new ArrayList<String>();
		assertMessage.add(message);
	}
}

public List<String>getAssertMessage(){
	
	
	return assertMessage;
	
} 

@AfterMethod(timeOut=600000,alwaysRun=true)
@org.testng.annotations.Parameters({"browser","clientName"})
public void quit(String browser,String clientName) {
	
try {
	if((!assertMessage.isEmpty())&& (!(assertMessage==null))) {
		
		assertMessage.clear();
		
	}
}catch(Exception e) {
	assertMessage=new ArrayList<String>();
	assertMessage.addAll(assertMessage);
	
}
tearDown(browser,clientName);
}
@AfterTest(alwaysRun=true,groups= {"regression","CAP","Credit Control Policy"})
@org.testng.annotations.Parameters({"browser","clientName"})
public void RollbackDatabase(String browser,String clientName) {
	this.setclientName(clientName);
	if (clientName.equals("CAP")) {
		System.out.println("In after Test");
	//	commonFunction.roll("Adminstrator Business");
	}
}



private void tearDown(String browser, String clientName) {
	try {
		if(clientName.equals("CAP")) {
			driver.close();
			driver.quit();
			if(WindowsUtils.thisIsWindows()&&browser.equalsIgnoreCase("Firefox"))
				WindowsUtils.killByName("firefox.exe");
			else if (WindowsUtils.thisIsWindows()&&browser.equalsIgnoreCase("chrome"))
				WindowsUtils.killByName("chrome.exe");
			else if (WindowsUtils.thisIsWindows()&&browser.equalsIgnoreCase("InternetExplorer"))
				WindowsUtils.killByName("iexplore.exe");
		}
		if (clientName.equals("MAP")) {
			androidDriver.close();
		}
		if (clientName.equals("E2E")) {
			androidDriver.closeApp();
			WindowsUtils.killByName("adb.exe");
			WindowsUtils.killByName("IEDriverServer.exe");
			WindowsUtils.killByName("iexplore.exe");
		}
	}catch(Exception n) {
		n.printStackTrace();
	}
	
}
public void setDriver(WebDriver driver) {
	this.driver=driver;
}

public WebDriver getDriver() {
	return driver;
	
}


public String getclientName() {
	return clientName;
	
	
}
private void setclientName(String clientName) {
	// TODO Auto-generated method stub
this.clientName=clientName;	
}


public AndroidDriver getAndroidDriver() {
	return androidDriver;
	
	
}


}
