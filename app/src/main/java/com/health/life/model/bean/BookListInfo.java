package com.health.life.model.bean;

import java.util.List;

/**
 * Created by ligang967 on 16/2/23.
 */
public class BookListInfo {

    /**
     * total : 489
     * tngou : [{"count":1,"description":"\u2026\u2026","fcount":0,"id":503,"img":"\u2026\u2026","keywords":"\u2026\u2026","rcount":0,"time":1435561395000,"title":"\u2026\u2026","topclass":0}]
     */

    private int total;
    /**
     * count : 1
     * description : ……
     * fcount : 0
     * id : 503
     * img : ……
     * keywords : ……
     * rcount : 0
     * time : 1435561395000
     * title : ……
     * topclass : 0
     */

    private List<TngouEntity> tngou;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTngou(List<TngouEntity> tngou) {
        this.tngou = tngou;
    }

    public int getTotal() {
        return total;
    }

    public List<TngouEntity> getTngou() {
        return tngou;
    }

    public static class TngouEntity {
        private int count;
        private String description;
        private int fcount;
        private int id;
        private String img;
        private String keywords;
        private int rcount;
        private long time;
        private String title;
        private int topclass;

        public void setCount(int count) {
            this.count = count;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTopclass(int topclass) {
            this.topclass = topclass;
        }

        public int getCount() {
            return count;
        }

        public String getDescription() {
            return description;
        }

        public int getFcount() {
            return fcount;
        }

        public int getId() {
            return id;
        }

        public String getImg() {
            return img;
        }

        public String getKeywords() {
            return keywords;
        }

        public int getRcount() {
            return rcount;
        }

        public long getTime() {
            return time;
        }

        public String getTitle() {
            return title;
        }

        public int getTopclass() {
            return topclass;
        }
    }
}
