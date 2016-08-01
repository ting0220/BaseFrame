package com.example.zhaoting.baseframe.interfaces;

import com.example.zhaoting.baseframe.bean.LoginBean;
import com.example.zhaoting.baseframe.bean.WordBean;
import com.example.zhaoting.baseframe.httpUtils.HttpResult;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zhaoting on 16/7/29.
 */
public interface ApiInterface {
    @FormUrlEncoded
    @POST("account/login")
    Observable<HttpResult<LoginBean>> getLogin(@FieldMap Map<String, String> options);

    @GET("word/list")
    Observable<HttpResult<List<WordBean>>> getWordList(@QueryMap Map<String, String> options);
}
