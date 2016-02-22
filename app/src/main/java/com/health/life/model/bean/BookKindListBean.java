package com.health.life.model.bean;

import java.util.List;

/**
 * 图片列表
 * Created by ligang967 on 16/1/20.
 */
public class BookKindListBean {
    private List<BookKindBean> tngou;

    private String status;

    public List<BookKindBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<BookKindBean> tngou) {
        this.tngou = tngou;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
