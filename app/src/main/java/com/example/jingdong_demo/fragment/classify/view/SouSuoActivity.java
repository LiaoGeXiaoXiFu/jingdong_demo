package com.example.jingdong_demo.fragment.classify.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.R;
import com.example.jingdong_demo.customview.FlowLayout;
import com.example.jingdong_demo.fragment.classify.adapter.MyAdapter;
import com.example.jingdong_demo.fragment.classify.sousuo.ShowActivity;
import com.example.jingdong_demo.mysqlhelp.Dao;
import com.example.jingdong_demo.mysqlhelp.Users;

import java.util.List;

/**
 *
 * ━━━━━━草泥马神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃ 神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 *
 * ━━━━━━当善良的人摘下面具.你连下跪的机会都没有━━━━━━
 */

public class SouSuoActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 请输入要搜索的关键词
     */
    private EditText mEtSearch;
    /**
     * 搜索
     */
    private TextView mTvSearch;
    private ImageView mIvDelete;
    private String[] rs = {"手机", "笔记本", "坚果", "月饼", "欧式壁灯", "吸尘器干湿两用", "户外登山鞋", "无线恐引起", "洗面奶", "泡茶壶"};
    private Dao dao;
    private FlowLayout mFlowHotSearch;
    private RecyclerView mLvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        initView();
        initChildViews();
        dao = new Dao(MyApp.context);
        history();
    }

    private void initView() {
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mTvSearch = (TextView) findViewById(R.id.tv_search);
        mIvDelete = (ImageView) findViewById(R.id.iv_delete);
        mFlowHotSearch = (FlowLayout) findViewById(R.id.flow_hot_search);
        mLvSearch = (RecyclerView) findViewById(R.id.lv_search);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mLvSearch.setLayoutManager(linearLayoutManager);
        mTvSearch.setOnClickListener(this);
        mIvDelete.setOnClickListener(this);
    }

    private void initChildViews() {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for (int i = 0; i < rs.length; i++) {
            final Button view = new Button(this);
            view.setText(rs[i]);
            view.setTextColor(Color.WHITE);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.tv_shape));
            mFlowHotSearch.addView(view, lp);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEtSearch.setText(view.getText().toString());
                    String s = mEtSearch.getText().toString();
                    Users users = new Users(s, "", "", "");
                    dao.insert(users);
                    history();
                    Intent it = new Intent(SouSuoActivity.this, ShowActivity.class);
                    it.putExtra("pname", s);
                    startActivity(it);
                }
            });
        }

    }

    public void history() {
        List<Users> select = dao.select();
        Log.e("select2------>", select.size() + "");
        MyAdapter adapter = new MyAdapter(select, MyApp.context);
        mLvSearch.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_search:
                String s = mEtSearch.getText().toString();
                Users users = new Users(s, "", "", "");
                dao.insert(users);
                history();
                break;
            case R.id.iv_delete:
                dao.clear();
                history();
                break;
        }
    }
}