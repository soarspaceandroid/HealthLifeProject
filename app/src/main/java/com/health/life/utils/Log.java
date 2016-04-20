package com.health.life.utils;

/**
 * Created by gaofei on 2016/4/20.
 */
public class Log {

    private final static String TAG = "debug";

    public static void e(String message){
        android.util.Log.e(TAG , message);
    }

    public static void d(String message){
        android.util.Log.e(TAG , message);
    }

    public static void i(String message){
        android.util.Log.e(TAG , message);
    }
}
