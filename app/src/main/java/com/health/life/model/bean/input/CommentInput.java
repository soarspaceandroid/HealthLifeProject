package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */

public class CommentInput extends BaseBeanInput{

    public String access_token ;
    public int oid;
    public String otype ;
    public String title ;
    public String memo ;

    public CommentInput(String access_token, int oid, String otype, String title, String memo) {
        this.access_token = access_token;
        this.oid = oid;
        this.otype = otype;
        this.title = title;
        this.memo = memo;
    }

    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.addComment(access_token,oid , otype , title , memo);
    }
}
