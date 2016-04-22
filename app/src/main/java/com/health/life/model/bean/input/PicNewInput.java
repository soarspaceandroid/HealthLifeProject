package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */

public class PicNewInput extends BaseBeanInput{

    public int classify;
    public int rows;
    public long id;

    public PicNewInput(int classify, int rows, long id) {
        this.classify = classify;
        this.rows = rows;
        this.id = id;
    }

    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.getPicNew(id,classify,rows);
    }
}
