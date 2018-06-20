package com.example.jingdong_demo.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.R;
import com.example.jingdong_demo.fragment.classify.view.SouSuoActivity;
import com.example.jingdong_demo.fragment.homepager.ObservableScrollView;
import com.example.jingdong_demo.fragment.homepager.adapter.CatagoryAdapter;
import com.example.jingdong_demo.fragment.homepager.adapter.MiaoshaAdapter;
import com.example.jingdong_demo.fragment.homepager.adapter.TuijianAdapter;
import com.example.jingdong_demo.fragment.homepager.bean.CatagoryBean;
import com.example.jingdong_demo.fragment.homepager.bean.GetAdBean;
import com.example.jingdong_demo.fragment.homepager.presenter.CatagoryPresenter;
import com.example.jingdong_demo.fragment.homepager.presenter.GetAdPresenter;
import com.example.jingdong_demo.fragment.homepager.view.ICatagoryView;
import com.example.jingdong_demo.fragment.homepager.view.IGetAdView;
import com.example.jingdong_demo.fragment.homepager.view.WebViewActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
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
 * <p>
 * ━━━━━━当善良的人摘下面具.你连下跪的机会都没有━━━━━━
 * <p>
 * <p>
 * 主页
 */

public class HomePage extends Fragment implements View.OnClickListener, IGetAdView, ICatagoryView {
    private ImageView mSaoIv;
    private ImageView mMsgIv;
    private Banner mBanner;
    private GetAdPresenter getAdPresenter;
    private CatagoryPresenter catagoryPresenter;
    private RecyclerView mJiuRlv;
    private ArrayList<String> imges;
    /**
     * 京东秒杀
     */
    private TextView mTvMiaosha;
    /**
     * 10点场
     */
    private TextView mTvMiaoshaTime;
    /**
     * 1
     */
    private TextView mTvMiaoshaShi;
    /**
     * 1
     */
    private TextView mTvMiaoshaMinter;
    /**
     * 1
     */
    private TextView mTvMiaoshaSecond;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setTime();
            sendEmptyMessageDelayed(0, 1000);
        }
    };
    private RecyclerView mMiaosha;
    private RecyclerView mTuijian;
    /**
     * 周大福礼包大放送
     */
    private LinearLayout mSouSou;
    //讯飞语音
    private ImageView mImgXunfei;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.homepage, container, false);
        initData();
        initView(view);
        handler.sendEmptyMessage(0);
        return view;
    }


    private void initData() {
        getAdPresenter = new GetAdPresenter(this);
        getAdPresenter.getData();
    }

    private void initView(View view) {
        mSaoIv = (ImageView) view.findViewById(R.id.sao_iv);
        mSaoIv.setOnClickListener(this);
        mMsgIv = (ImageView) view.findViewById(R.id.msg_iv);
        mMsgIv.setOnClickListener(this);
        mBanner = (Banner) view.findViewById(R.id.banner);
        mJiuRlv = (RecyclerView) view.findViewById(R.id.jiu_rlv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MyApp.context, 2, RecyclerView.HORIZONTAL, false);
        mJiuRlv.setLayoutManager(gridLayoutManager);
        mTvMiaosha = (TextView) view.findViewById(R.id.tv_miaosha);
        mTvMiaoshaTime = (TextView) view.findViewById(R.id.tv_miaosha_time);
        mTvMiaoshaShi = (TextView) view.findViewById(R.id.tv_miaosha_shi);
        mTvMiaoshaMinter = (TextView) view.findViewById(R.id.tv_miaosha_minter);
        mTvMiaoshaSecond = (TextView) view.findViewById(R.id.tv_miaosha_second);
        mMiaosha = (RecyclerView) view.findViewById(R.id.miaosha);
        LinearLayoutManager miaosha = new LinearLayoutManager(MyApp.context, RecyclerView.HORIZONTAL, false);
        mMiaosha.setLayoutManager(miaosha);
        mTuijian = (RecyclerView) view.findViewById(R.id.tuijian);
        GridLayoutManager tuijian = new GridLayoutManager(MyApp.context, 2, RecyclerView.VERTICAL, false);
        mTuijian.setLayoutManager(tuijian);
        mSouSou = (LinearLayout) view.findViewById(R.id.sou_sou);
        mSouSou.setOnClickListener(this);
        mImgXunfei = (ImageView) view.findViewById(R.id.img_xunfei);
        mImgXunfei.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.sao_iv:
                break;
            case R.id.msg_iv:
                break;
            case R.id.sou_sou:
                Intent it = new Intent(MyApp.context, SouSuoActivity.class);
                startActivity(it);
                break;
            case R.id.img_xunfei://集成讯飞

                break;
        }
    }

    @Override
    public void showData(GetAdBean getAdBean) {
        List<GetAdBean.DataBean> data = getAdBean.getData();
        final List<GetAdBean.MiaoshaBean.ListBeanX> miaosha = getAdBean.getMiaosha().getList();
        List<GetAdBean.TuijianBean.ListBean> tuijian = getAdBean.getTuijian().getList();
        imges = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            imges.add(data.get(i).getIcon());
        }
        //设置图片加载器
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        mBanner.setImages(imges);
        mBanner.start();
        MiaoshaAdapter adapter = new MiaoshaAdapter(miaosha, MyApp.context);
        mMiaosha.setAdapter(adapter);
        adapter.setOnItemClickListener(new MiaoshaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转显示详情
                //获取地址
                String detailUrl = miaosha.get(position).getDetailUrl();
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("detailUrl", detailUrl);
                startActivity(intent);
            }
        });
        TuijianAdapter tuijianAdapter = new TuijianAdapter(tuijian, MyApp.context);
        mTuijian.setAdapter(tuijianAdapter);
        catagoryPresenter = new CatagoryPresenter(this);
        catagoryPresenter.getCatagory();
    }

    @Override
    public void showCatagory(CatagoryBean catagoryBean) {
        final List<CatagoryBean.DataBean> list = catagoryBean.getData();
        CatagoryAdapter adapter = new CatagoryAdapter(list, getContext());
        mJiuRlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new CatagoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), list.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //秒杀倒计时
    public void setTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        // Log.d("ccc", substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour % 2 == 0) {
            mTvMiaoshaTime.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            mTvMiaoshaTime.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            Date date = df.parse(totime);
            Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            mTvMiaoshaShi.setText("0" + hours + "");
            if (minute >= 10) {
                mTvMiaoshaMinter.setText(minute + "");
            } else {
                mTvMiaoshaMinter.setText("0" + minute + "");
            }
            if (second >= 10) {
                mTvMiaoshaSecond.setText(second + "");
            } else {
                mTvMiaoshaSecond.setText("0" + second + "");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
