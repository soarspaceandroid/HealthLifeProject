package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.health.life.R;
import com.health.life.base.BaseFragment;
import com.health.life.model.bean.input.CookClassifyInput;
import com.health.life.model.bean.output.CookClassfyOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;

/**
 * Created by ligang967 on 16/2/23.
 */
public class LifeFragment extends BaseFragment implements BaseViewInterface<CookClassfyOutput> {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.life_fragment, null);
        return view;
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
        BasePresenter.getInstance().setBaseViewInterface(this).setRequestListener(this).setInput(new CookClassifyInput(1)).load();
    }

    @Override
    protected String currentTitle() {
        return "生活";
    }
}
