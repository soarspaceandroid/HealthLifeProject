package com.health.life.model.bean.output;

import java.util.List;

/**
 * 图片Bean
 * Created by gaofei on 16/1/20.
 */
public class HotPointListOutput extends BaseBeanOutput {

    public boolean status;
    public int total;
    public List<TngouEntity> tngou;

    public static class TngouEntity {
        public int id;
        public int count;
        public long time;
        public int rcount;
        public int topclass;
        public int fcount;
        public String description;
        public String fromname;
        public String fromurl;
        public String img;
        public String keywords;
        public String title;



    }
}
