package cn.tedu.javaweb.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
	private static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");//用于解析请求参数
	private static final DateFormat DEFAULT_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//用于解析请求参数
	private static final DateFormat TIMESTAMP_CHARSEQUENCE_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");//用于生成订单编号
	
	public static Date parseDate(String sDate) throws ParseException{
		return DEFAULT_DATE_FORMAT.parse(sDate);
	}
	
	public static String formatDate(Date date){
		return DEFAULT_DATE_FORMAT.format(date);
	}
	
	public static Date parseDateTime(String sDate) throws ParseException{
		return DEFAULT_DATE_TIME_FORMAT.parse(sDate);
	}
	
	public static String formatDateTime(Date date){
		return DEFAULT_DATE_TIME_FORMAT.format(date);
	}
	
	public static Date parseTimestamp(String sDate) throws ParseException{
		return TIMESTAMP_CHARSEQUENCE_FORMAT.parse(sDate);
	}
	
	public static String formatTimestamp(Date date){
		return TIMESTAMP_CHARSEQUENCE_FORMAT.format(date);
	}
	
}
