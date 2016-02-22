package com.health.life.model.view;


import com.health.life.model.bean.BookKindListBean;

/**
 * Created by ligang967 on 16/1/20.
 */
public interface BookKindViewInterface {
    void updateView(BookKindListBean bookList);

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String msg);
}
