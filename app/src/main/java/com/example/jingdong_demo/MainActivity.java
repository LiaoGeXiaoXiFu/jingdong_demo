package com.example.jingdong_demo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.jingdong_demo.fragment.Classify;
import com.example.jingdong_demo.fragment.Find;
import com.example.jingdong_demo.fragment.HomePage;
import com.example.jingdong_demo.fragment.Mine;
import com.example.jingdong_demo.fragment.ShoppingCart;
import com.example.jingdong_demo.greendao.BeanDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mContainer;
    private RadioButton mHome;
    private RadioButton mCla;
    private RadioButton mCar;
    private RadioButton mFind;
    private RadioButton mMy;
    private LinearLayout mActivityMain;
    private FragmentManager fragmentManager;
    private BeanDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        int id = getIntent().getIntExtra("id", 0);//获取intent值
        if (id == 2) {//判断intent值
            mCar.performClick();//给按钮设置状态
        } else {
            mHome.performClick();
        }

        //实例化greendao
        dao = MyApp.getInstances().getDaoSession().getBeanDao();
        //增
        Bean bean = new Bean(1,"anye3");
        dao.insert(bean);//添加一个
        //删
//        dao.deleteByKey(bean.id);
        //改
//        Bean bean1 = new Bean(2, "anye0803");
//        dao.update(bean1);
        //查
        List<Bean> users = dao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getA()+",";
        }
//        mContext.setText("查询全部数据==>"+userName);
        Log.e("name",userName);

    }

    private void initView() {
        mContainer = (FrameLayout) findViewById(R.id.container);
        mHome = (RadioButton) findViewById(R.id.home);
        mCla = (RadioButton) findViewById(R.id.cla);
        mCar = (RadioButton) findViewById(R.id.car);
        mFind = (RadioButton) findViewById(R.id.find);
        mMy = (RadioButton) findViewById(R.id.my);
        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);
        fragmentManager = getSupportFragmentManager();

        mContainer.setOnClickListener(this);
        mHome.setOnClickListener(this);
        mCla.setOnClickListener(this);
        mCar.setOnClickListener(this);
        mFind.setOnClickListener(this);
        mMy.setOnClickListener(this);
        mActivityMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                fragmentManager.beginTransaction().replace(R.id.container, new HomePage()).commit();
                break;
            case R.id.home://首页
                fragmentManager.beginTransaction().replace(R.id.container, new HomePage()).commit();
                break;
            case R.id.cla://分类
                fragmentManager.beginTransaction().replace(R.id.container, new Classify()).commit();
                break;
            case R.id.car://购物车
                fragmentManager.beginTransaction().replace(R.id.container, new ShoppingCart()).commit();
                break;
            case R.id.find://发现
                fragmentManager.beginTransaction().replace(R.id.container, new Find()).commit();
                break;
            case R.id.my://我的
                fragmentManager.beginTransaction().replace(R.id.container, new Mine()).commit();
                break;
        }
    }
}
