package com.example.jingdong_demo.fragment.homepager.model;

import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.fragment.homepager.bean.GetAdBean;
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
public class GetAdModel {
    private OkHttpUtils okHttpUtils;
    private IGetAdModel iGetAdModel;

    public void getAd(String url, final IGetAdModel iGetAdModel) {
        okHttpUtils = OkHttpUtils.getInstance(MyApp.context);
        okHttpUtils.getData(url);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                GetAdBean getAdBean = new Gson().fromJson(result, GetAdBean.class);
                iGetAdModel.getData(getAdBean);
            }
        });
    }

    public interface IGetAdModel {
        void getData(GetAdBean getAdBean);
    }
}
