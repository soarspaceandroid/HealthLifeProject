package com.health.life.model.view;

/**
 * Created by gaofei on 2016/2/24.
 */
public interface BaseViewInterface<T>{

    void updateView(T t);

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String msg);
}
