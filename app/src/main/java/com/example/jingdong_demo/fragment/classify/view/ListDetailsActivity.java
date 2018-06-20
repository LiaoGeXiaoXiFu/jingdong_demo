package com.example.jingdong_demo.fragment.classify.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jingdong_demo.MainActivity;
import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.R;
import com.example.jingdong_demo.fragment.classify.bean.ProductsBean;
import com.example.jingdong_demo.fragment.classify.sousuo.bean.SousuoBean;
import com.example.jingdong_demo.shopcar.bean.AddCarBean;
import com.example.jingdong_demo.shopcar.presenter.AddCarPresenter;
import com.example.jingdong_demo.shopcar.view.IAddCarView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.Arrays;

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

public class ListDetailsActivity extends AppCompatActivity implements View.OnClickListener, IAddCarView {

    private ImageView mIvShare;
    private Banner mBanner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private TextView mTvVipPrice;
    /**
     * 收藏
     */
    private Button mCollect;
    /**
     * 购物车
     */
    private Button mEnjoy;
    /**
     * 添加购物车
     */
    private Button mAppend;
    private ImageView mBackBtn;
    private ProductsBean.DataBean bean;
    private AddCarPresenter addCarPresenter;
    private int count = 0;
    private SousuoBean.DataBean sousuoBean;
    private int id;
    private int pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);
        //获取JavaBean
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 1);
        bean = (ProductsBean.DataBean) intent.getSerializableExtra("bean");
        sousuoBean = (SousuoBean.DataBean) intent.getSerializableExtra("sousuoBean");
        addCarPresenter = new AddCarPresenter(this);
        initView();
        initData();
    }

    private void initView() {
        mIvShare = (ImageView) findViewById(R.id.ivShare);
        mIvShare.setOnClickListener(this);
        mBanner = (Banner) findViewById(R.id.banner);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvPrice = (TextView) findViewById(R.id.tvPrice);
        mTvVipPrice = (TextView) findViewById(R.id.tvVipPrice);
        mCollect = (Button) findViewById(R.id.collect);
        mCollect.setOnClickListener(this);
        mEnjoy = (Button) findViewById(R.id.enjoy);
        mEnjoy.setOnClickListener(this);
        mAppend = (Button) findViewById(R.id.append);
        mAppend.setOnClickListener(this);
        mBackBtn = (ImageView) findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(this);
    }

    private void initData() {
        if (id == 1) {
            if (bean == null) {
                return;
            }
            pid = bean.getPid();
            //设置图片加载器
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
            String[] imgs = bean.getImages().split("\\|");
            //设置图片集合
            mBanner.setImages(Arrays.asList(imgs));
            //banner设置方法全部调用完毕时最后调用
            mBanner.start();

            mTvTitle.setText(bean.getTitle());
            //给原价加横线
            SpannableString spannableString = new SpannableString("原价:" + bean.getSalenum());
            StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
            spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            mTvPrice.setText(spannableString);
            mTvVipPrice.setText("优惠价：" + bean.getPrice());
        } else if (id == 2) {
            if (sousuoBean == null) {
                return;
            }
            pid = sousuoBean.getPid();
            //设置图片加载器
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
            String[] imgs = sousuoBean.getImages().split("\\|");
            //设置图片集合
            mBanner.setImages(Arrays.asList(imgs));
            //banner设置方法全部调用完毕时最后调用
            mBanner.start();

            mTvTitle.setText(sousuoBean.getTitle());
            //给原价加横线
            SpannableString spannableString = new SpannableString("原价:" + sousuoBean.getSalenum());
            StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
            spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            mTvPrice.setText(spannableString);
            mTvVipPrice.setText("优惠价：" + sousuoBean.getPrice());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ivShare:
                break;
            case R.id.collect:
                break;
            case R.id.enjoy:
                Intent intent = new Intent(ListDetailsActivity.this, MainActivity.class);
                intent.putExtra("id", 2);
                startActivity(intent);
                break;
            case R.id.append:
                count += 1;
                mEnjoy.setText("购物车(" + count + ")");
                SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                addCarPresenter.addCar(sp.getString("uid", "71"), pid);
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }

    @Override
    public void issucceed(AddCarBean addCarBean) {
        Toast.makeText(MyApp.context, addCarBean.getMsg(), Toast.LENGTH_SHORT).show();
    }
}
