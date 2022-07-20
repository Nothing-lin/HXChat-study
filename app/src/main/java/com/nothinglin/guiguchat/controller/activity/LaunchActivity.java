package com.nothinglin.guiguchat.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.hyphenate.chat.EMClient;
import com.nothinglin.guiguchat.R;
import com.nothinglin.guiguchat.model.Model;

public class LaunchActivity extends AppCompatActivity {

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            //如果当前activity已经退出，那么我就不处理handler中的消息
            if (isFinishing()){
                return;
            }

            //判断进入主界面还是登录界面
            toMainOrLogin();
        }
    };

    private void toMainOrLogin() {
//        new Thread(){
//            @Override
//            public void run() {
//            }
//        }.start();

        //全局线程池的调用
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //判断当前账号是否已经退出
                if (EMClient.getInstance().isLoggedInBefore()){//登陆过

                    //如果登录过，跳转到主界面
                    Intent intent = new Intent(LaunchActivity.this,MainActivity.class);
                    startActivity(intent);

                }else {//没登陆过
                    //如果没登陆过，那么跳转到登陆界面
                    Intent intent = new Intent(LaunchActivity.this,LoginActivity.class);
                    startActivity(intent);

                }

                //结束启动页面
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch);

        //发送2秒的延时消息
        handler.sendMessageDelayed(Message.obtain(),2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁消息,如果不销毁，那么启动页面一直存在
        handler.removeCallbacks(null);
    }
}