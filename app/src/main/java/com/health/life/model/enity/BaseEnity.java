package com.health.life.model.enity;


import com.health.life.model.bean.output.BookKindListBeanOutput;
import com.health.life.model.bean.output.BookListInfoOutput;
import com.health.life.model.bean.output.CookClassfyOutput;
import com.health.life.model.bean.output.CookClassifyListInfoOutput;
import com.health.life.model.bean.output.CookDetailOutput;
import com.health.life.model.bean.output.FoodDetailOutput;

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

    /**
     * 菜谱分类
     * @param id
     * @return
     */
    @POST("/api/cook/classify")
    public Observable<CookClassfyOutput> getCookClassfy(@Query("id") int id);


    /**
     * 菜谱list
     * @param id
     * @param page
     * @param rows
     * @return
     */
    @POST("/api/cook/list")
    public Observable<CookClassifyListInfoOutput> getCookListInfo(@Query("id") int id,@Query("page") int page,@Query("rows") int rows);

    /**
     * 获取菜谱信息    同一种菜
     * @param name
     * @return
     */
    @POST("/api/cook/name")
    public Observable<CookDetailOutput> getCookInfoDetail(@Query("name") String name);


    /**
     * 获取菜单详情
     * @param id
     * @return
     */
    @POST("/api/cook/show")
    public Observable<FoodDetailOutput> getFoodDetail(@Query("id") int id);

}
