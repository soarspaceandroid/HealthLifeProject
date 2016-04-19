package com.health.life.model.bean.output;

import java.util.List;

/**
 * 图片Bean
 * Created by gaofei on 16/1/20.
 */
public class CookDetailOutput extends BaseBeanOutput {

    public boolean status;

    public List<TngouEntity> tngou;

    public static class TngouEntity {
        public int count;
        public String description;
        public int id;
        public int fcount;
        public String food;
        public String images;
        public String img;
        public String keywords;
        public String message;
        public String name;
        public int rcount;
    }
}
