package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.health.life.R;
import com.health.life.base.BaseFragment;
import com.health.life.model.bean.output.BookListInfoOutput;
import com.health.life.model.view.BaseViewInterface;

/**
 * Created by ligang967 on 16/2/23.
 */
public class LifeFragment extends BaseFragment implements BaseViewInterface<BookListInfoOutput>{



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.life_fragment, null);

        return view;
    }

    @Override
    public void updateView(BookListInfoOutput bookListInfo) {
    }

    @Override
    public void showError(String msg) {

    }
}
