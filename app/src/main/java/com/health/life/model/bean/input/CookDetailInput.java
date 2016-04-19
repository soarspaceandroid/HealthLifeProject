package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */

public class CookDetailInput extends BaseBeanInput{

    private String name ;

    public CookDetailInput(String name) {
        this.name = name;
    }

    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.getCookInfoDetail(name);
    }
}
