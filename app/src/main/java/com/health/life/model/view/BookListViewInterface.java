package com.health.life.model.view;

import com.health.life.model.bean.BookListInfo;

/**
 * Created by ligang967 on 16/2/23.
 */
public interface BookListViewInterface {

    void updateView(BookListInfo bookListInfo);

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String msg);

}
