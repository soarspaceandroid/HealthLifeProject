package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.health.life.R;
import com.health.life.base.BaseFragment;
import com.health.life.model.bean.output.BookKindBeanOutput;
import com.health.life.model.view.BaseViewInterface;

/**
 * Created by gaofei on 16/2/23.
 */
public class HealthFragment extends BaseFragment implements BaseViewInterface<BookKindBeanOutput>{




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_fragment, null);

        return view;
    }

    @Override
    public void updateView(BookKindBeanOutput bookListInfo) {
    }

    @Override
    public void showError(String msg) {

    }
}