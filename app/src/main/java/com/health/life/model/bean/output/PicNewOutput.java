package com.health.life.model.bean.output;

import java.util.List;

/**
 * 图片Bean
 * Created by gaofei on 16/1/20.
 */
public class PicNewOutput extends BaseBeanOutput {

    public boolean status;
    public int total;
    public List<TngouEntity> tngou;

    public static class TngouEntity {
        public int id;
        public int size;
        public int rcount;
        public long time;
        public String title;
        public int count;
        public int fcount;
        public int galleryclass;
        public String img;


    }
}
