package com.example.zhaoting.baseframe.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhaoting.baseframe.R;
import com.example.zhaoting.baseframe.presenters.LoginPresenter;
import com.example.zhaoting.baseframe.utils.NetUtils;
import com.example.zhaoting.baseframe.utils.SharedPManager;
import com.example.zhaoting.baseframe.utils.Utils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, BaseView {
    EditText accountText;
    EditText passwordText;
    TextView login;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountText = (EditText) findViewById(R.id.id_account);
        passwordText = (EditText) findViewById(R.id.id_password);
        login = (TextView) findViewById(R.id.id_login);
        Utils.getInstance().init(this);
        NetUtils.getInstance().init(this);
        SharedPManager.getInstance().init(this);
        login.setOnClickListener(this);

        setPresenter();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_login: {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                presenter.getLogin(accountText.getText().toString().trim(), passwordText.getText().toString().trim());
            }
            break;
        }
    }

    @Override
    public void setPresenter() {
        presenter = new LoginPresenter();
        presenter.attachView(this);
    }

    @Override
    public void onSuccess(Object o) {
        if (o.equals("login_success")){
            Utils.getInstance().ToastShort("登录成功");
        }
    }

    @Override
    public void onError() {
        Utils.getInstance().ToastShort("网络连接错误，请检查网络连接");
    }
}
