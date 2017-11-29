package com.aillience.enginee.util;

import android.util.Log;

import com.aillience.enginee.BuildConfig;

/**
 * Log统一管理类
 * @author yfl
 */

@SuppressWarnings("unused")
public class MyLog {

    private MyLog() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    /**
     * isDebug
     * 是否需要打印bug，可以在application的onCreate函数里面初始化
     */
    private static boolean isDebug = BuildConfig.LOG_DEBUG;
    private static final String TAG = "way";

    public static void i(String msg) {
        if (isDebug){
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug){
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug){
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug){
            Log.v(TAG, msg);
        }
    }
    public static void i(String tag, String msg) {
        if (isDebug){
            Log.i(tag, msg);
        }
    }
    public static void d(String tag, String msg) {
        if (isDebug){
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug){
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug){
            Log.v(tag, msg);
        }
    }
}

