package com.health.life.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.health.life.R;
import com.health.life.base.BaseActivity;
import com.health.life.base.BaseFragment;
import com.health.life.fragment.HealthFragment;
import com.health.life.fragment.LifeFragment;
import com.health.life.fragment.MyFragment;
import com.health.life.view.CustomTabView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.custom_tab_view)
    CustomTabView customTabView;
    private List<BaseFragment> listFragment = new ArrayList<>();
    private int mLastselectFragment = 0;

    private final static String[] mFragmentTag = {"healthfragment", "lifefragment", "myfragment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        setSwipeBackEnable(false); // 主页面tab不滑动返回
        getAppBar().getLeftRoot().setVisibility(View.INVISIBLE);
        initTabView();

    }

    @Override
    protected void requestData() {

    }

    @Override
    protected String currActivityName() {
        return "新闻";
    }

    private void initViews() {
    }


    /**
     * init tab view
     */
    private void initTabView() {
        listFragment.add(new HealthFragment());
        listFragment.add(new LifeFragment());
        listFragment.add(new MyFragment());

        List<CustomTabView.TabViewData> list = new ArrayList<>();
        list.add(customTabView.new TabViewData(R.mipmap.health_back, R.mipmap.health, R.string.tab_1, 0));
        list.add(customTabView.new TabViewData(R.mipmap.life_back, R.mipmap.life, R.string.tab_2, 0));
        list.add(customTabView.new TabViewData(R.mipmap.my_back, R.mipmap.my, R.string.tab_3, 0));
        customTabView.setData(list);
        customTabView.setOnTabClickListener(new CustomTabView.OnTabClickListener() {
            @Override
            public void onTabClick(View itemView, int position) {
                switchFragment(listFragment.get(position), mLastselectFragment, position, true);
                mLastselectFragment = position;
            }
        });

        switchFragment(listFragment.get(0), 0, 0, true);
    }


    /**
     * change fragment
     *
     * @param mFragment
     * @param selectedPage
     * @param currPage
     * @param isAnim
     */
    protected void switchFragment(Fragment mFragment, int selectedPage, int currPage, boolean isAnim) {
        Fragment orginFragment = getSupportFragmentManager().findFragmentByTag(mFragmentTag[selectedPage]);
        FragmentTransaction ft = getFragmentTransaction(isAnim, selectedPage, currPage);
        if (mFragment != null && !mFragment.isAdded()) {
            if (orginFragment != null) {
                ft.hide(orginFragment).add(R.id.container, mFragment, mFragmentTag[currPage]);
            } else {
                ft.add(R.id.container, mFragment, mFragmentTag[currPage]);
            }

        } else {
            if (orginFragment != null) {
                ft.hide(orginFragment).show(mFragment);
            } else {
                ft.show(mFragment);
            }

        }
        ft.commit();
    }


    /**
     * get transaction
     *
     * @param isAnimation
     * @param selectedPage
     * @param currPage
     * @return
     */
    private FragmentTransaction getFragmentTransaction(boolean isAnimation, int selectedPage, int currPage) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (isAnimation) {
            if (selectedPage < currPage) {
                ft.setCustomAnimations(R.anim.fragment_slide_left_in, R.anim.fragment_slide_left_out);
            } else {
                ft.setCustomAnimations(R.anim.fragment_slide_right_in, R.anim.fragment_slide_right_out);
            }
        }
        return ft;
    }

}
