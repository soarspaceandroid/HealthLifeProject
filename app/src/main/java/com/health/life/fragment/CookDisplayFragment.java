package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;

import com.health.life.R;
import com.health.life.activity.CookInfoDetailActivity;
import com.health.life.adapter.CookListAdapter;
import com.health.life.base.BaseFragment;
import com.health.life.model.bean.input.CookClassifyListInfoInput;
import com.health.life.model.bean.output.CookClassifyListInfoOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;
import com.health.life.view.pulltorefresh.PullToRefreshLayout;
import com.health.life.view.pulltorefresh.PullableListView;

/**
 * Created by ligang967 on 16/2/23.
 */
public class CookDisplayFragment extends BaseFragment implements BaseViewInterface<CookClassifyListInfoOutput> {

    PullableListView listView;
    PullToRefreshLayout pulllayout;


    private View self;
    private int id;
    private String title;
    private boolean isFirstIn = true;
    private CookClassifyListInfoInput infoInput;
    private CookClassifyListInfoOutput infoOutput;
    private BasePresenter basePresenter;
    private CookListAdapter mAdapter = null;
    private int page = 0;
    public static CookDisplayFragment getInstance(String title, int id) {
        CookDisplayFragment firstFragment = new CookDisplayFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("id", id);
        firstFragment.setArguments(bundle);
        return firstFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
        infoInput = new CookClassifyListInfoInput(1, 20, id);
        infoInput.setShowDialog(false);
        basePresenter = new BasePresenter().setBaseViewInterface(this).setRequestListener(this);

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if(!enter){
            finishRequestUI();
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    protected String currentTitle() {
        return "";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    private void finishRequestUI(){
        if(pulllayout != null) {
            pulllayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            pulllayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.self == null) {
            this.self = inflater.inflate(R.layout.dis_custom_fragment, null);
        }
        listView = (PullableListView)self.findViewById(R.id.list_view);
        pulllayout = (PullToRefreshLayout)self.findViewById(R.id.pulllayout);

        pulllayout.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                page = 1;
                doRequest(page);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                page++;
                doRequest(page);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CookInfoDetailActivity.showActivity(getActivity(), infoOutput.tngou.get(position).name , view.findViewById(R.id.image) , infoOutput.tngou.get(position).img);
            }
        });
        firstRequest();
        return this.self;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void updateView(CookClassifyListInfoOutput cookClassifyListInfoOutput) {

        if(infoOutput == null){
            infoOutput = cookClassifyListInfoOutput ;
        }else {
            infoOutput.tngou.addAll(cookClassifyListInfoOutput.tngou);
        }
        if(mAdapter == null){
            mAdapter = new CookListAdapter(infoOutput.tngou , getActivity());
            listView.setAdapter(mAdapter);

        }else{
            mAdapter.notifyDataSetChanged();
        }
        finishRequestUI();

    }

    @Override
    public void showError(String msg) {
        finishRequestUI();
    }

    @Override
    protected void requestData() {

        // 使用下拉刷新自带 , 不在此处写,重新写一个方法
    }


    public void doRequest(int page){
        infoInput.page = page;
        basePresenter.setInput(infoInput).load();
    }


    public void firstRequest(){
        if (isFirstIn)
        {
            pulllayout.autoRefresh();
            isFirstIn = false;
        }
    }

}
