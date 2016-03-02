package com.health.life.model.enity;


import com.health.life.model.bean.input.BookListInfoInput;
import com.health.life.model.bean.input.CookClassifyInput;
import com.health.life.model.bean.output.BookKindListBeanOutput;
import com.health.life.model.bean.output.BookListInfoOutput;
import com.health.life.model.bean.output.CookClassfyOutput;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
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
    public Observable<BookListInfoOutput> getListById(@Body BookListInfoInput input);



    /**生活 接口  start**/

    @POST("/api/cook/classify")
    public Observable<CookClassfyOutput> getCookClassfy(@Body CookClassifyInput input);

}
