package com.health.life.model.bean.output;

import com.google.gson.annotations.SerializedName;

/**
 * 图片Bean
 * Created by ligang967 on 16/1/20.
 */
public class CookClassfyOutput extends BaseBeanOutput {

    @SerializedName("id")
    public int id;
    @SerializedName("cookclass")
    public String cookclass;
    @SerializedName("name")
    public String name;
    @SerializedName("title")
    public String title;
    @SerializedName("keywords")
    public String keywords;
    @SerializedName("description")
    public String description;
    @SerializedName("seq")
    public int seq;
}
