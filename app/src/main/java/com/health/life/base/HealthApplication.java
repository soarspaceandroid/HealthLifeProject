package com.health.life.base;

import android.app.Application;

import com.health.life.utils.Config;

/**
 * Created by ligang967 on 16/2/23.
 */
public class HealthApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Config.CACHAE_DIR=getCacheDir();
    }
}
