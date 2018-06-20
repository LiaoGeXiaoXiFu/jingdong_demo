package com.example.jingdong_demo.fragment.classify.sousuo.model;

import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.constant.Api;
import com.example.jingdong_demo.fragment.classify.sousuo.bean.SousuoBean;
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

public class ShowModel {
    private IShowModel iShowModel;

    public void getGoods(String s, int page, final IShowModel iShowModel) {
        String url = Api.SEARCHPRODUCTS + "?keywords=" + s + "&page=" + page;
        OkHttpUtils okHttpUtils = OkHttpUtils.getInstance(MyApp.context);
        okHttpUtils.getData(url);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                SousuoBean sousuoBean = new Gson().fromJson(result, SousuoBean.class);
                iShowModel.issucceed(sousuoBean);
            }
        });
    }

    public interface IShowModel {
        void issucceed(SousuoBean sousuoBean);
    }
}