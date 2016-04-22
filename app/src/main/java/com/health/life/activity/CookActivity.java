package com.health.life.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.health.life.R;
import com.health.life.adapter.BaseAdapterTools.BaseAdapterHelper;
import com.health.life.adapter.BaseAdapterTools.QuickAdapter;
import com.health.life.adapter.CookClassfyAdapter;
import com.health.life.base.BaseActivity;
import com.health.life.model.bean.input.CookClassifyInput;
import com.health.life.model.bean.output.CookClassfyOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.view.PagerSlidingTabStrip;
import com.health.life.view.pulltorefresh.PullToRefreshLayout;
import com.health.life.view.pulltorefresh.PullableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ligang967 on 16/2/23.
 */
public class CookActivity extends BaseActivity implements BaseViewInterface<CookClassfyOutput> {

    @Bind(R.id.life_tab)
    PagerSlidingTabStrip lifeTab;
    @Bind(R.id.tab_parent)
    LinearLayout tabParent;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.cook_list)
    PullableListView cookList;
    @Bind(R.id.cook_parent)
    PullToRefreshLayout cookParent;
    private BasePresenter basePresenter;
    private CookClassfyAdapter adapter = null;
    private List<CookClassfyOutput.TngouEntity> outPut = new ArrayList<>();

    private CookClassfyOutput cookClassfyOutput; // 目录类

    private boolean isListData = false;

    public static void showActivity(Activity activity) {
        activity.startActivity(new Intent(activity, CookActivity.class));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life_fragment);
        ButterKnife.bind(this);
        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);
        initView();
    }

    private void initView() {
        adapter = new CookClassfyAdapter(getSupportFragmentManager(), outPut);
        pager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        lifeTab.setViewPager(pager);
        lifeTab.setScrollOffset(getResources().getDisplayMetrics().widthPixels / 2 - 80); //  Tab 居中
        cookParent.setPullUpEnable(false);
        cookParent.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                doRequest();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });
        cookParent.autoRefresh();
        cookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                requestDataById(cookClassfyOutput.tngou.get(position).id);
            }
        });
    }
    @Override
    public void updateView(CookClassfyOutput cookClassfyOutput) {
        cookParent.refreshFinish(PullToRefreshLayout.SUCCEED);
        cookParent.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        if(isListData){
            //list shuju 请求带id =0
            this.cookClassfyOutput = cookClassfyOutput;
            cookList.setAdapter(new QuickAdapter<CookClassfyOutput.TngouEntity>(this , R.layout.cook_list_item , cookClassfyOutput.tngou) {
                @Override
                protected void convert(BaseAdapterHelper helper, CookClassfyOutput.TngouEntity item) {
                    helper.setText(R.id.cook_name , item.name);
                }
            });
            requestDataById(1);
        }else{
            outPut.clear();
            outPut.addAll(cookClassfyOutput.tngou);
            adapter.setData(cookClassfyOutput.tngou);
            adapter.notifyDataSetChanged();
            lifeTab.notifyDataSetChanged();
        }


    }

    @Override
    public void showError(String msg) {
        cookParent.refreshFinish(PullToRefreshLayout.FAIL);
        cookParent.loadmoreFinish(PullToRefreshLayout.FAIL);
    }

    @Override
    protected void requestData() {

    }

    public void doRequest(){
        isListData = true;
        basePresenter.setInput(new CookClassifyInput(0)).load();
    }

    private void requestDataById(int id){
        isListData = false;
        basePresenter.setInput(new CookClassifyInput(id)).load();
    }

    @Override
    protected String currActivityName() {
        return "健康菜谱";
    }



}
