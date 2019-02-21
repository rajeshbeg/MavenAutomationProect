package testscripts;

import java.io.IOException;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsersetup.BaseClass;
import pageobjects.Login;
import utilities.ExcelUtilities;
import utilities.ReadExcel;

public class TestExcelCAP extends BaseClass {
	
	String workbook="TestCaseDataMap-old.xlsx";
	String sheet1="MapData";
	
	
	@Test(enabled=true,groups= {"Regression","CAP","Login"},retryAnalyzer=helpers.RetryMechanism.class)
	@Parameters({"environment","clientName"})
	public void testExcel() throws InterruptedException, IOException {
		
		ReadExcel.loadData(workbook, sheet1, "210");

		
		
		System.out.println(ReadExcel.getData("Agent"));

	
		System.out.println("Hello , I am Mira ");
	
	String ExcelValue=	ExcelUtilities.getKeyValueFromExcelwithPosition("TestCaseDataMap-old.xlsx","MapTestData", "234", 1);
	System.out.println(ExcelValue);
	
	Login login= new Login(driver);
	login.setEmailId(ExcelUtilities.getKeyValueFromExcelwithPosition("TestCaseDataMap-old.xlsx","MapTestData", "234", 1));
	Thread.sleep(4000);
	
	
	}

}
