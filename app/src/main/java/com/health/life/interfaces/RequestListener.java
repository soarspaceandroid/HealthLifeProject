package com.health.life.interfaces;

/**
 * Created by gaofei on 2016/2/23.
 */
public interface RequestListener {
    /**
     * action call
     * @param t
     * @param <T>
     */
     <T> void actionCall(T t);

    /**
     * request conplete
     */
    void requestOnCompleted();

    /**
     * request error
     * @param e
     */
    void requestOnError(Throwable e);


    /**
     * request on next
     * @param t
     * @param <T>
     */
    <T> void requestOnNext(T t);

}
