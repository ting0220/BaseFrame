package com.example.zhaoting.baseframe.interfaces;

import com.example.zhaoting.baseframe.bean.LoginBean;
import com.example.zhaoting.baseframe.netUtils.HttpResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zhaoting on 16/7/29.
 */
public interface LoginInterface {
    @FormUrlEncoded
    @POST("account/login")
    Observable<HttpResult<LoginBean>> getLogin(@FieldMap Map<String, String> options);
}
