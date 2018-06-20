package com.example.jingdong_demo.fragment.classify.model;

import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.constant.Api;
import com.example.jingdong_demo.fragment.classify.bean.ProductsBean;
import com.example.jingdong_demo.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

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

public class ProductsModel {
    private IProductsModel iProductsModel;
    private OkHttpUtils okHttpUtils;

    public void getData(String pscid, final IProductsModel iProductsModel) {
        okHttpUtils = OkHttpUtils.getInstance(MyApp.context);
        Map<String, String> params = new HashMap<>();
        params.put("pscid", pscid);
        okHttpUtils.post(Api.GETPRODUCTS,params);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                ProductsBean productsBean = new Gson().fromJson(result, ProductsBean.class);
                iProductsModel.getProducts(productsBean);
            }
        });
    }

    public interface IProductsModel {
        void getProducts(ProductsBean productsBean);
    }
}
