package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */

public class HotPointDetailInput extends BaseBeanInput{

    public int id;

    public HotPointDetailInput(int id) {
        this.id = id;
    }

    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.getHotPointDetail(id);
    }
}
