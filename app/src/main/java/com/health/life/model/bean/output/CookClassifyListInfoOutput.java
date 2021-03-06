package com.health.life.model.bean.output;

import java.util.List;

/**
 * Created by gaofei on 2016/2/26.
 */

public class CookClassifyListInfoOutput extends BaseBeanOutput{

    public boolean status;
    public int total;
    public List<TngouEntity> tngou;


    public static class  TngouEntity  {
        public int id;
        public String name;//名称
        public String  food;//食物
        public String img;//图片
        public String images;//图片,
        public String description;//描述
        public String keywords;//关键字
        public int count ;//访问次数
        public int fcount;//收藏数
        public int rcount;//评论读数
    }

}
