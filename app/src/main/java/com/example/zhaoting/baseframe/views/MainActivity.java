package com.example.zhaoting.baseframe.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhaoting.baseframe.R;
import com.example.zhaoting.baseframe.models.LoginModel;
import com.example.zhaoting.baseframe.utils.NetUtils;
import com.example.zhaoting.baseframe.utils.SharedPManager;
import com.example.zhaoting.baseframe.utils.Utils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText accountText;
    EditText passwordText;
    TextView login;
    LoginModel model;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_login: {
                model.getInstance().getLogin(accountText.getText().toString(), passwordText.getText().toString());
            }
            break;
        }
    }
}
