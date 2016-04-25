package com.health.life.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.health.life.R;
import com.health.life.adapter.TextTagsAdapter;
import com.health.life.base.BaseActivity;
import com.health.life.interfaces.TagsOnclickListener;
import com.health.life.model.bean.input.PicClassfyInput;
import com.health.life.model.bean.output.PicClassfyOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.moxun.tagcloudlib.view.TagCloudView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ligang967 on 16/2/23.
 */
public class PicClassActivity extends BaseActivity implements BaseViewInterface<PicClassfyOutput> {
    @Bind(R.id.tagcloudview)
    TagCloudView tagcloudview;
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
        controlMenu(false);
        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);
    }

    @Override
    public void updateView(final PicClassfyOutput picClassfyOutput) {
        this.picClassfyOutput = picClassfyOutput;
        tagcloudview.setAdapter(new TextTagsAdapter(picClassfyOutput, new TagsOnclickListener() {
            @Override
            public void onClick(int position, View view) {
                PicInfoActivity.showActivity(PicClassActivity.this, picClassfyOutput.tngou.get(position).id, view, picClassfyOutput.tngou.get(position).name);
            }
        }));
    }


    @Override
    protected void onResume() {
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
