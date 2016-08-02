package com.example.zhaoting.baseframe.presenters;

import com.example.zhaoting.baseframe.models.LoginModel;
import com.example.zhaoting.baseframe.views.BaseView;

/**
 * Created by Z_TING on 2016/8/1.
 */
public class LoginPresenter implements BasePresenter {
    private BaseView mView;


    public void getLogin(String account, String password) {
        LoginModel.getInstance().getLogin(account, password);
    }

    @Override
    public void attachView(BaseView view) {
        mView = view;
    }


//    @Override
//    public void start() {
//        getLogin()
//        LoginModel.getInstance().getLogin();
//    }
}
