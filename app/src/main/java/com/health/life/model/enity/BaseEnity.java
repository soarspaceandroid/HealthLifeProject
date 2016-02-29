package com.health.life.model.enity;


import com.health.life.model.bean.input.BookListInfoInput;
import com.health.life.model.bean.output.BookKindListBeanOutput;
import com.health.life.model.bean.output.BookListInfoOutput;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by ligang967 on 16/1/20.
 */
public interface BaseEnity
{
    @GET("/api/book/classify")
    public Observable<BookKindListBeanOutput> getClassify();

    @POST("/api/info/list")
    public Observable<BookListInfoOutput> getListById(@Body BookListInfoInput input);

}
