package com.example.jingdong_demo.mysqlhelp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Mysql extends SQLiteOpenHelper {
    public Mysql(Context context) {
        super(context, "zhangjunyou", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建表
        sqLiteDatabase.execSQL("create table t_shop (a varchar(20), aa varchar(11), aaa varchar(20) , aaaa varchar(20))");
        //添加数据
        sqLiteDatabase.execSQL("insert into t_shop values('羊毛衫男','新品','恒轩祥','高领')");
        sqLiteDatabase.execSQL("insert into t_shop values('衬衣','新品','男长袖','保暖')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
