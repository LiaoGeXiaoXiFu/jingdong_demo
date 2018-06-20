package com.example.jingdong_demo.fragment.classify.presenter;

import com.example.jingdong_demo.fragment.classify.bean.ProductsBean;
import com.example.jingdong_demo.fragment.classify.model.ProductsModel;
import com.example.jingdong_demo.fragment.classify.view.ProductsView;

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

public class ProductsPresenter {
    private ProductsView productsView;
    private final ProductsModel productsModel;

    public ProductsPresenter(ProductsView productsView) {
        this.productsView = productsView;
        productsModel = new ProductsModel();
    }


    public void getProducts(String pscid) {
        productsModel.getData(pscid, new ProductsModel.IProductsModel() {
            @Override
            public void getProducts(ProductsBean productsBean) {
                if (productsView != null) {
                    List<ProductsBean.DataBean> data = productsBean.getData();
                    productsView.showData(data);
                }
            }
        });
    }

    public void detach() {
        if (productsView != null) {
            productsView = null;
        }
    }
}

