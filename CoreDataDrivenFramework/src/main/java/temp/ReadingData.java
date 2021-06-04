package temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadingData {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		String testName="Login Test";
		String path=System.getProperty("user.dir")+"//data//json//session_suite.json";
		JSONParser parser = new JSONParser();
		JSONObject testData=(JSONObject)parser.parse(new FileReader(new File(path)));
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
		

	}

}
