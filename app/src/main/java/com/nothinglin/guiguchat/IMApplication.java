package com.nothinglin.guiguchat;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.nothinglin.guiguchat.model.Model;

public class IMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initEasemob();

        //初始化数据模型层类
        Model.getInstance().init(this);
    }

    private void initEasemob(){
        //初始化环信SDK
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);//需要经过同意才能接受邀请
        options.setAutoAcceptGroupInvitation(false);//需要同意后才能接受群邀请
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);


        EMClient.getInstance().init(this,options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}
