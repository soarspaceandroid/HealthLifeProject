package com.health.life.presenter;

import com.health.life.interfaces.RequestListener;
import com.health.life.model.bean.input.BaseBeanInput;
import com.health.life.model.bean.output.BaseBeanOutput;
import com.health.life.model.enity.BaseEnity;
import com.health.life.model.view.BaseViewInterface;
import com.health.life.utils.RestUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ligang967 on 16/2/23.
 */
public class BasePresenter<T extends BaseBeanOutput> {

    private BaseViewInterface baseViewInterface;


    private static BaseEnity enity = null;

    private BaseBeanInput input;

    private RequestListener requestListener;

    public BasePresenter() {


        if (enity == null) {
            enity = RestUtils.createApi(BaseEnity.class);
        }
    }



    public BasePresenter setBaseViewInterface(BaseViewInterface baseViewInterface) {
        this.baseViewInterface = baseViewInterface;
        return this;
    }


    public BasePresenter setInput(BaseBeanInput input) {
        this.input = input;
        return this;
    }

    public BasePresenter setRequestListener(RequestListener requestListener) {
        this.requestListener = requestListener;
        return this;
    }

    public void load() {
        if (input.isShowDialog()&&this.requestListener!=null) {
            this.requestListener.showProgressDialog();
        }
        input.getData(enity).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        if (input.isShowDialog()&&requestListener!=null) {
                            requestListener.hideProgressDialog();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (input.isShowDialog()&&requestListener!=null) {
                            requestListener.hideProgressDialog();
                        }
                        baseViewInterface.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(T t) {
                        baseViewInterface.updateView(t);

                        if (input.isShowDialog()&&requestListener!=null) {
                            requestListener.hideProgressDialog();
                        }
                    }
                });


    }

}
