package com.health.life.model.bean.output;

import java.util.List;

/**
 * 图片Bean
 * Created by gaofei on 16/1/20.
 */
public class PicDetailOutput extends BaseBeanOutput {

    public int id;
    public int count;
    public int fcount;
    public int galleryclass;
    public String img;

    public int rcount;
    public int size;
    public boolean status;
    public long time;
    public String title;
    public String url;

    public List<TngouEntity> list;

    public static class TngouEntity {
        public int gallery;
        public int id;
        public String src;
    }
}
