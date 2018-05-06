package com.healthy.food.helper.util;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author bsl
 * @package little.boy.bsl.timeassistant.util
 * @filename TimeHelper
 * @date 18-4-19
 * @email don't tell you
 * @describe TODO
 */

public class TimeHelper {

    private static final String TAG = "TimeHelper";

    public static final int ONE_SECOND  = 1000;

    public static final int ONE_MINUTE  = 60 * ONE_SECOND;

    public static final int ONE_HOUR    = 60 * ONE_MINUTE;

    public static final int ONE_DAY     = 24 * ONE_HOUR;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({FORMAT_MODE_TIME , FORMAT_MODE_DAY, FORMAT_MODE_DATE})
    public @interface FormatMode {}

    public static final String FORMAT_MODE_DAY   = "yyyy-MM-dd";

    public static final String FORMAT_MODE_TIME  = "HH:mm:ss";

    public static final String FORMAT_MODE_DATE  = FORMAT_MODE_DAY + " " + FORMAT_MODE_TIME;

    // translate time from long to str in style of format
    public static String long2str(long time, @FormatMode String format) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    // translate time from str to long in style of format
    public static long str2long(String time, @FormatMode String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(time);
            if(date != null) {
                return date.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0L;
    }

    // get current time in long
    public static long getTime() {
        return System.currentTimeMillis();
    }

    // get time by zone in String
    public static String getTimeByZone(String zone, @FormatMode String format) {
        TimeZone timeZone = TimeZone.getTimeZone(zone);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date());
    }

}
