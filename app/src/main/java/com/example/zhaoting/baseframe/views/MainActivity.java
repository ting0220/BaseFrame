package com.example.zhaoting.baseframe.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.zhaoting.baseframe.R;
import com.example.zhaoting.baseframe.dagger.DaggerMainComponent;
import com.example.zhaoting.baseframe.dagger.MainModule;
import com.example.zhaoting.baseframe.presenters.LoginPresenter;
import com.example.zhaoting.baseframe.utils.NetUtils;
import com.example.zhaoting.baseframe.utils.SharedPManager;
import com.example.zhaoting.baseframe.utils.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhaoting on 16/12/16.
 */

public class MainActivity extends AppCompatActivity implements BaseView {
    @BindView(R.id.id_account)
    EditText accountText;
    @BindView(R.id.id_password)
    EditText passwordText;
    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Utils.getInstance().init(this);
        NetUtils.getInstance().init(this);
        SharedPManager.getInstance().init(this);

        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
    }

    @OnClick(R.id.id_login)
    public void login() {
        Utils.getInstance().changeInputMethod(this.getCurrentFocus(), false);
        presenter.getLogin(accountText.getText().toString().trim(), passwordText.getText().toString().trim());
    }

    @Override
    public void onSuccess(Object o) {
        if (o.equals("login_success")) {
            Utils.getInstance().ToastShort("登录成功");
        }
    }

    @Override
    public void onError() {
        Utils.getInstance().ToastShort("网络连接错误，请检查网络连接");
    }
}
