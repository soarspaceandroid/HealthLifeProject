package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */

public class CookClassifyListInfoInput extends BaseBeanInput{

    public int page;
    public int rows;
    public int id;

    public CookClassifyListInfoInput(int page, int rows, int id) {
        this.page = page;
        this.rows = rows;
        this.id = id;
    }
    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.getCookListInfo(id , page , rows);
    }
}
