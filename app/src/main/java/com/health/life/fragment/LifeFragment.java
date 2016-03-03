package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.health.life.R;
import com.health.life.adapter.BaseAdapterTools.BaseAdapterHelper;
import com.health.life.adapter.BaseAdapterTools.QuickAdapter;
import com.health.life.base.BaseFragment;
import com.health.life.model.bean.input.CookClassifyInput;
import com.health.life.model.bean.output.CookClassfyOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.view.pulltorefresh.ILoadingLayout;
import com.health.life.view.pulltorefresh.PullToRefreshBase;
import com.health.life.view.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligang967 on 16/2/23.
 */
public class LifeFragment extends BaseFragment implements BaseViewInterface<CookClassfyOutput> {

    private  BasePresenter basePresenter;
    private PullToRefreshListView mListView ;
    private List<String> data = new ArrayList<>();
    private QuickAdapter<String> adapter;

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
        mListView =(PullToRefreshListView)view.findViewById(R.id.pullrefreshlistview);
        mListView.setMode(PullToRefreshBase.Mode.BOTH); //  上下都可以刷
        ILoadingLayout footLabels = mListView.getLoadingLayoutProxy(
                false, true);
        footLabels.setPullLabel("上拉加载更多");
        footLabels.setRefreshingLabel("加载中...");
        footLabels.setReleaseLabel("释放加载更多");


        data.add("test1");
        data.add("test2");
        data.add("test3");
        data.add("test4");
        data.add("test5");
        data.add("test6");
        data.add("test7");
        data.add("test8");
        data.add("test9");
        data.add("test1");
        data.add("test2");
        data.add("test3");
        data.add("test4");
        data.add("test5");
        data.add("test6");
        data.add("test7");
        data.add("test8");
        data.add("test9");

        adapter = new QuickAdapter<String>(getActivity(), R.layout.item_life_classfy, data) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                helper.setText(R.id.life_tv, item);
            }
        };
        mListView.setAdapter(adapter);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {

                mListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListView.setRefreshing();
                    }
                }, 2000);


                mListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshView.onRefreshComplete();
                    }
                }, 2000);

                mListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.add("test4");
                        data.add("test5");
                        data.add("test6");
                        data.add("test7");
                        data.add("test8");
                        data.add("test9");
                        Log.e("soar", "list.sze ==- " + data.size());
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);


            }
        });


    }
    @Override
    public void updateView(CookClassfyOutput bookKindListBeanOutput) {
            Log.e("soar", "test --- " + bookKindListBeanOutput.tngou.get(0).name);
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
