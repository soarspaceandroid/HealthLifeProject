package com.health.life.model.bean.input;

import com.health.life.model.bean.output.BaseBeanOutput;
import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/24.
 */
public class BaseBeanInput<T extends BaseBeanOutput> {

    private boolean showDialog = false;

    public Observable<T> getData(BaseEnity baseEnity){
        return null;
    };

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
