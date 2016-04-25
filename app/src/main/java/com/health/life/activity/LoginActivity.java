package com.health.life.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;

import com.health.life.R;
import com.health.life.base.BaseActivity;
import com.health.life.model.bean.input.LoginInput;
import com.health.life.model.bean.output.LoginOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.utils.Config;
import com.health.life.view.editinputlayout.MaterialEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaofei on 2016/4/22.
 */
public class LoginActivity extends BaseActivity implements BaseViewInterface<LoginOutput> {


    @Bind(R.id.login_name)
    MaterialEditText loginName;
    @Bind(R.id.login_code)
    MaterialEditText loginCode;
    @Bind(R.id.register_to)
    RelativeLayout registerTo;
    @Bind(R.id.submit_login)
    CardView submitLogin;
    private BasePresenter basePresenter;


    public static void showActivity(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    public static void showActivityForResult(Activity activity , int requestCode){
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        controlMenu(false);
        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);
        registerTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.showActivity(LoginActivity.this);
            }
        });
        submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRequest(loginName.getText().toString(), loginCode.getText().toString());
            }
        });

    }

    @Override
    protected void requestData() {

    }

    private void doRequest(String userName , String psw){
        basePresenter.setInput(new LoginInput(userName , psw)).load();
    }

    @Override
    public void updateView(LoginOutput loginOutput) {
        // 保存登录信息
        share.setValues(Config.LOGIN_KEY_TOKEN , loginOutput.access_token);
        share.setValues(Config.LOGIN_KEY_ID , loginOutput.id);
        share.setValues(Config.LOGIN_KEY_REFRESH_TOKEN, loginOutput.refresh_token);
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    protected String currActivityName() {
        return "登录";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Config.REGISTER_RESULT && resultCode == Activity.RESULT_OK){
            setResult(Activity.RESULT_OK);
            this.finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
