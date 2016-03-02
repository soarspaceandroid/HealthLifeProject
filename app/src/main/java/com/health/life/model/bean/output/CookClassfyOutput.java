package com.health.life.model.bean.output;

import java.util.List;

/**
 * 图片Bean
 * Created by gaofei on 16/1/20.
 */
public class CookClassfyOutput extends BaseBeanOutput {

    public boolean status;

    public List<TngouEntity> tngou;

    public static class TngouEntity {
        public int cookclass;
        public String description;
        public int id;
        public String keywords;
        public String name;
        public int seq;
        public String title;
    }
}
