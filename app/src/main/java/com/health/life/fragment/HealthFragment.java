package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.health.life.R;
import com.health.life.adapter.TabsFragmentAdapter;
import com.health.life.base.BaseFragment;
import com.health.life.model.bean.input.BookKindInput;
import com.health.life.model.bean.output.BookKindBeanOutput;
import com.health.life.model.bean.output.BookKindListBeanOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaofei on 16/2/23.
 */
public class HealthFragment extends BaseFragment implements BaseViewInterface<BookKindListBeanOutput> {

    private TabPageIndicator indicator;

    private ViewPager pager;

    private List<BookKindBeanOutput> titles = new ArrayList<BookKindBeanOutput>();

    private TabsFragmentAdapter adapter = null;

    private BasePresenter basePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter=new BasePresenter().setBaseViewInterface(this).setRequestListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_fragment, null);

        indicator = (TabPageIndicator) view.findViewById(R.id.indicator);

        pager = (ViewPager) view.findViewById(R.id.view_parent);

        adapter = new TabsFragmentAdapter(getFragmentManager(), titles);

        pager.setAdapter(adapter);

        indicator.setViewPager(pager);

        return view;
    }

    @Override
    public void updateView(BookKindListBeanOutput bookListInfo) {

        titles.clear();

        titles.addAll(bookListInfo.getTngou());

        adapter.notifyDataSetChanged();

        indicator.notifyDataSetChanged();


    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void requestData() {

        basePresenter.setInput(new BookKindInput()).load();

    }

    @Override
    protected String currentTitle() {
        return "健康";
    }
}
