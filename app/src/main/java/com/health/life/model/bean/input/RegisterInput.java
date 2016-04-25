package com.health.life.model.bean.input;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/26.
 */

public class RegisterInput extends BaseBeanInput{
    public String email ;
    public String account ;
    public String password ;

    public RegisterInput( String email, String account, String password) {
        this.email = email;
        this.account = account;
        this.password = password;
    }

    @Override
    public Observable getData(BaseEnity baseEnity) {
        return baseEnity.register(client_id,client_secret,email,account,password);
    }
}
