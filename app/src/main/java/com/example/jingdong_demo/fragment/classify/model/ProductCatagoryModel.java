package com.example.jingdong_demo.fragment.classify.model;

import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.constant.Api;
import com.example.jingdong_demo.utils.OkHttpUtils;

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

public class ProductCatagoryModel {
    private IProductCatagoryModel iProductCatagoryModel;

    public void getProductCatagory(String cid, final IProductCatagoryModel iProductCatagoryModel) {
        //创建Map集合
        Map<String, String> params = new HashMap<>();
        params.put("cid", cid);
        OkHttpUtils okHttpUtils = OkHttpUtils.getInstance(MyApp.context);
        okHttpUtils.post(Api.GETPRODUCTCATAGORY, params);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                iProductCatagoryModel.showData(result);
            }
        });
    }

    public interface IProductCatagoryModel {
        void showData(String s);
    }
}
