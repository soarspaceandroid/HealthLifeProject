package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */

public class LoginInput extends BaseBeanInput{

    public String name ;
    public String password ;

    public LoginInput(String name,  String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.login(client_id, client_secret, name ,password);
    }
}
