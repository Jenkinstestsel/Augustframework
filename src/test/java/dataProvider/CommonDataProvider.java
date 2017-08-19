package dataProvider;

import genericLibrary.ExcelReadwrite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class CommonDataProvider {
	
	@DataProvider(name="invalidLogin")
	public static Iterator<Object[]> dpdata_invalidLogin() throws Exception{
		
		return  commonDpLogic("Login", "invalidlogin" );
						
	}
	
	
	@DataProvider(name="validLogin")
	public static Iterator<Object[]> dpdata_validLogin() throws Exception{

		return  commonDpLogic("Login", "validlogin" );
	}

	
	@DataProvider(name="invalidSearch")
	public static Iterator<Object[]> dpdata_invalidSearch() throws Exception{

		return  commonDpLogic("Search", "invalidsearch" );
	}
	
	
	@DataProvider(name="validSearchCount")
	public static Iterator<Object[]> dpdata_validSearchCount() throws Exception{

		return  commonDpLogic("Search", "validatesearchcount" );
	}
	
		
	public static Iterator<Object[]> commonDpLogic(String sheetName, String scriptName ) throws Exception{		

		ExcelReadwrite ex = new ExcelReadwrite(System.getProperty("user.dir") + "\\src\\test\\resources\\Test_data.xlsx");
		int rowCount = ex.rowCount(sheetName);
		int colCount = ex.colCount(sheetName);
		
		ArrayList<Object[]> ls = new ArrayList<Object[]>();	
		
//		iterate thru each row
		for(int iRow = 1 ;iRow<=rowCount;iRow++){
			String scriptname = ex.readCellValue(sheetName, iRow, 1);
			String execute = ex.readCellValue(sheetName, iRow, 2);
			
			if(scriptname.trim().toLowerCase().equals(scriptName) && execute.trim().toLowerCase().equals("y")){			
			
//			create object array
			Object[] obj = new Object[1];
			
//			create map
			Map<String, String> Map = new HashMap<String,String>();
//			iterate thru column
			for(int iCol = 0;iCol<colCount;iCol++){
				
				String Key = ex.readCellValue(sheetName, 0, iCol);
				String Val = ex.readCellValue(sheetName, iRow, iCol);
				
				Map.put(Key, Val);
				
			}	//			iterate thru column
			
			obj[0]=Map;
			ls.add(obj);
			}
			
		}//		iterate thru each row
		
		
		return ls.iterator();
		
		
	}

}
