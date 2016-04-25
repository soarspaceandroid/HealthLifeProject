package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;
import com.health.life.utils.Config;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/24.
 */
public class BaseBeanInput {

    public final static  String client_id = Config.APP_ID;
    public final static String client_secret = Config.APP_KEY;

    private boolean showDialog = true;

    public Observable getData(BaseEnity baseEnity){
        return null;
    };

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
