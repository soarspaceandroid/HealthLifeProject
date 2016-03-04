package com.health.life.model.bean.output;

import com.health.life.model.bean.input.BaseBeanInput;

import java.util.List;

/**
 * Created by gaofei on 2016/2/26.
 */

public class CookClassifyListInfoOutput extends BaseBeanInput{

    public boolean status;
    public int total;
    public List<Cook> tngou;


    public static class  Cook  {
        public int id;
        public String name;//名称
        public String  food;//食物
        public String img;//图片
        public String images;//图片,
        public String description;//描述
        public String keywords;//关键字
        public String message;//资讯内容
        public int count ;//访问次数
        public int fcount;//收藏数
        public int rcount;//评论读数
    }

}
