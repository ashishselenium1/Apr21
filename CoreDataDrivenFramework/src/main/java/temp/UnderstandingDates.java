package temp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UnderstandingDates {

	public static void main(String[] args) throws ParseException {
		String date="15/01/2020";
		// Month, Year
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateObj = sdf.parse(date);
		System.out.println(dateObj);

		sdf = new SimpleDateFormat("dd");
		String day = sdf.format(dateObj);
		System.out.println(day);
		
		sdf = new SimpleDateFormat("MMMM");
		String month = sdf.format(dateObj);
		System.out.println(month);

		sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(dateObj);
		System.out.println(year);
		
	}

}
