package com.health.life.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.health.life.R;
import com.health.life.base.BaseActivity;
import com.health.life.model.bean.input.PicClassfyInput;
import com.health.life.model.bean.output.PicClassfyOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.view.RadomTransLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ligang967 on 16/2/23.
 */
public class PicClassActivity extends BaseActivity implements BaseViewInterface<PicClassfyOutput> {

    @Bind(R.id.radomlayout)
    RadomTransLayout radomlayout;
    private BasePresenter basePresenter;

    private PicClassfyOutput picClassfyOutput;

    public static void showActivity(Activity activity) {
        activity.startActivity(new Intent(activity, PicClassActivity.class));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_classfy);
        ButterKnife.bind(this);
        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);
    }

    @Override
    public void updateView(PicClassfyOutput picClassfyOutput) {
        this.picClassfyOutput =picClassfyOutput;
        radomlayout.setData(picClassfyOutput);

    }

    @Override
    protected void onPause() {
        radomlayout.setEnables(false);
        radomlayout.removeAllViews();
        super.onPause();
    }

    @Override
    protected void onResume() {
        radomlayout.setEnables(true);
        super.onResume();
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    protected void requestData() {
        basePresenter.setInput(new PicClassfyInput()).load();
    }


    @Override
    protected String currActivityName() {
        return "美图";
    }


}
