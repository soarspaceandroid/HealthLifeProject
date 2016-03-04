package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */

public class CookClassifyListInfoInput extends BaseBeanInput{

    private int page;
    private int rows;
    private int id;

    public CookClassifyListInfoInput(int page, int rows, int id) {
        this.page = page;
        this.rows = rows;
        this.id = id;
    }
    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.getCookListInfo(this);
    }
}
