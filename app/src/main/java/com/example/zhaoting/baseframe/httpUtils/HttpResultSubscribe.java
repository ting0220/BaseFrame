package com.example.zhaoting.baseframe.httpUtils;

import com.example.zhaoting.baseframe.bean.ErrorBean;
import com.example.zhaoting.baseframe.utils.Utils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by zhaoting on 16/8/1.
 */
public abstract class HttpResultSubscribe<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        try {
            String s = ((HttpException) e).response().errorBody().string();
            JSONObject object = new JSONObject(s);
            String s1 = object.getString("errors");
            String message = stringToArray(s1, ErrorBean[].class).get(0).getMessage();
            Utils.getInstance().ToastShort(message);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public abstract void onNext(T t);

    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }
}
