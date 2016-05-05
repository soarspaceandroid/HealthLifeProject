package com.health.life.utils;


import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.concurrent.TimeUnit;


/**
 * Created by ligang967 on 16/2/23.
 */
public class OkHttpUtils {



    private static OkHttpClient singleton;

    public static OkHttpClient getInstance() {
        if (singleton == null) {
            synchronized (OkHttpUtils.class) {
                if (singleton == null) {
                    File cacheDir = new File(Config.CACHAE_DIR, Config.RESPONSE_CACHE);

                    singleton = new OkHttpClient();
                    try {
                        singleton.setCache(new Cache(cacheDir, Config.RESPONSE_CACHE_SIZE));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    singleton.networkInterceptors().add(new HttpLoggingInterceptor());
                    singleton.setConnectTimeout(Config.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
                    singleton.setReadTimeout(Config.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
                }
            }
        }
        return singleton;
    }
}
