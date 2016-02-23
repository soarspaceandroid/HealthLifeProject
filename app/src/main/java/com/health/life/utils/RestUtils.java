package com.health.life.utils;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


/**
 * Created by ligang967 on 16/1/19.
 */
public class RestUtils {

    private static final String BASE_URL = "http://www.tngou.net";
    private static Retrofit retrofit;

    /**
     * 创建API实例
     *
     * @param cls Api定义类的类型
     * @param <T> 范型
     * @return API实例
     */
    public static <T> T createApi(Class<T> cls) {


        return restAdapterInstance().create(cls);
    }

    private static Retrofit restAdapterInstance() {


        if (retrofit == null) synchronized (RestUtils.class) {

            return retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(OkHttpUtils.getInstance())
                    .build();
        }
        else {
            return retrofit;
        }
    }
}
