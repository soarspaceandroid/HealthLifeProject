package com.health.life.presenter;

import com.health.life.model.bean.BookListInfo;
import com.health.life.model.enity.BookListEnity;
import com.health.life.model.view.BookListViewInterface;
import com.health.life.utils.RestUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ligang967 on 16/2/23.
 */
public class BookListPresenter {

    private BookListEnity bookListEnity;

    private BookListViewInterface bookListViewInterface;

    public BookListPresenter(BookListViewInterface bookListViewInterface) {
        this.bookListEnity = RestUtils.createApi(BookListEnity.class);
        this.bookListViewInterface = bookListViewInterface;
    }

    public void getListById(int id,int page){

        this.bookListViewInterface.showProgressDialog();

        this.bookListEnity.getListById(page,10,id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<BookListInfo>() {
                    @Override
                    public void onCompleted() {

                        bookListViewInterface.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {

                        bookListViewInterface.hideProgressDialog();
                        bookListViewInterface.showError(e.getMessage());

                    }

                    @Override
                    public void onNext(BookListInfo bookListInfo) {

                        bookListViewInterface.updateView(bookListInfo);

                    }
                });
    }
}
