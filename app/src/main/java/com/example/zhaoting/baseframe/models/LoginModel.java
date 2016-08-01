package com.example.zhaoting.baseframe.models;

import android.util.Log;

import com.example.zhaoting.baseframe.bean.LoginBean;
import com.example.zhaoting.baseframe.httpUtils.Constans;
import com.example.zhaoting.baseframe.httpUtils.HttpResultFunc;
import com.example.zhaoting.baseframe.httpUtils.HttpResultSubscribe;
import com.example.zhaoting.baseframe.httpUtils.RetrofitUtil;
import com.example.zhaoting.baseframe.interfaces.ApiInterface;
import com.example.zhaoting.baseframe.utils.NetUtils;
import com.example.zhaoting.baseframe.utils.SharedPManager;
import com.example.zhaoting.baseframe.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Z_TING on 2016/8/1.
 */
public class LoginModel {
    private static class SingletonHolder {
        private static LoginModel instance = new LoginModel();
    }

    private LoginModel() {
    }

    public static LoginModel getInstance() {
        return SingletonHolder.instance;
    }

    public void getLogin(String username,String password){
        Map<String, String> map = new HashMap<>();
        map.put("client_id", Constans.APP_KEY);
        map.put("client_secret", Constans.APP_SECRET);
        map.put("username", username);
        map.put("password", password);
        map.put("grant_type", "password");

        if (NetUtils.getInstance().isNetConnected()) {
            RetrofitUtil.getInstance().create(ApiInterface.class).getLogin(map)
                    .subscribeOn(Schedulers.io())
                    .map(new HttpResultFunc<LoginBean>())
                    .map(new Func1<LoginBean, LoginBean>() {
                        @Override
                        public LoginBean call(LoginBean loginBean) {
                            SharedPManager.getInstance().setLoginMessage(loginBean);
                            return loginBean;
                        }
                    })
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new HttpResultSubscribe<LoginBean>() {
                        @Override
                        public void onNext(LoginBean loginBean) {
                            Log.i("tag", loginBean.toString());
                        }

                    });
        }else{
            Utils.getInstance().ToastShort("网络连接错误，请检查网络连接");
        }
    }
}
