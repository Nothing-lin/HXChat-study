package com.nothinglin.guiguchat.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.nothinglin.guiguchat.R;
import com.nothinglin.guiguchat.model.Model;

public class LoginActivity extends AppCompatActivity {
    private EditText et_login_name;
    private EditText et_login_password;
    private Button bt_login_register;
    private Button bt_login_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //初始化控件
        initView();

        //初始化监听
        initListener();
    }

    private void initListener() {
        //注册按钮的点击事件处理
        bt_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regist();
            }
        });

        //登录按钮的点击事件处理
        bt_login_login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        }));

    }

    //登录的业务逻辑处理
    private void login() {
    }

    //注册的业务逻辑处理
    private void regist() {
        //1.获取输入的和密码
        String registerName = et_login_name.getText().toString();
        String registerPwd = et_login_password.getText().toString();

        //2.校验输入的用户名和密码
        if (TextUtils.isEmpty(registerName) || TextUtils.isEmpty(registerPwd)){
            Toast.makeText(LoginActivity.this,"输入的用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        //3.去服务器注册账号
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(registerName,registerPwd);

                    //更新页面显示,要使用runOnUiThread来更新页面
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    //更新页面显示,要使用runOnUiThread来更新页面
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }


    private void initView() {
        et_login_name = (EditText) findViewById(R.id.et_login_name);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        bt_login_register = (Button) findViewById(R.id.bt_login_register);
        bt_login_login = (Button) findViewById(R.id.bt_login_login);
    }
}