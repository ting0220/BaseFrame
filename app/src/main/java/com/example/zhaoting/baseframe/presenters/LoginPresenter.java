package com.example.zhaoting.baseframe.presenters;

import com.example.zhaoting.baseframe.bean.LoginBean;
import com.example.zhaoting.baseframe.models.LoginModel;
import com.example.zhaoting.baseframe.views.BaseView;

import javax.inject.Inject;

/**
 * Created by zhaoting on 16/12/16.
 */

public class LoginPresenter {
    private BaseView mView;

    @Inject
    public LoginPresenter(BaseView view) {
        mView = view;
    }

    public void getLogin(String account, String password) {
        LoginModel.getInstance().getLogin(account, password, new LoginModel.LoginInterface<LoginBean>() {
            @Override
            public void loginSuccess(LoginBean loginBean) {
                mView.onSuccess("login_success");
            }

            @Override
            public void loginNetError() {
                mView.onError();
            }
        });
    }

}
