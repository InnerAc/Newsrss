package newsrss.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	static SimpleDateFormat dateConvert=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static long Date2TimeStamp(String date){
		try {
			Date date2 = dateConvert.parse(date);
			return date2.getTime();
		} catch (ParseException e) {
			return new Date().getTime();
		}
	}
}
