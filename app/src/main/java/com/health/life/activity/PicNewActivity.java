package com.health.life.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.View;

import com.health.life.R;
import com.health.life.adapter.PhotosAdapter;
import com.health.life.base.BaseActivity;
import com.health.life.model.bean.input.PicNewInput;
import com.health.life.model.bean.output.PicNewOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.utils.DensityUtil;
import com.health.life.view.greedolayout.GreedoLayoutManager;
import com.health.life.view.greedolayout.GreedoSpacingItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ligang967 on 16/2/23.
 */
public class PicNewActivity extends BaseActivity implements BaseViewInterface<PicNewOutput> {

    private final static String ID = "pic_id";
    private final static String TITLE = "pic_title";
    private final static String CLASSFY = "classfy";
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private BasePresenter basePresenter;
    private int id;
    private String title;
    private int classFy;

    public static void showActivity(Activity activity, int id, View shareview, String title, int classFy) {
        Intent intent = new Intent(activity, PicNewActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(CLASSFY, classFy);
        intent.putExtra(TITLE, title);
        if (Build.VERSION.SDK_INT >= 21) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(activity, shareview, "shareview3");
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
        classFy = getIntent().getIntExtra(CLASSFY, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_new);
        ButterKnife.bind(this);
        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);


    }

    @Override
    public void updateView(PicNewOutput picNewOutput) {
        PhotosAdapter photosAdapter = new PhotosAdapter(this , picNewOutput);
        GreedoLayoutManager layoutManager = new GreedoLayoutManager(photosAdapter);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(photosAdapter);

        int spacing = DensityUtil.dp2px(this ,4);
        recyclerView.addItemDecoration(new GreedoSpacingItemDecoration(spacing));
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    protected void requestData() {
        basePresenter.setInput(new PicNewInput(classFy, 20, id)).load();
    }


    @Override
    protected String currActivityName() {
        return title;
    }


}
