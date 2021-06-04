package qtpselenium.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataUtil {
	
	
	public Object[][] getJSONData(String jsonFileName,String testName) {
		String path=System.getProperty("user.dir")+"//data//json//"+jsonFileName;
		JSONParser parser = new JSONParser();
		JSONObject testData=null;
		try {
			testData = (JSONObject)parser.parse(new FileReader(new File(path)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		JSONArray dataSets = (JSONArray)testData.get("testdata");
		Object[][] finalData =null;
		for(int i=0;i<dataSets.size();i++) {
			JSONObject testCaseData = (JSONObject)dataSets.get(i);
			System.out.println(testCaseData.get("testname"));
			if(testName.equals(testCaseData.get("testname"))) {
				JSONArray data  = (JSONArray)testCaseData.get("data");
				finalData = new Object[data.size()][1];
				for(int dsId=0;dsId<data.size();dsId++) {
					JSONObject currentTestData = (JSONObject)data.get(dsId);
					System.out.println(currentTestData);
					finalData[dsId][0]=currentTestData;
					//System.out.println(currentTestData.get("username"));
				}

			}
		}
		
		
		return finalData;
	}
	public Object[][] getXLSData(String testName, Xls_Reader xls){
		int rows= xls.getRowCount(testName);
		int cols= xls.getColumnCount(testName);
		System.out.println("Rows- "+rows);
		System.out.println("Cols- "+cols);
		Object[][] testData = new Object[rows-1][1];
		
		for(int rNum=2;rNum<=rows;rNum++) {
			Hashtable<String,String> table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++) {
				String data=xls.getCellData(testName, cNum, rNum);
				String colName=xls.getCellData(testName, cNum, 1);
				System.out.println(colName +" --- "+data);
				table.put(colName, data);
				
			}
			System.out.println(table);
			testData[rNum-2][0]=table;
		}
		return testData;
	}
	
	

}
