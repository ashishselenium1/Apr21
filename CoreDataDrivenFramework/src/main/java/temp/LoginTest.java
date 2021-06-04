package temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qtpselenium.util.DataUtil;

public class LoginTest {
	
	
	@Test(dataProvider = "getData")
	public void login(JSONObject data) {
		System.out.println(data.get("username")+" -- "+data.get("browser"));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws FileNotFoundException, IOException, ParseException{
		String testName="Login Test";
		return new DataUtil().getJSONData("session_suite.json", testName);
	}

}
