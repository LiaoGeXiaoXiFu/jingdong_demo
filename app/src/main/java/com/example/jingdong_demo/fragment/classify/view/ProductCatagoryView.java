package com.example.jingdong_demo.fragment.classify.view;

import com.example.jingdong_demo.fragment.classify.bean.ProductCatagoryBean;

import java.util.List;

public interface ProductCatagoryView {
    void showData(List<String> groupList, List<List<ProductCatagoryBean.DataBean.ListBean>> childList);
}