package com.health.life.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionInflater;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.health.life.R;
import com.health.life.adapter.CircularAdapter;
import com.health.life.base.BaseActivity;
import com.health.life.model.bean.input.PicInfoInput;
import com.health.life.model.bean.output.PicInfoOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.view.AppBar;
import com.jpardogo.listbuddies.lib.views.ListBuddiesLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ligang967 on 16/2/23.
 */
public class PicInfoActivity extends BaseActivity implements BaseViewInterface<PicInfoOutput> {

    private final static String ID = "pic_id";
    private final static String TITLE = "pic_title";
    @Bind(R.id.listbuddies)
    ListBuddiesLayout listbuddies;

    private BasePresenter basePresenter;
    private int id;
    private String title;

    public static void showActivity(Activity activity, int id, View shareview, String title) {
        Intent intent = new Intent(activity, PicInfoActivity.class);
        intent.putExtra(ID, id);
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_info);
        ButterKnife.bind(this);
        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);

    }

    @Override
    public void updateView(PicInfoOutput picInfoOutput) {
        final List<PicInfoOutput.TngouEntity> data1 = picInfoOutput.tngou.subList(0 ,picInfoOutput.tngou.size()/2-3);
        final List<PicInfoOutput.TngouEntity> data2 = picInfoOutput.tngou.subList(picInfoOutput.tngou.size()/2-3 ,picInfoOutput.tngou.size());
        CircularAdapter adapter = new CircularAdapter(this, getResources().getDimensionPixelSize(R.dimen.dp_300), data1);
        CircularAdapter adapter2 = new CircularAdapter(this, getResources().getDimensionPixelSize(R.dimen.dp_200), data2);
        listbuddies.setOnItemClickListener(new ListBuddiesLayout.OnBuddyItemClickListener() {
            @Override
            public void onBuddyItemClicked(AdapterView<?> parent, View view, int buddy, int position, long id) {
                switch (buddy){
                    case 0:
                        //left
                        PicNewActivity.showActivity(PicInfoActivity.this ,data1.get(position).id , view.findViewById(R.id.text) ,data1.get(position).title ,data1.get(position).galleryclass);
                        break;
                    case 1:
                        PicNewActivity.showActivity(PicInfoActivity.this ,data2.get(position).id , view.findViewById(R.id.text) ,data2.get(position).title ,data2.get(position).galleryclass);
                        //right
                        break;
                }
            }
        });
        listbuddies.setAdapters(adapter, adapter2);
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    protected void requestData() {
        basePresenter.setInput(new PicInfoInput(id)).load();
    }


    @Override
    public void controlAppBar(AppBar appbar) {
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textView.setTransitionName("shareview3");
        appbar.setMiddleCustom(textView);
        super.controlAppBar(appbar);
    }

    @Override
    protected String currActivityName() {
        return "";
    }


}
