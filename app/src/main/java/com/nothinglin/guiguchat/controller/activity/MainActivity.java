package com.nothinglin.guiguchat.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.nothinglin.guiguchat.R;
import com.nothinglin.guiguchat.controller.fragment.ChatFragment;
import com.nothinglin.guiguchat.controller.fragment.ContactListFragment;
import com.nothinglin.guiguchat.controller.fragment.SettingFragment;

public class MainActivity extends FragmentActivity {
    private RadioGroup rg_main;
    ChatFragment chatFragment = null;
    ContactListFragment contactListFragment = null;
    SettingFragment settingFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        //初始化界面
        initView();
        
        //初始化数据
        initData();
        
        //初始化监听器
        initListener();
    }

    private void initListener() {
        //RadioGroup的选择事件
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Fragment fragment = null;
                switch (i){
                    //会话列表
                    case R.id.rb_main_chat:
                        fragment = chatFragment;
                        break;
                    //联系人列表
                    case R.id.rb_main_contact:
                        fragment = contactListFragment;
                        break;
                    //设置列表
                    case R.id.rb_main_setting:
                        fragment = settingFragment;
                        break;
                }

                //实现fragment切换的方法
                switchFragment(fragment);
            }
        });

        //默认选择会话界面
        rg_main.check(R.id.rb_main_chat);
    }

    private void switchFragment(Fragment fragment) {

        //替换fragment界面中的内容
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();

    }

    private void initData() {
        //创建三个fragment对象
        chatFragment = new ChatFragment();
        contactListFragment = new ContactListFragment();
        settingFragment = new SettingFragment();
    }

    private void initView() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
    }
}