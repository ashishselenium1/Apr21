package temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadingJSON {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		String path=System.getProperty("user.dir")+"//data//json//sample.json";
		JSONParser parser = new JSONParser();
		JSONObject person=(JSONObject)parser.parse(new FileReader(new File(path)));
		String name = (String)person.get("name");
		String place = (String)person.get("place");
		JSONArray education = (JSONArray)person.get("education");
		System.out.println(name);
		System.out.println(place);
		System.out.println(education);
		for(int i=0;i<education.size();i++) {
			System.out.println(education.get(i));
		}
		
		JSONArray family = (JSONArray)person.get("family");
		for(int i=0;i<family.size();i++) {
			JSONObject member = (JSONObject)family.get(i);
			System.out.println(member.get("name") +" --- "+ member.get("relation"));
			
		}

	}

}
