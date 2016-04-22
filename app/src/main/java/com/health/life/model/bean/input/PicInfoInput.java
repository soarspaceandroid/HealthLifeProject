package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */

public class PicInfoInput extends BaseBeanInput{

    private int id;

    public PicInfoInput(int id) {
        this.id = id;
    }

    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.getPicInfo(id);
    }
}
