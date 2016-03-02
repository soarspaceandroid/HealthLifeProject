package com.health.life.model.bean.input;

import com.health.life.model.bean.output.BookListInfoOutput;
import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */
public class BookListInfoInput extends BaseBeanInput {

    private int page;
    private int rows;
    private int id;

    public BookListInfoInput(int page, int rows, int id) {
        this.page = page;
        this.rows = rows;
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Observable<BookListInfoOutput> getData(BaseEnity baseEnity) {

        Observable<BookListInfoOutput> observable = baseEnity.getListById(this.id,this.page,this.rows);

        return observable;
    }

}
