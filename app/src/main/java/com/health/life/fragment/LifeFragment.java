package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.health.life.R;
import com.health.life.adapter.CookClassfyAdapter;
import com.health.life.base.BaseFragment;
import com.health.life.model.bean.input.CookClassifyInput;
import com.health.life.model.bean.output.CookClassfyOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligang967 on 16/2/23.
 */
public class LifeFragment extends BaseFragment implements BaseViewInterface<CookClassfyOutput> {

    private  BasePresenter basePresenter;
    private PagerSlidingTabStrip mTabView ;
    private ViewPager mViewPager;
    private CookClassfyAdapter adapter = null;
    private List<CookClassfyOutput.TngouEntity> outPut = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter=new BasePresenter().setBaseViewInterface(this).setRequestListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.life_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view){
        mTabView = (PagerSlidingTabStrip)view.findViewById(R.id.life_tab);
        mViewPager = (ViewPager)view.findViewById(R.id.pager);
        adapter = new CookClassfyAdapter(getFragmentManager(), outPut);
        mViewPager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources()
                .getDisplayMetrics());
        mViewPager.setPageMargin(pageMargin);
        mTabView.setViewPager(mViewPager);
        mTabView.setScrollOffset(getResources().getDisplayMetrics().widthPixels/2 - 80); //  Tab 居中

    }

    @Override
    public void updateView(CookClassfyOutput cookClassfyOutput) {
        outPut.clear();
        outPut.addAll(cookClassfyOutput.tngou);
        adapter.notifyDataSetChanged();
        mTabView.notifyDataSetChanged();

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void requestData() {
        basePresenter.setInput(new CookClassifyInput(1)).load();
    }

    @Override
    protected String currentTitle() {
        return "生活";
    }
}
