package com.example.zhaoting.baseframe.netUtils;

import com.example.zhaoting.baseframe.bean.ErrorBean;

import org.json.JSONArray;
import org.json.JSONException;

import rx.functions.Func1;

/**
 * Created by zhaoting on 16/7/28.
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> tHttpResult) {
        if (!tHttpResult.getResult().equals("ok")) {
            try {
                JSONArray array = new JSONArray(tHttpResult.getErrors());
                ErrorBean bean = (ErrorBean) array.get(0);
                throw new ApiException(bean);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return tHttpResult.getData();
    }
}
