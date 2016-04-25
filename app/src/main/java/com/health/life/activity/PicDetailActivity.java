package com.health.life.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.transition.TransitionInflater;
import android.view.View;

import com.health.life.R;
import com.health.life.adapter.PicDetailAdapter;
import com.health.life.base.BaseActivity;
import com.health.life.model.bean.input.PicDetailInput;
import com.health.life.model.bean.output.PicDetailOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ligang967 on 16/2/23.
 */
public class PicDetailActivity extends BaseActivity implements BaseViewInterface<PicDetailOutput> {

    private final static String ID = "pic_id";
    private final static String TITLE = "pic_title";
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private BasePresenter basePresenter;
    private int id;
    private String title;

    public static void showActivity(Activity activity, int id, View shareview, String title) {
        Intent intent = new Intent(activity, PicDetailActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(TITLE, title);
        if (Build.VERSION.SDK_INT >= 21) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(activity, shareview, "shareview4");
            activity.startActivity(intent, options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.activity_transition));
        }
        id = getIntent().getIntExtra(ID, 0);
        title = getIntent().getStringExtra(TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_detail_parent);
        ButterKnife.bind(this);
        controlMenu(true);
        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);


    }

    @Override
    public void updateView(final PicDetailOutput picDetailOutput) {
        viewpager.setAdapter(new PicDetailAdapter(this , picDetailOutput));
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    protected void requestData() {
        basePresenter.setInput(new PicDetailInput(id)).load();
    }


    @Override
    protected String currActivityName() {
        return title;
    }


}
