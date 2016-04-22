package com.health.life.model.enity;


import com.health.life.model.bean.output.BookKindListBeanOutput;
import com.health.life.model.bean.output.BookListInfoOutput;
import com.health.life.model.bean.output.CookClassfyOutput;
import com.health.life.model.bean.output.CookClassifyListInfoOutput;
import com.health.life.model.bean.output.CookDetailOutput;
import com.health.life.model.bean.output.FoodDetailOutput;
import com.health.life.model.bean.output.PicClassfyOutput;
import com.health.life.model.bean.output.PicDetailOutput;
import com.health.life.model.bean.output.PicInfoOutput;
import com.health.life.model.bean.output.PicNewOutput;

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


    /**
     * 获取pic分类
     * @return
     */
    @POST("/tnfs/api/classify")
    public Observable<PicClassfyOutput> getPicClassfy();

    /**
     * 获取picinfo
     * @return
     */
    @POST("/tnfs/api/list")
    public Observable<PicInfoOutput> getPicInfo(@Query("id") int id);

    /**
     * 获取pic 最新
     * @return
     */
    @POST("/tnfs/api/news")
    public Observable<PicNewOutput> getPicNew(@Query("id") long id,@Query("classify") int classify,@Query("rows") int rows);

    /**
     * 获取pic 
     * detail
     * @return
     */
    @POST("/tnfs/api/show")
    public Observable<PicDetailOutput> getPicDetail(@Query("id") long id);
}
