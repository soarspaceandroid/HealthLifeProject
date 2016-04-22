package com.health.life.model.bean.output;

import java.util.List;

/**
 * 图片Bean
 * Created by gaofei on 16/1/20.
 */
public class PicClassfyOutput extends BaseBeanOutput {

    public boolean status;

    public List<TngouEntity> tngou;

    public static class TngouEntity {
        public String description;
        public int id;
        public String keywords;
        public String name;
        public String title;
        public int seq;
    }
}
