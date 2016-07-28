package com.example.zhaoting.baseframe.interfaces;


import com.example.zhaoting.baseframe.bean.WordBean;
import com.example.zhaoting.baseframe.netUtils.HttpResult;

import java.util.List;
import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by zhaoting on 16/7/28.
 */
public interface WordInterface {
    @GET("word/list")
    Observable<HttpResult<List<WordBean>>> getWordList(@QueryMap Map<String, String> options);

}
