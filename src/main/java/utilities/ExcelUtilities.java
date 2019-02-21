package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gargoylesoftware.htmlunit.javascript.host.Map;

public class ExcelUtilities {
	static String projectpath=System.getProperty("user.dir");
	
	@SuppressWarnings("resource")
	public static String getExcelcallValue(String fileName,String sheetName,int rowNumber,int columnNumber) throws IOException {
		
		File file =new File(projectpath+"\\ExcelDataFiles\\"+fileName);
		
		FileInputStream inputStream=new FileInputStream(file);
		String cellValue= null;
		Workbook excelWorkbook=null;
		String fileExtensionName= fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			excelWorkbook=new XSSFWorkbook(inputStream);
		}else if(fileExtensionName.equals(".xls")) {
			excelWorkbook=new HSSFWorkbook(inputStream);
		}
		//Read sheet Inside the workbook  by its Name
		Sheet excelSheet = excelWorkbook.getSheet(sheetName);
		
		int rowCount = excelSheet.getLastRowNum()-excelSheet.getFirstRowNum();
		
		excelSheet.getRow(0).getCell(0);
		for (int i=0;i<rowCount+1;i++) {
			if(i==rowNumber-1) {
				// Print Excel Data in Console
				
				Row row =excelSheet.getRow(i);
				
				System.out.println(rowCount);
				// Create a loop to Print cell value in Row 
				for (int j=0;j< row.getLastCellNum()+1;j++) {
					if(j==columnNumber-1) {
						// Print Excell data in Console 
						try {
							
							@SuppressWarnings("deprecation")
							int cel_Type=row.getCell(j).getCellType();
							switch(cel_Type) {
							
							case 0: cellValue=String.valueOf((int)(row.getCell(j).getNumericCellValue()));
							break;
							case 1: cellValue=row.getCell(j).getStringCellValue();
							break;
							case 2: SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
							cellValue=sdf.format(row.getCell(j).getDateCellValue());
							break;
							case 4: cellValue= String.valueOf(row.getCell(j).getBooleanCellValue());
							break;
							default:
								cellValue="";
							
							
							
							}
							
						}
						
						catch(Exception e) {
							cellValue="";
						}
						break;
					}
				}
				break;
			}
		}
		
		return cellValue;
		
	}

	public static String getKeyValueFromExcelwithPosition(String fileName,String sheetName,String keyName,int positionNo) {
// call ReadExcellFile() method by passing it location of xsl
		// this method will load keys and value from xls to HashMap
		return getKeyValue(fileName,sheetName,keyName,positionNo);
		
	}
	public static String getKeyValueFromExcel(String fileName,String sheetName,String keyName) {
		
		return getKeyValue(fileName,sheetName,keyName,1);
	
	
	}
	public static String getKeyValue(String fileName,String sheetName,String keyName,int positionNo) {
		
	java.util.Map<String,List<String>> map= new HashMap<String ,List<String>>();
		
		String keyValue=null;
		try {
			File file =new File(projectpath+"\\ExcelDataFiles\\"+fileName);
			FileInputStream input=new FileInputStream(file);
		
			Workbook excelWorkbook=null;
			String fileExtensionName= fileName.substring(fileName.indexOf("."));
			if (fileExtensionName.equals(".xlsx")) {
				excelWorkbook=new XSSFWorkbook(input);
			}else if(fileExtensionName.equals(".xls")) {
				excelWorkbook=new HSSFWorkbook(input);
			}
			
			Sheet excelSheet = excelWorkbook.getSheet(sheetName);
			
			int rowCount = excelSheet.getLastRowNum()-excelSheet.getFirstRowNum();
			
			for (int i =0;i<rowCount+1;i++) {
				
				Row row =excelSheet.getRow(i);
				List<String> values= new ArrayList<String>();
				
				String key = null;
				String val= null ;
				
				// create a loop tp print call values in a row 
				int columnCounter=1;
				
				for (int j=0;j<row.getLastCellNum();j++) {
					// Print excel data in console 
					if (columnCounter==1) {
						
						try {
						@SuppressWarnings("deprecation")
						int cel_Type = row.getCell(j).getCellType();
						switch(cel_Type) {
						
						case 0: key=String.valueOf((int)(row.getCell(j).getNumericCellValue()));
						break;
						case 1: key=row.getCell(j).getStringCellValue();
						break;
						case 2: SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
						key=sdf.format(row.getCell(j).getDateCellValue());
						break;
						case 4: key= String.valueOf(row.getCell(j).getBooleanCellValue());
						break;
						default:
							key="";
						
						}
					}catch(Exception e) {
						key="";
						}
						columnCounter++;
					}else {
						try {
							
							@SuppressWarnings("deprecation")
							int cel_Type =row.getCell(j).getCellType();
							switch(cel_Type) {
						case 0: val=String.valueOf((int)(row.getCell(j).getNumericCellValue()));
						break;
						case 1: val=row.getCell(j).getStringCellValue();
						break;
						case 2: SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
						val=sdf.format(row.getCell(j).getDateCellValue());
						break;
						case 4: val= String.valueOf(row.getCell(j).getBooleanCellValue());
						break;
						default:
							val="";
							}
						}catch(Exception e) {
							val="";
						}
						values.add(val);
						//storing each properties into hashmap 
						columnCounter++;
					}
				}
				map.put(key, values);
			}
			
			
		}
		catch (Exception e) {
		e.printStackTrace();	
		}
		
		for (java.util.Map.Entry<String,List<String>> entry : map.entrySet()) {
			
			if(entry.getKey().equals(keyName)) {
				keyValue=entry.getValue().get(positionNo-1);
				break;
			}
		}
		
		
		return keyValue;
		
	}
	
}