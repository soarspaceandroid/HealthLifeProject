package com.health.life.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.health.life.R;
import com.health.life.base.BaseActivity;
import com.health.life.base.BaseFragment;
import com.health.life.fragment.HealthFragment;
import com.health.life.fragment.LifeFragment;
import com.health.life.fragment.MyFragment;
import com.health.life.view.CustomTabView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{


    private CustomTabView mCustomTabView;
    private List<BaseFragment> listFragment = new ArrayList<>();
    private int mLastselectFragment = 0;

    private final static String[] mFragmentTag = {"healthfragment","lifefragment","myfragment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViews();
        showLeftBack(false);   // 主页不显示返回按钮
        showRightMenu(true);
        setSwipeBackEnable(false); // 主页面tab不滑动返回
        initTabView();

    }

    @Override
    protected void requestData() {

    }

    @Override
    protected String currActivityName() {
        return "新闻";
    }

    private void initViews(){

        mCustomTabView = (CustomTabView)findViewById(R.id.custom_tab_view);
    }



    private void initTabView(){
        listFragment.add(new HealthFragment());
        listFragment.add(new LifeFragment());
        listFragment.add(new MyFragment());

        List<CustomTabView.TabViewData> list = new ArrayList<>();
        list.add(mCustomTabView.new TabViewData(R.mipmap.health_back, R.mipmap.health, R.string.tab_1, 0));
        list.add(mCustomTabView.new TabViewData(R.mipmap.life_back, R.mipmap.life, R.string.tab_2, 0));
        list.add(mCustomTabView.new TabViewData(R.mipmap.my_back, R.mipmap.my, R.string.tab_3, 0));
        mCustomTabView.setData(list);
        mCustomTabView.setOnTabClickListener(new CustomTabView.OnTabClickListener() {
            @Override
            public void onTabClick(View itemView, int position) {
                switchFragment(listFragment.get(position) , mLastselectFragment , position , true);
                mLastselectFragment = position;
            }
        });

        switchFragment(listFragment.get(0) , 0 , 0 ,true);
    }



    protected void switchFragment(Fragment mFragment, int selectedPage, int currPage, boolean isAnim) {
        Fragment orginFragment = getSupportFragmentManager().findFragmentByTag(mFragmentTag[selectedPage]);
        FragmentTransaction ft = getFragmentTransaction(isAnim, selectedPage, currPage);
        if (mFragment != null && !mFragment.isAdded()) {
            if(orginFragment !=null){
                ft.hide(orginFragment).add(R.id.container, mFragment, mFragmentTag[currPage]);
            }else{
                ft.add(R.id.container, mFragment, mFragmentTag[currPage]);
            }

        } else {
            if(orginFragment !=null){
                ft.hide(orginFragment).show(mFragment);
            }else{
                ft.show(mFragment);
            }

        }
        ft.commit();
    }


    private FragmentTransaction getFragmentTransaction(boolean isAnimation , int selectedPage , int currPage){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (isAnimation) {
            if (selectedPage < currPage) {
                ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
            } else {
                ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
            }
        }
        return ft;
    }

}
