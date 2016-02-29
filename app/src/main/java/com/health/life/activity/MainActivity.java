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
import com.health.life.model.bean.output.BookKindBeanOutput;
import com.health.life.model.bean.output.BookKindListBeanOutput;
import com.health.life.model.enity.BaseEnity;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;

import java.util.List;

import rx.Observable;

public class MainActivity extends BaseActivity implements BaseViewInterface<BookKindListBeanOutput>{

    private BasePresenter bookListPresenter;

    private EasySlidingTabs easySlidingTabs;
    private ViewPager easyVP;
    private TabsFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViews();
        bookListPresenter = new BasePresenter<BookKindListBeanOutput>(this , this);
    }

    @Override
    protected String currActivityName() {
        return "新闻";
    }

    private void initViews(){
        this.easySlidingTabs = (EasySlidingTabs) this.findViewById(R.id.easy_sliding_tabs);
        this.easySlidingTabs.setDividerColor(Color.BLACK);
        this.easyVP = (ViewPager) this.findViewById(R.id.easy_vp);
    }

    private void initData(List<BookKindBeanOutput> list) {
        this.adapter = new TabsFragmentAdapter(this.getSupportFragmentManager(), list);
        this.easyVP.setAdapter(this.adapter);
        this.easySlidingTabs.setViewPager(this.easyVP);
    }


    @Override
    protected void onResume() {
        super.onResume();
        bookListPresenter.getRequestResult(new DoRequest<BookKindListBeanOutput>() {
            @Override
            public Observable<BookKindListBeanOutput> doRequest(Object t) {
                return ((BaseEnity) t).getClassify();
            }
        } , true);
    }

    @Override
    public void updateView(BookKindListBeanOutput bookList) {

        initData(bookList.getTngou());

    }

    @Override
    public void showError(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

}
