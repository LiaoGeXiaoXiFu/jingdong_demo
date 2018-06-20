package com.example.jingdong_demo.shopcar.model;

import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.shopcar.bean.AddCarBean;
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

public class AddCarModel {
    private IAddCarModel iAddCarModel;

    public void addCar(String uid, int pid, final IAddCarModel iAddCarModel) {
        OkHttpUtils okHttpUtils = OkHttpUtils.getInstance(MyApp.context);
        String url = "https://www.zhaoapi.cn/product/addCart?uid=" + uid + "&pid=" + pid;
        okHttpUtils.getData(url);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                AddCarBean addCarBean = new Gson().fromJson(result, AddCarBean.class);
                iAddCarModel.issucceed(addCarBean);
            }
        });
    }

    public interface IAddCarModel {
        void issucceed(AddCarBean addCarBean);
    }
}
