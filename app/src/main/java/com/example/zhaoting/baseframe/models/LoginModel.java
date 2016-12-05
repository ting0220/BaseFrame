package com.example.zhaoting.baseframe.models;

import com.example.zhaoting.baseframe.bean.LoginBean;
import com.example.zhaoting.baseframe.httpUtils.Constans;
import com.example.zhaoting.baseframe.httpUtils.HttpResult;
import com.example.zhaoting.baseframe.httpUtils.HttpResultFunc;
import com.example.zhaoting.baseframe.httpUtils.HttpResultSubscribe;
import com.example.zhaoting.baseframe.httpUtils.RetrofitUtil;
import com.example.zhaoting.baseframe.interfaces.ApiInterface;
import com.example.zhaoting.baseframe.utils.NetUtils;
import com.example.zhaoting.baseframe.utils.SharedPManager;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
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

    public void getLogin(String username, String password, final LoginInterface<LoginBean> loginInterface) {
        Map<String, String> map = new HashMap<>();
        map.put("client_id", Constans.APP_KEY);
        map.put("client_secret", Constans.APP_SECRET);
        map.put("username", username);
        map.put("password", password);
        map.put("grant_type", "password");

        if (NetUtils.getInstance().isNetConnected()) {
            RetrofitUtil.getInstance().create(ApiInterface.class).getLogin(map)
                    .subscribeOn(Schedulers.io())
                    .map(new HttpResultFunc<LoginBean>() {
                        @Override
                        public LoginBean call(HttpResult<LoginBean> loginBeanHttpResult) {
                            SharedPManager.getInstance().setLoginMessage(loginBeanHttpResult.getData());
                            return loginBeanHttpResult.getData();
                        }
                    })
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new HttpResultSubscribe<LoginBean>() {
                        @Override
                        public void onNext(LoginBean loginBean) {
                            loginInterface.loginSuccess(loginBean);
                        }

                    });
        } else {
            loginInterface.loginNetError();
        }
    }

    public interface LoginInterface<T> {
        void loginSuccess(T t);
        void loginNetError();
    }
}
