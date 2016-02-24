package com.health.life.presenter;

import com.health.life.model.bean.BookKindListBean;
import com.health.life.model.enity.BookEnity;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.utils.RestUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ligang967 on 16/1/20.
 */

public class BookKindPresenter
{
    private BookEnity apiService;
    private BaseViewInterface bookView;

    public BookKindPresenter(BaseViewInterface bookView)
    {

        apiService = RestUtils.createApi(BookEnity.class);
        this.bookView=bookView;
    }

    public BookEnity getBookService()
    {
        return apiService;
    }

    public void getClassify(){
        this.bookView.showProgressDialog();
        getBookService().getClassify().subscribeOn(Schedulers.io())
                .doOnNext(new Action1<BookKindListBean>() {
                    @Override
                    public void call(BookKindListBean kindsList) {
                        kindsList.setStatus(kindsList.getStatus().toUpperCase());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BookKindListBean>() {
                    @Override
                    public void onCompleted() {
                        bookView.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        bookView.showError(e.getMessage());
                        bookView.hideProgressDialog();
                    }

                    @Override
                    public void onNext(BookKindListBean o) {
                        bookView.updateView(o);
                    }
                });

    }


}
