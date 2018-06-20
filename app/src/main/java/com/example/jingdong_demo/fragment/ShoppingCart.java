package com.example.jingdong_demo.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.R;
import com.example.jingdong_demo.shopcar.adapter.ShopcartAdapter;
import com.example.jingdong_demo.shopcar.bean.GoodsInfo;
import com.example.jingdong_demo.shopcar.bean.Selectshop;
import com.example.jingdong_demo.shopcar.bean.StoreInfo;
import com.example.jingdong_demo.shopcar.presenter.GetCartsPresenter;
import com.example.jingdong_demo.shopcar.view.ShopcarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 撩个小媳妇 on 2018/6/15.
 */

public class ShoppingCart extends Fragment implements View.OnClickListener, ShopcartAdapter.CheckInterface,
        ShopcartAdapter.ModifyCountInterface, ShopcarView {
    /**
     * 后退键
     */
    private ImageView mBack;
    /**
     * 购物车
     */
    private TextView mTitle;
    /**
     * 二级列表
     */
    private ExpandableListView mExListView;
    /**
     * 全选
     */
    private CheckBox mAllChekbox;
    /**
     * ￥0.00
     */
    private TextView mTvTotalPrice;
    /**
     * 结算(0)
     */
    private TextView mTvGoToPay;
    // 购买的商品总价
    private double totalPrice = 0.00;
    // 购买的商品总数量
    private int totalCount = 0;
    private ShopcartAdapter selva;
    // 组元素数据列表
    private List<StoreInfo> groups = new ArrayList<StoreInfo>();
    // 子元素数据列表
    private Map<String, List<GoodsInfo>> children = new HashMap<String, List<GoodsInfo>>();
    private List<GoodsInfo> products;
    private AlertDialog alert;
    private GetCartsPresenter getCartsPresenter;
    private View view;
    private String uid;
    private String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shoppingcar, container, false);
        //绑定
        getCartsPresenter = new GetCartsPresenter(this);
        SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "71");
        // token = sp.getString("token", "");
        getCartsPresenter.getCar(uid);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBack = (ImageView) view.findViewById(R.id.back);
        mTitle = (TextView) view.findViewById(R.id.title);
        mExListView = (ExpandableListView) view.findViewById(R.id.exListView);
        mAllChekbox = (CheckBox) view.findViewById(R.id.all_chekbox);
        mTvTotalPrice = (TextView) view.findViewById(R.id.tv_total_price);
        mTvGoToPay = (TextView) view.findViewById(R.id.tv_go_to_pay);
        mTvGoToPay.setOnClickListener(this);
        mAllChekbox.setOnClickListener(this);
    }

    private void initEvents() {
        selva = new ShopcartAdapter(groups, children, MyApp.context);
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        mExListView.setAdapter(selva);
        //默认展开
        for (int i = 0; i < selva.getGroupCount(); i++) {
            // 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
            mExListView.expandGroup(i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_go_to_pay:
                if (totalCount == 0) {
                    Toast.makeText(MyApp.context, "请选择要支付的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            case R.id.all_chekbox:
                doCheckAll();
                break;
        }
    }

    /**
     * 全选反选
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(mAllChekbox.isChecked());
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(mAllChekbox.isChecked());
            }
        }
        //调用计算总数量与总价方法
        calculate();
        //刷新
        selva.notifyDataSetChanged();
    }

    /**
     * 判断所有组选框状态是否全部选中
     *
     * @return
     */
    private boolean isAllCheck() {

        for (StoreInfo group : groups) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 计算总数量与总价
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                GoodsInfo product = childs.get(j);
                if (product.isChoosed()) {
                    totalCount++;
                    totalPrice += product.getPrice() * product.getCount();
                }
            }
        }
        mTvTotalPrice.setText("￥" + totalPrice);
        mTvGoToPay.setText("去支付(" + totalCount + ")");
    }

    /**
     * 组选框状态改变触发的事件
     *
     * @param groupPosition 组元素位置
     * @param isChecked     组元素选中与否
     */
    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            mAllChekbox.setChecked(true);
        else
            mAllChekbox.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 子选框状态改变时触发的事件
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param isChecked     子元素选中与否
     */
    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        // 判断改组下面的所有子元素是否是同一种状态
        boolean allChildSameState = true;
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            // 不全选中
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        //获取店铺选中商品的总金额
        if (allChildSameState) {
            // 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
            group.setChoosed(isChecked);
        } else {
            // 否则，组元素一律设置为未选中状态
            group.setChoosed(false);
        }
        if (isAllCheck()) {
            // 全选
            mAllChekbox.setChecked(true);
        } else {
            // 反选
            mAllChekbox.setChecked(false);
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 增加操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        currentCount++;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        calculate();
        selva.notifyDataSetChanged();
    }

    /**
     * 删减操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        if (currentCount == 1) {
            Toast.makeText(MyApp.context, "数量不能少于1", Toast.LENGTH_SHORT).show();
            return;
        }
        currentCount--;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        calculate();
        selva.notifyDataSetChanged();
    }

    /**
     * 删除子item
     *
     * @param groupPosition
     * @param childPosition
     */
    @Override
    public void childDelete(int groupPosition, int childPosition) {
        children.get(groups.get(groupPosition).getId()).remove(childPosition);
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        if (childs.size() == 0) {
            groups.remove(groupPosition);
        }
        selva.notifyDataSetChanged();
    }

    /**
     * 获取购物车数据
     *
     * @param data
     */
    @Override
    public void getshopcar(List<Selectshop.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            groups.add(new StoreInfo(data.get(i).getSellerid(), data.get(i).getSellerName()));
            products = new ArrayList<GoodsInfo>();
            for (int j = 0; j < data.get(i).getList().size(); j++) {
                String images = data.get(i).getList().get(j).getImages();
                String[] split = images.split("[|]");
                String[] split1 = split[0].split("[!]");
                List<Selectshop.DataBean.ListBean> list = data.get(i).getList();
                products.add(new GoodsInfo(list.get(j).getPid() + "", "商品", list.get(j).getTitle(), Double.valueOf(list.get(j).getPrice() + ""), Integer.parseInt(list.get(j).getNum() + ""), "", "1", split1[0], Double.valueOf(list.get(j).getBargainPrice())));
            }
            // 将组元素的一个唯一值，这里取Id，作为子元素List的Key
            children.put(groups.get(i).getId(), products);
        }
        initEvents();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getCartsPresenter.detach();
    }
}
