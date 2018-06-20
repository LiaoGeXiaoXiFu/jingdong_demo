package com.example.jingdong_demo.fragment.classify.sousuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.R;
import com.example.jingdong_demo.fragment.classify.sousuo.adapter.SouSuoGridAdapter;
import com.example.jingdong_demo.fragment.classify.sousuo.adapter.SouSuoListAdapter;
import com.example.jingdong_demo.fragment.classify.sousuo.bean.SousuoBean;
import com.example.jingdong_demo.fragment.classify.sousuo.presenter.ShowPresenter;
import com.example.jingdong_demo.fragment.classify.sousuo.view.IShowActivityView;
import com.example.jingdong_demo.fragment.classify.view.ListDetailsActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ShowActivity extends AppCompatActivity implements IShowActivityView {

    /**
     * 请输入要搜索的关键词
     */
    private EditText mEtSearch;
    private ImageView mTvSearch;
    private XRecyclerView mXrvshow;
    private Boolean layoutShifter = true;
    private ShowPresenter showPresenter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        initData();

    }

    /**
     * 初始化数据
     */
    private void initData() {
        showPresenter = new ShowPresenter(this);
        Intent intent = getIntent();
        String pname = intent.getStringExtra("pname");
        mEtSearch.setText(pname + "");
        String s = mEtSearch.getText().toString();
        showPresenter.getGoods(s, page);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mTvSearch = (ImageView) findViewById(R.id.tv_search);
        mXrvshow = (XRecyclerView) findViewById(R.id.xrvshow);
    }

    /**
     * 页面显示逻辑
     *
     * @param sousuoBean
     */
    @Override
    public void issucceed(final SousuoBean sousuoBean) {
        final List<SousuoBean.DataBean> data = sousuoBean.getData();
        //默认显示
        mXrvshow.setLayoutManager(new LinearLayoutManager(MyApp.context));
        SouSuoListAdapter souSuoListAdapter = new SouSuoListAdapter(MyApp.context, data);
        mXrvshow.setAdapter(souSuoListAdapter);
        souSuoListAdapter.setOnRecyclerViewItemClickListener(new SouSuoListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(SousuoBean.DataBean dataBean) {
                Intent intent = new Intent(ShowActivity.this, ListDetailsActivity.class);
                intent.putExtra("id", 2);
                intent.putExtra("sousuoBean", dataBean);
                startActivity(intent);
            }
        });
        //布局切换监听
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutShifter) {
                    //网格布局
                    mTvSearch.setBackgroundResource(R.drawable.menu_item);
                    mXrvshow.setLayoutManager(new GridLayoutManager(MyApp.context, 2, GridLayoutManager.VERTICAL, false));
                    SouSuoGridAdapter suoGridAdapter = new SouSuoGridAdapter(MyApp.context, data);
                    mXrvshow.setAdapter(suoGridAdapter);
                    suoGridAdapter.setOnRecyclerViewItemClickListener(new SouSuoGridAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void OnItemClick(SousuoBean.DataBean dataBean) {
                            Intent intent = new Intent(ShowActivity.this, ListDetailsActivity.class);
                            intent.putExtra("id", 2);
                            intent.putExtra("sousuoBean", dataBean);
                            startActivity(intent);
                        }
                    });
                    layoutShifter = false;
                } else {
                    //列表布局
                    mTvSearch.setBackgroundResource(R.drawable.menu_item_detail_normal);
                    mXrvshow.setLayoutManager(new LinearLayoutManager(MyApp.context));
                    SouSuoListAdapter suoListAdapter = new SouSuoListAdapter(MyApp.context, data);
                    mXrvshow.setAdapter(suoListAdapter);
                    suoListAdapter.setOnRecyclerViewItemClickListener(new SouSuoListAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(SousuoBean.DataBean dataBean) {
                            Intent intent = new Intent(ShowActivity.this, ListDetailsActivity.class);
                            intent.putExtra("id", 2);
                            intent.putExtra("sousuoBean", dataBean);
                            startActivity(intent);
                        }
                    });
                    layoutShifter = true;
                }
            }
        });
    }
}