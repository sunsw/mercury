package com.sunsw.mercury.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Sucre on 2016/12/29.
 */
public class DateUtils {

    public static final String FORMAT_DEFAULT = "yyyy-MM-dd";
    public static final String FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_MIN = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_CHINESE = "yyyy年MM月dd日";
    public static final String FORMAT_ALL_M = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FORMAT_SMALL = "yyyyMMddHHmmss";
    public static final String FORMAT_DEFAULT_SMALL = "yyyyMMdd";
    public static final String FORMAT_TIME = "HH:mm:ss";

    private static SimpleDateFormat getDateFormat(String patten) {
        return new SimpleDateFormat(patten);
    }

    public static String getToday() {
        return getDateFormat(FORMAT_ALL).format(Calendar.getInstance().getTime());
    }

    public static String getToday(String format) {
        return getDateFormat(format).format(Calendar.getInstance().getTime());
    }

    public static String getWeek(Date date) {
        String[] weeks = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    public static String formatDate(Date date) {
        return getDateFormat(FORMAT_ALL).format(date);
    }

    public static String formatDate(Date date, String patten) {
        if (!StringUtils.isEmpty(date) && !StringUtils.isEmpty(patten)) {
            return getDateFormat(patten).format(date);
        }
        return null;
    }

    public static Date parseDate(String source, String patten) throws ParseException {
        if (!StringUtils.isEmpty(source) && !StringUtils.isEmpty(patten)) {
            return getDateFormat(patten).parse(source);
        }
        return null;
    }

    public static Date addDays(Date time, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.set(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static long date2Long(String date) throws ParseException {
        SimpleDateFormat format = getDateFormat(FORMAT_ALL);
        Date bDate = format.parse(date);
        GregorianCalendar d1 = new GregorianCalendar();
        d1.setTime(bDate);
        return d1.getTimeInMillis();
    }

    public static long date2Long(String date, String formatStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date bDate = format.parse(date);
        GregorianCalendar d1 = new GregorianCalendar();
        d1.setTime(bDate);
        return d1.getTimeInMillis();
    }

    public static String long2DateStr(long msel, String format) {
        Date date = new Date(msel);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat formatter = getDateFormat(format);
        return formatter.format(date);
    }

    public static long getIntervalMillis(String startDate, String endDate) throws ParseException {
        Date bDate = getDateFormat(FORMAT_ALL).parse(startDate);
        Date eDate = getDateFormat(FORMAT_ALL).parse(endDate);
        GregorianCalendar d1 = new GregorianCalendar();
        d1.setTime(bDate);
        GregorianCalendar d2 = new GregorianCalendar();
        d2.setTime(eDate);
        return d2.getTimeInMillis() - d1.getTimeInMillis();
    }

    public static int getIntervalMinutes(String startDate, String endDate) throws ParseException {
        long ei = getIntervalMillis(startDate, endDate);
        int dd = (int) (ei / 60000L);
        if (dd < 1) {
            dd = 1;
        }
        return dd;
    }

    public static int getIntervalHour(String startDate, String endDate) throws ParseException {
        long ei = getIntervalMillis(startDate, endDate);
        int dd = (int) (ei / 3600000L);
        if (dd < 1) {
            dd = 1;
        }
        return dd;
    }

    public static int getIntervalDays(String startDate, String endDate) throws ParseException {
        long ei = getIntervalMillis(startDate, endDate);
        int dd = (int) (ei / 86400000L);
        return dd;
    }

    public static int getIntervalDays(Date startDate, Date endDate) {
        GregorianCalendar d1 = new GregorianCalendar();
        d1.setTime(startDate);
        GregorianCalendar d2 = new GregorianCalendar();
        d2.setTime(endDate);
        long ei = d2.getTimeInMillis() - d1.getTimeInMillis();
        int dd = (int) (ei / 86400000L);
        return dd;
    }

}
