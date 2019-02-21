package testdata;

import static org.testng.Assert.fail;

import org.testng.Assert;

import utilities.ExcelUtilities;

public class ConfigurationData  {
	static String configurationFileName="ConfigurationDetails.xlsx";
	
	public static String getUserDetails(String environment,String clientName,String keyName) {
		
		String fileName="UserDetails.xlsx";
		String sheetName=null;
		switch(clientName.toUpperCase()) {
		case "MAP":
			sheetName="MAP";
			break;
		case "CAP":
			sheetName="CAP";
			break;
		case "WEB SERVICES":
			sheetName="Web Services";
			break;
			
		case "E2E" : 
			sheetName ="MAP";
			break;
			default :
				Assert.fail("Database for : "+clientName+"dosen't exist");
		}
		int keyValueNumber =0;
		switch(environment.toUpperCase()) {
		case "TEST 1": 
			keyValueNumber=2;
			break;
		case "TEST 2": 
			keyValueNumber=3;
			break;
		case "TEST 3": 
			keyValueNumber=4;
			break;
		case "MOBILE APP UAT1": 
			keyValueNumber=5;
			break;
			
		case "MOBILE APP UAT2": 
			keyValueNumber=6;
			break;
		case "MOBILE APP TEST1": 
			keyValueNumber=7;
			break;
		case "MOBILE APP TEST3": 
			keyValueNumber=8;
			break;
			default:
				Assert.fail(environment+" dosent exist.");
		}
		
		return ExcelUtilities.getKeyValueFromExcelwithPosition(fileName, sheetName, keyName, keyValueNumber);
		
	}
// url Details
	public static String baseurlEnvironmentOne=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "TEST 1");
	public static String baseurlEnvironmentTwo=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "TEST 2");
	public static String baseurlEnvironmentThree=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "TEST 3");
	public static String baseurlEnvironmentFour=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "EnvironMent 4");

// Mobile Application : Configuration Data 
	public static String nodJsPath=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "Node JS Path");
	public static String mobileDeviceName=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "Device Name");
	public static String mobilePlatformVersion=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "Platform Version");
	public static String appPackageUAT1=ExcelUtilities.getKeyValueFromExcelwithPosition(configurationFileName, "config", "MOBILE APP UAT1",1);
	public static String appActivityUAT1=ExcelUtilities.getKeyValueFromExcelwithPosition(configurationFileName, "config", "MOBILE APP UAT1",2);
	public static String appPackageUAT2=ExcelUtilities.getKeyValueFromExcelwithPosition(configurationFileName, "config", "MOBILE APP UAT2",1);
	public static String appActivityUAT2=ExcelUtilities.getKeyValueFromExcelwithPosition(configurationFileName, "config", "MOBILE APP UAT2",2);
	public static String appPackageTEST1=ExcelUtilities.getKeyValueFromExcelwithPosition(configurationFileName, "config", "MOBILE APP TEST1",1);
	public static String appActivityTEST1=ExcelUtilities.getKeyValueFromExcelwithPosition(configurationFileName, "config", "MOBILE APP TEST1",2);
	public static String appPackageTEST3=ExcelUtilities.getKeyValueFromExcelwithPosition(configurationFileName, "config", "MOBILE APP TEST3",1);
	public static String appActivityTEST3=ExcelUtilities.getKeyValueFromExcelwithPosition(configurationFileName, "config", "MOBILE APP TEST3",2);
	
	
	
	// Drive Path Details
	public static String driverPathIE=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "Internet Explorer Driver");
	public static String driverPathChrome=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "Chrome Driver");
	public static String driverPathFirefox=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "Firefox Driver");

// Database Details
	public static String databaseHostNameautomationdb=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "Automation DB");
	public static  String databaseuserNameautomationdb=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "Automation DB User Name");
	public static String databasePasswordautomationdb=ExcelUtilities.getKeyValueFromExcel(configurationFileName, "config", "Automation DB Password");
}
