package com.example.jingdong_demo.shopcar.presenter;

import com.example.jingdong_demo.shopcar.bean.Selectshop;
import com.example.jingdong_demo.shopcar.model.GetCartsModel;
import com.example.jingdong_demo.shopcar.view.ShopcarView;

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

public class GetCartsPresenter {
    private GetCartsModel getCartsModel;
    private ShopcarView shopcarView;

    public GetCartsPresenter(ShopcarView shopcarView) {
        this.shopcarView = shopcarView;
        getCartsModel = new GetCartsModel();
    }

    public void getCar(String uid) {
        getCartsModel.getCar(uid, new GetCartsModel.IGetCartsModel() {
            @Override
            public void issucceed(List<Selectshop.DataBean> data) {
                if (shopcarView != null) {
                    shopcarView.getshopcar(data);
                }
            }
        });
    }

    //解绑
    public void detach() {
        if (shopcarView != null) {
            shopcarView = null;
        }
    }
}
