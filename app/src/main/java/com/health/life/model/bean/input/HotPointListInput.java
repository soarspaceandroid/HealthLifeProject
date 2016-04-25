package com.health.life.model.bean.input;

import com.health.life.model.bean.output.HotPointListOutput;
import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */
public class HotPointListInput extends BaseBeanInput {

    public int page;
    public int rows;
    public int id;

    public HotPointListInput(int page, int rows, int id) {
        this.page = page;
        this.rows = rows;
        this.id = id;
    }

    @Override
    public Observable<HotPointListOutput> getData(BaseEnity baseEnity) {
        Observable<HotPointListOutput> observable = baseEnity.getHotPointList(id , page, rows);
        return observable;
    }

}
