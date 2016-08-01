package com.example.zhaoting.baseframe.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhaoting.baseframe.R;
import com.example.zhaoting.baseframe.bean.LoginBean;
import com.example.zhaoting.baseframe.interfaces.ApiInterface;
import com.example.zhaoting.baseframe.netUtils.Constans;
import com.example.zhaoting.baseframe.netUtils.HttpResultFunc;
import com.example.zhaoting.baseframe.netUtils.HttpResultSubscribe;
import com.example.zhaoting.baseframe.netUtils.RetrofitUtil;
import com.example.zhaoting.baseframe.utils.NetUtils;
import com.example.zhaoting.baseframe.utils.SharedPManager;
import com.example.zhaoting.baseframe.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
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
        accountText = (EditText) findViewById(R.id.id_account);
        passwordText = (EditText) findViewById(R.id.id_password);
        login = (TextView) findViewById(R.id.id_login);
        Utils.getInstance().init(this);
        NetUtils.getInstance().init(this);
        SharedPManager.getInstance().init(this);
        retrofitUtil = new RetrofitUtil();
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

                if (NetUtils.getInstance().isNetConnected()) {
                    retrofitUtil.getInstance().create(ApiInterface.class).getLogin(map)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.newThread())
                            .map(new HttpResultFunc<LoginBean>())
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
            break;
        }
    }
}
