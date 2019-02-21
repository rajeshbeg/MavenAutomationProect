package testscripts;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsersetup.BaseClass;
import helpers.Function;
import pageobjects.GoogleSearch;
import utilities.ExcelUtilities;

public class TestExcelMAP extends BaseClass {

	
	@Test(enabled=true,groups= {"Regression","MAP","Login"},retryAnalyzer=helpers.RetryMechanism.class)
	@Parameters({"environment","clientName"})
	public void testExcel() throws InterruptedException {
		
		
	
		System.out.println("Hello , I am Mira ");
		
	String ExcelValue=	ExcelUtilities.getKeyValueFromExcelwithPosition("TestCaseDataMap-old.xlsx","MapTestData", "227", 1);
	System.out.println(ExcelValue);

	Function function = new Function(getAndroidDriver());
	
	function.switchContext("WEBVIEW");
	Thread.sleep(3000);
	// Enter the Value in Google Search
	GoogleSearch googlesearch= new GoogleSearch(getAndroidDriver());
	googlesearch.SearchText("email");
	
	}

}

