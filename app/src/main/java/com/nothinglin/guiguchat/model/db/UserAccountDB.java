package com.nothinglin.guiguchat.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nothinglin.guiguchat.model.dao.UserAccountTable;

public class UserAccountDB extends SQLiteOpenHelper {

    //构造器
    public UserAccountDB(@Nullable Context context) {
        super(context,"account.db",null,2);
    }

    //数据库创建的时候调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库表的语句
        db.execSQL(UserAccountTable.CREATE_TAB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
