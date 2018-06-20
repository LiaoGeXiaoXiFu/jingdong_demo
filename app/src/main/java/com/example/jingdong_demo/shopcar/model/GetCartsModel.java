package com.example.jingdong_demo.shopcar.model;

import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.constant.Api;
import com.example.jingdong_demo.shopcar.bean.Selectshop;
import com.example.jingdong_demo.utils.OkHttpUtils;
import com.google.gson.Gson;

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

public class GetCartsModel {
    private IGetCartsModel iGetCartsModel;

    public void getCar(String uid, final IGetCartsModel iGetCartsModel) {
        OkHttpUtils okHttpUtils = OkHttpUtils.getInstance(MyApp.context);
        String url = Api.GETCARTS + "?uid=" + uid;
        okHttpUtils.getData(url);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                Selectshop selectshop = new Gson().fromJson(result, Selectshop.class);
                List<Selectshop.DataBean> data = selectshop.getData();
                iGetCartsModel.issucceed(data);
            }
        });
    }

    public interface IGetCartsModel {
        void issucceed(List<Selectshop.DataBean> data);
    }
}
