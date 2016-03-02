package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/24.
 */
public class BaseBeanInput {

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
