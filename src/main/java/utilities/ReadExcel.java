package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	static String projectPath=System.getProperty("user.dir");
	static FileInputStream fis;
	static XSSFWorkbook workbook;
	static  XSSFSheet sheet;
	static HashMap<String,String>map;
	static DataFormatter formatter;
	
	public static void loadData(String fileName,String  sheetName,String tcID) throws IOException {
		formatter= new DataFormatter();
		fis = new FileInputStream(projectPath+"\\ExcelDataFiles\\"+fileName);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		
		
		int rowCount=sheet.getPhysicalNumberOfRows();
		int rowNumber=0;
		
		for (int i=0;i<=rowCount;i++) {
			String str = formatter.formatCellValue(sheet.getRow(i).getCell(0));
//			String str = sheet.getRow(i).getCell(0).getStringCellValue();
			
			if(str.equalsIgnoreCase(tcID)) {
				rowNumber=i;
				break;
				
			}
		}
				
				
				map=new HashMap<String, String>();
				
				int rowCount2=sheet.getRow(rowNumber-1).getPhysicalNumberOfCells();
				XSSFRow row= sheet.getRow(0);
				int maxcell=row.getLastCellNum();
				for (int j=0;j<maxcell;j++) {
					String key =sheet.getRow(0).getCell(j).getStringCellValue();
					String value=formatter.formatCellValue(sheet.getRow(rowNumber).getCell(j));
					map.put(key, value);
				}
				System.out.println(map);
				workbook.close();
	}
	
	public static String getData(String key) {
		return map.get(key);
	}
	public static void clearData() {
		map.clear();
	}
		
	}


