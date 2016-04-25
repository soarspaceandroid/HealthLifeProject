package com.health.life.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.view.View;

import com.health.life.R;
import com.health.life.base.BaseActivity;
import com.health.life.model.bean.input.RegisterInput;
import com.health.life.model.bean.output.RegisterOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.utils.Config;
import com.health.life.view.editinputlayout.MaterialEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaofei on 2016/4/22.
 */
public class RegisterActivity extends BaseActivity implements BaseViewInterface<RegisterOutput> {



    @Bind(R.id.register_email)
    MaterialEditText registerEmail;
    @Bind(R.id.edit_phone_inputlayout)
    TextInputLayout editPhoneInputlayout;
    @Bind(R.id.register_name)
    MaterialEditText registerName;
    @Bind(R.id.register_code)
    MaterialEditText registerCode;
    @Bind(R.id.submit_register)
    CardView submitRegister;
    private BasePresenter basePresenter;

    public static void showActivity(Activity activity) {
        activity.startActivityForResult(new Intent(activity, RegisterActivity.class), Config.REGISTER_RESULT);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        controlMenu(false);
        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);
        submitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRequest();
            }
        });
    }

    @Override
    protected void requestData() {
    }

    public void  doRequest(){
        basePresenter.setInput(new RegisterInput(registerEmail.getText().toString() ,registerName.getText().toString() , registerCode.getText().toString())).load();
    }

    @Override
    public void updateView(RegisterOutput registerOutput) {
        share.setValues(Config.LOGIN_KEY_TOKEN , registerOutput.access_token);
        share.setValues(Config.LOGIN_KEY_ID , registerOutput.id);
        share.setValues(Config.LOGIN_KEY_REFRESH_TOKEN , registerOutput.refresh_token);
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected String currActivityName() {
        return "注册";
    }


}
