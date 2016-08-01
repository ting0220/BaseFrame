package com.example.zhaoting.baseframe.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhaoting.baseframe.R;
<<<<<<< HEAD
import com.example.zhaoting.baseframe.interfaces.LoginInterface;
import com.example.zhaoting.baseframe.netUtils.Constans;
=======
import com.example.zhaoting.baseframe.bean.LoginBean;
import com.example.zhaoting.baseframe.interfaces.ApiInterface;
import com.example.zhaoting.baseframe.netUtils.Constans;
import com.example.zhaoting.baseframe.netUtils.HttpResultFunc;
import com.example.zhaoting.baseframe.netUtils.HttpResultSubscribe;
>>>>>>> a9f1614d90748f923780fddfdcde6e4ac73c7dfc
import com.example.zhaoting.baseframe.netUtils.RetrofitUtil;
import com.example.zhaoting.baseframe.utils.SharedPManager;
import com.example.zhaoting.baseframe.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
<<<<<<< HEAD
import rx.functions.Func1;
=======
>>>>>>> a9f1614d90748f923780fddfdcde6e4ac73c7dfc
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
                map.put("grant_type", "password");
                map.put("username", accountText.getText().toString());
                map.put("password", passwordText.getText().toString());
<<<<<<< HEAD
                retrofitUtil.getInstance().create(LoginInterface.class).getLogin(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Func1<String,String>() {
                            @Override
                            public String call(String loginBeanHttpResult) {
                                Log.d("tag", "map");
                                return loginBeanHttpResult;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                Log.d("tag", "completed");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("tag", e.toString());
                                onCompleted();
                            }

                            @Override
                            public void onNext(String loginBeanHttpResult) {
                                Log.d("tag", loginBeanHttpResult.toString());
                                onCompleted();
=======
                map.put("grant_type", "password");

                retrofitUtil.getInstance().create(ApiInterface.class).getLogin(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.newThread())
                        .map(new HttpResultFunc<LoginBean>())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new HttpResultSubscribe<LoginBean>() {
                            @Override
                            public void onNext(LoginBean loginBean) {
                                Log.i("tag", loginBean.toString());
>>>>>>> a9f1614d90748f923780fddfdcde6e4ac73c7dfc
                            }

                        });
//                retrofitUtil.getInstance().create(LoginInterface.class).getLogin(map)
//                        .subscribeOn(Schedulers.io())
//                        .map(new Func1<HttpResult<LoginBean>, LoginBean>() {
//                            @Override
//                            public LoginBean call(HttpResult<LoginBean> loginBeanHttpResult) {
//                                if (!loginBeanHttpResult.getResult().equals("ok")) {
//                                    try {
//                                        JSONArray array = new JSONArray(loginBeanHttpResult.getErrors());
//                                        ErrorBean bean = (ErrorBean) array.get(0);
//                                        throw new ApiException(bean);
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//
//                                }
//                                Log.i("tag","true+map");
//                                return loginBeanHttpResult.getData();
//
//                            }
//                            })
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Action1<LoginBean>() {
//                            @Override
//                            public void call(LoginBean loginBean) {
//                                LoginBean.DataBean bean= (LoginBean.DataBean) loginBean.getData();
//                                Utils.getInstance().ToastShort(bean.getAccessToken());
//                            }
//                        }, new Action1() {
//                            @Override
//                            public void call(Object o) {
//                                Utils.getInstance().ToastShort("error");
//
//                            }
//                        });
            }
            break;
            //                        .map(new HttpResultFunc<LoginBean>())
        }
    }
}
