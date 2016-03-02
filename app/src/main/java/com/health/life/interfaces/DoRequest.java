package com.health.life.interfaces;

import com.health.life.model.enity.BaseEnity;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/24.
 */
public interface DoRequest<T>{
   public  Observable<T> doRequest(BaseEnity baseEnity);
}