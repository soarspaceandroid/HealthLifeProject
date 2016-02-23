package com.health.life.model.enity;

import com.health.life.model.bean.BookListInfo;

import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by ligang967 on 16/2/23.
 */
public interface BookListEnity {


    @POST("/api/info/list")
    public Observable<BookListInfo> getListById(@Query("page")int page,@Query("rows")int rows,@Query("id")int id);
}
