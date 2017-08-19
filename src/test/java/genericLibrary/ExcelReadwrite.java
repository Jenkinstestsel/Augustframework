package genericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadwrite {
	
	FileInputStream fis;
	XSSFWorkbook wb;
	
//Initialize Workbook
	public ExcelReadwrite(String Path) throws Exception{
		
//		Fileinput stream
		fis = new FileInputStream(Path);		
//		Workbook
		wb = new XSSFWorkbook(fis);
	}
	
//	get Row Count 
	public  int rowCount(String sheetName){
		
		XSSFSheet sheet = wb.getSheet(sheetName);		
		return sheet.getLastRowNum();			
		
	}
	
//	Get Column Count
	public  int colCount(String sheetName){
		
		XSSFSheet sheet = wb.getSheet(sheetName);		
		return sheet.getRow(0).getLastCellNum();			
		
	}
	
//	Read Cewll value
	public String readCellValue(String sheetName,int row,int col){
		XSSFSheet sheet = wb.getSheet(sheetName);
		XSSFCell cell = sheet.getRow(row).getCell(col);
		String celltext = null;
		
		if(cell.getCellType()==Cell.CELL_TYPE_STRING){
			celltext = cell.getStringCellValue();
		}else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
			celltext = String.valueOf(cell.getNumericCellValue());			
		}else if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
			celltext = "";			
		}
	
		return celltext;	
		
	}
	
//	Write Cell Value
	public void writeCellValue(String sheetName,int row,int col,String Val){
		XSSFSheet sheet = wb.getSheet(sheetName);
		sheet.getRow(row).getCell(col).setCellValue(Val);
		
	}
	
//	Save and Close
	public void  saveAndClose(String Path) throws Exception{
//		fileoutput Stream
		FileOutputStream fos = new FileOutputStream(Path);
		
//		Save
		wb.write(fos);
		
//		Close stream
		fis.close();
		fos.close();
		
		
		
	}
	
	
	
	
	
	

}
