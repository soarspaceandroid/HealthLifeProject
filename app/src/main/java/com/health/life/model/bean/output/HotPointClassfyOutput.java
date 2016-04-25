package com.health.life.model.bean.output;

import java.util.List;

/**
 * 图片Bean
 * Created by gaofei on 16/1/20.
 */
public class HotPointClassfyOutput extends BaseBeanOutput {

    public boolean status;
    public List<TngouEntity> tngou;

    public static class TngouEntity {
        public int id;
        public int seq;
        public String description;
        public String keywords;
        public String name;
        public String title;


    }
}
