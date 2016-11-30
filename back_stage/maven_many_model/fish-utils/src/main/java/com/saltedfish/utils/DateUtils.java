package com.saltedfish.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;


/**
 * <P>Date工具类</P>
 * @author lkd
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils  { 

	static private org.slf4j.Logger logger = LoggerFactory.getLogger(DateUtils.class);

	/**缺省日期格式*/
	public static final String DEFAULT_DATE_FORMAT_DEFAULT = "yyyy-MM-dd";

	/**紧凑日期格式*/
	public static final String COMPACT_DATE_FORMAT = "yyyyMMdd";

	/**缺省长日期格式*/
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH-mm";

	/**紧凑长日期格式*/
	public static final String COMPACT_DATETIME_FORMAT = "yyyyMMddHHmm";

	/**长日期格式,精确到秒*/
	public static final String DEFAULT_DATETIME_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";

	/**紧凑长日期格式,精确到秒*/
	public static final String COMPACT_DATETIME_FORMAT_SEC = "yyyyMMddHHmmss";

	private static Map<String, SimpleDateFormat> formats = new HashMap<String, SimpleDateFormat>();

	static {
		formats.put(DEFAULT_DATE_FORMAT_DEFAULT, new SimpleDateFormat(DEFAULT_DATE_FORMAT_DEFAULT));
		formats.put(COMPACT_DATE_FORMAT, new SimpleDateFormat(COMPACT_DATE_FORMAT));
		formats.put(DEFAULT_DATETIME_FORMAT, new SimpleDateFormat(DEFAULT_DATETIME_FORMAT));
		formats.put(COMPACT_DATETIME_FORMAT, new SimpleDateFormat(COMPACT_DATETIME_FORMAT));
		formats.put(DEFAULT_DATETIME_FORMAT_SEC, new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_SEC));
		formats.put(COMPACT_DATETIME_FORMAT_SEC, new SimpleDateFormat(COMPACT_DATETIME_FORMAT_SEC));
	}

	/**
	 * <p>将日期格式化为字符串</p>
	 * @param date
	 * @param formate
	 * @return
	 * @author Jonathan
	 */
	public static String formate(Date date, String formate) {
		if (date == null)
			return null;
		SimpleDateFormat simpleDateFormat = formats.get(formate);
		return simpleDateFormat.format(date);
	}

	/**
	 * <p>将日期格式化为字符串</p>
	 * @param date
	 * @param formate
	 * @return
	 * @author Jonathan
	 */
	public static String formate(String date, String formate) {
		SimpleDateFormat simpleDateFormat = formats.get(formate);
		return simpleDateFormat.format(date);
	}

	/**
	 * <p>将当前日期格式化为字符串</p>
	 * @param formate
	 * @return
	 * @author Jonathan
	 */
	public static String formateCurrent(String formate) {
		SimpleDateFormat simpleDateFormat = formats.get(formate);
		return simpleDateFormat.format(new Date());
	}

	/**
	 * <p>解析日期字段</p>
	 * @param dateStr
	 * @param formate
	 * @return
	 * @author Jonathan
	 */
	public static Date parse(String dateStr, String formate) {
		SimpleDateFormat simpleDateFormat = formats.get(formate);
		try {
			return simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			logger.error("用格式{}解析{}时出错", new String[] { formate, dateStr });
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <p>计算两个时间差</p>
	 * @param startDate
	 * @param endDate
	 * @param timeUnit （时间单位）TimeUnit 
	 * @return int 
	 * @author mjy
	 */
	public static int timeDiff(Date startDate, Date endDate, TimeUnit timeUnit) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(endDate);
		c2.setTime(startDate);
		long time1 = c1.getTimeInMillis();
		long time2 = c2.getTimeInMillis();
		Long timeDiff = null;
		if (timeUnit != null) {
			switch (timeUnit) {
				case DAYS: {
					timeDiff = (time1 - time2) / (1000 * 3600 * 24);
					break;
				}
				case HOURS: {
					timeDiff = (time1 - time2) / (1000 * 3600);
					break;
				}
				case MINUTES: {
					timeDiff = (time1 - time2) / (1000 * 60);
					break;
				}
				case SECONDS: {
					timeDiff = (time1 - time2) / 1000;
					break;
				}
				default: {
					// 默认返回月
					timeDiff = (time1 - time2) / (1000 * 3600 * 24) / 30;
				}
			}
		} else {
			timeDiff = (time1 - time2) / (1000 * 3600 * 24) / 30;
		}
		return timeDiff.intValue();

	}

	/**
	 * 
	 * <p>根据生日获取年龄:限定格式为yyyy-MM-dd</p>
	 * @param birthday
	 * @return
	 * @throws Exception
	 * @author lidongfu
	 * @throws ParseException 
	 */
	public static String getAgeByString(String birthday) throws ParseException {
		if (birthday == null || birthday.trim().length() == 0)
			return null;
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthday)) {
			throw new IllegalArgumentException("The birthday is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(formats.get(DEFAULT_DATE_FORMAT_DEFAULT).parse(birthday));
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age + "";
	}

	/**
	 * 
	 * <p>根据指定格式将指定String类型的生日转为年龄</p>
	 * @param birthday
	 * @param format
	 * @return
	 * @throws Exception
	 * @author mjy
	 */
	public static String getAgeByStringFormat(String birthday, String format) {
		if (birthday == null || birthday.trim().length() == 0)
			return null;
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthday)) {
			// throw new
			return "0";
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		try {
			cal.setTime(formats.get(format).parse(birthday));
		} catch (ParseException e) {
			throw new RuntimeException("日期转换异常!");
		}
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age + "";
	}

	/**
	 * 
	 * <p>根据生日获取年龄</p>
	 * @param birthday
	 * @return
	 * @throws Exception
	 * @author lidongfu
	 */
	public static String getAgeByDate(Date birthday) {
		if (birthday == null)
			return null;
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthday)) {
			throw new IllegalArgumentException("The birthday is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age + "";
	}

	/**
	 * <p>获取当前时间</p>
	 * @return
	 * @author Jonathan
	 */
	static public Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

}
