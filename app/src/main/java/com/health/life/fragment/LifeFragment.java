package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ligang967 on 16/2/23.
 */
public class LifeFragment extends BaseFragment implements BaseViewInterface<CookClassfyOutput> {

    @Bind(R.id.life_tab)
    PagerSlidingTabStrip lifeTab;
    @Bind(R.id.tab_parent)
    LinearLayout tabParent;
    @Bind(R.id.pager)
    ViewPager pager;
    private BasePresenter basePresenter;
    private CookClassfyAdapter adapter = null;
    private List<CookClassfyOutput.TngouEntity> outPut = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.life_fragment, null);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        adapter = new CookClassfyAdapter(getFragmentManager(), outPut);
        pager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        lifeTab.setViewPager(pager);
        lifeTab.setScrollOffset(getResources().getDisplayMetrics().widthPixels / 2 - 80); //  Tab 居中

    }

    @Override
    public void updateView(CookClassfyOutput cookClassfyOutput) {
        outPut.clear();
        outPut.addAll(cookClassfyOutput.tngou);
        adapter.notifyDataSetChanged();
        lifeTab.notifyDataSetChanged();

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
