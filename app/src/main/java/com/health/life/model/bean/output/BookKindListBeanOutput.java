package com.health.life.model.bean.output;

import java.util.List;

/**
 * 图片列表
 * Created by ligang967 on 16/1/20.
 */
public class BookKindListBeanOutput extends BaseBeanOutput {

    private List<BookKindBeanOutput> tngou;

    private String status;

    public List<BookKindBeanOutput> getTngou() {
        return tngou;
    }

    public void setTngou(List<BookKindBeanOutput> tngou) {
        this.tngou = tngou;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
