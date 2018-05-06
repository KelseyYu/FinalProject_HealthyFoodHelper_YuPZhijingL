package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * @author bsl
 * @package com.tencent.mm.plugin.util
 * @filename LogUtil
 * @date 18-3-5
 * @email don't tell you
 * @describe encapsulate log class for manager conveniently
 */

public class LogUtil {

    private static final int VERBOSE    = 1;
    private static final int DEBUG      = 2;
    private static final int INFO       = 3;
    private static final int WARN       = 4;
    private static final int ERROR      = 5;
    private static final int NOTHING    = 6;

    public static int level = VERBOSE;  // now level of log to control log

    private static final String TOKEN = "LogUtil, ";

    // log in level verbose
    public static void v(String TAG, String msg) {
        if(level <= VERBOSE) {
            String out = TOKEN + "(v) " + msg;
            Log.v(TAG, out);
            write(TAG + "->" + out);
        }
    }

    // log in level debug
    public static void d(String TAG, String msg) {
        if(level <= DEBUG) {
            String out = TOKEN + "(d) " + msg;
            Log.d(TAG, out);
            write(TAG + "->" + out);
        }
    }

    // log in level info
    public static void i(String TAG, String msg) {
        if(level <= INFO) {
            String out = TOKEN + "(i) " + msg;
            Log.i(TAG, out);
            write(TAG + "->" + out);
        }
    }

    // log in level warn
    public static void w(String TAG, String msg) {
        if(level <= WARN) {
            String out = TOKEN + "(w) " + msg;
            Log.w(TAG, out);
            write(TAG + "->" + out);
        }
    }

    // log in level error
    public static void e(String TAG, String msg) {
        if(level <= ERROR) {
            String out = TOKEN + "(e) " + msg;
            Log.e(TAG, out);
            write(TAG + "->" + out);
        }
    }

    // close log switch
    public static void closeLog() {
        level = NOTHING;
    }

    // log out level now
    public static void outputLevel() {
        Log.i("LogUtil", "level is " + level);
    }

    // write log into file
    private static void write(@NonNull String data) {
       /*
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long time   = System.currentTimeMillis();
        String name = "/mnt/sdcard/" + format.format(time);

        File file = new File(name);
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;

        try {
            if (file.exists() == false) {
                file.createNewFile();
            }

            outputStream    = new FileOutputStream(file);
            writer          = new BufferedWriter(new OutputStreamWriter(outputStream));
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(time);
            writer.write(date + "  " + data + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) {
                    writer.close();
                    writer = null;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    */
    }

}
