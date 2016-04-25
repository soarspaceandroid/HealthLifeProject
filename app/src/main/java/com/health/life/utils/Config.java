package com.health.life.utils;

import java.io.File;

/**
 * Created by ligang967 on 16/2/23.
 */
public class Config {


    public static String APP_KEY = "ac9d2a68329f0705eaefff78fedba19e";

    public static String APP_ID ="967455";

    public static int RESPONSE_CACHE_SIZE = 10 * 1024 * 1024;

    public static String RESPONSE_CACHE = "cache_name";

    public static int HTTP_CONNECT_TIMEOUT = 20 * 1000;

    public static int HTTP_READ_TIMEOUT = 20 * 1000;

    public static File CACHAE_DIR = null;

    public static String BASE_IMAGE_URL = "http://tnfs.tngou.net/image";


    /**健康 接口  start**/




    /**生活 接口  start**/

    //login register name code
    public final static String LOGIN_KEY_TOKEN = "linfo1";
    public final static String LOGIN_KEY_ID = "linfo2";
    public final static String LOGIN_KEY_REFRESH_TOKEN = "linfo3";

    public final static int REGISTER_RESULT = 1001;
    public  final  static int LOGIN_RESULT = 1002;
}
