package util;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 获取当前时间时间戳
	 *
	 * @return
	 */
	public static long getTimestamp() {
		Calendar cal = Calendar.getInstance();
		return cal.getTimeInMillis();
	}

	/**
	 * 格式化日期[yyyy-MM-dd HH:mm:ss]
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate(Object date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化日期[yyyy-MM-dd HH:mm:ss]
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate(Object date, String formats) {
		if (date == null || (!(date instanceof Calendar) && !(date instanceof Date))) {
			return "";
		}

		if (date instanceof Calendar) {
			date = ((Calendar) date).getTime();
		}
		if (StringUtils.isEmpty(formats)) {
			formats = "yyyy-MM-dd HH:mm:ss";
		}

		SimpleDateFormat format = new SimpleDateFormat(formats);
		return format.format(date);
	}

	/**
	 * 转换为日期类型
	 *
	 * @param dateStr
	 * @return
	 */
	public static Date convertStringToDate(String dateStr) {
		return convertStringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}


	public static Date convertStringToDate(String dateStr, String formatStr) {
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			date = format.parse(dateStr);
		} catch (Exception ex) {
			LogUtil.error("convertStringToDate", ex);
		}
		return date;
	}

	/**
	 * 获取当前日期
	 *
	 * @return
	 */
	public static Date nowDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * +-month
	 *
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date origDate, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(origDate);
		calendar.add(Calendar.MONTH, month);

		return calendar.getTime();
	}

	public static Date addDay(Date origDate, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(origDate);
		calendar.add(Calendar.DATE, date);

		return calendar.getTime();
	}
}
