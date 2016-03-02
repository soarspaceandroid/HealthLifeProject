package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.health.life.R;
import com.health.life.base.BaseFragment;
import com.health.life.model.bean.input.BookKindInput;
import com.health.life.model.bean.output.BookKindListBeanOutput;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;

/**
 * Created by ligang967 on 16/2/23.
 */
public class LifeFragment extends BaseFragment implements BaseViewInterface<BookKindListBeanOutput> {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.life_fragment, null);
        requestData();
        return view;
    }

    @Override
    public void updateView(BookKindListBeanOutput bookKindListBeanOutput) {
            Log.e("soar", "test --- " + bookKindListBeanOutput.getStatus());
    }

    @Override
    public void showError(String msg) {

    }

    protected void requestData() {

        BasePresenter.getInstance().setBookListViewInterface(this).setRequestListener(this).setInput(new BookKindInput()).load();

    }
}
