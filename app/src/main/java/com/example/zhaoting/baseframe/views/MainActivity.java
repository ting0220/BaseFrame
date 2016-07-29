package com.example.zhaoting.baseframe.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhaoting.baseframe.R;
import com.example.zhaoting.baseframe.bean.LoginBean;
import com.example.zhaoting.baseframe.interfaces.LoginInterface;
import com.example.zhaoting.baseframe.netUtils.Constans;
import com.example.zhaoting.baseframe.netUtils.HttpResultFunc;
import com.example.zhaoting.baseframe.netUtils.RetrofitUtil;
import com.example.zhaoting.baseframe.utils.SharedPManager;
import com.example.zhaoting.baseframe.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText accountText;
    EditText passwordText;
    TextView login;
    RetrofitUtil retrofitUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountText= (EditText) findViewById(R.id.id_account);
        passwordText= (EditText) findViewById(R.id.id_password);
        login= (TextView) findViewById(R.id.id_login);
        Utils.getInstance().init(this);
        SharedPManager.getInstance().init(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_login: {
                Map<String, String> map = new HashMap<>();
                map.put("client_id", Constans.APP_KEY);
                map.put("client_secret", Constans.APP_SECRET);
                map.put("username", accountText.getText().toString());
                map.put("password", passwordText.getText().toString());
                map.put("grant_type", "password");
                retrofitUtil.getInstance().create(LoginInterface.class).getLogin(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new HttpResultFunc<LoginBean>())
                        .subscribe(new Action1<LoginBean>() {
                            @Override
                            public void call(LoginBean loginBean) {
                                LoginBean.DataBean bean= (LoginBean.DataBean) loginBean.getData();
                                Utils.getInstance().ToastShort(bean.getAccessToken());
                            }
                        });
            }
            break;
        }
    }
}
