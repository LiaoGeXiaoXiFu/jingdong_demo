package com.example.jingdong_demo.fragment.classify.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jingdong_demo.R;
import com.example.jingdong_demo.fragment.classify.adapter.XrvListAdapter;
import com.example.jingdong_demo.fragment.classify.bean.ProductsBean;
import com.example.jingdong_demo.fragment.classify.presenter.ProductsPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
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

public class ListActivity extends AppCompatActivity implements ProductsView, View.OnClickListener {

    private XRecyclerView mXrv;
    private ProductsPresenter productsPresenter;
    private int pscid;
    private boolean isRefresh = true;
    private XrvListAdapter adapter;
    private LinearLayout mSouSou;
    private ImageView mSaoIv;
    private ImageView mMsgIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
        //获取pscid
        Intent intent = getIntent();
        pscid = intent.getIntExtra("pscid", 0);
        //绑定
        productsPresenter = new ProductsPresenter(this);
        productsPresenter.getProducts(pscid + "");
    }

    private void initView() {
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mXrv.setLayoutManager(linearLayoutManager);

        //设置刷新和加载更多监听
        mXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                isRefresh = true;
                productsPresenter.getProducts(pscid + "");
            }

            @Override
            public void onLoadMore() {
                //加载更多
                isRefresh = false;
                productsPresenter.getProducts(pscid + "");
            }
        });
        mSouSou = (LinearLayout) findViewById(R.id.sou_sou);
        mSouSou.setOnClickListener(this);
        mSaoIv = (ImageView) findViewById(R.id.sao_iv);
        mSaoIv.setOnClickListener(this);
        mMsgIv = (ImageView) findViewById(R.id.msg_iv);
        mMsgIv.setOnClickListener(this);
    }

    @Override
    public void showData(List<ProductsBean.DataBean> list) {
        final List<ProductsBean.DataBean> tempList = new ArrayList<>();
        tempList.addAll(list);
        //创建适配器
        if (isRefresh) {
            adapter = new XrvListAdapter(this, list);
            mXrv.setAdapter(adapter);
            adapter.refresh(tempList);
            mXrv.refreshComplete();//设置刷新完成
        } else {
            if (adapter != null) {
                //判断适配器是否创建过
                adapter.loadMore(tempList);
                mXrv.loadMoreComplete();//设置加载更多完成
            }
        }
        if (adapter == null) {
            return;
        }
        adapter.setOnListItemClickListener(new XrvListAdapter.OnListItemClickListener() {
            @Override
            public void OnItemClick(ProductsBean.DataBean dataBean) {
                Intent intent = new Intent(ListActivity.this, ListDetailsActivity.class);
                intent.putExtra("id",1);
                intent.putExtra("bean", dataBean);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.sou_sou:
                Intent it = new Intent(ListActivity.this,SouSuoActivity.class);
                startActivity(it);
                break;
            case R.id.sao_iv:
                break;
            case R.id.msg_iv:
                break;
        }
    }
}
