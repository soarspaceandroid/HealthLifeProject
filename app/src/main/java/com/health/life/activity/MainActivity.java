package com.health.life.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.camnter.easyslidingtabs.widget.EasySlidingTabs;
import com.health.life.R;
import com.health.life.adapter.TabsFragmentAdapter;
import com.health.life.base.BaseActivity;
import com.health.life.base.BaseFragment;
import com.health.life.fragment.HealthFragment;
import com.health.life.fragment.LifeFragment;
import com.health.life.interfaces.DoRequest;
import com.health.life.model.bean.output.BookKindBeanOutput;
import com.health.life.model.bean.output.BookKindListBeanOutput;
import com.health.life.model.enity.BaseEnity;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.view.CustomTabView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class MainActivity extends BaseActivity implements BaseViewInterface<BookKindListBeanOutput>{

    private BasePresenter bookListPresenter;

    private EasySlidingTabs easySlidingTabs;
    private ViewPager easyVP;
    private TabsFragmentAdapter adapter;
    private CustomTabView mCustomTabView;
    private List<BaseFragment> listFragment = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViews();
        showLeftBack(false);   // 主页不显示返回按钮
        showRightMenu(true);
        setSwipeBackEnable(false); // 主页面tab不滑动返回
        bookListPresenter = new BasePresenter<BookKindListBeanOutput>(this , this);

        initTabView();

    }

    @Override
    protected String currActivityName() {
        return "新闻";
    }

    private void initViews(){
        this.easySlidingTabs = (EasySlidingTabs) this.findViewById(R.id.easy_sliding_tabs);
        this.easySlidingTabs.setDividerColor(Color.BLACK);
        this.easyVP = (ViewPager) this.findViewById(R.id.easy_vp);
        mCustomTabView = (CustomTabView)findViewById(R.id.custom_tab_view);
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
        }, true);
    }

    @Override
    public void updateView(BookKindListBeanOutput bookList) {

        initData(bookList.getTngou());

    }

    @Override
    public void showError(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    private void initTabView(){
        listFragment.add(new HealthFragment());
        listFragment.add(new LifeFragment());



        List<CustomTabView.TabViewData> list = new ArrayList<>();
        list.add(mCustomTabView.new TabViewData(R.mipmap.health_back, R.mipmap.health, R.string.tab_1, 0));
        list.add(mCustomTabView.new TabViewData(R.mipmap.life_back, R.mipmap.life, R.string.tab_2, 0));
        list.add(mCustomTabView.new TabViewData(R.mipmap.my_back, R.mipmap.my, R.string.tab_3, 0));
        mCustomTabView.setData(list);
        mCustomTabView.setOnTabClickListener(new CustomTabView.OnTabClickListener() {
            @Override
            public void onTabClick(View itemView, int position) {
                Toast.makeText(MainActivity.this, "3个item中的第 " + position + "个", Toast.LENGTH_SHORT).show();
            }
        });
    }



//    protected void switchFragment(Fragment mFragment, int selectedPage, int currPage, boolean isAnim) {
//        Fragment orginFragment = getSupportFragmentManager().findFragmentByTag(mFragmentTag[selectedPage]);
//        FragmentTransaction ft = getFragmentTransaction(isAnim , selectedPage ,currPage);
//        if (mFragment != null && !mFragment.isAdded()) {
//            if(orginFragment !=null){
//                ft.hide(orginFragment).add(R.id.home_contain, mFragment, mFragmentTag[currPage]);
//            }else{
//                ft.add(R.id.home_contain, mFragment, mFragmentTag[currPage]);
//            }
//
//        } else {
//            if(orginFragment !=null){
//                ft.hide(orginFragment).show(mFragment);
//            }else{
//                ft.show(mFragment);
//            }
//
//        }
//        ft.commit();
//    }

}
