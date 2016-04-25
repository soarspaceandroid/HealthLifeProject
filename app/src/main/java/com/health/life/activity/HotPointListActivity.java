package com.health.life.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.health.life.R;
import com.health.life.adapter.HotPointListAdapter;
import com.health.life.base.BaseActivity;
import com.health.life.model.bean.input.HotPointClassfyInput;
import com.health.life.model.bean.output.HotPointClassfyOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.view.PagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaofei on 16/2/23.
 */
public class HotPointListActivity extends BaseActivity implements BaseViewInterface<HotPointClassfyOutput> {

    public final static String ID = "list_id";
    public final static String TITLE = "list_title";
    @Bind(R.id.hot_tab)
    PagerSlidingTabStrip hotTab;
    @Bind(R.id.pager_hot)
    ViewPager pagerHot;
    @Bind(R.id.tab_parent)
    RelativeLayout tabParent;

    private HotPointListAdapter adapter;

    private int page = 1;

    private BasePresenter basePresenter;

    public static void showActivity(Activity activity) {
        Intent intent = new Intent(activity, HotPointListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_point_list);
        ButterKnife.bind(this);
        controlMenu(false);
        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);


    }



    @Override
    public void updateView(final HotPointClassfyOutput hotPointClassfyOutput) {
        adapter = new HotPointListAdapter(getSupportFragmentManager(), hotPointClassfyOutput.tngou);
        pagerHot.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources()
                .getDisplayMetrics());
        pagerHot.setPageMargin(pageMargin);
        hotTab.setViewPager(pagerHot);
        hotTab.setScrollOffset(getResources().getDisplayMetrics().widthPixels / 2 - 80); //  Tab 居中

    }

    @Override
    protected void onPause() {
        super.onPause();
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
        basePresenter.setInput(new HotPointClassfyInput()).load();
    }

    @Override
    protected String currActivityName() {
        return "社会热点";
    }


}
