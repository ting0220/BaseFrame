package com.example.zhaoting.baseframe.views;

/**
 * Created by Z_TING on 2016/8/3.
 */
public interface BaseView<T> {

    void onSuccess(T t);

    void onError();
}
