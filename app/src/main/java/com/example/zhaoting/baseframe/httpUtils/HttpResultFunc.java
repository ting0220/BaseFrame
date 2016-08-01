package com.example.zhaoting.baseframe.httpUtils;

import rx.functions.Func1;

/**
 * Created by zhaoting on 16/7/28.
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> tHttpResult) {
        return tHttpResult.getData();
    }
}
