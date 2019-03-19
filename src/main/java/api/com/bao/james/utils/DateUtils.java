package api.com.bao.james.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: James Bao
 * @Date: 2018/12/4 10:05
 */
public class DateUtils {

    private static String EMPTY = "";
    private static String datePattern = "yyyy-MM-dd";
    private static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(dateTimePattern);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);

    /**
     * String 转 Date
     * 格式: yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date stringToDateTime(String strDate) {
        if (strDate == null) {
            return null;
        }
        ParsePosition pos = new ParsePosition(0);
        return dateTimeFormat.parse(strDate, pos);
    }

    /**
     * String 转 Date
     * 格式: yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date stringToDate(String strDate) {
        if (strDate == null) {
            return null;
        }
        ParsePosition pos = new ParsePosition(0);
        return dateFormat.parse(strDate, pos);
    }

    /**
     * Date 转 String
     * 格式: yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateTimeToString(Date dateDate) {
        if (dateDate == null) {
            return EMPTY;
        }
        return dateTimeFormat.format(dateDate);
    }

    /**
     * Date 转 String
     * 格式: yyyy-MM-dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToString(Date dateDate) {
        if (dateDate == null) {
            return EMPTY;
        }
        return dateFormat.format(dateDate);
    }

    /**
     * LocalDate 转 String
     * 格式: yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
    public static String dateToString(LocalDate dateDate) {
        if (dateDate == null) {
            return EMPTY;
        }
        return dateFormatter.format(dateDate);
    }

    /**
     * LocalDateTime 转 String
     * 格式: yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateTimeToString(LocalDateTime dateDate) {
        if (dateDate == null) {
            return EMPTY;
        }
        return dateTimeFormatter.format(dateDate);
    }

    /**
     * Date 转 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }

        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone).toLocalDate();
    }


    /**
     * 距离今天的时间差(天)
     *
     * @param before
     * @return
     */
    public static long getDays(LocalDate before) {
        if (before == null) {
            return 0;
        }

        LocalDate now = LocalDate.now();

        return now.toEpochDay() - before.toEpochDay();
    }

    /**
     * 距离今天的时间差(周),不足一周的舍去
     *
     * @param before
     * @return
     */
    public static long getWeeks(LocalDate before) {
        if (before == null) {
            return 0;
        }
        long days = getDays(before);
        return days / 7;
    }

    /**
     * 距离今天的时间差(周),不足一周的舍去
     *
     * @param before
     * @return
     */
    public static long getWeeks(Date before) {
        if (before == null) {
            return 0;
        }
        LocalDate localDate = dateToLocalDate(before);
        long days = getDays(localDate);
        return days / 7;
    }

    /**
     * 获取前月的第一天
     *
     * @return
     */
    public static Date getMonthFirstDay(Integer month) {
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, month);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        //将小时至0
        cal_1.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        cal_1.set(Calendar.MINUTE, 0);
        //将秒至0
        cal_1.set(Calendar.SECOND, 0);
        //将毫秒至0
        cal_1.set(Calendar.MILLISECOND, 0);
        return cal_1.getTime();
    }

    /**
     * 获取某月的最后一天
     */
    public static Date getLastDayOfMonth(int month) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, month);
        //将小时至0
        cal.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至0
        cal.set(Calendar.MINUTE, 59);
        //将秒至0
        cal.set(Calendar.SECOND, 59);
        //将毫秒至0
        cal.set(Calendar.MILLISECOND, 999);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
    }

    public static long getMonths(Date before) {
        dateToString(before);
        int i = calDiffMonth(dateToString(before), dateToString(new Date()));
        return i;
    }


    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的月份，1-12,即yyyy-MM-dd中的MM
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日期的年,即yyyy-MM-dd中的yyyy
     *
     * @param date Date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static int calDiffMonth(String startDate, String endDate) {
        int result = 0;
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sfd.parse(startDate);
            Date end = sfd.parse(endDate);
            int startYear = getYear(start);
            int startMonth = getMonth(start);
            int startDay = getDay(start);
            int endYear = getYear(end);
            int endMonth = getMonth(end);
            int endDay = getDay(end);
            if (startDay > endDay) { //1月17  大于 2月28
                if (endDay == getDaysOfMonth(getYear(new Date()), 2)) {   //也满足一月
                    result = (endYear - startYear) * 12 + endMonth - startMonth;
                } else {
                    result = (endYear - startYear) * 12 + endMonth - startMonth - 1;
                }
            } else {
                result = (endYear - startYear) * 12 + endMonth - startMonth;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 列出两个日期中所有的年份
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static List<Integer> yearsInTwoDate(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int startYear = start.get(Calendar.YEAR);
        int endYear = end.get(Calendar.YEAR);
        List<Integer> years = new ArrayList<>();
        for (int i = startYear; i <= endYear; i++) {
            years.add(i);
        }
        return years;
    }

    /**
     * 列出两个日期中每年的月数
     * 暂未考虑天的影响（开始日均按当月第一日，结束日均按当月最后一日处理）
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static Map<Integer, Integer> monthDurationPerYear(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int startYear = start.get(Calendar.YEAR);
        int endYear = end.get(Calendar.YEAR);
        Map<Integer, Integer> duration = new LinkedHashMap<>();
        duration.put(startYear, 12 - start.get(Calendar.MONTH) + 1);
        for (int i = startYear + 1; i < endYear; i++) {
            duration.put(i, 12);
        }
        duration.put(endYear, end.get(Calendar.MONTH));
        return duration;
    }

    /**
     * 列出两个日期中经过的月数
     * 暂未考虑天的影响（开始日均按当月第一日，结束日均按当月最后一日处理）
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static Integer monthDuration(Date startDate, Date endDate) {
        Map<Integer, Integer> monthDurationPerYear = DateUtils.monthDurationPerYear(startDate, endDate);
        return monthDurationPerYear.entrySet().stream().map(Map.Entry::getValue)
                .collect(Collectors.summingInt(Integer::intValue));
    }
}
