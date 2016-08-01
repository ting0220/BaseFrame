package com.example.zhaoting.baseframe.interfaces;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zhaoting on 16/7/29.
 */
public interface LoginInterface {
    @POST("account/login")
    Observable<String> getLogin(@QueryMap Map<String, String> options);
}
