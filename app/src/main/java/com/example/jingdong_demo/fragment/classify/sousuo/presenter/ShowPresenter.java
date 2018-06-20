package com.example.jingdong_demo.fragment.classify.sousuo.presenter;

import com.example.jingdong_demo.fragment.classify.sousuo.bean.SousuoBean;
import com.example.jingdong_demo.fragment.classify.sousuo.model.ShowModel;
import com.example.jingdong_demo.fragment.classify.sousuo.view.IShowActivityView;

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

public class ShowPresenter {
    private ShowModel showModel;
    private IShowActivityView iShowActivityView;

    public ShowPresenter(IShowActivityView iShowActivityView) {
        this.iShowActivityView = iShowActivityView;
        showModel = new ShowModel();
    }

    public void getGoods(final String s, int page) {
        showModel.getGoods(s, page, new ShowModel.IShowModel() {
            @Override
            public void issucceed(SousuoBean sousuoBean) {
                iShowActivityView.issucceed(sousuoBean);
            }
        });
    }
}