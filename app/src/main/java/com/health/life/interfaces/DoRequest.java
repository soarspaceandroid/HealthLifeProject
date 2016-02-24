package com.health.life.interfaces;

import rx.Observable;

/**
 * Created by gaofei on 2016/2/24.
 */
public interface DoRequest<T>{
   public  <E> Observable<T> doRequest(E t);
}