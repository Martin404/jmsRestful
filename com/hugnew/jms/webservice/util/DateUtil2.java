/**  
 * @Title: NotSerialize.java 
 * @Package com.supuy.activemq.webservice.annotation 
 * @author Martin
 * @version V1.0
 * @Copyright: Copyright (c) SUPUY Co.Ltd. 2015
 * ★★★★★★★★版权所有※拷贝必究 ★★★★★★★★
*/ 
package com.hugnew.jms.webservice.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @ClassName: DateUtil
 * @Description: TODO
 * @author Martin
 * @date 2013-9-23 下午4:04:45
 * @version V1.0
 */
public class DateUtil2 {

	/**
	 * 
	 * @Title: parseDate
	 * @Description: 把字符串解析为日期
	 * @param dateStr
	 * @param format
	 * @return Date
	 */
	public static java.util.Date parseDate(String dateStr, String format) {

		java.util.Date date = null;
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(format);
			date = (java.util.Date) df.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @Title: parseDate
	 * @Description: 把字符串解析为日期
	 * @param dateStr
	 * @return Date
	 */
	public static java.util.Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd");
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

	/**
	 * 
	 * @Title: formatDate
	 * @Description: 把日期解析为字符串
	 * @param date
	 * @return
	 */
	public static String formatDate(java.util.Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 
	 * @Title: formatDate
	 * @Description: 把日期解析为字符串
	 * @param date
	 * @return
	 */
	public static String formatHDate(java.util.Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @Title: getYear
	 * @Description: 返回当前年
	 * @param date
	 * @return
	 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	/**
	 * 
	 * @Title: getMonth
	 * @Description: 返回当前月
	 * @param date
	 * @return
	 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}

	/**
	 * 
	 * @Title: getDay
	 * @Description:返回当前日
	 * @param date
	 * @return
	 */
	public static int getDay(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * 
	 * @Title: getHour
	 * @Description: 返回当前小时
	 * @param date
	 * @return
	 */
	public static int getHour(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}

	/**
	 * 
	 * @Title: getMinute
	 * @Description: 返回当前分钟
	 * @param date
	 * @return
	 */
	public static int getMinute(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}

	/**
	 * 
	 * @Title: getSecond
	 * @Description: 返回当前秒
	 * @param date
	 * @return
	 */
	public static int getSecond(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}

	/**
	 * 
	 * @Title: getMillis
	 * @Description: 返回当前毫秒
	 * @param date
	 * @return
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 
	 * @Title: getTime
	 * @Description: 返回当前时分秒
	 * @param date
	 * @return
	 */
	public static String getTime(java.util.Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 
	 * @Title: getDateTime
	 * @Description: 返回当前年月日 时分秒
	 * @param date
	 * @return
	 */
	public static String getDateTime(java.util.Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @Title: getFormatDateTime
	 * @Description: 返回当前年月日 时分秒
	 * @param date
	 * @return
	 */
	public static String getFormatDateTime(java.util.Date date) {
		return format(date, "yyyyMMddHHmmss");
	}

	/**
	 * 
	 * @Title: getDateTime
	 * @Description: 返回当前年月日
	 * @param date
	 * @return
	 */
	public static String getDate(java.util.Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 
	 * @Title: addDate
	 * @Description: 增加 几天后的日期
	 * @param date
	 * @param day
	 * @return
	 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 
	 * @Title: diffDate
	 * @Description: 两个日期相差几天
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	public static int diffDate(java.util.Date dateStart, java.util.Date dateEnd) {
		return (int) ((getMillis(dateStart) - getMillis(dateEnd)) / (24 * 3600 * 1000));
	}

	/**
	 * 
	 * @Title: getMonthBegin
	 * @Description: 返回某天所属月份的第一天
	 * @param strdate
	 * @return
	 */
	public static String getMonthBegin(String strdate) {
		java.util.Date date = parseDate(strdate);
		return format(date, "yyyy-MM") + "-01";
	}

	/**
	 * 
	 * @Title: getMonthEnd
	 * @Description: 返回某天所属月份的最后一天
	 * @param strdate
	 * @return
	 */
	public static String getMonthEnd(String strdate) {
		java.util.Date date = parseDate(getMonthBegin(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}

	/**
	 * 
	 * @Title: getGMT8Time
	 * @Description: 获取东八区时间
	 * @return
	 */
	public static Date getGMT8Time() {
		Date gmt8 = null;
		try {
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"),
					Locale.CHINESE);
			Calendar day = Calendar.getInstance();
			day.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			day.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			day.set(Calendar.DATE, cal.get(Calendar.DATE));
			day.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
			day.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
			day.set(Calendar.SECOND, cal.get(Calendar.SECOND));
			gmt8 = day.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			gmt8 = null;
		}
		return gmt8;
	}

	/**
	 * 由出生日期计算年龄
	 * 
	 * @param birthDay
	 * @return
	 */
	public static int selectAge(Date birthDay) {
		Date endDay = new Date();
		int age = diffDate(endDay, birthDay) / 365;
		return age;
	}

	// 获得当前时间所在月份的上个月的最后一天所在日期
	public static Date getLastMonthDay(Date curDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		int year = 0;
		int month = cal.get(Calendar.MONTH);
		// 设置年月
		if (month == 0) {
			year = cal.get(Calendar.YEAR) - 1;
			month = 12;
		} else {
			year = cal.get(Calendar.YEAR);
		}
		// 设置天数
		String temp = year + "-" + month;
		Date d = parseDate(temp, "yyyy-MM");
		cal.setTime(d);
		int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String endDay = year + "-" + month + "-" + day;
		Date endDate = parseDate(endDay);
		return endDate;
	}

	// 获得当前时间所在月份的上个月的第一天所在日期
	public static Date getFirstMonthDay(Date curDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		int year = 0;
		int month = cal.get(Calendar.MONTH);
		// 设置年月
		if (month == 0) {
			year = cal.get(Calendar.YEAR) - 1;
			month = 12;
		} else {
			year = cal.get(Calendar.YEAR);
		}
		// 设置天数
		String temp = year + "-" + month;
		Date d = parseDate(temp, "yyyy-MM");
		cal.setTime(d);
		int day = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		String endDay = year + "-" + month + "-" + day;
		Date endDate = parseDate(endDay);
		return endDate;
	}

	// 获得当前日期前一个月的日期
	public static Date getBeforeMonthDay(Date curDate, int monthNum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.DATE, -30 * monthNum);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		// 设置天数
		String temp = year + "-" + month + "-" + day;
		Date d = parseDate(temp, "yyyy-MM-dd");
		return d;
	}
	
	// 获得当前日期前几天的日期
	public static Date getBeforeDay(Date curDate, int dayNum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.DATE, -dayNum);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		// 设置天数
		String temp = year + "-" + month + "-" + day;
		Date d = parseDate(temp, "yyyy-MM-dd");
		return d;
	}
}
