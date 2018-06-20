package com.example.jingdong_demo.fragment.classify.presenter;

import com.example.jingdong_demo.fragment.classify.bean.ProductCatagoryBean;
import com.example.jingdong_demo.fragment.classify.model.ProductCatagoryModel;
import com.example.jingdong_demo.fragment.classify.view.ProductCatagoryView;
import com.google.gson.Gson;

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

public class ProductCatagoryPresenter {
    private ProductCatagoryView view;
    private final ProductCatagoryModel productCatagoryModel;

    public ProductCatagoryPresenter(ProductCatagoryView view) {
        this.view = view;
        productCatagoryModel = new ProductCatagoryModel();
    }
    public void getProductCatagory(String cid) {
        productCatagoryModel.getProductCatagory(cid, new ProductCatagoryModel.IProductCatagoryModel() {
            @Override
            public void showData(String result) {
                //定义一个集合用于存放title
                List<String> groupList = new ArrayList<>();
                //定义一个集合用于存放title对应的内容
                List<List<ProductCatagoryBean.DataBean.ListBean>> childList = new ArrayList<>();
                ProductCatagoryBean productCatagoryBean = new Gson().fromJson(result, ProductCatagoryBean.class);
                List<ProductCatagoryBean.DataBean> data = productCatagoryBean.getData();
                for (int i = 0; i < data.size(); i++) {
                    groupList.add(data.get(i).getName());
                    List<ProductCatagoryBean.DataBean.ListBean> list = data.get(i).getList();
                    childList.add(list);
                }
                if (view != null) {
                    view.showData(groupList, childList);
                }
            }
        });
    }
}
