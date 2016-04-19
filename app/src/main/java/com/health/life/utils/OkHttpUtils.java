package com.health.life.utils;

import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
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

                    Interceptor interceptor = new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            //拦截
                            Response originalResponse = chain.proceed(chain.request());
                            //包装响应体并返回
                            Log.e("soar" , "result --- "+(originalResponse.newBuilder().body(originalResponse.body()).build()));
                            return originalResponse.newBuilder().body(originalResponse.body()).build();
                        }
                    };
                    singleton.networkInterceptors().add(interceptor);
                    singleton.setConnectTimeout(Config.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
                    singleton.setReadTimeout(Config.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
                }
            }
        }
        return singleton;
    }
}
