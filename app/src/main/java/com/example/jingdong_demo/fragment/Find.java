package com.example.jingdong_demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jingdong_demo.MyApp;
import com.example.jingdong_demo.R;
import com.example.jingdong_demo.fragment.find.LineActivity;

/**
 * Created by 撩个小媳妇 on 2018/6/15.
 */

public class Find extends Fragment implements View.OnClickListener {
    private View view;
    /**
     * 开启美好生活
     */
    private Button mBtnImg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(MyApp.context, R.layout.find, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mBtnImg = (Button) inflate.findViewById(R.id.btn_img);
        mBtnImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_img:
                Intent intent = new Intent(getActivity(), LineActivity.class);
                startActivity(intent);
                break;
        }
    }
}
