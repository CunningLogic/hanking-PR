package com.hanking.pr.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 * @author Alex Yu
 * 
 */
public final class DateUtil {
	private static Logger log = Logger.getLogger(DateUtil.class);

	private static Map<Integer, String> months = new HashMap<Integer, String>();
	
	public final static DateFormat STANDARD_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	
	public final static DateFormat STANDARD_DATES_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);

	public final static DateFormat STANDARD_DATE_FORMAT = new SimpleDateFormat("ddMMMyy", Locale.US);
	public final static DateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("ddMMMyyyy", Locale.US);
	public final static DateFormat PAGE_DATE_FORMAT = new SimpleDateFormat("ddMMMyyyy HH:mm:ss", Locale.US);
	public final static DateFormat FLIGHT_DATE_FORMAT = new SimpleDateFormat("ddMMM", Locale.US);
	public final static DateFormat STANDARD_TIME_FORMAT = new SimpleDateFormat("HH:mm");
	public final static DateFormat PIP_DATE_FORMAT = new SimpleDateFormat("ddMMyy");
	public final static DateFormat FULL_DATE_FORMAT = new SimpleDateFormat("ddMMMyy HH:mm:ss", Locale.US);
	public final static DateFormat DEPARTURE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.US);
	public final static DateFormat FILENAME_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
	public final static DateFormat FUEL_ORDER_DATE_FORMAT = new SimpleDateFormat("yyMMdd", Locale.US);

	public final static DateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.US);
	public final static DateFormat TRANSACTION_DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
	public final static DateFormat SELECT_TITLE_FORMAT = new SimpleDateFormat("dd MMM yyyy", Locale.US);
	public final static DateFormat SELECT_VALUE_FORMAT = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
	public final static DateFormat TRADING_DAY_FORMAT = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
//	public final static DateFormat ISSUE_DATE_FORMAT = new SimpleDateFormat(
//			"dd/MMM/yyyy HH:mm", Locale.US);
	public final static DateFormat LOGIN_DATE_FORMAT = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss", Locale.US);
	public final static DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm",Locale.US);
	public final static DateFormat PROC_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
	public final static DateFormat PRINT_DATE_FORMAT = new SimpleDateFormat("dd/MM/yy", Locale.US);
	public final static DateFormat PRINT_TIME_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.US);
	public final static DateFormat TRANS_DATE_FORMAT = new SimpleDateFormat("yyMMdd-HHmmss", Locale.US);
	public final static DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS", Locale.US);
	public final static DateFormat TIMESTAMP_NEW_FORMAT = new SimpleDateFormat("dd MM yyyy HH:mm:ss.SSS", Locale.US);
	public final static DateFormat DOCID_FORMAT = new SimpleDateFormat("yyMMddHHmmss", Locale.US);
	public final static DateFormat TRANSID_FORMAT = new SimpleDateFormat("yyMMddHHmmss", Locale.US);
	
	public final static DateFormat ISSUENO_FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.US);
	public final static DateFormat GAMING_TIME_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.US);
	public final static DateFormat ISSUE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	public final static DateFormat GAMING_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	
	public final static DateFormat REPORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd EEE", Locale.US);
	public final static DateFormat ISSUE_NO_FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.US);
	
	public final static int MINUTES_IN_HOUR = 60;

	public static final String[] monthList = new String[] { "January",
			"February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	static {
		months.put(1, "January");
		months.put(2, "February");
		months.put(3, "March");
		months.put(4, "April");
		months.put(5, "May");
		months.put(6, "June");
		months.put(7, "July");
		months.put(8, "August");
		months.put(9, "September");
		months.put(10, "October");
		months.put(11, "November");
		months.put(12, "December");

	}

	public static int toMonthNumber(String monthStr) {
		if (StringUtil.isNotEmpty(monthStr)) {
			for (int i = 0; i < monthList.length; i++) {
				if (monthList[i].equals(monthStr))
					return i + 1;
			}
		}
		return -1;
	}

	public static String toMonthStr(int month) {
		if (month < 1 || month > 12) {
			return null;
		}
		return months.get(month);
	}

	public static Date getTodayBoundary(Date date) {
		try {
			return FULL_DATE_FORMAT.parse(STANDARD_DATE_FORMAT.format(date)
					+ " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String toDateStr(Date date) {
		return FILENAME_DATE_FORMAT.format(date);
	}

	public static String toDateStr(Date date, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.US);
		return dateFormat.format(date);
	}

	public static String toTimeStr(Date date, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.US);
		return dateFormat.format(date);
	}

	public static Date strToTime(String timeStr, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.US);
		try {
			return dateFormat.parse(timeStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getYesterday() {
		return addHours(getToday(), -24);
	}

	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		DateFormat standard_date_format = new SimpleDateFormat("ddMMMyy",
				Locale.US);
		try {
			return standard_date_format.parse(standard_date_format
					.format(calendar.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static Date getTomorrow() {
		return getDaysAfter(1);
	}

	public static Date getPreviousDay(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);

			try {
				return calendar.getTime();
			} catch (Exception e) {
				if (log.isEnabledFor(Level.ERROR)) {
					log.error("getDaysAfter()", e);
				}
			}
		}
		return null;
	}

	public static Date getNow() {
//		Date now = Calendar.getInstance().getTime();
//		return addHours(now, 1);
		return Calendar.getInstance().getTime();
	}

	public static Timestamp getNowTimestamp() {
		return new Timestamp(getNow().getTime());
	}

	public static Date getDaysAfter(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getToday());
		calendar.add(Calendar.DATE, days);

		try {
			return calendar.getTime();
		} catch (Exception e) {
			if (log.isEnabledFor(Level.ERROR)) {
				log.error("getDaysAfter()", e);
			}
		}
		return new Date();
	}

	public static Date getDaysBefore(int days) {
		return getDaysAfter(-days);
	}

	public static Date getNextHour(Date date) {
		if (date != null) {
			return addHours(date, 1);
		}
		return null;
	}
	
	public static Date getNextDay(Date date) {
		if (date != null) {
			return addHours(date, 24);
		}
		return null;
	}
	
	public static Date getTheFirstDayOfCurrentWeek() {
		Date now = DateUtil.getNow();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK); // 获取周开始基准
		int current = calendar.get(Calendar.DAY_OF_WEEK); // 获取当天周内天数
		calendar.add(Calendar.DAY_OF_WEEK, min - current + 1); // 当天-基准，获取周开始日期(默认星期日是第一天, +1 星期一为第一天)
		Date start = calendar.getTime();
		return start;
	}
	
	public static Date getTheLastDayOfCurrentWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getTheFirstDayOfCurrentWeek());
		calendar.add(Calendar.DAY_OF_WEEK, 6);
		return calendar.getTime();
	}

	public static Date getTheFirstDayOfMonth() {
		return getTheFirstDayOfMonth(getToday());
	}

	public static Date getTheFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date == null) {
			calendar.setTime(getToday());
		} else {
			calendar.setTime(date);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getTheLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date == null) {
			calendar.setTime(getToday());
		} else {
			calendar.setTime(date);
		}
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	public static Date addHours(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);

		try {
			return calendar.getTime();
		} catch (Exception e) {
			if (log.isEnabledFor(Level.ERROR)) {
				log.error("addHours()", e);
			}
		}
		return new Date();
	}
	
	public static Date addMinutes(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		
		try {
			return calendar.getTime();
		} catch (Exception e) {
			if (log.isEnabledFor(Level.ERROR)) {
				log.error("addHours()", e);
			}
		}
		return new Date();
	}
	
	public static Date addSeconds(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		
		try {
			return calendar.getTime();
		} catch (Exception e) {
			if (log.isEnabledFor(Level.ERROR)) {
				log.error("addHours()", e);
			}
		}
		return new Date();
	}

	public static int daysDifference(Date date1, Date date2) {
		return new Long((date2.getTime() - date1.getTime())
				/ (1000 * 60 * 60 * 24)).intValue();
	}

	public static int minutesDifference(Date date1, Date date2) {
		return new Long((date1.getTime() - date2.getTime()) / (60 * 1000))
				.intValue();
	}

	public static String getTime(int second) {
		// String s = "";
		if (second < 0) {
			return "0000";
		}

		if (second < 60) {
			return StringUtil.padPrefix(Integer.toString(second), '0', 4);
		}

		int min = second / 60;
		int sec = second % 60;

		return StringUtil.padPrefix(Integer.toString(min), '0', 2)
				+ StringUtil.padPrefix(Integer.toString(sec), '0', 2);
	}

	public static Date getDate(String day, String month, String year) {
		try {
			return INPUT_DATE_FORMAT.parse(day + month + year);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getDate(String day, String month, String year,
			String time) {
		try {
			// "ddMMMyyyy HH:mm:ss"
			return PAGE_DATE_FORMAT.parse(day + month + year + " " + time
					+ ":00");
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getDate(String date, String month, String year,
			String hour, String munite, String second, String millisecond) {
		try {
			return TIMESTAMP_FORMAT.parse(date + " " + month + " " + year + " "
					+ hour + ":" + munite + ":" + second + "." + millisecond);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getDate(int day, int month, int year) {
		try {
			String dayStr = "";
			String monthStr = "";
			if (day < 10) {
				dayStr = "0" + day;
			} else {
				dayStr = day + "";
			}

			if (month < 10) {
				monthStr = "0" + month;
			} else {
				monthStr = month + "";
			}

			return new SimpleDateFormat("ddMMyyyy").parse(dayStr + monthStr
					+ year);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getDate(int day, int month, int year, int hour,
			int munite, int second, int millisecond) {
		String dayStr = String.valueOf(day);
		String monthStr = toMonthStr(month);
		String yearStr = String.valueOf(year);
		String hourStr = String.valueOf(hour);
		String muniteStr = String.valueOf(munite);
		String secondStr = String.valueOf(second);
		String millisecondStr = String.valueOf(millisecond);

		if (day < 10)
			dayStr = "0" + day;

		if (hour < 10)
			hourStr = "0" + hour;
		if (munite < 10)
			muniteStr = "0" + munite;
		if (second < 10)
			secondStr = "0" + second;
		if (millisecond < 10)
			millisecondStr = "00" + millisecond;
		else if (millisecond < 100)
			millisecondStr = "0" + millisecond;

		return getDate(dayStr, monthStr, yearStr, hourStr, muniteStr,
				secondStr, millisecondStr);
	}

	public static boolean isDateValidity(String day, String month, String year) {
		boolean result = false;
		try {
			INPUT_DATE_FORMAT.setLenient(false);
			INPUT_DATE_FORMAT.parse(day + month + year);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static int getDay() {
		return Calendar.getInstance().get(Calendar.DATE);
	}
	
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 将毫秒数换算成x天x时x分x秒x毫秒
	 * 
	 * @param ms
	 * @return
	 */
	public static String format(long ms) {
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = ms / dd;
		long hour = (ms - day * dd) / hh;
		long minute = (ms - day * dd - hour * hh) / mi;
		long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		long milliSecond = ms - day * dd - hour * hh - minute * mi - second
				* ss;

		String strDay = day < 10 ? "0" + day : "" + day;
		String strHour = hour < 10 ? "0" + hour : "" + hour;
		String strMinute = minute < 10 ? "0" + minute : "" + minute;
		String strSecond = second < 10 ? "0" + second : "" + second;
		String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : ""
				+ milliSecond;
		strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : ""
				+ strMilliSecond;
		return strDay + " " + strHour + ":" + strMinute + ":" + strSecond + " "
				+ strMilliSecond;
	}

	public static Date getPreviousMonth(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			calendar.add(Calendar.MONTH, -1);

			return calendar.getTime();
		}
		return null;
	}

	public static Date trimTime(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			return calendar.getTime();
		}
		return null;
	}

	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}

	public static int getMaxDays(int year, int month) {
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else if (month == 2) {
			return isLeapYear(year) ? 29 : 28;
		} else {
			return 31;
		}
	}

	public static String formatDate(Date date, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.US);
		return dateFormat.format(date);
	}
	
	public static boolean isBetween(Date date,Date from, Date to){
		return date.compareTo(from) >= 0 && date.compareTo(to) <= 0;
	}
	
	public static boolean isSameDay(Date date1, Date date2){
		int year1 = getYear(date1);
		int month1 = getMonth(date1);
		int day1 = getDay(date1);
		int year2 = getYear(date2);
		int month2 = getMonth(date2);
		int day2 = getDay(date2);
		
		return year1 == year2 && month1 == month2 && day1 == day2;
		
	}
	
	public static Date getDateBefore(Date date, int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}
	
	
	public static Date getStartDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	//获去下一年的当前时间
	public static Date getYearDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR,1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	public static void main(String[] args){
//		System.out.println(toDateStr(new Date()).substring(0, 8));
		String str = "2015-05-19";
		try {
			Date date = ISSUE_DATE_FORMAT.parse(str);
			String tt = STANDARD_DATETIME_FORMAT.format(getYearDate(date));
			System.out.println("tt "+tt);
			
			String s = formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
			System.out.println("s "+s);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jinyang", 222);
//		System.out.println(map.get("11"));
//		double   f   =   111231.5585;  
//		BigDecimal   b   =   new   BigDecimal(f);  
//		double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
//		System.out.println(f1);
		
		String st = "daa";
		System.out.println(st.indexOf("aa"));
		long lon = System.currentTimeMillis();
		System.out.println(lon);
		
		long timestamp = System.currentTimeMillis();//获取时间戳
		int i=1;
		
		Object sss = "478792.11";
		System.out.println(Math.abs(-124));
//		System.out.println(b);
	}
}
