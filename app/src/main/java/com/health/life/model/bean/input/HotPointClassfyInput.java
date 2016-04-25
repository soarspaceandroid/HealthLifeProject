package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */

public class HotPointClassfyInput extends BaseBeanInput{

    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.getHotPointClassfy();
    }
}
