package com.example.jingdong_demo.fragment.homepager.presenter;

import com.example.jingdong_demo.constant.Api;
import com.example.jingdong_demo.fragment.homepager.bean.CatagoryBean;
import com.example.jingdong_demo.fragment.homepager.model.CatagoryModel;
import com.example.jingdong_demo.fragment.homepager.view.ICatagoryView;

public class CatagoryPresenter {
    private ICatagoryView iCatagoryView;
    private CatagoryModel catagoryModel;

    public CatagoryPresenter(ICatagoryView iCatagoryView) {
        this.iCatagoryView = iCatagoryView;
        catagoryModel = new CatagoryModel();
    }

    public void getCatagory() {
        catagoryModel.getCatagory(Api.GETCATAGORY, new CatagoryModel.ICatagoryModel() {
            @Override
            public void getData(CatagoryBean catagoryBean) {
                if (iCatagoryView != null) {
                    iCatagoryView.showCatagory(catagoryBean);
                }
            }
        });
    }

    public void detach() {
        if (iCatagoryView != null) {
            iCatagoryView = null;
        }
    }
}