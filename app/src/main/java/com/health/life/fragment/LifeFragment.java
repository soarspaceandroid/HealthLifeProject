package com.health.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.health.life.R;
import com.health.life.base.BaseFragment;
import com.health.life.interfaces.DoRequest;
import com.health.life.model.bean.input.CookClassifyInput;
import com.health.life.model.bean.output.CookClassfyOutput;
import com.health.life.model.enity.BaseEnity;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.presenter.BasePresenter;

import java.util.List;

import rx.Observable;

/**
 * Created by ligang967 on 16/2/23.
 */
public class LifeFragment extends BaseFragment implements BaseViewInterface<List<CookClassfyOutput>> {

    private BasePresenter<CookClassfyOutput> presenter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.life_fragment, null);
        presenter = new BasePresenter<>(this , this );
        requestData();
        return view;
    }

    @Override
    public void updateView(List<CookClassfyOutput> cookData) {
        for (CookClassfyOutput item :cookData ) {
            Log.e("soar", "test --- " + item.cookclass + "   " + item.title + "   " + item.name);
        }
    }

    @Override
    public void showError(String msg) {

    }

    protected void requestData() {
        presenter.getRequestResult(new DoRequest<CookClassfyOutput>() {
            @Override
            public Observable<CookClassfyOutput> doRequest(BaseEnity baseEnity) {
                return baseEnity.getCookClassfy(new CookClassifyInput(0));
            }
        }  , true);
    }
}
