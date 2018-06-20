package com.example.jingdong_demo.mysqlhelp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 撩个小媳妇 on 2018/6/18.
 */

public class Dao {
    //获取帮助类对象--->通过帮助类对象得到操作数据库对象(mh.getWritableDatabase())---->  SQLiteDatabase
    Mysql mh;

    public Dao(Context ctx) {
        super();
        mh=new Mysql(ctx);  //获取帮助类实例对象
    }

    public List<Users> select() {
        // 创建存放所有行对象集合
        List<Users> us = new ArrayList<Users>();

        //获取SQLiteDatabase对象
        SQLiteDatabase database = mh.getWritableDatabase();

        //执行查询的sql语句--->获取到结果集对象  Cursor
        Cursor query = database.rawQuery("select * from t_shop", null);   //null是为sequel语句中的占位符赋值

        //是否有下一行
        while (query.moveToNext()) {
            //获取当前行中每个列的值
            String a = query.getString(0);
            String aa = query.getString(1);
            String aaa = query.getString(2);
            String aaaa = query.getString(3);
            Users u = new Users(a, aa, aaa, aaaa);
            us.add(u);
        }
        database.close();
        return us;
    }
    public void insert(Users users){
        SQLiteDatabase database = mh.getWritableDatabase();
        database.execSQL("insert into t_shop values('"+users.getA()+"','"+users.getAa()+"','"+users.getAaa()+"','"+users.getAaaa()+"')");
        database.close();
    }
    public void clear(){
        SQLiteDatabase database = mh.getWritableDatabase();
        database.execSQL("delete from t_shop");
    }
}
