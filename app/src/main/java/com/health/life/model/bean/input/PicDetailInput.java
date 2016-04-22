package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */

public class PicDetailInput extends BaseBeanInput{

    public long id;

    public PicDetailInput(long id) {
        this.id = id;
    }

    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.getPicDetail(id);
    }
}
