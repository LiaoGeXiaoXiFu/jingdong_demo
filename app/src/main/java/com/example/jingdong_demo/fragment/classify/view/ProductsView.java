package com.example.jingdong_demo.fragment.classify.view;

import com.example.jingdong_demo.fragment.classify.bean.ProductsBean;

import java.util.List;

public interface ProductsView {
    void showData(List<ProductsBean.DataBean> list);
}