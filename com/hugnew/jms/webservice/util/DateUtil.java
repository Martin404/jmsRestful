/**
 * 
 */
package com.hugnew.jms.webservice.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utility Class for working with <code>Dates</code>.
 * 
 * @author bpasero modified by http://joeblow.iteye.com 2010-11-11
 */
public class DateUtil {

	/** 1 Day in Millis */
	public static final long DAY = 24L * 60L * 60L * 1000L;

	/** 1 Week in Millis */
	public static final long WEEK = 7 * DAY;

	/* An array of custom date formats */
	private static final DateFormat[] CUSTOM_DATE_FORMATS;

	/* The Default Timezone to be used */
	private static final TimeZone TIMEZONE = TimeZone.getTimeZone("GMT+8"); //$NON-NLS-1$  

	/**
	 * Tries different date formats to parse against the given string
	 * representation to retrieve a valid Date object.
	 * 
	 * @param strdate
	 *            Date as String
	 * @return Date The parsed Date
	 */
	public static Date parseDate(String strdate) {

		/* Return in case the string date is not set */
		if (strdate == null || strdate.length() == 0)
			return null;

		Date result = null;
		strdate = strdate.trim();
		if (strdate.length() > 10) {

			/* Open: deal with +4:00 (no zero before hour) */
			if ((strdate.substring(strdate.length() - 5).indexOf("+") == 0 || strdate.substring(strdate.length() - 5).indexOf("-") == 0) && strdate.substring(strdate.length() - 5).indexOf(":") == 2) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$  
				String sign = strdate.substring(strdate.length() - 5,
						strdate.length() - 4);
				strdate = strdate.substring(0, strdate.length() - 5) + sign
						+ "0" + strdate.substring(strdate.length() - 4); //$NON-NLS-1$  
			}

			String dateEnd = strdate.substring(strdate.length() - 6);

			/*
			 * try to deal with -05:00 or +02:00 at end of date replace with
			 * -0500 or +0200
			 */
			if ((dateEnd.indexOf("-") == 0 || dateEnd.indexOf("+") == 0) && dateEnd.indexOf(":") == 3) { //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$  
				if (!"GMT".equals(strdate.substring(strdate.length() - 9, strdate.length() - 6))) { //$NON-NLS-1$  
					String oldDate = strdate;
					String newEnd = dateEnd.substring(0, 3)
							+ dateEnd.substring(4);
					strdate = oldDate.substring(0, oldDate.length() - 6)
							+ newEnd;
				}
			}
		}

		/* Try to parse the date */
		int i = 0;
		while (i < CUSTOM_DATE_FORMATS.length) {
			try {

				/*
				 * This Block needs to be synchronized, because the parse-Method
				 * in SimpleDateFormat is not Thread-Safe.
				 */
				synchronized (CUSTOM_DATE_FORMATS[i]) {
					return CUSTOM_DATE_FORMATS[i].parse(strdate);
				}
			} catch (ParseException e) {
				i++;
			} catch (NumberFormatException e) {
				i++;
			}
		}
		return result;
	}

	/** Initialize the array of common date formats and formatter */
	static {

		/* Create Date Formats */
		final String[] possibleDateFormats = {
		/* RFC 1123 with 2-digit Year */"EEE, dd MMM yy HH:mm:ss z",
		/* RFC 1123 with 4-digit Year */"EEE, dd MMM yyyy HH:mm:ss z",
		/* RFC 1123 with no Timezone */"EEE, dd MMM yy HH:mm:ss",
		/* Variant of RFC 1123 */"EEE, MMM dd yy HH:mm:ss",
		/* RFC 1123 with no Seconds */"EEE, dd MMM yy HH:mm z",
		/* Variant of RFC 1123 */"EEE dd MMM yyyy HH:mm:ss",
		/* RFC 1123 with no Day */"dd MMM yy HH:mm:ss z",
		/* RFC 1123 with no Day or Seconds */"dd MMM yy HH:mm z",
		/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ssZ",
		/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss'Z'",
		/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:sszzzz",
		/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss z",
		/* ISO 8601 */"yyyy-MM-dd'T'HH:mm:ssz",
		/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss.SSSz",
		/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HHmmss.SSSz",
		/* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss",
		/* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mmZ",
		/* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mm'Z'",
		/* RFC 1123 without Day Name */"dd MMM yyyy HH:mm:ss z",
		/* RFC 1123 without Day Name and Seconds */"dd MMM yyyy HH:mm z",
		/* Simple Date Format */"yyyy-MM-dd",
		/* Simple Date Format */"MMM dd, yyyy" };

		/* Create the dateformats */
		CUSTOM_DATE_FORMATS = new SimpleDateFormat[possibleDateFormats.length];

		for (int i = 0; i < possibleDateFormats.length; i++) {
			CUSTOM_DATE_FORMATS[i] = new SimpleDateFormat(
					possibleDateFormats[i], Locale.CHINA);
			CUSTOM_DATE_FORMATS[i].setTimeZone(TIMEZONE);
		}
	}
	
	
	public static String jDateToISO8601(Date date){
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.YEAR)+"-"+cl.get(Calendar.MONTH)+"-"+cl.get(Calendar.DAY_OF_MONTH)+"T"+cl.get(Calendar.HOUR_OF_DAY)+":"+cl.get(Calendar.MINUTE)+":"+cl.get(Calendar.SECOND)+"Z";
	}
	
		 /** 
	     * 计算出两个日期之间相差的天数 
	     * 建议date1 大于 date2 这样计算的值为正数 
	     * @param date1 日期1 
	     * @param date2 日期2 
	     * @return date1 - date2 
	     */  
	    public static int dateInterval(long date1, long date2) {  
	        if(date2 > date1){  
	            date2 = date2 + date1;  
	            date1 = date2 - date1;  
	            date2 = date2 - date1;  
	        }  
	        /* 
	         * Canlendar 该类是一个抽象类  
	         * 提供了丰富的日历字段 
	         *  
	         * 本程序中使用到了 
	         * Calendar.YEAR    日期中的年份 
	         * Calendar.DAY_OF_YEAR     当前年中的天数 
	         * getActualMaximum(Calendar.DAY_OF_YEAR) 返回今年是 365 天还是366天 
	         */  
	        Calendar calendar1 = Calendar.getInstance(); // 获得一个日历  
	        calendar1.setTimeInMillis(date1); // 用给定的 long 值设置此 Calendar 的当前时间值。  
	          
	        Calendar calendar2 = Calendar.getInstance();  
	        calendar2.setTimeInMillis(date2);  
	        // 先判断是否同年  
	        int y1 = calendar1.get(Calendar.YEAR);  
	        int y2 = calendar2.get(Calendar.YEAR);  
	          
	        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
	        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);  
	        int maxDays = 0;  
	        int day = 0;  
	        if(y1 - y2 > 0){  
	            day = numerical(maxDays, d1, d2, y1, y2, calendar2);  
	        }else{  
	            day = d1 - d2;  
	        }  
	        return day;  
	    }  
	      
	    /** 
	     * 日期间隔计算 
	     * 计算公式(示例): 
	     *      20121230 - 20071001 
	     *      取出20121230这一年过了多少天 d1 = 365     取出20071001这一年过了多少天 d2 = 274 
	     *      如果2007年这一年有366天就要让间隔的天数+1，因为2月份有29日。 
	     * @param maxDays   用于记录一年中有365天还是366天 
	     * @param d1    表示在这年中过了多少天 
	     * @param d2    表示在这年中过了多少天 
	     * @param y1    当前为2010年 
	     * @param y2    当前为2012年 
	     * @param calendar  根据日历对象来获取一年中有多少天 
	     * @return  计算后日期间隔的天数 
	     */  
	    public static int numerical(int maxDays, int d1, int d2, int y1, int y2, Calendar calendar){  
	        int day = d1 - d2;  
	        int betweenYears = y1 - y2;  
	        List<Integer> d366 = new ArrayList<Integer>();  
	          
	        if(calendar.getActualMaximum(Calendar.DAY_OF_YEAR) == 366){
	            System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
	            day += 1;  
	        }  
	          
	        for (int i = 0; i < betweenYears; i++) {  
	            // 当年 + 1 设置下一年中有多少天  
	            calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR)) + 1);  
	            maxDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);  
	            // 第一个 366 天不用 + 1 将所有366记录，先不进行加入然后再少加一个  
	            if(maxDays != 366){  
	                day += maxDays;  
	            }else{  
	                d366.add(maxDays);  
	            }  
	            // 如果最后一个 maxDays 等于366 day - 1  
	            if(i == betweenYears-1 && betweenYears > 1 && maxDays == 366){  
	                day -= 1;  
	            }  
	        }  
	          
	        for(int i = 0; i < d366.size(); i++){  
	            // 一个或一个以上的366天  
	            if(d366.size() >= 1){  
	                day += d366.get(i);  
	            }  
	//          else{  
	//              day -= 1;  
	//          }  
	        }  
	        return day;  
	    }  
	      
	    /** 
	     * 将日期字符串装换成日期 
	     * @param strDate   日期支持年月日 示例:yyyyMMdd 
	     * @return  1970年1月1日器日期的毫秒数 
	     */  
	    public static long getDateTime(String strDate) {  
	        return getDateByFormat(strDate, "yyyyMMdd").getTime();  
	    }  
	      
	    /** 
	     * @param strDate   日期字符串 
	     * @param format    日期格式 
	     * @return      Date 
	     */  
	    public static Date getDateByFormat(String strDate, String format) {  
	        SimpleDateFormat sdf = new SimpleDateFormat(format);  
	        try{  
	            return (sdf.parse(strDate));  
	        }catch (Exception e){  
	            return null;  
	        }  
	    } 
	
	    /**
	     * 
	    * @Title: compareDate 
	    * @Description: TODO
	    * @param @param DATE1
	    * @param @param DATE2
	    * @param @return
	    * @return boolean
	    * @throws 
	    * @author Martin
	    * @date 2013-11-21 下午6:41:36
	    * @version V1.0
	     */
	    public static boolean compareDate(String DATE1, String DATE2) {
	       DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	       boolean compareFlag = false;
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	            	compareFlag = true;
	            } else if (dt1.getTime() < dt2.getTime()) {
	            	compareFlag = false;
	            } else {
	            	compareFlag = false;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return compareFlag;
	    }
	    
	    
		public static String formatDate(java.util.Date date) {
			return format(date, "yyyy-MM-dd");
		}
		
		/**
		 * 
		 * @Title: format
		 * @Description: 把日期格式化输出为字符串
		 * @param date
		 * @param format
		 * @return String
		 */
		public static String format(java.util.Date date, String format) {
			String result = "";
			try {
				if (date != null) {
					java.text.DateFormat df = new java.text.SimpleDateFormat(format);
					result = df.format(date);
				}
			} catch (Exception e) {
			}
			return result;
		}
	
}
