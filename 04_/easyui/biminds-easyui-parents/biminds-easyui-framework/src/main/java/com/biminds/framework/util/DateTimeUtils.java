package com.biminds.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(DateTimeUtils.class);

	public static final long SECOND = 1000L;

	public static final long MINUTE = 60 * SECOND;

	public static final long HOUR = 60 * MINUTE;

	public static final long DAY = 24 * HOUR;

	public static final long WEEK = 7 * DAY;

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DATETIME_LONG_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String DATE_FORMAT_CHINESE = "yyyy年MM月dd日";

	public static final String YEAR_MONTH_DAY_FORMAT = "yyyyMMdd";

	public static final String YEAR_MONTH_FORMAT = "yyyyMM";

	public static final String YEAR_FORMAT = "yyyy";

	public static final String MONTH_DAY_FORMAT = "MMdd";

	public static final String MONTH_FORMAT = "MM";

	public static final String DAY_FORMAT = "dd";

	/**
	 * 给日期增加或减少天数
	 * 
	 * @param date
	 * @param day
	 *            天数
	 * @return
	 * @author bieskeith.li
	 * @date 2017年10月24日 下午9:23:14
	 */
	public static Date addDays(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	/**
	 * 给日期+00:00:00.000
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getBeginDateByDate(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.set(Calendar.HOUR, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		return new Date(gc.getTimeInMillis());
	}

	/**
	 * 给日期+23:59:59.999
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getEndDateByDate(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.set(Calendar.HOUR_OF_DAY, 23);
		gc.set(Calendar.MINUTE, 59);
		gc.set(Calendar.SECOND, 59);
		gc.set(Calendar.MILLISECOND, 999);
		return new Date(gc.getTimeInMillis());
	}

	/**
	 * 将Date对象格式化为字符串，格式为：yyyy-MM-dd
	 * 
	 * @param date
	 *            Date
	 * @return 日期字符串
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}

	/**
	 * 将日期格式化为字符串
	 * 
	 * @param date
	 *            Date
	 * @param pattern
	 *            日期格式
	 * @return 日期字符串
	 */
	public static String formatDate(Date date, String pattern) {
		return (new SimpleDateFormat(pattern)).format(date);
	}

	/**
	 * 将Date对象格式化为字符串，格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            Date
	 * @return 日期字符串
	 */
	public static String formatDateTime(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(DATETIME_FORMAT).format(date);
	}

	/**
	 * 将Date对象格式化为字符串，格式为：yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param date
	 *            Date
	 * @return 日期字符串
	 */
	public static String formatLongDateTime(Date date) {
		return formatDate(date, DATETIME_LONG_FORMAT);
	}

	/**
	 * 将毫秒数格式化为日期字符串，格式为：yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param milliseconds
	 *            the milliseconds since January 1, 1970, 00:00:00 GMT.
	 * @return 日期字符串
	 */
	public static String formatLongDateTime(long milliseconds) {
		return formatDate(new Date(milliseconds), DATETIME_LONG_FORMAT);
	}

	/**
	 * 尝试使用以下格式对日期字符串进行转换，转换失败返回null.
	 * <ol>
	 * <li>yyyy-MM-dd HH:mm:ss
	 * <li>yyyy-MM-dd HH:mm:ss.SSS
	 * <li>yyyy-MM-dd
	 * <li>yyyy年MM月dd日
	 * </ol>
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return Date
	 */
	public static Date parseDateWithTry(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}

		String[] formats = { DATETIME_FORMAT, // NL
				DATETIME_LONG_FORMAT, // NL
				DATE_FORMAT, // NL
				DATE_FORMAT_CHINESE // NL
		};

		Date date = null;
		try {
			date = DateUtils.parseDate(dateStr, formats);
		} catch (ParseException e) {
			logger.error("Cannot parse {} as date.", dateStr);
		}
		return date;
	}

	/**
	 * 将毫秒数转换为Date对象
	 * 
	 * @param milliseconds
	 *            the milliseconds since January 1, 1970, 00:00:00 GMT.
	 * @return Date
	 */
	public static Date parseLongDateTime(long milliseconds) {
		return new Date(milliseconds);
	}

	/**
	 * 判断两个日期是否为同一天
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return true相同；false不同
	 * @author bieskeith.li
	 * @date 2017年10月24日 下午9:23:14
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		int month1 = cal1.get(Calendar.MONTH);
		int month2 = cal2.get(Calendar.MONTH);
		int day1 = cal1.get(Calendar.DAY_OF_MONTH);
		int day2 = cal2.get(Calendar.DAY_OF_MONTH);

		return (year1 == year2) && (month1 == month2) && (day1 == day2);
	}

	/**
	 * 判断两个日期忽略年后是否为同一天，如生日
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return true相同；false不同
	 * @author bieskeith.li
	 * @date 2017年10月24日 下午9:23:14
	 */
	public static boolean isSameDayIgnoreYear(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);

		int month1 = cal1.get(Calendar.MONTH);
		int month2 = cal2.get(Calendar.MONTH);
		int day1 = cal1.get(Calendar.DAY_OF_MONTH);
		int day2 = cal2.get(Calendar.DAY_OF_MONTH);

		return (month1 == month2) && (day1 == day2);
	}

	/**
	 * 将日期转换成字符串
	 * 
	 * @param date
	 * @param formatType
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:51:49
	 */
	public static String convertDate(Date date, int formatType) {
		String format = "";
		if (null == date) {
			return null;
		}
		switch (formatType) {
		case 1:
			format = "yyyy-MM-dd";
			break;
		case 2:
			format = "yyyyMMdd";
			break;
		case 3:
			format = "yyyy";
			break;
		case 4:
			format = "yyyyMMddHHmmss";
			break;
		case 5:
			format = "MM";
			break;
		case 6:
			format = "yyyy-MM";
			break;
		case 7:
			format = "yyyy-MM-dd HH:mm";
			break;
		default:
			format = "yyyy-MM-dd HH:mm:ss";
			break;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String newDate = sdf.format(date);
		return newDate;
	}

}
