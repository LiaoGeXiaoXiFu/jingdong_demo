package com.example.jingdong_demo.fragment.homepager.presenter;

import com.example.jingdong_demo.constant.Api;
import com.example.jingdong_demo.fragment.homepager.bean.GetAdBean;
import com.example.jingdong_demo.fragment.homepager.model.GetAdModel;
import com.example.jingdong_demo.fragment.homepager.view.IGetAdView;

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

public class GetAdPresenter {
    private IGetAdView iGetAdView;
    private GetAdModel getAdModel;

    public GetAdPresenter(IGetAdView iGetAdView) {
        this.iGetAdView = iGetAdView;
        getAdModel = new GetAdModel();
    }

    public void getData() {
        getAdModel.getAd(Api.GETAD, new GetAdModel.IGetAdModel() {
            @Override
            public void getData(GetAdBean getAdBean) {
                if (iGetAdView != null) {
                    iGetAdView.showData(getAdBean);
                }
            }
        });
    }

    public void detach() {
        if (iGetAdView != null) {
            iGetAdView = null;
        }
    }
}
