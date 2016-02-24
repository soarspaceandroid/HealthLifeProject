package com.health.life.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.camnter.easyslidingtabs.widget.EasySlidingTabs;
import com.health.life.R;
import com.health.life.adapter.TabsFragmentAdapter;
import com.health.life.base.BaseActivity;
import com.health.life.model.bean.BookKindBean;
import com.health.life.model.bean.BookKindListBean;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BookKindPresenter;

import java.util.List;

public class MainActivity extends BaseActivity implements BaseViewInterface<BookKindListBean> {

    private BookKindPresenter bookKindPresenter;

    private EasySlidingTabs easySlidingTabs;
    private ViewPager easyVP;
    private TabsFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViews();
        bookKindPresenter = new BookKindPresenter(this);
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

        bookKindPresenter.getClassify();
    }

    @Override
    public void updateView(BookKindListBean bookList) {


        initData(bookList.getTngou());

    }

    @Override
    public void showProgressDialog() {

        showLoadDialog(this);

    }

    @Override
    public void hideProgressDialog() {

        removeDialog(this);

    }

    @Override
    public void showError(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
