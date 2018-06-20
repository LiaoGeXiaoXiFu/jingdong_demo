package com.example.jingdong_demo.shopcar.presenter;


import com.example.jingdong_demo.shopcar.bean.AddCarBean;
import com.example.jingdong_demo.shopcar.model.AddCarModel;
import com.example.jingdong_demo.shopcar.view.IAddCarView;

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

public class AddCarPresenter {
    private AddCarModel addCarModel;
    private IAddCarView iAddCarView;

    public AddCarPresenter(IAddCarView iAddCarView) {
        this.iAddCarView = iAddCarView;
        addCarModel = new AddCarModel();
    }

    public void addCar(String uid, int pid) {
        addCarModel.addCar(uid, pid, new AddCarModel.IAddCarModel() {
            @Override
            public void issucceed(AddCarBean addCarBean) {
                if (iAddCarView != null) {
                    iAddCarView.issucceed(addCarBean);
                }
            }
        });
    }

    public void detach() {
        if (iAddCarView != null) {
            iAddCarView = null;
        }
    }
}
