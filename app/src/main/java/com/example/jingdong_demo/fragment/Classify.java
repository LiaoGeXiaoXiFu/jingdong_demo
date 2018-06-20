package com.example.jingdong_demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.jingdong_demo.R;
import com.example.jingdong_demo.fragment.classify.adapter.ElvAdapter;
import com.example.jingdong_demo.fragment.classify.adapter.RvLeftAdapter;
import com.example.jingdong_demo.fragment.classify.bean.ProductCatagoryBean;
import com.example.jingdong_demo.fragment.classify.presenter.ProductCatagoryPresenter;
import com.example.jingdong_demo.fragment.classify.view.OnItemClickListener;
import com.example.jingdong_demo.fragment.classify.view.ProductCatagoryView;
import com.example.jingdong_demo.fragment.homepager.bean.CatagoryBean;
import com.example.jingdong_demo.fragment.homepager.presenter.CatagoryPresenter;
import com.example.jingdong_demo.fragment.homepager.view.ICatagoryView;

import java.util.List;

/**
 * Created by 撩个小媳妇 on 2018/6/15.
 * 分类
 */

public class Classify extends Fragment implements View.OnClickListener, ICatagoryView, ProductCatagoryView {
    private View view;
    private RecyclerView mRvLeft;
    private ImageView mIv;
    private ExpandableListView mElv;
    private CatagoryPresenter catagoryPresenter;
    private ProductCatagoryPresenter productCatagoryPresenter;
    private ImageView mSaoIv;
    /**
     * 周大福礼包大放送
     */
    private EditText mSouSou;
    private ImageView mMsgIv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classify, container, false);
        initView(view);
        //先绑定
        catagoryPresenter = new CatagoryPresenter(this);
        productCatagoryPresenter = new ProductCatagoryPresenter(this);
        //请求数据
        catagoryPresenter.getCatagory();
        return view;
    }

    private void initView(View view) {
        mRvLeft = (RecyclerView) view.findViewById(R.id.rvLeft);
        mRvLeft.setOnClickListener(this);
        mIv = (ImageView) view.findViewById(R.id.iv);
        mIv.setBackgroundResource(R.drawable.timg);
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mSaoIv = (ImageView) view.findViewById(R.id.sao_iv);
        mSaoIv.setOnClickListener(this);
        mSouSou = (EditText) view.findViewById(R.id.sou_sou);
        mSouSou.setOnClickListener(this);
        mMsgIv = (ImageView) view.findViewById(R.id.msg_iv);
        mMsgIv.setOnClickListener(this);
        mIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.sao_iv:
                break;
            case R.id.sou_sou:
                break;
            case R.id.msg_iv:
                break;
        }
    }

    @Override
    public void showData(List<String> groupList, List<List<ProductCatagoryBean.DataBean.ListBean>> childList) {
        //使用ExpandableListView展示数据
        ElvAdapter elvAdapter = new ElvAdapter(getContext(), groupList, childList);
        mElv.setAdapter(elvAdapter);
        //默认展开列表
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }

    }

    @Override
    public void showCatagory(CatagoryBean catagoryBean) {
        final List<CatagoryBean.DataBean> data = catagoryBean.getData();
        //设置布局管理器
        mRvLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvLeft.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        //创建适配器
        final RvLeftAdapter adapter = new RvLeftAdapter(getContext(), data);
        //显示左侧列表数据
        mRvLeft.setAdapter(adapter);
        //根据左侧列表第一项的cid去请求右侧的数据
        productCatagoryPresenter.getProductCatagory(data.get(0).getCid() + "");

        //设置第一个为默认选中
        adapter.changeCheck(0, true);

        //设置点击事件
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //改变DataBean里面check的属性值
                adapter.changeCheck(position, true);
                //请求右侧对应的数据
                productCatagoryPresenter.getProductCatagory(data.get(position).getCid() + "");
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
    }
}
