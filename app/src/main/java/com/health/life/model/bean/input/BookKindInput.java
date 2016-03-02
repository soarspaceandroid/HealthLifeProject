package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by ligang967 on 16/3/2.
 */
public class BookKindInput extends BaseBeanInput {
    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.getClassify();
    }
}
