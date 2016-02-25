package com.health.life.presenter;

import com.health.life.interfaces.DoRequest;
import com.health.life.interfaces.RequestListener;
import com.health.life.model.bean.BaseBean;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.utils.RestUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ligang967 on 16/2/23.
 */
public class BasePresenter<T extends  BaseBean>{

    private BaseViewInterface bookListViewInterface;
    private RequestListener listener;
    private Observable observable;

    public BasePresenter(BaseViewInterface baseViewInterface , RequestListener ls) {
        this.bookListViewInterface = baseViewInterface;
        this.listener = ls;
    }


    public <F> Observable<T> getObservable(Class<F> clz , DoRequest doRequest){
        if(observable == null) {
            F eEnity = RestUtils.createApi(clz);
            observable = doRequest.doRequest(eEnity);
        }
        return observable;
    }

    public <F> void  getRequestResult(Class<F> clz , DoRequest doRequest){
        listener.showProgressDialog();
        getObservable(clz , doRequest).observeOn(AndroidSchedulers.mainThread())
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
