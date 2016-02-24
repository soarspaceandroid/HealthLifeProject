package com.health.life.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.camnter.easyslidingtabs.widget.EasySlidingTabs;
import com.health.life.R;
import com.health.life.adapter.TabsFragmentAdapter;
import com.health.life.base.BaseActivity;
import com.health.life.interfaces.DoRequest;
import com.health.life.model.bean.BookKindBean;
import com.health.life.model.bean.BookKindListBean;
import com.health.life.model.enity.BookEnity;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;

import java.util.List;

import rx.Observable;

public class MainActivity extends BaseActivity implements BaseViewInterface<BookKindListBean>{

    private BasePresenter basePresenter;

    private EasySlidingTabs easySlidingTabs;
    private ViewPager easyVP;
    private TabsFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViews();
        basePresenter = new BasePresenter(this , this);
    }



    private void initViews(){
        this.easySlidingTabs = (EasySlidingTabs) this.findViewById(R.id.easy_sliding_tabs);
        this.easySlidingTabs.setDividerColor(Color.BLACK);
        this.easyVP = (ViewPager) this.findViewById(R.id.easy_vp);
    }

    private void initData(List<BookKindBean> list) {
        this.adapter = new TabsFragmentAdapter(this.getSupportFragmentManager(), list);
        this.easyVP.setAdapter(this.adapter);
        this.easySlidingTabs.setViewPager(this.easyVP);
    }


    @Override
    protected void onResume() {
        super.onResume();
        basePresenter.getRequestResult(BookEnity.class, new DoRequest<BookKindListBean>() {
            @Override
            public Observable<BookKindListBean> doRequest(Object t) {
                return ((BookEnity) t).getClassify();
            }
        });
    }

    @Override
    public void updateView(BookKindListBean bookList) {


        initData(bookList.getTngou());

    }

    @Override
    public void showError(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
