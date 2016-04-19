package com.health.life.model.enity;


import com.health.life.model.bean.output.BookKindListBeanOutput;
import com.health.life.model.bean.output.BookListInfoOutput;
import com.health.life.model.bean.output.CookClassfyOutput;
import com.health.life.model.bean.output.CookClassifyListInfoOutput;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by ligang967 on 16/1/20.
 */
public interface BaseEnity
{

    /**健康 接口  start**/
    @GET("/api/book/classify")
    public Observable<BookKindListBeanOutput> getClassify();

    @POST("/api/info/list")
    public Observable<BookListInfoOutput> getListById(@Query("id") int id,@Query("page") int page,@Query("rows") int rows);



    /**生活 接口  start**/

    @POST("/api/cook/classify")
    public Observable<CookClassfyOutput> getCookClassfy(@Query("id") int id);


    @POST("/api/cook/list")
    public Observable<CookClassifyListInfoOutput> getCookListInfo(@Query("id") int id,@Query("page") int page,@Query("rows") int rows);

}
