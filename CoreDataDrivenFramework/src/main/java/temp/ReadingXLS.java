package temp;

import java.util.Hashtable;

import qtpselenium.util.Xls_Reader;

public class ReadingXLS {

	public static void main(String[] args) {
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"//data//xls//session_suite.xlsx");
		int rows= xls.getRowCount("Login Test");
		int cols= xls.getColumnCount("Login Test");
		System.out.println("Rows- "+rows);
		System.out.println("Cols- "+cols);
		Object[][] testData = new Object[rows][1];
		
		for(int rNum=2;rNum<=rows;rNum++) {
			Hashtable<String,String> table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++) {
				String data=xls.getCellData("Login Test", cNum, rNum);
				String colName=xls.getCellData("Login Test", cNum, 1);
				System.out.println(colName +" --- "+data);
				table.put(colName, data);
				
			}
			System.out.println(table);
			testData[rNum-2][0]=table;
		}

	}

}
