package com.health.life.presenter;

import com.health.life.interfaces.DoRequest;
import com.health.life.interfaces.RequestListener;
import com.health.life.model.bean.BaseBean;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.utils.RestUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ligang967 on 16/2/23.
 */
public class BasePresenter {

    private BaseViewInterface bookListViewInterface;
    private RequestListener listener;

    public BasePresenter(BaseViewInterface baseViewInterface , RequestListener ls) {
        this.bookListViewInterface = baseViewInterface;
        this.listener = ls;
    }


    public <T extends BaseBean, E> void getRequestResult(Class<E> clz , DoRequest doRequest){

        listener.showProgressDialog();
        E eEnity = RestUtils.createApi(clz);
        doRequest.doRequest(eEnity)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        listener.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.hideProgressDialog();
                        bookListViewInterface.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(T t) {
                        bookListViewInterface.updateView(t);
                    }
                });
    }

}
