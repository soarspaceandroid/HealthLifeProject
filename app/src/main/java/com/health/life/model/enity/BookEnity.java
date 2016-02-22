package com.health.life.model.enity;


import com.health.life.model.bean.BookKindListBean;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by ligang967 on 16/1/20.
 */
public interface BookEnity
{
    @GET("/api/book/classify")
    public Observable<BookKindListBean> getClassify();

}
