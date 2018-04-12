package newsrss.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	static SimpleDateFormat dateConvert0=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat dateConvert1=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	static SimpleDateFormat dateConvert2=new SimpleDateFormat("yyyy-MM-dd HH");
	static SimpleDateFormat dateConvert3=new SimpleDateFormat("yyyy-MM-dd");
	public static long Date2TimeStamp(String date){
		date = date.trim();
		try {
			Date date2 = null;
			if(date.matches("[\\d]{4}-[\\d]{1,2}-[\\d]{1,2} [\\d]{1,2}:[\\d]{1,2}:[\\d]{1,2}")){
				synchronized (dateConvert0) {
					date2 = dateConvert0.parse(date);
				}
			}else if(date.matches("[\\d]{4}-[\\d]{1,2}-[\\d]{1,2} [\\d]{1,2}:[\\d]{1,2}")){
				synchronized (dateConvert1) {
					date2 = dateConvert1.parse(date);
				}
			}else if(date.matches("[\\d]{4}-[\\d]{1,2}-[\\d]{1,2} [\\d]{1,2}")){
				synchronized (dateConvert2) {
					date2 = dateConvert2.parse(date);
				}
			}else if (date.matches("[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}")) {
				synchronized (dateConvert3) {
					date2 = dateConvert3.parse(date);
				}
			}
			return date2.getTime();
		} catch (ParseException e) {
			System.out.println("日期解析错误 = "+date+" "+e);
			return new Date().getTime();
		}
	}
	
	public static long HanDate2TimeStamp(String dateStr){
		dateStr = dateStr.replaceAll("[年月/]", "-").replaceAll("[日]","").replaceAll("(时|分|小时|分钟)", ":").replaceAll("[秒种]", "");
		System.out.println(dateStr);
		return Date2TimeStamp(dateStr);
	}
	
	public static void main(String[] args){
		System.out.println(new Date(HanDate2TimeStamp("2018年10月23日")));
	}
}
