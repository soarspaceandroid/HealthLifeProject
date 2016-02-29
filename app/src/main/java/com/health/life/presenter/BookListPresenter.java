package com.health.life.presenter;

import com.health.life.interfaces.DoRequest;
import com.health.life.interfaces.RequestListener;
import com.health.life.model.bean.output.BaseBeanOutput;
import com.health.life.model.view.BaseViewInterface;

import rx.functions.Func1;

/**
 * Created by ligang967 on 16/2/23.
 */
public class BookListPresenter<T extends BaseBeanOutput> extends BasePresenter{


    public BookListPresenter(BaseViewInterface baseViewInterface, RequestListener ls) {
        super(baseViewInterface, ls);
    }

    @Override
    public void getRequestResult(DoRequest doRequest , boolean showDialog) {
        getObservable(doRequest ).filter(new Func1() {
            @Override
            public Object call(Object o) {
                return null;
            }
        });
        super.getRequestResult(doRequest,showDialog);
    }
}
