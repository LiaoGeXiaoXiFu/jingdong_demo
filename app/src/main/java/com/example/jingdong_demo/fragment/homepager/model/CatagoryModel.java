package com.example.jingdong_demo.fragment.homepager.model;

import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.fragment.homepager.bean.CatagoryBean;
import com.example.jingdong_demo.utils.OkHttpUtils;
import com.google.gson.Gson;

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

public class CatagoryModel {
    private ICatagoryModel iCatagoryModel;
    private OkHttpUtils okHttpUtils;

    public void getCatagory(String url, final ICatagoryModel iCatagoryModel) {
        okHttpUtils = OkHttpUtils.getInstance(MyApp.context);
        okHttpUtils.getData(url);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                CatagoryBean catagoryBean = new Gson().fromJson(result, CatagoryBean.class);
                iCatagoryModel.getData(catagoryBean);
            }
        });

    }

    public interface ICatagoryModel {
        void getData(CatagoryBean catagoryBean);
    }
}
