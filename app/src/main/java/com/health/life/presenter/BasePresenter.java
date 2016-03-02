package com.health.life.presenter;

import com.health.life.interfaces.DoRequest;
import com.health.life.interfaces.RequestListener;
import com.health.life.model.bean.output.BaseBeanOutput;
import com.health.life.model.enity.BaseEnity;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.utils.RestUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ligang967 on 16/2/23.
 */
public class BasePresenter<T extends BaseBeanOutput>{

    private BaseViewInterface bookListViewInterface;
    private RequestListener listener;
    private Observable observable;

    public BasePresenter(BaseViewInterface baseViewInterface , RequestListener ls) {
        this.bookListViewInterface = baseViewInterface;
        this.listener = ls;
    }

    /**
     * 有数据处理新建 presenter时候使用
     * @param doRequest
     * @return
     */
    public Observable<T> getObservable(DoRequest doRequest){
        if(observable == null) {
            BaseEnity eEnity = RestUtils.createApi(BaseEnity.class);
            observable = doRequest.doRequest(eEnity);
        }
        return observable;
    }

    /**
     * 默认使用这个方法获取data
     * @param doRequest
     * @param showDialog
     */
    public void  getRequestResult(DoRequest doRequest , boolean showDialog){
        if(showDialog) {
            listener.showProgressDialog();
        }
        getObservable(doRequest).observeOn(AndroidSchedulers.mainThread())
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
