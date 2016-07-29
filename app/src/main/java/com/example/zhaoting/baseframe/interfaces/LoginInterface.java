package com.example.zhaoting.baseframe.interfaces;

import com.example.zhaoting.baseframe.bean.LoginBean;
import com.example.zhaoting.baseframe.netUtils.HttpResult;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zhaoting on 16/7/29.
 */
public interface LoginInterface {
    @GET("word/list")
    Observable<HttpResult<LoginBean>> getLogin(@QueryMap Map<String, String> options);
}
