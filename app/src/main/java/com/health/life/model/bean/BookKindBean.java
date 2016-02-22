package com.health.life.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 图片Bean
 * Created by ligang967 on 16/1/20.
 */
public class BookKindBean {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("title")
    private String title;
    @SerializedName("keywords")
    private String keywords;
    @SerializedName("description")
    private String description;
    @SerializedName("seq")
    private int seq;//排序 从0。。。。10开始

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
