package com.nothinglin.guiguchat.model;

import android.content.Context;

import com.nothinglin.guiguchat.model.dao.UserAccountDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//数据模型层全局类
public class Model {
    private UserAccountDao userAccountDao;
    private Context mContext;
    private ExecutorService executorService = Executors.newCachedThreadPool();//线程池
    //创建对象
    private static Model model = new Model();

    //私有化构造
    private Model(){

    }

    //获取单例对象
    public static Model getInstance(){
        return model;
    }

    //初始化方法
    public void init(Context context){
        mContext = context;

        //创建用户账号数据库的操作类对象
        userAccountDao = new UserAccountDao(mContext);

    }

    //定义全局线程池
    public ExecutorService getGlobalThreadPool(){
        return executorService;
    }

    //用户登录成功后的处理方法
    public void loginSuccess() {
    }


    //获取用户账号数据库的操作类对象
    public UserAccountDao getUserAccountDao(){
        return userAccountDao;
    }
}
